package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchUserModel {

	SearchUserController controller;

	public User[] searchUsers(User newUser) {
//		String protype = "id: -1, taken_loc_id: 0, user_id: 0, privacy: null,firstName: "
//				+ ", lastName: , email 'ewgfergwegerg ' drop users; : , website: , link: , birthday: null,gender: M"
//				+ ", workedFor: [], educationPlaces: [], quotes: [], FriendRequests: "
//				+ "null,isVerified: false, hometown: null,livesInLocation: null,username: , password: , \r\n";
		try {
			List<Object[]> res = new ArrayList<Object[]>();
			ResultSet rs = null;
			CallableStatement cstmt = AuthenticationModel.conn.prepareCall(
					"{call searchUsers(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			cstmt.setInt("USERID", newUser.getId());
			cstmt.setArray("nvarchar(50)", newUser.educationPlaces);
			// ...
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
			
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return null;
	}

	public void setController(SearchUserController searchUserController) {
		this.controller = searchUserController;

	}

}
