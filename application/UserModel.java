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

}
