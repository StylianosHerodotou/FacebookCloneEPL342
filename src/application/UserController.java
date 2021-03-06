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
		this.model.setController(this);
		this.view.generateTabPane();
	}
	public static  HashMap<String, Integer> getStringToIntLocations() {
		ResultSet result= UserModel.getLocations();
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
	if(this.model.banFromFriendRequest(this.getUser().getId(),id)) {
		displayPopUp("Banned from the friend request List "+id);
	}else {
	displayPopUp("Failed to ban from list "+id);
	}
	Tab tab= this.view.tabPane.getTabs().get(tabint);
	tab.setContent(this.view.getFriendRequestView(tabint));
}
public void unignoreFriend(int id, int tabIndex) {
	if(this.model.unbanFromFriendRequest(this.getUser().getId(),id)) {
		displayPopUp("Removed from Ignored friend request List "+id);
	}else {
	displayPopUp("Failed to Remove from ignored friend  list "+id);
	}
	Tab tab= this.view.tabPane.getTabs().get(tabIndex);
	tab.setContent(this.view.getFriendRequestView(tabIndex));
	
}
	public void denyFriend(int id, int tabint) {
		if(this.model.removeFromFriendRequest(this.getUser().getId(),id)) {
			displayPopUp("Remove from the friend request List "+id);
		}else {
		displayPopUp("Failed to remove ban from list "+id);
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
	public ArrayList<User> getIgnoredFriendRequests(int id)  {
		ArrayList<User> UsersThatSendFriendRequest= this.model.getIgnoredUserFriendRequests(id);
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
	public ArrayList<User> getFriendsWithAlbumBiggerThanX(int id,int x) {
		ArrayList<User> FriendsWithBiggerAlbumThanX= this.model.getFriendsWithBiggerAlbumThanX(id,x);
		return FriendsWithBiggerAlbumThanX;
	}
	public ArrayList<User> getNetworkFriendsWithAlbumBiggerThanX(int id,int x) {
		ArrayList<User> FriendsNetworkWithBiggerAlbumThanX= this.model.getFriendsNetworkWithBiggerAlbumThanX(id, x);
		return FriendsNetworkWithBiggerAlbumThanX;
	}

	public ArrayList<Event> getLeastAttendableEvents() {
		ArrayList<Event> LeastEvents= this.model.getLeastAttendableEvent();
		return LeastEvents;
	}

	public static User[] generateDummyUsers() {
		User[] users= new User[3];
		users[0]= new User("sherod01","");
		users[1]= new User("pchris04","");
		users[2]= new User("cchris","");
		return users;

	}

	public static Location generateDummyLocation() {
		return new Location(0,"lemesos");
	}

	public static Picture generateDummyPicture() {
		return new Picture(0,0,0,"link","source",new Privacy("public"), null,0);
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
		return new Video(0,"message","description",40,"source",0,new Privacy("OPEN"), null);
	}
	public static Event generateDummyEvent() {
		return new Event(0,"venue","event",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),
				"description",0,new Privacy("Open"), new Location(0, "larnaka"));

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
		
		
//		HashMap<String, Integer> locations = new HashMap<String, Integer>();
//		locations.put("larnaka", 0);
//		locations.put("lefkosia", 1);


		return locations;
		// TODO Auto-generated method stub
	}

	public void showFormView(int tabIndex, FBItem item,boolean canEdit, boolean mine,boolean isInsert) {
		Tab currentTab=this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getFormView(tabIndex, item,canEdit,mine,isInsert ));
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


	public void  showItemView(FBItem object, int tabIndex, boolean canEditItems,boolean mine,boolean isInsert) {
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getItemView(tabIndex, object,canEditItems,mine,isInsert));
	}

	public void showProfile(int tabIndex) {
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getMyProfileView(tabIndex));
	}

	public void updateItem(ArrayList<Object> newData, FBItem object, int tabIndex) {
		String className= object.getClass().getSimpleName();
		switch(className) {
		case "User":
			User updatedUser= new User(newData,(User)object);
			this.model.updateUser(updatedUser);
		break;
		case "Event":
			Event newEvent= new Event(newData,(Event)object);
			newEvent.setUser_id(this.user.id);
			this.model.updateEvent(newEvent);
		break;
		case "Link":
			Link newLink= new Link(newData,(Link)object);
			newLink.setUser_id(this.user.id);
			this.model.updateLink(newLink);
		break;
		case "Video":
			Video newVideo= new Video(newData,(Video)object);
			newVideo.setUser_id(this.user.id);
			this.model.updateVideo(newVideo);
			break;
		case "Picture":
			Picture newPicture= new Picture(newData,(Picture)object);
			newPicture.setUserId(this.user.id);
			this.model.updatePicture(newPicture);
			break;
		case "PictureAlbum":
			PictureAlbum newAlbum= new PictureAlbum(newData,(PictureAlbum)object);
			newAlbum.setUser_id(this.user.id);
			this.model.updateAlbum(newAlbum);
			break;
		}
		this.setUser(this.model.getUser(this.getUser().getId()));
		this.showProfile(tabIndex);
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

	public void showUserImagesView(int tabIndex,boolean mine) {
		ArrayList<Picture> pictures= this.model.getUserImages(this.user.getId());
		FBItem[] pictureItems= turnPicturesToFBArray(pictures);
		ScrollPane grid = this.view.getItemCrollView(pictureItems,tabIndex,true,mine,false);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}

	public void showUserVideosView(int tabIndex,boolean mine) {
		ArrayList<Video> Videos= this.model.getUserVideos(this.user.getId());


		FBItem[] videoItems= turnVideosToFBArray(Videos);
		ScrollPane grid = this.view.getItemCrollView(videoItems,tabIndex,true,mine,false);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}

	public void showUserAlbumsView(int tabIndex,boolean mine) {
		ArrayList<PictureAlbum> Albums= this.model.getUserAlbums(this.user.getId());
		FBItem[] albumItems= turnAlbumToFBArray(Albums);
		ScrollPane grid = this.view.getItemCrollView(albumItems,tabIndex,true,mine,false);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);	}

	public void showUserLinksView(int tabIndex,boolean mine) {
		ArrayList<Link> links= this.model.getUserLinks(this.user.getId());
		FBItem[] linkItems= turnLinksToFBArray(links);
		ScrollPane grid = this.view.getItemCrollView(linkItems,tabIndex,true,mine,false);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}
	public void showUserEventsView(int tabIndex,boolean mine) {
		ArrayList<Event> events= this.model.getUserEvents(this.user.getId());
		FBItem[] eventItems= turnEventsToFBArray(events);
		ScrollPane grid = this.view.getItemCrollView(eventItems,tabIndex,true,mine,false);
		Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(grid);
	}

	public void startChris(int tabIndex) {
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getFriendView(tabIndex));
		
	}
	
	public void startPanikos(int tabIndex) {
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getSearchUsersView(tabIndex));
		
	}
	public void showSearchUsersView(int tabIndex) {
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getSearchUsersView(tabIndex));
	}
	
	public void showSearchItemsView(int tabIndex) {
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getSearchForItemsView(tabIndex));
	}
	
	
	public void searchUsers (User newUser,int tabIndex) {
		User[] users = this.model.searchUsers(newUser);
//		User[] users =  this.generateDummyUsers();

		if (users==null) {
			String message = "Could not find Users";
			displayPopUp(message);
			return;
		}
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getItemCrollView(users, tabIndex, false,false,false));
		
				
	}

	public void insertItem(ArrayList<Object> newData, FBItem object, int tabIndex) {
		String className= object.getClass().getSimpleName();
		switch(className) {
		case "Event":
			Event newEvent= new Event(newData,(Event)object);
			newEvent.setUser_id(this.user.id);
			this.model.addEvent(newEvent);
		break;
		case "Link":
			Link newLink= new Link(newData,(Link)object);
			newLink.setUser_id(this.user.id);
			this.model.addLink(newLink);
		break;
		case "Video":
			Video newVideo= new Video(newData,(Video)object);
			newVideo.setUser_id(this.user.id);

			this.model.addVideo(newVideo);
			break;
		case "Picture":
			int albumID=-1;
			if(((Picture)object).getId()!=-1) {
				albumID=((Picture)object).getId();
			}
			Picture newPicture= new Picture(newData,(Picture)object);
			newPicture.setUserId(this.user.id);
			System.out.println("user id"+this.user.getId()+" album id"+ albumID);
			this.model.addPicture(newPicture,albumID);
			break;
		case "PictureAlbum":
			PictureAlbum newAlbum= new PictureAlbum(newData,(PictureAlbum)object);
			newAlbum.setUser_id(this.user.id);
			this.model.addAlbum(newAlbum);
			break;
		}
		this.showProfile(tabIndex);

	}

	public void showAddImagesView(int tabIndex, int albumId) {
		Picture newitem = new Picture();
		newitem.setId(albumId);
		this.showFormView(tabIndex, newitem,true, true, true);
	}

	public void showAddUserVideosView(int tabIndex) {
		Video newitem = new Video ();
		this.showFormView(tabIndex, newitem,true, true, true);
	}

	public void showAddUserAlbumsView(int tabIndex) {
		PictureAlbum newitem = new PictureAlbum ();
		this.showFormView(tabIndex, newitem,true, true, true);
	}

	public void showAddUserLinksView(int tabIndex) {
		Link newitem = new Link ();
		this.showFormView(tabIndex, newitem,true, true, true);
	}

	public void showAddUserEventsView(int tabIndex) {
		Event newitem = new Event ();
		this.showFormView(tabIndex, newitem,true, true, true);
	}

	public void searchForItems(int tabIndex,String albumName, String linkName, String eventName, String pictureSource,
			String videoMessage) {
		FBItem[] items = this.model.searchUsers_other(albumName, pictureSource, videoMessage, linkName, eventName,this.getUser().getId());
//		User[] users =  this.generateDummyUsers();

		if (items==null) {
			String message = "Could not find items";
			displayPopUp(message);
			return;
		}
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getItemCrollView(items, tabIndex, false,false,false));
		
		
		// TODO Auto-generated method stub
		
	}

	public void showFriendsWithAlbumBiggerThanXView(int tabIndex, int x) {
			Tab tab = this.view.tabPane.getTabs().get(tabIndex);
			tab.setContent(this.view.FriendWithBiggerthanXAlbums(tabIndex,x));
	}
	public void showFriendsWithNetworkAlbumBiggerThanXView(int tabIndex, int x) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.FriendNetworkWithBiggerthanXAlbums(tabIndex,x));
}
	public void showMostFamousFriendsView(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.mostPopularFriendsVuew(tabIndex));
}
	public void showComonFriendsFriendsView(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.FriendWithCommonFriends(tabIndex));
}
	public void showSearchBiggerThanXFriends(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.SearchForFriendsWithBiggerAlbumthanX(tabIndex));
	}
	public void showSearchBiggerThanXNetworkFriends(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.SearchForFriendsNetworkWithBiggerAlbumthanX(tabIndex));
	}
	public void showFriendsView(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.getFriendView(tabIndex));
}
	public void showFriendRequestView(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		tab.setContent(this.view.getFriendRequestView(tabIndex));
}
	public void showIgnoredFriendRequestView(int tabIndex) {
		Tab tab = this.view.tabPane.getTabs().get(tabIndex);
		try {
			tab.setContent(this.view.getIgnoredFriendRequestView(tabIndex));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public void showCommonInterestFriendView(int tabIndex) throws FileNotFoundException {
			Tab tab = this.view.tabPane.getTabs().get(tabIndex);
			tab.setContent(this.view.SameInterestsFriendsView(tabIndex));
}
		public void showLeastPopularEvent(int tabIndex) throws FileNotFoundException {
			Tab tab = this.view.tabPane.getTabs().get(tabIndex);
			tab.setContent(this.view.LeastAttendableEvents(tabIndex));
}
		public void showIgnoredRequestsView(int tabIndex) throws FileNotFoundException {
			Tab tab = this.view.tabPane.getTabs().get(tabIndex);
			tab.setContent(this.view.getIgnoredFriendRequestView(tabIndex));
}

		public void searchForUsersItem(int tabIndex, String albumName, String linkName, String eventName,
				String pictureSource, String videoMessage) {
		FBItem[] items = this.model.searchThisUsers_other(albumName, pictureSource, videoMessage, linkName, eventName, this.getUser().getId());
//		User[] users =  this.generateDummyUsers();

		if (items==null) {
			String message = "Could not find items";
			displayPopUp(message);
			return;
		}
		Tab currentTab = this.view.tabPane.getTabs().get(tabIndex);
		currentTab.setContent(this.view.getItemCrollView(items, tabIndex, false,false,false));
		
		
		// TODO Auto-generated method stub
		

			
		}
		
public int getAvgAge(int tabIndex) {
	return this.model.AverageAge();
}
		public void sentFriendRequest(int id, int tabIndex) {
			if(this.model.sentFriendRequest(this.getUser().getId(),id)) {
				displayPopUp("Added to friends "+id);
			}else {
			displayPopUp("Failed to Add to friends"+id);
			}
			Tab tab= this.view.tabPane.getTabs().get(tabIndex);
		}

		public void showKLogs(int tabIndex, int k) {
			FBItem[] logs= this.model.searchUsersLogs(this.getUser().getId(),true, true, true, true, true, true, k);
			ScrollPane grid = this.view.getItemCrollView(logs,tabIndex,true,true,false);
			Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
			currentTab.setContent(grid);		
		}

		public void showitemLogs(int tabIndex, String ans, int k) {
			FBItem[] logs=null;
			switch(ans) {
			case"Picture":
				logs= this.model.searchUsersLogs(this.getUser().getId(),false, false, true, false, false, false, k);
			break;
			case"Album":
				logs= this.model.searchUsersLogs(this.getUser().getId(),false, true, false, false, false, false, k);

				break;
			case"Video":
				logs= this.model.searchUsersLogs(this.getUser().getId(),false, false, false, true, false, false, k);

				break;
			case"Link":
				logs= this.model.searchUsersLogs(this.getUser().getId(),false, false, false, false, true, false, k);

				break;
			case"Event":
				logs= this.model.searchUsersLogs(this.getUser().getId(),false, false, false, false, false, true, k);

				break;
				
			}
			ScrollPane grid = this.view.getItemCrollView(logs,tabIndex,true,true,false);
			Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
			currentTab.setContent(grid);		
			
		}

		public void attendEvent(int id, int tabIndex) {
			if(this.model.userAttendEvent(this.getUser().getId(),id)) {
				displayPopUp("the user will atend event");
			}else {
			displayPopUp("Failed to attend event");
			}
			Tab tab= this.view.tabPane.getTabs().get(tabIndex);
			tab.setContent(this.view.getFriendRequestView(tabIndex));
		}

		public void searchForEvents(Event searchEvent, int tabIndex) {
			
			FBItem[] events= this.model.search_events(searchEvent);

			ScrollPane grid =this.view.getItemCrollView(events, tabIndex, false, false, false);
			Tab currentTab= this.view.tabPane.getTabs().get(tabIndex);
			currentTab.setContent(grid);		
			
		}

		public void deleteDatabase() {
			AuthenticationController.displayPopUp("Database is deleted");
			this.view.primaryStage.close();
		}

		public void importDatabase() {
			this.model.importData();
			
		}

		public void exportDatabase() {
			this.model.exportDataToCSV("" , "");
		}

	
	


	}
