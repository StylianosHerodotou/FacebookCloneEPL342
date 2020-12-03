package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

	public static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
		if (!resultSet.isBeforeFirst())
			return true;
		else
			return false;
	}

	public User[] searchUsers(User newUser) {
//		String protype = "id: -1, taken_loc_id: 0, user_id: 0, privacy: null,firstName: "
//				+ ", lastName: , email 'ewgfergwegerg ' drop users; : , website: , link: , birthday: null,gender: M"
//				+ ", workedFor: [], educationPlaces: [], quotes: [], FriendRequests: "
//				+ "null,isVerified: false, hometown: null,livesInLocation: null,username: , password: , \r\n";
		ArrayList<User> users = new ArrayList<User>();
		try {
			List<Object[]> res = new ArrayList<Object[]>();
			ResultSet resultSet = null;
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
					resultSet = cstmt.getResultSet();
					break;
				} else {
					rowsAffected = cstmt.getUpdateCount();
				}
				results = cstmt.getMoreResults();
			}
			
			if (isResultSetEmpty(resultSet))
				return null;
			else {

				HashMap<Integer, String> locations = this.controller.getIntToStringLocations();

				while (resultSet.next()) {
					int id = resultSet.getInt("UserID");
					firstName = resultSet.getString("First_Name");
					lastName = resultSet.getString("Last_Name");
					email = resultSet.getString("Email");
					website = resultSet.getString("Website");
					link = resultSet.getString("Link");
					birthday = resultSet.getDate("Birthday");
					gender = resultSet.getBoolean("Gender");
					isVerified = resultSet.getBoolean("Is_verified");
					hometownFK = resultSet.getInt("Hometown_LOC_ID");
					Location hometown = new Location(hometownFK, locations.get(hometownFK));
					livesInLocationFK = resultSet.getInt("Current_LOC_ID");
					Location livesHereNow = new Location(livesInLocationFK, locations.get(livesInLocationFK));
					username = resultSet.getString("Username");
					String password = resultSet.getString("pass");

					ArrayList<String> workedFor2 = this.getWorkOfUser(id);

					ArrayList<String> educationPlaces2 = this.getEducationOfUser(id);
					ArrayList<String> quotes2 = this.getQuotesOfUser(id);
					// newer with pass and username

					users.add(new User(id, firstName, lastName, email, website, link, birthday, gender, workedFor2,
							educationPlaces2, quotes2, isVerified, hometown, livesHereNow, username, password));
				}
			}
		}

		catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return (User[]) users.toArray();
	}

	public ArrayList<String> getQuotesOfUser(int UserID) {

		String SPsql = "EXEC getQuotesOfUser ?"; // for stored proc taking 2 parameters
		PreparedStatement ps;
		ResultSet resultSet = null;
		ArrayList<String> quotes = new ArrayList<String>();
		try {
			ps = AuthenticationModel.conn.prepareStatement(SPsql);

			ps.setEscapeProcessing(true);
			int index = 1;
			ps.setInt(index++, UserID);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String quote = resultSet.getString("Quote");
				quotes.add(quote);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quotes;

	}

	public ArrayList<String> getWorkOfUser(int UserID) {

		String SPsql = "EXEC getWorkOfUser ?"; // for stored proc taking 2 parameters
		PreparedStatement ps;
		ResultSet resultSet = null;
		ArrayList<String> workPlaces = new ArrayList<String>();
		try {
			ps = AuthenticationModel.conn.prepareStatement(SPsql);

			ps.setEscapeProcessing(true);
			int index = 1;
			ps.setInt(index++, UserID);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String workplace = resultSet.getString("Workplace");
				workPlaces.add(workplace);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workPlaces;

	}

	public ArrayList<String> getEducationOfUser(int UserID) {

		String SPsql = "EXEC getEducationOfUser ?"; // for stored proc taking 2 parameters
		PreparedStatement ps;
		ResultSet resultSet = null;
		ArrayList<String> educationPlaces = new ArrayList<String>();
		try {
			ps = AuthenticationModel.conn.prepareStatement(SPsql);

			ps.setEscapeProcessing(true);
			int index = 1;
			ps.setInt(index++, UserID);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String educationplace = resultSet.getString("Education_Place");
				educationPlaces.add(educationplace);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return educationPlaces;

	}

	public void setController(SearchUserController searchUserController) {
		this.controller = searchUserController;

	}

}
