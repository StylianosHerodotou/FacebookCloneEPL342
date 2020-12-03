package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class search_others_result {

	private ArrayList<Picture> turnresultSetToPictures(ResultSet resultSet) {
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("Picture_ID");
				int width = resultSet.getInt("Width");
				int height = resultSet.getInt("Height");
				String Link = resultSet.getString("Link");
				String src = resultSet.getString("SRC");
				Privacy privacy = new Privacy(resultSet.getString("Privacy_Name"));
				ArrayList<Comment> comments = null;
				Picture pic = new Picture(id, width, height, Link, src, privacy, comments);

				pictures.add(pic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pictures;
	}

	public FBItem[] searchUsers( String albStr,  String picStr,
			String vidStr, String linkStr, String eventStr) {
//		String protype = "id: -1, taken_loc_id: 0, user_id: 0, privacy: null,firstName: "
//				+ ", lastName: , email 'ewgfergwegerg ' drop users; : , website: , link: , birthday: null,gender: M"
//				+ ", workedFor: [], educationPlaces: [], quotes: [], FriendRequests: "
//				+ "null,isVerified: false, hometown: null,livesInLocation: null,username: , password: , \r\n";
		ArrayList<FBItem> items = new ArrayList<FBItem>();
	
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_Album(?,?)}",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			
			cstmt.setString(columnIndex++, albStr);

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

				while (resultSet.next()) {
					int index = 1;
					items.add(new PictureAlbum(resultSet.getInt(index++), resultSet.getString(index++),
							resultSet.getString(index++), resultSet.getString(index++), null, 12,
							resultSet.getInt(index++), new Privacy(resultSet.getString(index++)), null));
				}
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_pic(?,?)}",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			
			cstmt.setString(columnIndex++, picStr);

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

				ArrayList<Picture> a = turnresultSetToPictures(resultSet);
				for (int i = 0; i < a.size(); i++) {
					items.add(a.get(i));
				}

			}
			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_vid3(?,?)}",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			
			cstmt.setString(columnIndex++, vidStr);

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

				ArrayList<Video> a = turnresultSetToVideo(resultSet);
				for (int i = 0; i < a.size(); i++) {
					items.add(a.get(i));
				}

			}
			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_link3(?,?)}",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			
			cstmt.setString(columnIndex++, linkStr);

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

			if (SearchUserModel.turnresultSetToLinks(resultSet))
				return null;
			else {

				ArrayList<Picture> a = turnresultSetToPictures(resultSet);
				for (int i = 0; i < a.size(); i++) {
					items.add(a.get(i));
				}

			}
			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_link3(?,?)}",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			
			cstmt.setString(columnIndex++, linkStr);

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

			if (SearchUserModel.turnresultSetToLinks(resultSet))
				return null;
			else {

				ArrayList<Picture> a = turnresultSetToPictures(resultSet);
				for (int i = 0; i < a.size(); i++) {
					items.add(a.get(i));
				}

			}
			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_event3(?,?)}",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			
			cstmt.setString(columnIndex++, eventStr);

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

			if (SearchUserModel.turnresultSetToEvents(resultSet))
				return null;
			else {

				ArrayList<Picture> a = turnresultSetToPictures(resultSet);
				for (int i = 0; i < a.size(); i++) {
					items.add(a.get(i));
				}

			}
			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		
		
		return (FBItem[]) items.toArray();
	}

}
