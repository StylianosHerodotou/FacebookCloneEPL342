package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
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
	public HashMap<String, Integer> getStringToIntLocations() {
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
	
	public HashMap<Integer, String> getIntToStringLocations() {
		ResultSet result= this.model.getLocations();
		HashMap<Integer, String> locations = new HashMap<Integer, String>();
		
		try {
			while (result.next()) {
				String name = result.getString("Name");
				int id = result.getInt("LOC_ID");
				locations.put(id, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locations;
		// TODO Auto-generated method stub
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
			String message="User successfully updated ";
			AuthenticationController.displayPopUp(message);
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
//	public ObservableList<FRequest> getFriendRequests(int UsersId){
//		ObservableList<FRequest> Req = FXCollections.observableArrayList();
//
//	FRequest a=new FRequest(1,"Joe","Biden");
//	FRequest b=new FRequest(2,"Donald","Trump");
//	  Req.add(a);
//	 Req.add(b);
//	  return  Req;
//
//	}
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

//	public ObservableList<FRequest> getFriends(int id) {
//		ObservableList<FRequest> Req = FXCollections.observableArrayList();
//		//AHHAHAHAHHAHHAHAHHAHAHHAHHAHAHHAHAHHAHHAAHHAHAHHAHAHAH
//		FRequest a=new FRequest(1,"Joe","Biden");
//		FRequest b=new FRequest(2,"Donald","Trump");
//		  Req.add(a);
//		 Req.add(b);
//		  return  Req;
//	}

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
	public ArrayList<User> getFriendRequests(int id)  {
		ArrayList<User> UsersThatSendFriendRequest= this.model.getUserFriendRequests(id);
		return UsersThatSendFriendRequest;
	}
	public ArrayList<User> getFriends(int id)  {
		ArrayList<User> UsersThatAreFriends= this.model.getUserFriends(id);
		return UsersThatAreFriends;
	}
// occurences pu isunte me afta
	public ArrayList<Event> getSpecificOccurences(String firstName, String slocation, String startDate, String endDate) {

		return null;
	}

	public void goToEvent(Event event, int tabIndex) {
		// TODO Auto-generated method stub

	}
// bring me my most famous friends
	public ArrayList<User> getMostPopularFriends(int id) {
		ArrayList<User> PopularFriends= this.model.getMostPopularFriends(id);
		return PopularFriends;
	}
//friends with same friends or even more people
	public ArrayList<User> getFriendswithsamefriends(int id) {
		ArrayList<User> FriendsWithAtleastSameFriendsRequest= this.model.getFriendsWithAtleastSameFriends(id);
		return FriendsWithAtleastSameFriendsRequest;
	}
// get Friends with same interests
	public ArrayList<User> getSameInterestFriends(int id) {
		ArrayList<User> FriendsWithSameInterests= this.model.FriendsSameInterests(id);
		return FriendsWithSameInterests;
	}

	public ArrayList<Event> getLeastAttendableEvents() {
		ArrayList<Event> LeastEvents= this.model.getLeastAttendableEvent();
		return LeastEvents;
	}

	public static ArrayList<User> generateDummyUsers() {
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
		return new Picture(0,0,0,"link","source",new Privacy("public"), null);
	}

	public static PictureAlbum generateDummyPictureAlbum() {
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		pictures.add(generateDummyPicture());
		pictures.add(generateDummyPicture());
		pictures.add(generateDummyPicture());

		return new PictureAlbum(0,"almbum name","description","link",pictures,
				generateDummyLocation(),0,new Privacy("public"), null);
	}

	public static Link generateDummyLink() {
		return new Link(0,"link name","URL","message","description","caption", 0);
	}
	public static Video generateDummyVideo() {
		return new Video(0,"message","description",40,"source",0,null);
	}
	public static Event generateDummyEvent() {
		return new Event(0,"venue","event",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),
				"description",0,0,new Privacy("Open"));

	}


	public HashMap<String, Integer> getLocations() {
//		ResultSet result= this.model.getLocations();
//		HashMap<String, Integer> locations = new HashMap<String, Integer>();
//
//		try {
//			while (result.next()) {
//				String name = result.getString("Name");
//				int id = result.getInt("LOC_ID");
//				locations.put(name, id);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		HashMap<String, Integer> locations = new HashMap<String, Integer>();
		locations.put("larnaka", 0);
		locations.put("lefkosia", 1);


		return locations;
		// TODO Auto-generated method stub
	}

	public void showFormView(int tabIndex, FBItem item) {
		Tab currentTab=this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getFormView(tabIndex, item));
	}

	public FBItem[] turnPicturesToFBArray(ArrayList<Picture> objects) {
		FBItem[] items = new FBItem[objects.size()];
		for (int index=0; index<objects.size(); index++) {
			items[index]=objects.get(index);
		}
		return items;

	}
	public FBItem[] turnVideosToFBArray(ArrayList<Video> objects) {
		FBItem[] items = new FBItem[objects.size()];
		for (int index=0; index<objects.size(); index++) {
			items[index]=objects.get(index);
		}
		return items;

	}
	public FBItem[] turnEventsToFBArray(ArrayList<Event> objects) {
		FBItem[] items = new FBItem[objects.size()];
		for (int index=0; index<objects.size(); index++) {
			items[index]=objects.get(index);
		}
		return items;

	}
	public FBItem[] turnAlbumToFBArray(ArrayList<PictureAlbum> objects) {
		FBItem[] items = new FBItem[objects.size()];
		for (int index=0; index<objects.size(); index++) {
			items[index]=objects.get(index);
		}
		return items;

	}
	public FBItem[] turnLinksToFBArray(ArrayList<Link> objects) {
		FBItem[] items = new FBItem[objects.size()];
		for (int index=0; index<objects.size(); index++) {
			items[index]=objects.get(index);
		}
		return items;
	}


	public void  showItemView(FBItem object, int tabIndex) {
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getItemView(tabIndex, object));
	}

	public void showProfile(int tabIndex) {
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getMyProfileView(tabIndex));
	}

	public void updateItem(ArrayList<Object> newData, String className, FBItem object, int tabIndex) {
		AuthenticationController.displayPopUp(newData.toString());
//		switch( className) {
//		  case "User":
//		    User updateduser = new User(newData, (User) object);
//		    this.UpdateUser(updateduser);
////			System.out.print(updateduser.toString());
//		    break;
//		  case "Picture":
//			    Picture updatedPic = new Picture(newData, (Picture) object);
//			    this.updatePicture(updatedPic,tabIndex);
////				System.out.print(updateduser.toString());
//			    break;
////		  case 2:
//
//		}

	}

	private void updatePicture(Picture updatedPic, int tabIndex) {
		boolean wasSuccessful=this.model.updatePicture(updatedPic);
		if(wasSuccessful==true) {
			String message="User successfully updated ";
			AuthenticationController.displayPopUp(message);
		}
		else {
			String message=" Unable to update User";
			AuthenticationController.displayPopUp(message);
		}

	}

	public void showUserImagesView(int tabIndex) {
		ArrayList<Picture> pictures= this.model.getUserImages(this.user.getId());
		FBItem[] pictureItems= turnPicturesToFBArray(pictures);
		ScrollPane grid = this.view.getItemCrollView(pictureItems,tabIndex,true);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}

	public void showUserVideosView(int tabIndex) {
		ArrayList<Video> Videos= this.model.getUserVideos(this.user.getId());


		FBItem[] videoItems= turnVideosToFBArray(Videos);
		ScrollPane grid = this.view.getItemCrollView(videoItems,tabIndex,true);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}

	public void showUserAlbumsView(int tabIndex) {
		ArrayList<PictureAlbum> Albums= this.model.getUserAlbums(this.user.getId());
		FBItem[] albumItems= turnAlbumToFBArray(Albums);
		ScrollPane grid = this.view.getItemCrollView(albumItems,tabIndex,true);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);	}

	public void showUserLinksView(int tabIndex) {
		ArrayList<Link> links= this.model.getUserLinks(this.user.getId());
		FBItem[] linkItems= turnLinksToFBArray(links);
		ScrollPane grid = this.view.getItemCrollView(linkItems,tabIndex,true);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}
	public void showUserEventsView(int tabIndex) {
		ArrayList<Event> events= this.model.getUserEvents(this.user.getId());
		FBItem[] eventItems= turnEventsToFBArray(events);
		ScrollPane grid = this.view.getItemCrollView(eventItems,tabIndex,true);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}

	public void startChris(int tabIndex) {
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getFriendView(tabIndex));
		
	}
	


	}
