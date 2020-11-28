package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserController {

	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserView getView() {
		return view;
	}

	public void setView(UserView view) {
		this.view = view;
	}

	public UserModel getModel() {
		return model;
	}

	public void setModel(UserModel model) {
		this.model = model;
	}

	private UserView view;
	private UserModel model;

	public UserController(Stage primaryStage, User user)  {
		this.user = user;
		this.view = new UserView(primaryStage);
		this.model = new UserModel();
		this.view.setController(this);
		this.view.generateTabPane();
		this.model.setController(this);
	}

	public static void startController(Stage primaryStage, User user)  {
		UserController controller = new UserController(primaryStage, user);
		controller.view.startView();
	}

	public void UpdateUser(User updateduser) {
		boolean wasSuccessful=this.model.updateUser(updateduser);
		if(wasSuccessful==true) {
			this.setUser(updateduser);
		}
		else {
			String message=" Unable to update User";
			AuthenticationController.displayPopUp(message);
		}
	}
	//Dummy data 
	public ArrayList<String> getFriendRequests(int UsersId){
		ArrayList<String> friendsReq = new ArrayList<String>(); 
	  String d="21 Max Ban";
	  String r="bay blade";
	  friendsReq.add(d);
	  friendsReq.add(r);
	  return friendsReq;
	  
	}
	protected void logOut() {
		AuthenticationController.startController(this.view.primaryStage);
	}
	
	public static ArrayList<User> generateDummyUser() {
		ArrayList<User> users= new ArrayList<User>();
		users.add(new User("sherod01",""));
		users.add(new User("pchris04",""));
		users.add(new User("cchris",""));
		return users;
		
	}
	
	public void doSomething(String message) {
		AuthenticationController.displayPopUp(message);
	}
	
	public void changeView(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.getMyProfileView(tabIndex));
	}


}
