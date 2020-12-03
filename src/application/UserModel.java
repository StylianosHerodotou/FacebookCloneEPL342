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
			System.out.print("okay sinexise\n");
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

	public ResultSet getLocations() {
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
				String name = resultSet.getString("Album_Name");
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
				Event evnt = new Event(id, ven, name, st, et, desc, CreatId, location, privacy);

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
				int id = resultSet.getInt("User_ID");
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

	public ArrayList<Picture> getUserImages(int id) {
		String SPsql = "EXEC retrieveUserImages ? "; // for stored proc taking 2 parameters
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

	public ArrayList<Video> getUserVideos(int id) {
		String SPsql = "EXEC getVideosOfUser ? "; // for stored proc taking 2 parameters
		ResultSet resultSet = null;
		ArrayList<Video> vids = new ArrayList<Video>();
		try {
			PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
			ps.setInt(1, id);
			ps.setEscapeProcessing(true);
			resultSet = ps.executeQuery();
			vids = turnresultSetToVideos(resultSet);
			System.out.println(vids);

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
			Albums = turnresultSetToPictureAlbums(resultSet);
			System.out.println(Albums);

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
			links = turnresultSetToLinks(resultSet);
			System.out.println(links);

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
			events = turnresultSetToEvent(resultSet);
			System.out.println(events);

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
			users = turnresultSetToUser(resultSet);
			System.out.println(users);

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
			users = turnresultSetToUser(resultSet);
			System.out.println(users);

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
			users = turnresultSetToUser(resultSet);
			System.out.println(users);

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
			users = turnresultSetToUser(resultSet);
			System.out.println(users);

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
			users = turnresultSetToUser(resultSet);
			System.out.println(users);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return users;
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
			users = turnresultSetToUser(resultSet);
			System.out.println(users);

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
			int id = resultSet.getInt("User_ID");
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

	private User getUser(int userId) {
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
		ResultSet resultSet = null;
		CallableStatement cstmt = null;
		try {
			cstmt = AuthenticationModel.conn.prepareCall("{call insertVideo (?,?,?,?,?,?)}");

			int index = 1;
			cstmt.setString(index++, obj.getVenue());
			cstmt.setString(index++, obj.getName());
			cstmt.setTimestamp(index++, obj.getStartTime());
			cstmt.setTimestamp(index++, obj.getEndTime());
			cstmt.setString(index++, obj.description);
			cstmt.setInt(index++, obj.user_id);
			cstmt.setInt(index++, obj.loc.getId());
			cstmt.setString(index++, obj.getPrivacy().name);
			cstmt.setEscapeProcessing(true);
			cstmt.registerOutParameter(index, java.sql.Types.BIT);
			cstmt.execute();
			resultSet = cstmt.executeQuery();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.print(e);
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
		ResultSet resultSet = null;
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
			resultSet = cstmt.executeQuery();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.print(e);
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
		ResultSet resultSet = null;
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
			cstmt.execute();
			resultSet = cstmt.executeQuery();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.print(e);
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
		ResultSet resultSet = null;

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
			resultSet = cstmt.executeQuery();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.print(e);
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
		ResultSet resultSet = null;
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
			resultSet = cstmt.executeQuery();

			if (cstmt.getInt(index) == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.print(e);
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

	protected boolean updateEvent(Event obj) {
		CallableStatement cstmt = null;
		int id = obj.id;
		String ven =obj.venue;
		String name = obj.name;
		Timestamp startTim = obj.startTime;
		Timestamp endTim = obj.endTime;
		String descri = obj.description;
		int user_id = obj.getUser_id();
		Privacy privacy = obj.privacy;
		Location loca = obj.loc;

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

}
