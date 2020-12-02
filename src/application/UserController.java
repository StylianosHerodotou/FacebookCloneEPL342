package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	public static void displayPopUp(String message) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Pop Up Window");
		Label label1 = new Label(message);
		Button button1 = new Button("OK");
		button1.setOnAction(e -> popupwindow.close());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label1, button1);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 300, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();

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
public void ignoreFriend(int id, int tabint) throws FileNotFoundException {
	if(this.model.removeFromFriendRequest(this.getUser().getId(),id)) {
		displayPopUp("Removed from the friend request List "+id);
	}else {
	displayPopUp("Failed to remove from list "+id);
	}
	Tab tab= this.view.tabPane.getTabs().get(tabint);
	tab.setContent(this.view.getFriendRequestView(tabint));
}
	public void denyFriend(int id, int tabint) {
		if(this.model.banFromFriendRequest(this.getUser().getId(),id)) {
			displayPopUp("Banned from the friend request List "+id);
		}else {
		displayPopUp("Failed to ban from list "+id);
		}
		Tab tab= this.view.tabPane.getTabs().get(tabint);
		tab.setContent(this.view.getFriendRequestView(tabint));
	}
	
public void addFriend(int id,int tabint) {
	if(this.model.addToFriends(this.getUser().getId(),id)) {
		displayPopUp("Added to friends "+id);
	}else {
	displayPopUp("Failed to Add to friends"+id);
	}
	Tab tab= this.view.tabPane.getTabs().get(tabint);
	tab.setContent(this.view.getFriendRequestView(tabint));
	}
//Dummy data 
	public ObservableList<FRequest> getFriendRequests(int UsersId){
		ObservableList<FRequest> Req = FXCollections.observableArrayList();
		
	FRequest a=new FRequest(1,"Joe","Biden",0);
	FRequest b=new FRequest(2,"Donald","Trump",0);
	  Req.add(a);
	 Req.add(b);
	  return  Req;
	  
	}
	protected void logOut() {
		AuthenticationController.startController(this.view.primaryStage);
	}
		
	public void doSomething(String message) {
		AuthenticationController.displayPopUp(message);
	}
	
	public void changeView(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.getMyProfileView(tabIndex));
	}

	public ObservableList<FRequest> getFriends(int id) {
		ObservableList<FRequest> Req = FXCollections.observableArrayList();
		//AHHAHAHAHHAHHAHAHHAHAHHAHHAHAHHAHAHHAHHAAHHAHAHHAHAHAH
		FRequest a=new FRequest(1,"Joe","Biden",0);
		FRequest b=new FRequest(2,"Donald","Trump",0);
		  Req.add(a);
		 Req.add(b);
		  return  Req;
	}

	public void DeleteFriend(int id, int tabIndex) {
		if(this.model.removeFromFriends(this.getUser().getId(),id)) {
			displayPopUp("Removed friend with id : "+id);
		}else {
		displayPopUp("Failed to remove friend with id :"+id);
		}
		Tab tab= this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.getFriendRequestView(tabIndex));
	}

	public void searchOccurence(String firstName, String slocation, String startDate, String endDate, int tabIndex) {
		Tab tab= this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.getOccurenceResultView(firstName,slocation,startDate,endDate,tabIndex));
		
	}
// occurences pu isunte me afta
	public ObservableList<Event> getSpecificOccurences(String firstName, String slocation, String startDate, String endDate) {
		
		return null;
	}

	public void goToEvent(Event event, int tabIndex) {
		// TODO Auto-generated method stub
		
	}
// fermu tus pio famous friends 
	public ObservableList<User> getMostPopularFriends(int id) {
		// TODO Auto-generated method stub
		return null;
	}
//same friends or even more
	public ObservableList<User> getFriendswithsamefriends(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableList<User> getSameInterestFriends(int id) {
		
		return null;
	}

	public ObservableList<Event> getLeastAttendableEvents() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ArrayList<User> generateDummyUser() {
		ArrayList<User> users= new ArrayList<User>();
		users.add(new User("sherod01",""));
		users.add(new User("pchris04",""));
		users.add(new User("cchris",""));
		return users;
		
	}
	
	public static Location generateDummyLocation() {
		return new Location(0,"lemesos");
	}
	
	public static Picture generateDummyPicture() {
		return new Picture(0,0,0,"link","source","public", null);
	}
	
	public static PictureAlbum generatePictureAlbum() {
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		pictures.add(generateDummyPicture());
		return new PictureAlbum(0,"almbum name","description","link",pictures,
				generateDummyLocation(),0,"public", null);
	}

	public HashMap<String, Integer> getLocations() {
		ResultSet result= this.model.getLocations();
		HashMap<String, Integer> locations = new HashMap<String, Integer>();
		
		try {
			while (result.next()) {
				String name = result.getString("Name");
				int id = result.getInt("LOC_ID");
				locations.put(name, id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locations;
		// TODO Auto-generated method stub
	}
		
	}



