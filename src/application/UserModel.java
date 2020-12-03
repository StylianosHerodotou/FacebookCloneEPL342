package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
	UserController controller;
	
	public UserModel(UserController controller) {
		this.controller= controller;
	}
	public UserModel() {
	}
	public void setController(UserController controller) {
		this.controller=controller;
	}
	public boolean updateUser(User updatedUser) {
		CallableStatement cstmt=null;

			 int id=updatedUser.getId();//TODO DELETE THIS molis sasti to autoincrement.
			 String firstName= updatedUser.firstName;
			 String lastName=updatedUser.lastName;
			 String email=updatedUser.email;
			 String website=updatedUser.website;
			 String link=updatedUser.link;
			 Date birthday=updatedUser.birthday;
			 boolean gender=updatedUser.gender;
			 String  workedFor=AuthenticationModel.translateArrayListToString(updatedUser.workedFor);
			 String  educationPlaces=AuthenticationModel.translateArrayListToString(updatedUser.educationPlaces);
			 String  quotes=AuthenticationModel.translateArrayListToString(updatedUser.quotes);
			 boolean isVerified=updatedUser.isVerified;
			 int hometownFK=updatedUser.hometown.getId();
			 int livesInLocationFK= updatedUser.livesInLocation.getId();
			//newer with pass and username
			 String username=updatedUser.username;
			 String password=updatedUser.password;
			
				try {
					System.out.print("okay sinexise\n");
				cstmt = AuthenticationModel.conn.prepareCall("{call updateUser(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,? ,?)}");
				int columnIndex=1;		
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
				if(cstmt.getInt(columnIndex)==1) {
					return true;
				}
				else {
					return false;
				}
	}catch (SQLException e) {
		e.printStackTrace();
		return false;
			}
		finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//From friend request tu id 1 remove the guy with id2 
	public boolean removeFromFriendRequest(int id, int id2) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean banFromFriendRequest(int id, int id2) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean addToFriends(int id, int id2) {
		// TODO Auto-generated method stub
		return false;
	}
	// id en o user ke to id2 en tutos pu tha gini removed from friend list tu id
	public boolean removeFromFriends(int id, int id2) {
		// TODO Auto-generated method stub
		return false;
	}
	public ResultSet getLocations() {
		String SPsql = "EXEC retrieveLocations ";   // for stored proc taking 2 parameters
		ResultSet resultSet=null;
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
	
	private ArrayList<Picture> turnresultSetToPictures(ResultSet resultSet){
		 ArrayList<Picture> pictures= new  ArrayList<Picture>();
		try {
			while(resultSet.next()) {
				int id=resultSet.getInt("Picture_ID");
				int width=resultSet.getInt("Width");
				int height = resultSet.getInt("Height");
				String Link=resultSet.getString("Link");
				String src = resultSet.getString("SRC");
				Privacy privacy = new Privacy(resultSet.getString("Privacy_Name"));
				ArrayList<Comment> comments=null;
				Picture pic = new Picture(id,width,height,Link,src,
						privacy,comments);
						
				pictures.add(pic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pictures;
	}
	private ArrayList<User> turnresultSetToUser(ResultSet resultSet){
		 ArrayList<User> users= new  ArrayList<User>();
		try {
			while(resultSet.next()) {
				int id=resultSet.getInt("User_ID");
				String Username=resultSet.getString("Username");
				int password= resultSet.getInt("Pass");
				String First_Name=resultSet.getString("First_Name");
				String Last_Name = resultSet.getString("Last_Name");
				String Email=resultSet.getString("Email");
				String Website = resultSet.getString("WebSite");
				String Link=resultSet.getString("Link");
				Date Birthday = resultSet.getDate("Birthday");
				int gender=resultSet.getInt("Gender");
				int is_verified = resultSet.getInt("Is_verified");
				int    hometown_loc_id =resultSet.getInt("Hometown_LOC_ID");
				int    current_loc_id = resultSet.getInt("Current_LOC_ID");
				Location home=new Location(hometown_loc_id ,AuthenticationController.
				User user = new User(id,Username,password,First_Name,Last_Name,Email,Website,Link,Birthday,gender,is_verified,hometown_loc_id,current_loc_id);
						
						
				pictures.add(pic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pictures;
	}
	public ArrayList<Picture> getUserImages(int id) {
		String SPsql = "EXEC retrieveUserImages ? ";   // for stored proc taking 2 parameters
		ResultSet resultSet=null;
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		try {
		PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
		ps.setInt(1,id );
		ps.setEscapeProcessing(true);
		resultSet = ps.executeQuery();
		pictures=turnresultSetToPictures(resultSet);
		System.out.println(pictures);

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		e.printStackTrace();
	}
		return pictures;
	}
	public boolean updatePicture(Picture updatedPic) {
		// TODO Auto-generated method stub
		return false;
	}
	public ArrayList<User> getUserFriendRequests(int id) {
		String SPsql = "EXEC GET_FRIEND_REQUESTS ? ";   // for stored proc taking 2 parameters
		ResultSet resultSet=null;
		ArrayList<User> users = new ArrayList<User>();
		try {
		PreparedStatement ps = AuthenticationModel.conn.prepareStatement(SPsql);
		ps.setInt(1,id );
		ps.setEscapeProcessing(true);
		resultSet = ps.executeQuery();
		users=turnresultSetToPictures(resultSet);
		System.out.println(pictures);

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		e.printStackTrace();
	}
		return pictures;
	}


}
