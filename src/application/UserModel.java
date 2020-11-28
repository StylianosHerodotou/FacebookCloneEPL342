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
		// TODO Auto-generated method stub
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

}
