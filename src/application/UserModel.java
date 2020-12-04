package application;

import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.Animation;

public class UserModel {
	UserController controller;

	public UserModel(UserController controller) {
		this.controller = controller;
	}

	public UserModel() {
	}

	public void setController(UserController controller) {
		this.controller = controller;
	}

	public boolean updateUser(User updatedUser) {
		CallableStatement cstmt = null;

		int id = updatedUser.getId();// TODO DELETE THIS molis sasti to autoincrement.
		String firstName = updatedUser.firstName;
		String lastName = updatedUser.lastName;
		String email = updatedUser.email;
		String website = updatedUser.website;
		String link = updatedUser.link;
		Date birthday = updatedUser.birthday;
		boolean gender = updatedUser.gender;
		String workedFor = AuthenticationModel.translateArrayListToString(updatedUser.workedFor);
		String educationPlaces = AuthenticationModel.translateArrayListToString(updatedUser.educationPlaces);
		String quotes = AuthenticationModel.translateArrayListToString(updatedUser.quotes);
		boolean isVerified = updatedUser.isVerified;
		int hometownFK = updatedUser.hometown.getId();
		int livesInLocationFK = updatedUser.livesInLocation.getId();
		// newer with pass and username
		String username = updatedUser.username;
		String password = updatedUser.password;

		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call updateUser(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,? ,?)}");
			int columnIndex = 1;
			cstmt.setString(columnIndex++, firstName);
			cstmt.setString(columnIndex++, lastName);
			cstmt.setString(columnIndex++, username);
			cstmt.setString(columnIndex++, password);

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
			cstmt.setInt(columnIndex++, id);

			cstmt.registerOutParameter(columnIndex, java.sql.Types.BIT);
			cstmt.execute();
			System.out.print("okay sinexise\n");
			if (cstmt.getInt(columnIndex) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// From friend request tu id 1 remove the guy with id2
	public boolean removeFromFriendRequest(int id, int id2) {
		CallableStatement cstmt = null;
		try {
			int outs = 3;
			cstmt = AuthenticationModel.conn.prepareCall("{call DELETE_A_REQUEST(? ,?,?)}");
			cstmt.setInt(1, id);
			cstmt.setInt(2, id2);
			cstmt.registerOutParameter(outs, java.sql.Types.BIT);
			cstmt.execute();
			if (cstmt.getInt(outs) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean banFromFriendRequest(int id, int id2) {
		CallableStatement cstmt = null;
		try {
			int outs = 3;
			cstmt = AuthenticationModel.conn.prepareCall("{call IGNORE_A_REQUEST(? ,?,?)}");
			cstmt.setInt(1, id);
			cstmt.setInt(2, id2);
			cstmt.registerOutParameter(outs, java.sql.Types.BIT);
			cstmt.execute();
			if (cstmt.getInt(outs) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean unbanFromFriendRequest(int id, int id2) {
		CallableStatement cstmt = null;
		try {
			int outs = 3;
			cstmt = AuthenticationModel.conn.prepareCall("{call REMOVE_IGNORE(? ,?,?)}");
			cstmt.setInt(1, id);
			cstmt.setInt(2, id2);
			cstmt.registerOutParameter(outs, java.sql.Types.BIT);
			cstmt.execute();
			if (cstmt.getInt(outs) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean addToFriends(int id, int id2) {
		CallableStatement cstmt = null;
		try {
			int outs = 3;
			cstmt = AuthenticationModel.conn.prepareCall("{call ACCEPT_A_REQUEST(? ,?,?)}");
			cstmt.setInt(1, id);
			cstmt.setInt(2, id2);
			cstmt.registerOutParameter(outs, java.sql.Types.BIT);
			cstmt.execute();
			if (cstmt.getInt(outs) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// id en o user ke to id2 en tutos pu tha gini removed from friend list tu id
	public boolean removeFromFriends(int id, int id2) {
		CallableStatement cstmt = null;
		try {
			int outs = 3;
			cstmt = AuthenticationModel.conn.prepareCall("{call DELETE_FRIENDS(? ,?,?)}");
			cstmt.setInt(1, id);
			cstmt.setInt(2, id2);
			cstmt.registerOutParameter(outs, java.sql.Types.BIT);
			cstmt.execute();
			if (cstmt.getInt(outs) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

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

	private ArrayList<PictureAlbum> turnresultSetToPictureAlbums(ResultSet resultSet) {
		ArrayList<PictureAlbum> events = new ArrayList<PictureAlbum>();
		HashMap<Integer, String> locations = this.controller.getIntToStringLocations();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("Picture_Album_ID");
				String name = resultSet.getString("Name");
				String description = resultSet.getString("Description");
				String linkname = resultSet.getString("Link");
				Date End_time = resultSet.getDate("End_time");
				String desc = resultSet.getString("Description_");
				int UserId = resultSet.getInt("UserID");
				int LocId = resultSet.getInt("LOC_ID");
				Privacy privacy = new Privacy(resultSet.getString("Privacy_Name"));
				Location location = new Location(LocId, locations.get(LocId));
				ArrayList<Picture> pictures = this.getPicturesByAlbum(id);
				for (int i = 0; i < pictures.size(); i++) {
					ArrayList<Comment> comen = getItemComments(pictures.get(i));
					pictures.get(i).setComments(comen);
				}
				PictureAlbum albm = new PictureAlbum(id, name, description, linkname, pictures, location, UserId,
						privacy, null);
				ArrayList<Comment> com = getItemComments(albm);
				albm.setComments(com);
				events.add(albm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}

	private ArrayList<Picture> getPicturesByAlbum(int id) {
		String SPsql = "EXEC SHOW_ALBUMS_PICTURES ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			pictures = turnresultSetToPictures(resultSet);
			System.out.println(pictures);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return pictures;
	}

	private ArrayList<Event> turnresultSetToEvent(ResultSet resultSet) {
		ArrayList<Event> events = new ArrayList<Event>();
		HashMap<Integer, String> locations = this.controller.getIntToStringLocations();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("GATHERING_ID");
				String ven = resultSet.getString("Venue");
				String name = resultSet.getString("Name");
				Date start_time = resultSet.getDate("Start_time");
				Date End_time = resultSet.getDate("End_time");
				String desc = resultSet.getString("Description_");
				int CreatId = resultSet.getInt("Creator_ID");
				int LocId = resultSet.getInt("LOC_ID");
				Timestamp st = new Timestamp(start_time.getTime());
				Timestamp et = new Timestamp(End_time.getTime());
				Privacy privacy = new Privacy(resultSet.getString("Privacy_Name"));
				Location location = new Location(LocId, locations.get(LocId));
				Event evnt = new Event(id, ven, name, st, et, desc, CreatId, privacy, location);

				events.add(evnt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}

	private ArrayList<Picture> turnresultSetToPictures(ResultSet resultSet) {
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("Picture_ID");
				double width = resultSet.getDouble("Width");
				double height = resultSet.getDouble("Height");
				String Link = resultSet.getString("Link");
				String src = resultSet.getString("SRC");
				int userId = resultSet.getInt("UserId");
				Privacy privacy = new Privacy(resultSet.getString("Privacy_Name"));
				ArrayList<Comment> comments = null;
				Picture pic = new Picture(id, width, height, Link, src, privacy, comments, userId);
				comments = this.getItemComments(pic);
				pic.setComments(comments);
				pictures.add(pic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pictures;
	}

	private ArrayList<Comment> getItemComments(FBItem item) {
		ArrayList<Comment> commnets = null;

		String SPsql = null;
		int id = 0;
		switch (item.getClass().getSimpleName()) {
		case "Picture":
			SPsql = "EXEC getPictureComments ? "; // for stored proc taking 2 parameters
			id = ((Picture) item).id;
			break;
		case "Video":
			SPsql = "EXEC getVideoComments ? "; // for stored proc taking 2 parameters
			id = ((Video) item).id;
			break;
		case "PictureAlbum":
			SPsql = "EXEC getAlbumComments ? "; // for stored proc taking 2 parameters
			id = ((PictureAlbum) item).id;
			break;
		}

		ResultSet resultSet = null;
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			commnets = turnresultSetToComments(resultSet, item);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return commnets;
	}

	private ArrayList<Link> turnresultSetToLinks(ResultSet resultSet) {
		ArrayList<Link> links = new ArrayList<Link>();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("Link_ID");
				String name = resultSet.getString("Name");
				String URL = resultSet.getString("URL");
				String message = resultSet.getString("Message");
				String Description = resultSet.getString("Description");
				String caption = resultSet.getString("Caption");
				int UserId = resultSet.getInt("UserID");

				Link link = new Link(id, name, URL, message, Description, caption, UserId);
				links.add(link);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;
	}

	// TJELI TON PINAKA PU COMMENTS
	private ArrayList<Video> turnresultSetToVideos(ResultSet resultSet) {
		ArrayList<Video> videos = new ArrayList<Video>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("Video_ID");
				String message = resultSet.getString("Message");
				String description = resultSet.getString("Description");
				int length = resultSet.getInt("Length");
				String src = resultSet.getString("SRC");
				int userID = resultSet.getInt("UserID");
				String privacyName = resultSet.getString("Privacy_Name");
				Privacy pr = new Privacy(privacyName);
				ArrayList<Comment> com = new ArrayList<Comment>();

				Video video = new Video(id, message, description, length, src, userID, pr, null);
				com = this.getItemComments(video);
				video.setComments(com);
				videos.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videos;
	}

	private ArrayList<User> turnresultSetToUser(ResultSet resultSet) {
		ArrayList<User> users = new ArrayList<User>();
		HashMap<Integer, String> locations = this.controller.getIntToStringLocations();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("UserID");
				String Username = resultSet.getString("Username");
				String password = resultSet.getString("Pass");
				String First_Name = resultSet.getString("First_Name");
				String Last_Name = resultSet.getString("Last_Name");
				String Email = resultSet.getString("Email");
				String Website = resultSet.getString("WebSite");
				String Link = resultSet.getString("Link");
				Date Birthday = resultSet.getDate("Birthday");
				boolean gender = resultSet.getBoolean("Gender");
				boolean is_verified = resultSet.getBoolean("Is_verified");
				int hometown_loc_id = resultSet.getInt("Hometown_LOC_ID");
				int current_loc_id = resultSet.getInt("Current_LOC_ID");
				Location home = new Location(hometown_loc_id, locations.get(hometown_loc_id));
				Location current = new Location(current_loc_id, locations.get(current_loc_id));
				ArrayList<String> workedFor = this.getWorkOfUser(id);
				ArrayList<String> educationPlaces = this.getEducationOfUser(id);
				ArrayList<String> quotes = this.getQuotesOfUser(id);
				User use = new User(id, First_Name, Last_Name, Email, Website, Link, Birthday, gender, workedFor,
						educationPlaces, quotes, is_verified, home, current);
				users.add(use);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<Picture> getUserImages(int Userid) {
		String SPsql = "EXEC getPicturesOfUser ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, Userid);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				pictures = turnresultSetToPictures(resultSet);
				System.out.println(pictures);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return pictures;
	}

	public ArrayList<Video> getUserVideos(int id) {
		String SPsql = "EXEC getVideosOfUser ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<Video> vids = new ArrayList<Video>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				vids = turnresultSetToVideos(resultSet);
				System.out.println(vids);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return vids;
	}

	public ArrayList<PictureAlbum> getUserAlbums(int id) {
		String SPsql = "EXEC getUsersAlbum ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<PictureAlbum> Albums = new ArrayList<PictureAlbum>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				Albums = turnresultSetToPictureAlbums(resultSet);
				System.out.println(Albums);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return Albums;
	}

	public ArrayList<Link> getUserLinks(int id) {
		String SPsql = "EXEC getLinksOfUser ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<Link> links = new ArrayList<Link>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				links = turnresultSetToLinks(resultSet);
				System.out.println(links);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return links;
	}

	public ArrayList<Event> getUserEvents(int id) {
		String SPsql = "EXEC getEventsOfUser ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				events = turnresultSetToEvent(resultSet);
				System.out.println(events);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return events;
	}

	public ArrayList<User> getUserFriendRequests(int id) {
		String SPsql = "EXEC GET_FRIEND_REQUESTS ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<User> getUserIgnoredFriendRequests(int id) {
		String SPsql = "EXEC GET_FRIEND_REQUESTS_IGNORED ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<User> getUserFriends(int id) {
		String SPsql = "EXEC SHOW_FRIENDS ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
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

//Gets friends with same friends as you
	public ArrayList<User> getFriendsWithAtleastSameFriends(int id) {
		String SPsql = "EXEC FRIENDS_WITH_ATLEAST_SAME_FRIENDS ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<User> getFriendsWithBiggerAlbumThanX(int id, int x) {
		String SPsql = "EXEC FRIENDS_WITH_BIGGER_ALBUM_THAN_X ? ?"; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<User> getFriendsNetworkWithBiggerAlbumThanX(int id, int x) {
		String SPsql = "EXEC FRIENDS_NETOWRK_WITH_BIGGER_ALBUM_THAN_X ? ?"; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

//Gets Friends that are most popular
	public ArrayList<User> getMostPopularFriends(int id) {
		String SPsql = "EXEC MOST_POPULAR_FRIENDS ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<Event> getLeastAttendableEvent() {
		String SPsql = "EXEC SHOW_LEAST_POPULAR_GATHERINGS "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				events = turnresultSetToEvent(resultSet);
				System.out.println(events);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return events;
	}

// Gets Friends with same Interests
	public ArrayList<User> FriendsSameInterests(int id) {
		String SPsql = "EXEC FRIENDS_WITH_SAME_INTERESTS ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			if (isResultSetEmpty(resultSet))
				return null;
			else {
				users = turnresultSetToUser(resultSet);
				System.out.println(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<Comment> turnresultSetToComments(ResultSet resultSet, FBItem antikimeno) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		String type = antikimeno.getClass().getSimpleName();
		try {
			while (resultSet.next()) {

				int id = resultSet.getInt("Comment_ID");
				int userId = resultSet.getInt("UserID");
				String comment = resultSet.getString("Comment");
				FBItem item = antikimeno;
				User commenter = this.getUser(userId);

				Comment com = null;
				switch (type) {
				case "Video":
					com = new Comment(id, commenter, comment, (Video) item);
					break;
				case "Picture":
					com = new Comment(id, commenter, comment, (Picture) item);
					break;
				case "Album":
					com = new Comment(id, commenter, comment, (PictureAlbum) item);
					break;
				}
				comments.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;

	}

	private User turnResultIntoSingleUser(ResultSet resultSet) {
		HashMap<Integer, String> locations = this.controller.getIntToStringLocations();
		User user = null;
		try {
			resultSet.next();
			int id = resultSet.getInt("UserID");
			String Username = resultSet.getString("Username");
			int password = resultSet.getInt("Pass");
			String First_Name = resultSet.getString("First_Name");
			String Last_Name = resultSet.getString("Last_Name");
			String Email = resultSet.getString("Email");
			String Website = resultSet.getString("WebSite");
			String Link = resultSet.getString("Link");
			Date Birthday = resultSet.getDate("Birthday");
			boolean gender = resultSet.getBoolean("Gender");
			boolean is_verified = resultSet.getBoolean("Is_verified");
			int hometown_loc_id = resultSet.getInt("Hometown_LOC_ID");
			int current_loc_id = resultSet.getInt("Current_LOC_ID");
			Location home = new Location(hometown_loc_id, locations.get(hometown_loc_id));
			Location current = new Location(current_loc_id, locations.get(current_loc_id));
			ArrayList<String> workedFor = this.getWorkOfUser(id);
			ArrayList<String> educationPlaces = this.getEducationOfUser(id);
			ArrayList<String> quotes = this.getQuotesOfUser(id);
			user = new User(id, First_Name, Last_Name, Email, Website, Link, Birthday, gender, workedFor,
					educationPlaces, quotes, is_verified, home, current);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	User getUser(int userId) {
		String SPsql = "EXEC getUser ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		User user = null;
		PreparedStatement ps;
		try {
			ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, userId);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			user = turnResultIntoSingleUser(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	protected boolean addEvent(Event obj) {

		CallableStatement cstmt = null;
		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call insertVideo (?,?,?,?,?,?)}");

			int index = 1;
			cstmt.setString(index++, obj.getVenue());
			cstmt.setString(index++, obj.getName());
			cstmt.setTimestamp(index++, obj.getStartTime());
			cstmt.setTimestamp(index++, obj.getEndTime());
			cstmt.setString(index++, obj.description);
			cstmt.setInt(index++, obj.creatorID);
			cstmt.setInt(index++, obj.location.getId());
			cstmt.setString(index++, obj.getPrivacy().name);
			cstmt.setEscapeProcessing(true);
			cstmt.registerOutParameter(index, java.sql.Types.BIT);
			cstmt.execute();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected boolean addVideo(Video obj) {

		CallableStatement cstmt = null;
		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call insertVideo (?,?,?,?,?,?,?)}");

			int index = 1;
			cstmt.setString(index++, obj.getMessage());
			cstmt.setString(index++, obj.getDescription());
			cstmt.setInt(index++, obj.length);
			cstmt.setString(index++, obj.getSource());
			cstmt.setInt(index++, obj.user_id);
			cstmt.setString(index++, obj.privacy.name);
			cstmt.setEscapeProcessing(true);
			cstmt.registerOutParameter(index, java.sql.Types.BIT);
			cstmt.execute();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected boolean addPicture(Picture obj) {

		CallableStatement cstmt = null;
		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call insertPicture (?,?,?,?,?,?,?)}");

			int index = 1;
			cstmt.setFloat(index++, (float) obj.width);
			cstmt.setFloat(index++, (float) obj.getHeight());
			cstmt.setString(index++, obj.link);
			cstmt.setString(index++, obj.getSource());
			cstmt.setString(index++, obj.privacy.name);
			cstmt.setInt(index++, obj.UserId);

			cstmt.setEscapeProcessing(true);
			cstmt.registerOutParameter(index, java.sql.Types.BIT);

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected boolean addLink(Link obj) {

		CallableStatement cstmt = null;

		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call insertLink (?,?,?,?,?,?,?) }");

			int index = 1;
			cstmt.setString(index++, obj.getName());
			cstmt.setString(index++, obj.getURL());
			cstmt.setString(index++, obj.getMessage());
			cstmt.setString(index++, obj.getDescription());
			cstmt.setString(index++, obj.getCaption());
			cstmt.setInt(index++, obj.getUser_id());

			cstmt.setEscapeProcessing(true);
			cstmt.registerOutParameter(index, java.sql.Types.BIT);
			cstmt.execute();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected boolean addAlbum(PictureAlbum obj) {

		CallableStatement cstmt = null;
		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call insertAlbum (?,?,?,?,?,?,?)}");

			int index = 1;
			cstmt.setString(index++, obj.getName());
			cstmt.setString(index++, obj.getDescription());
			cstmt.setString(index++, obj.link);
			cstmt.setInt(index++, obj.LocationTaken.id);
			cstmt.setInt(index++, obj.user_id);
			cstmt.setString(index++, obj.privacy.name);

			cstmt.setEscapeProcessing(true);
			cstmt.registerOutParameter(index, java.sql.Types.BIT);
			cstmt.execute();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected boolean updateVideo(Video obj) {
		CallableStatement cstmt = null;
		int id = obj.id;
		String message = obj.message;
		String description = obj.description;
		double length = obj.getLength();
		String source = obj.getSource();
		int user_id = obj.getId();
		Privacy privacy = obj.privacy;

		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call updateVideo(?,?,?,?,?,?,?,?)}");
			int columnIndex = 1;
			cstmt.setInt(columnIndex++, id);
			cstmt.setString(columnIndex++, message);
			cstmt.setString(columnIndex++, description);
			cstmt.setInt(columnIndex++, (int) length);
			cstmt.setString(columnIndex++, source);
			cstmt.setInt(columnIndex++, user_id);
			cstmt.setString(columnIndex++, privacy.name);
			cstmt.registerOutParameter(columnIndex, java.sql.Types.BIT);
			cstmt.execute();
			System.out.print("okay sinexise\n");
			if (cstmt.getInt(columnIndex) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected boolean updatePicture(Picture obj) {
		CallableStatement cstmt = null;
		int id = obj.id;
		double height = obj.getHeight();
		double width = obj.getHeight();
		String link = obj.link;
		String source = obj.getSource();
		Privacy privacy = obj.privacy;
		int user_id = obj.getId();

		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call updatePicture(?,?,?,?,?,?,?,?)}");
			int columnIndex = 1;
			cstmt.setInt(columnIndex++, id);
			cstmt.setDouble(columnIndex++, height);
			cstmt.setDouble(columnIndex++, width);
			cstmt.setString(columnIndex++, link);
			cstmt.setString(columnIndex++, source);
			cstmt.setString(columnIndex++, privacy.name);
			cstmt.setInt(columnIndex++, user_id);
			cstmt.registerOutParameter(columnIndex, java.sql.Types.BIT);
			cstmt.execute();
			System.out.print("okay sinexise\n");
			if (cstmt.getInt(columnIndex) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected boolean updateLink(Link obj) {
		CallableStatement cstmt = null;
		int id = obj.id;

		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call updateLink(?,?,?,?,?,?,?,?)}");
			int columnIndex = 1;
			cstmt.setInt(columnIndex++, id);
			cstmt.setString(columnIndex++, obj.getName());
			cstmt.setString(columnIndex++, obj.getURL());
			cstmt.setString(columnIndex++, obj.getMessage());
			cstmt.setString(columnIndex++, obj.getDescription());
			cstmt.setString(columnIndex++, obj.caption);
			cstmt.setInt(columnIndex++, obj.user_id);
			cstmt.registerOutParameter(columnIndex, java.sql.Types.BIT);
			cstmt.execute();
			System.out.print("okay sinexise\n");
			if (cstmt.getInt(columnIndex) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected boolean updateAlbum(PictureAlbum obj) {
		CallableStatement cstmt = null;
		int id = obj.id;

		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call updateAlbum(?,?,?,?,?,?,?,?)}");
			int columnIndex = 1;
			cstmt.setInt(columnIndex++, id);
			cstmt.setString(columnIndex++, obj.getName());
			cstmt.setString(columnIndex++, obj.getDescription());
			cstmt.setString(columnIndex++, obj.getLink());
			cstmt.setInt(columnIndex++, obj.LocationTaken.id);
			cstmt.setInt(columnIndex++, obj.user_id);
			cstmt.setString(columnIndex++, obj.privacy.name);
			cstmt.registerOutParameter(columnIndex, java.sql.Types.BIT);
			cstmt.execute();
			System.out.print("okay sinexise\n");
			if (cstmt.getInt(columnIndex) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected boolean updateEvent(Event obj) {
		CallableStatement cstmt = null;
		int id = obj.id;
		String ven = obj.venue;
		String name = obj.name;
		Timestamp startTim = obj.startTime;
		Timestamp endTim = obj.endTime;
		String descri = obj.description;
		int user_id = obj.getUser_id();
		Privacy privacy = obj.privacy;
		Location loca = obj.location;

		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call updateEvent(?,?,?,?,?,?,?,?,?,?)}");
			int columnIndex = 1;
			cstmt.setInt(columnIndex++, id);
			cstmt.setString(columnIndex++, ven);
			cstmt.setString(columnIndex++, name);
			cstmt.setDate(columnIndex++, new Date(startTim.getTime()));
			cstmt.setDate(columnIndex++, new Date(endTim.getTime()));
			cstmt.setString(columnIndex++, descri);
			cstmt.setInt(columnIndex++, user_id);
			cstmt.setString(columnIndex++, privacy.name);
			cstmt.setInt(columnIndex++, loca.getId());
			cstmt.registerOutParameter(columnIndex, java.sql.Types.BIT);
			cstmt.execute();
			System.out.print("okay sinexise\n");
			if (cstmt.getInt(columnIndex) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

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
					"{call SEARCH_USERS_TEMP2(?,?,?,?, ?,?,?,?, ?,?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			cstmt.setString(columnIndex++, firstName);
			cstmt.setString(columnIndex++, lastName);
			cstmt.setString(columnIndex++, username);
			cstmt.setString(columnIndex++, email);
			cstmt.setString(columnIndex++, website);
			cstmt.setString(columnIndex++, link);
			cstmt.setDate(columnIndex++, birthday);
			cstmt.setString(columnIndex++, workedFor);
			cstmt.setString(columnIndex++, educationPlaces);
			cstmt.setString(columnIndex++, quotes);
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
		System.out.println(users);
		if (users.size() <= 0)
			return null;
		User[] arr = new User[users.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (User) users.get(i);
		}
		System.out.println(arr);
		return arr;
	}

	public ArrayList<User> getIgnoredUserFriendRequests(int id) {
		String SPsql = "GET_FRIEND_REQUESTS_IGNORED ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			users = turnresultSetToUser(resultSet);
			System.out.println(users);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
	}

	public FBItem[] searchUsers_other(String albStr, String picStr, String vidStr, String linkStr, String eventStr) {

		ArrayList<FBItem> items = new ArrayList<FBItem>();

		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_Album_m(?)}",
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

			ArrayList<PictureAlbum> res = turnresultSetToPictureAlbums(resultSet);

			for (int i = 0; i < res.size(); i++) {
				items.add(res.get(i));
			}

			cstmt.close();

		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_pic_m(?)}",
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

			ArrayList<Picture> res = turnresultSetToPictures(resultSet);
			for (int i = 0; i < res.size(); i++) {
				items.add(res.get(i));
			}
			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_vid_m(?)}",
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

			ArrayList<Video> res = turnresultSetToVideos(resultSet);
			for (int i = 0; i < res.size(); i++) {
				items.add(res.get(i));
			}
			cstmt.close();

			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_event_m(?)}",
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

			ArrayList<Event> a = turnresultSetToEvent(resultSet);
			for (int i = 0; i < a.size(); i++) {
				items.add(a.get(i));
			}

			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call search_others_link_m(?)}",
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

			ArrayList<Link> a = turnresultSetToLinks(resultSet);
			for (int i = 0; i < a.size(); i++) {
				items.add(a.get(i));
			}

			cstmt.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		if (items.size() <= 0)
			return null;
		FBItem[] arr = new FBItem[items.size()];
		for (int i = 0; i < arr.length; i++) {

			arr[i] = items.get(i);
		}
		return arr;

	}

	public FBItem[] searchUsersLogs(int id, boolean all, boolean alb, boolean pic, boolean vid, boolean link,
			boolean event, int k) {
		ArrayList<log> logs = new ArrayList<log>();
		try {

			ResultSet resultSet = null;

			CallableStatement cstmt = AuthenticationModel.conn.prepareCall("{call updates(?,?,?,?, ?,?,?,?)}",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

				while (resultSet.next()) {

					int index = 1;
					logs.add(new log(resultSet.getInt(index++), resultSet.getString(index++),
							resultSet.getString(index++), resultSet.getString(index++), resultSet.getTimestamp(index++),
							resultSet.getInt(5)));
				}
			}
		}

		catch (Exception e) {
			System.out.println(e);
			return null;
		}
		if (logs.size() == 0) {
			System.out.println("found nothing");
			return null;
		}
		System.out.println(logs);
		FBItem[] arr = new FBItem[logs.size()];
		for (int i = 0; i < arr.length; i++) {

			arr[i] = logs.get(i);
		}
		return arr;
	}

	public FBItem[] search_events(int id, String venue, String name, Date start, Date end, String descr, int creatorid,
			int locid) {

		ArrayList<Event> events = new ArrayList<Event>();
		try {
			ResultSet resultSet = null;
			CallableStatement cstmt = AuthenticationModel.conn.prepareCall(
					"{call SEARCH_USERS_TEMP2(?,?,?,?, ?,?,?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int columnIndex = 1;
			cstmt.setInt(columnIndex++, id);
			cstmt.setString(columnIndex++, venue);
			cstmt.setString(columnIndex++, name);
			cstmt.setDate(columnIndex++, start);
			cstmt.setDate(columnIndex++, end);
			cstmt.setString(columnIndex++, descr);
			cstmt.setInt(columnIndex++, creatorid);
			cstmt.setInt(columnIndex++, locid);

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
			
			ArrayList<Event> a = turnresultSetToEvent(resultSet);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		System.out.println(events);
		FBItem[] arr = new FBItem[events.size()];
		for (int i = 0; i < arr.length; i++) {

			arr[i] = events.get(i);
		}
		return arr;
	}

}
