package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class seeLogsModel {

	
	public log[] searchUsersLogs(int id,boolean all,boolean alb,boolean pic, boolean vid,boolean link,boolean event) {
//		String protype = "id: -1, taken_loc_id: 0, user_id: 0, privacy: null,firstName: "
//				+ ", lastName: , email 'ewgfergwegerg ' drop users; : , website: , link: , birthday: null,gender: M"
//				+ ", workedFor: [], educationPlaces: [], quotes: [], FriendRequests: "
//				+ "null,isVerified: false, hometown: null,livesInLocation: null,username: , password: , \r\n";
		ArrayList<log> logs = new ArrayList<log>();
		try {
			
			ResultSet resultSet = null;
			
			CallableStatement cstmt = AuthenticationModel.conn.prepareCall(
					"{call updates(?,?,?,?, ?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			cstmt.setBoolean(columnIndex++, all);
			cstmt.setInt(columnIndex++, id);
			cstmt.setBoolean(columnIndex++, alb);
			cstmt.setBoolean(columnIndex++, pic);
			cstmt.setBoolean(columnIndex++, vid);
			cstmt.setBoolean(columnIndex++, link);
			cstmt.setBoolean(columnIndex++, event);

	
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
			
			if (SearchUserModel.isResultSetEmpty(resultSet))
				return null;
			else {

				HashMap<Integer, String> locations = SearchUserController.getIntToStringLocations();

				while (resultSet.next()) {
					
					int index = 1;
					logs.add(new log(
							resultSet.getInt(index++), resultSet.getString(index++), resultSet.getString(index++),
							resultSet.getString(index++), resultSet.getTimestamp(index++), resultSet.getInt(5)));
				}
			}
		}

		catch (Exception e) {
			System.out.println(e);
			return null;
		}
		System.out.println(logs);
		return (log[]) logs.toArray();
	}
	
}
