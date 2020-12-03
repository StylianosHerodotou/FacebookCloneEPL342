package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchUserModel {

	public static ResultSet getLocations() {
		String SPsql = "EXEC retrieveLocations "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return resultSet;
	}

	SearchUserController controller;

	public User[] searchUsers(User newUser) {
//		String protype = "id: -1, taken_loc_id: 0, user_id: 0, privacy: null,firstName: "
//				+ ", lastName: , email 'ewgfergwegerg ' drop users; : , website: , link: , birthday: null,gender: M"
//				+ ", workedFor: [], educationPlaces: [], quotes: [], FriendRequests: "
//				+ "null,isVerified: false, hometown: null,livesInLocation: null,username: , password: , \r\n";
		try {
			List<Object[]> res = new ArrayList<Object[]>();
			ResultSet rs = null;
			String firstName = newUser.firstName;
			String lastName = newUser.lastName;
			String email = newUser.email;
			String website = newUser.website;
			String link = newUser.link;
			Date birthday = newUser.birthday;
			boolean gender = newUser.gender;
			String workedFor = AuthenticationModel.translateArrayListToString(newUser.workedFor);
			String educationPlaces = AuthenticationModel.translateArrayListToString(newUser.educationPlaces);
			String quotes = AuthenticationModel.translateArrayListToString(newUser.quotes);
			boolean isVerified = newUser.isVerified;
			int hometownFK = newUser.hometown.getId();
			int livesInLocationFK = newUser.livesInLocation.getId();
			// newer with pass and username
			String username = newUser.username;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall(
					"{call SEARCH_USERS_TEMP2(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			cstmt.setString(columnIndex++, firstName);
			cstmt.setString(columnIndex++, lastName);
			cstmt.setString(columnIndex++, username);
			cstmt.setString(columnIndex++, email);
			cstmt.setString(columnIndex++, website);
			cstmt.setString(columnIndex++, link);
			cstmt.setDate(columnIndex++, birthday);
			cstmt.setBoolean(columnIndex++, gender);
			cstmt.setString(columnIndex++, workedFor);
			cstmt.setString(columnIndex++, educationPlaces);
			cstmt.setString(columnIndex++, quotes);
			cstmt.setBoolean(columnIndex++, isVerified);
			cstmt.setInt(columnIndex++, hometownFK);
			cstmt.setInt(columnIndex++, livesInLocationFK);

			boolean results = cstmt.execute();
			int rowsAffected = 0;

			// Protects against lack of SET NOCOUNT in stored prodedure
			while (results || rowsAffected != -1) {
				if (results) {
					rs = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}
			List<Object[]> objs = DbFunctions.resultSetToList(rs);
			User[] ret = new User[objs.size()];

			for (int i = 0; i < ret.length; i++) {
				ret[i] = DbFunctions.covertObjectArray(objs.get(i));
			}
			System.out.println(ret);
			return ret;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public void setController(SearchUserController searchUserController) {
		this.controller = searchUserController;

	}

}
