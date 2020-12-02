package application;

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
	public boolean updateUser(User updateduser) {
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return false;
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

}
