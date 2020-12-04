package application;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserView {

	Stage primaryStage;
	TabPane tabPane;
	UserController controller;

	public void setController(UserController controller) {
		this.controller = controller;
	}

	public UserView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void startView() {
		VBox vBox = new VBox(this.tabPane);
		Scene scene = new Scene(vBox, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BookFace");
		primaryStage.show();
	}

	public void generateTabPane() {
		this.tabPane = new TabPane();
		int index = 0;

		Tab profileTab = new Tab("Profile", this.getMyProfileView(index++));
		Tab chrisTab = new Tab("My friends", this.ChrisView(index++));
Tab friendRequesTab= new Tab("Friend Requests", this.getFriendRequestView(index++));
		Tab panikosTab = new Tab("panikos", this.PanikosView(index++));
		Tab searchItemsTab = new Tab("panikos", this.getSearchForItemsView(index++));


		tabPane.getTabs().add(profileTab);
		tabPane.getTabs().add(chrisTab);
		tabPane.getTabs().add(friendRequesTab);
		tabPane.getTabs().add(panikosTab);
		tabPane.getTabs().add(searchItemsTab);


	}

	protected ScrollPane ChrisView(int tabIndex) {
		GridPane grid = new GridPane();
		Button goButton = new Button("Start Chris");
		goButton.setOnAction(event -> this.controller.startChris(tabIndex));
		grid.add(goButton, 0, 0);
		return new ScrollPane(grid);
	}

	protected ScrollPane PanikosView(int tabIndex) {
		GridPane grid = new GridPane();
		Button goButton = new Button("Start Panikos");
		goButton.setOnAction(event -> this.controller.startPanikos(tabIndex));
		grid.add(goButton, 0, 0);
		return new ScrollPane(grid);
	}

	protected void getResultsView(GridPane grid, ArrayList<FBItem> items) {
		// set dimentions of the grid.
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		for (int objectIndex = 0; objectIndex < items.size(); objectIndex++) {
			FBItem object = items.get(objectIndex);
//			System.out.println(object.toString());
			grid.add(new Label(object.getClass().getSimpleName()), HelperFunctions.initXlevel,
					objectIndex + HelperFunctions.initYlevel);
			grid.add(new Label(object.getFBName()), HelperFunctions.initXlevel + 1,
					objectIndex + HelperFunctions.initYlevel);
//			Button somethingButton = new Button("something");
//			somethingButton.setOnAction(event-> this.controller.doSomething("this is a something message"));
//			grid.add(somethingButton, HelperFunctions.initXlevel+2, objectIndex + HelperFunctions.initYlevel);
		}

	}

	protected ScrollPane getMyProfileView(int tabIndex) {
		HelperFunctions.initYlevel = 0;
		GridPane grid = (GridPane) this.getItemView(tabIndex, this.controller.getUser(), true, true,false).getContent();
		Button showPicturesButton = new Button("show pictures");
		showPicturesButton.setOnAction(event -> this.controller.showUserImagesView(tabIndex, true));
		Button showVideosButton = new Button("show videos");
		showVideosButton.setOnAction(event -> this.controller.showUserVideosView(tabIndex, true));
		Button showAlbumsButton = new Button("show Albums");
		showAlbumsButton.setOnAction(event -> this.controller.showUserAlbumsView(tabIndex, true));
		Button showLinksButton = new Button("show Links");
		showLinksButton.setOnAction(event -> this.controller.showUserLinksView(tabIndex, true));
		Button showEventsButton = new Button("show Events");
		showEventsButton.setOnAction(event -> this.controller.showUserEventsView(tabIndex, true));



		Button addPicturesButton = new Button("add pictures");
		addPicturesButton.setOnAction(event -> this.controller.showAddImagesView(tabIndex, true));
		Button addVideosButton = new Button("add videos");
		addVideosButton.setOnAction(event -> this.controller.showAddUserVideosView(tabIndex, true));
		Button addAlbumsButton = new Button("add Albums");
		addAlbumsButton.setOnAction(event -> this.controller.showAddUserAlbumsView(tabIndex, true));
		Button addLinksButton = new Button("add Links");
		addLinksButton.setOnAction(event -> this.controller.showAddUserLinksView(tabIndex, true));
		Button addEventsButton = new Button("add Events");
		addEventsButton.setOnAction(event -> this.controller.showAddUserEventsView(tabIndex, true));

		HelperFunctions.initYlevel = HelperFunctions.initYlevel - 16;
		HelperFunctions.initXlevel = 12;

		grid.add(showPicturesButton, HelperFunctions.initXlevel, HelperFunctions.initYlevel);
		grid.add(showVideosButton, HelperFunctions.initXlevel + 1, HelperFunctions.initYlevel++);
		grid.add(showAlbumsButton, HelperFunctions.initXlevel, HelperFunctions.initYlevel);
		grid.add(showLinksButton, HelperFunctions.initXlevel + 1, HelperFunctions.initYlevel++);
		grid.add(showEventsButton, HelperFunctions.initXlevel, HelperFunctions.initYlevel);
		grid.add(addPicturesButton, HelperFunctions.initXlevel+1, HelperFunctions.initYlevel++);
		grid.add(addVideosButton, HelperFunctions.initXlevel, HelperFunctions.initYlevel);
		grid.add(addAlbumsButton, HelperFunctions.initXlevel+1, HelperFunctions.initYlevel++);
		grid.add(addLinksButton, HelperFunctions.initXlevel, HelperFunctions.initYlevel);
		grid.add(addEventsButton, HelperFunctions.initXlevel+1, HelperFunctions.initYlevel++);


		ScrollPane scene = new ScrollPane(grid);
		return scene;

	}

	// FRIEND REQUEST VIEW
	protected ScrollPane getIgnoredFriendRequestView(int tabIndex) throws FileNotFoundException {
		GridPane grid = new GridPane();
		ArrayList<User> FriendRequests = this.controller.getIgnoredFriendRequests(this.controller.getUser().getId());
		if(FriendRequests == null) {
			Label empty = new Label("No Friend Requests :");
			grid.add(empty, 0, 1);
			return new ScrollPane(grid);
		}
		ArrayList<Button> unignb = new ArrayList<Button>();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		for (int i = 0; i < FriendRequests.size(); i++) {
			int b = i;
			Button unign = new Button("UnIgnore");
			unign.setOnAction(event -> {
				this.controller.unignoreFriend(FriendRequests.get(b).getId(), tabIndex);
				this.controller.showIgnoredFriendRequestView(tabIndex);
			});
			unignb.add(unign);

		}
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Friend Requests");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0, 0);
		Label name = new Label("First Name :");
		grid.add(name, 0, 1);
		Label lastname = new Label("Last Name :");
		grid.add(lastname, 1, 1);
		Label Email = new Label("Email :");
		grid.add(Email, 2, 1);
		Label Uningnore = new Label("Uningnore :");
		grid.add(Uningnore, 3, 1);

		int row = 2;
		for (int i = 0; i < FriendRequests.size(); i++) {
			Text fname = new Text(FriendRequests.get(i).getFirstName());
			Text lname = new Text(FriendRequests.get(i).getLastName());
			Text em = new Text(FriendRequests.get(i).getEmail());
			grid.add(fname, 0, row);
			grid.add(lname, 1, row);
			grid.add(em, 2, row);
			grid.add(unignb.get(i), 3, row);
			row++;
		}
		return new ScrollPane(grid);

	}

	protected ScrollPane getFriendRequestView(int tabIndex) {
		GridPane grid = new GridPane();
		ArrayList<User> FriendRequests = this.controller.getFriendRequests(this.controller.getUser().getId());
		if(FriendRequests == null) {
			Label empty = new Label("No Friend Requests :");
			grid.add(empty, 0, 1);
			return new ScrollPane(grid);
		}else {
		ArrayList<Button> addb = new ArrayList<Button>();
		ArrayList<Button> delb = new ArrayList<Button>();
		ArrayList<Button> ignb = new ArrayList<Button>();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		for (int i = 0; i < FriendRequests.size(); i++) {
			int b = i;
			Button add = new Button("Add");
			add.setOnAction(event -> {
				this.controller.addFriend(FriendRequests.get(b).getId(), tabIndex);
				this.controller.showFriendRequestView(tabIndex);
			});
			Button del = new Button("Delete");
			del.setOnAction(event -> {
				this.controller.denyFriend(FriendRequests.get(b).getId(), tabIndex);
				this.controller.showFriendRequestView(tabIndex);
			});
			Button ign = new Button("Ignore");
			ign.setOnAction(event -> {
				try {
					this.controller.ignoreFriend(FriendRequests.get(b).getId(), tabIndex);
					this.controller.showFriendRequestView(tabIndex);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			addb.add(add);
			delb.add(del);
			ignb.add(ign);

		}
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Friend Requests");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0, 0);
		Label name = new Label("First Name :");
		grid.add(name, 0, 1);
		Label lastname = new Label("Last Name :");
		grid.add(lastname, 1, 1);
		Label Email = new Label("Email :");
		grid.add(Email, 2, 1);
		Label Delete = new Label("Delete :");
		grid.add(Delete, 3, 1);
		Label Ignore = new Label("Ignore :");
		grid.add(Ignore, 4, 1);
		Label Add = new Label("Add :");
		grid.add(Add, 5, 1);
		int row = 2;
		for (int i = 0; i < FriendRequests.size(); i++) {
			Text fname = new Text(FriendRequests.get(i).getFirstName());
			Text lname = new Text(FriendRequests.get(i).getLastName());
			Text em = new Text(FriendRequests.get(i).getEmail());
			grid.add(fname, 0, row);
			grid.add(lname, 1, row);
			grid.add(em, 2, row);
			grid.add(delb.get(i), 3, row);
			grid.add(ignb.get(i), 4, row);
			grid.add(addb.get(i), 5, row);
			row++;
		}
		return new ScrollPane(grid);
		}
	}

	protected ScrollPane getFriendView(int tabIndex) {
		GridPane grid = new GridPane();
		ArrayList<User> Friends = this.controller.getFriends(this.controller.getUser().getId());
		if(Friends == null) {
			Text scenetitle = new Text("Friends");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0, 0);
			Label empty = new Label("No Friends ");
			grid.add(empty, 1, 1);
			Button Famous=new Button("Famous friends");
			Famous.setOnAction(event -> {
				this.controller.showMostFamousFriendsView(tabIndex);
			});
			grid.add(Famous, 5, 2);
			Button Common=new Button("Friends with common friends ");
			Common.setOnAction(event -> {
				this.controller.showComonFriendsFriendsView(tabIndex);
			});
			grid.add(Common, 5, 3);
			Button BigX=new Button("Search for friends with bigger than X album");
			BigX.setOnAction(event -> {
				this.controller.showSearchBiggerThanXFriends(tabIndex);
			});
			grid.add(BigX, 5, 4);
			Button NetBigX = new Button("Search for network of friends with bigger than X album");
			NetBigX.setOnAction(event -> {
				this.controller.showSearchBiggerThanXNetworkFriends(tabIndex);
			});
			grid.add(NetBigX, 5, 5);
			Button SameInter = new Button("Friends with same interests");
			SameInter.setOnAction(event -> {
				try {
					this.controller.showCommonInterestFriendView(tabIndex);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			grid.add(SameInter, 5, 6);
			Button notPopularEvents=new Button("Least Popular Events");
			notPopularEvents.setOnAction(event -> {
				try {
					this.controller.showLeastPopularEvent(tabIndex);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			grid.add(SameInter, 5, 7);
			return new ScrollPane(grid);
		}
		ArrayList<Button> delb = new ArrayList<Button>();
		ArrayList<Button> seeUser = new ArrayList<Button>();
		for (int i = 0; i < Friends.size(); i++) {
			int b = i;
			Button del = new Button("Delete");
			del.setOnAction(event -> {
				this.controller.DeleteFriend(Friends.get(b).getId(), tabIndex);
				this.controller.showFriendsView(tabIndex);
			});
			delb.add(del);
			Button see = new Button("See Prof");
			see.setOnAction(event -> {
				this.controller.showProfile(tabIndex);
			});
		}
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Friends");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0, 0);
		Label name = new Label("First Name :");
		grid.add(name, 0, 1);
		Label lastname = new Label("Last Name :");
		grid.add(lastname, 1, 1);
		Label Email = new Label("Email :");
		grid.add(Email, 2, 1);
		Label Delete = new Label("Delete :");
		grid.add(Delete, 3, 1);
		int row = 2;
		for (int i = 0; i < Friends.size(); i++) {
			Text fname = new Text(Friends.get(i).getFirstName());
			Text lname = new Text(Friends.get(i).getLastName());
			Text em = new Text(Friends.get(i).getEmail());
			grid.add(fname, 0, row);
			grid.add(lname, 1, row);
			grid.add(em, 2, row);
			grid.add(delb.get(i), 3, row);
			row++;
		}
		Button Famous=new Button("Famous friends");
		Famous.setOnAction(event -> {
			this.controller.showMostFamousFriendsView(tabIndex);
		});
		grid.add(Famous, 5, 2);
		Button Common=new Button("Friends with common friends ");
		Common.setOnAction(event -> {
			this.controller.showComonFriendsFriendsView(tabIndex);
		});
		grid.add(Common, 5, 3);
		Button BigX=new Button("Search for friends with bigger than X album");
		BigX.setOnAction(event -> {
			this.controller.showSearchBiggerThanXFriends(tabIndex);
		});
		grid.add(BigX, 5, 4);
		Button NetBigX = new Button("Search for network of friends with bigger than X album");
		NetBigX.setOnAction(event -> {
			this.controller.showSearchBiggerThanXNetworkFriends(tabIndex);
		});
		grid.add(NetBigX, 5, 5);
		Button SameInter = new Button("Friends with same interests");
		SameInter.setOnAction(event -> {
			try {
				this.controller.showCommonInterestFriendView(tabIndex);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		grid.add(SameInter, 5, 6);
		Button notPopularEvents=new Button("Least Popular Events");
		notPopularEvents.setOnAction(event -> {
			try {
				this.controller.showLeastPopularEvent(tabIndex);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		grid.add(SameInter, 5, 7);
		return new ScrollPane(grid);
	}

	protected ScrollPane getSearchOccurenceView(int tabIndex) {
		GridPane grid = new GridPane();

		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		HashMap<String, Integer> locationHashmap = this.controller.getLocations();
		String[] locations = AuthenticationController.convert(locationHashmap.keySet());
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Search for Occurence");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0, 0);
		Label name = new Label("Name :");
		grid.add(name, 0, 3);
		TextField nameField = new TextField();
		grid.add(nameField, 1, 3);

		Label location = new Label("Location :");
		grid.add(location, 0, 4);
		ComboBox LocationBox = new ComboBox(FXCollections.observableArrayList(locations));
		LocationBox.getSelectionModel().selectFirst();
		grid.add(LocationBox, 1, 4);
		Label startime = new Label("Start time :");
		grid.add(startime, 0, 5);
		TextField startField = new TextField();
		grid.add(startField, 1, 5);
		Label endtime = new Label("End time :");
		grid.add(endtime, 0, 6);
		TextField endField = new TextField();
		grid.add(endField, 1, 6);
		Button Searchbutton = new Button();
		Searchbutton.setText("Search");
		Searchbutton.setOnAction(event -> {
			String firstName = nameField.getText();
			String slocation = (String) LocationBox.getValue();
			String startDate = startField.getText();
			String endDate = endField.getText();

			this.controller.searchOccurence(firstName, slocation, startDate, endDate, tabIndex);
		});
		grid.add(Searchbutton, 0, 8);

		return new ScrollPane(grid);

	}

	protected ScrollPane getFormView(int tabIndex, FBItem object,boolean canEdit, boolean mine, boolean isInsert) {
		GridPane grid = new GridPane();
		String className = object.getClass().getSimpleName();
		if (mine) {
			prepareMyItemScene(grid, object, tabIndex);
		} else {
			prepareForeignItemScene(grid, object, tabIndex);
		}

		Field[] all_fields = object.getClass().getDeclaredFields();
		ArrayList<Field> fields = HelperFunctions.getNonSensitiveFields(object, all_fields);
//			ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

		HelperFunctions.initXlevel = 0;
		HelperFunctions.initYlevel = HelperFunctions.initYlevel + 5;
		ArrayList<Node> retriveFields = new ArrayList<Node>();
		for (int field_index = 0; field_index < fields.size(); field_index++) {
			try {
				Field currentField = fields.get(field_index);
				HelperFunctions.addItemField(currentField, object, retriveFields, field_index, this, true, tabIndex,canEdit,mine,isInsert);
				HelperFunctions.addFielditemInGrid(grid, currentField.getName(), field_index, retriveFields);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int submitButtonYPosition = fields.size() + 1;
		Button submitButton = new Button("submit");
		grid.add(submitButton, HelperFunctions.initXlevel + 1, submitButtonYPosition + HelperFunctions.initYlevel);
		submitButton.setOnAction(event -> {
			ArrayList<Object> newData = null;
			try {
				newData = HelperFunctions.getDataFromFields(object, fields, retriveFields, this, tabIndex,canEdit,mine,isInsert);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isInsert==false)
			this.controller.updateItem(newData, object, tabIndex);
			else
			this.controller.insertItem(newData,object,tabIndex);
		});

		return new ScrollPane(grid);
	}

//	protected ScrollPane mostPopularFriendsVuew(int tabIndex) {
//		GridPane grid = new GridPane();
//		grid.setAlignment(Pos.BASELINE_LEFT);
//		grid.setHgap(30);
//		grid.setVgap(30);
//		ObservableList<User> PopularFriends = this.controller.getMostPopularFriends(this.controller.getUser().getId());
//		Text scenetitle = new Text("Most Popular Friends");
//		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
//		grid.add(scenetitle, 0, 0);
//		Label name = new Label("First Name :");
//		grid.add(name, 0, 1);
//		Label lastname = new Label("Last Name :");
//		grid.add(lastname, 1, 1);
//		Label Email = new Label("Email :");
//		grid.add(Email, 2, 1);
//
//		int row = 2;
//		for (int i = 0; i < PopularFriends.size(); i++) {
//			Text fname = new Text(PopularFriends.get(i).getFirstName());
//			Text lname = new Text(PopularFriends.get(i).getLastName());
//			Text em = new Text(PopularFriends.get(i).getEmail());
//			grid.add(fname, 0, row);
//			grid.add(lname, 1, row);
//			grid.add(em, 2, row);
//			row++;
//		}
//	}

	protected ScrollPane mostPopularFriendsVuew(int tabIndex) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);
		ArrayList<User> PopularFriends= this.controller.getMostPopularFriends(this.controller.getUser().getId());
		if(PopularFriends == null) {
			Label empty = new Label("No Friend Requests :");
			grid.add(empty, 0, 1);
			return new ScrollPane(grid);
		}
		Text scenetitle = new Text("Most Popular Friends");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0,0);
		Label name = new Label("First Name :");
		grid.add(name, 0,1 );
		Label lastname = new Label("Last Name :");
		grid.add(lastname, 1,1 );
		Label Email = new Label("Email :");
		grid.add(Email, 2,1 );

         int row=2;
		for(int i=0;i<PopularFriends.size();i++) {
			Text fname = new Text(PopularFriends.get(i).getFirstName());
			Text lname = new Text(PopularFriends.get(i).getLastName());
			Text em = new Text(PopularFriends.get(i).getEmail());
			grid.add(fname, 0, row);
			grid.add(lname, 1, row);
			grid.add(em, 2, row);
			row++;
		}
		return new ScrollPane(grid);
	}
		protected ScrollPane SameInterestsFriendsView(
				int tabIndex) {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(30);
			grid.setVgap(30);
			ArrayList<User> SameInterestsFriends= this.controller.getSameInterestFriends(this.controller.getUser().getId());
			if(SameInterestsFriends == null) {
				Label empty = new Label("No Friend Requests :");
				grid.add(empty, 0, 1);
				return new ScrollPane(grid);
			}
			Text scenetitle = new Text("Friends with exact same interests ");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0,0);
			Label name = new Label("First Name :");
			grid.add(name, 0,1 );
			Label lastname = new Label("Last Name :");
			grid.add(lastname, 1,1 );
			Label Email = new Label("Email :");
			grid.add(Email, 2,1 );

	         int row=2;
			for(int i=0;i<SameInterestsFriends.size();i++) {
				Text fname = new Text(SameInterestsFriends.get(i).getFirstName());
				Text lname = new Text(SameInterestsFriends.get(i).getLastName());
				Text em = new Text(SameInterestsFriends.get(i).getEmail());
				grid.add(fname, 0, row);
				grid.add(lname, 1, row);
				grid.add(em, 2, row);
				row++;
			}
			return new ScrollPane(grid);
		}

//	protected ScrollPane SameInterestsFriendsView(int tabIndex) {
//		GridPane grid = new GridPane();
//		grid.setAlignment(Pos.BASELINE_LEFT);
//		grid.setHgap(30);
//		grid.setVgap(30);
//		ArrayList<User> SameInterestsFriends = this.controller
//				.getSameInterestFriends(this.controller.getUser().getId());
//		Text scenetitle = new Text("Friends with exact same interests ");
//		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
//		grid.add(scenetitle, 0, 0);
//		Label name = new Label("First Name :");
//		grid.add(name, 0, 1);
//		Label lastname = new Label("Last Name :");
//		grid.add(lastname, 1, 1);
//		Label Email = new Label("Email :");
//		grid.add(Email, 2, 1);
//
//		int row = 2;
//		for (int i = 0; i < SameInterestsFriends.size(); i++) {
//			Text fname = new Text(SameInterestsFriends.get(i).getFirstName());
//			Text lname = new Text(SameInterestsFriends.get(i).getLastName());
//			Text em = new Text(SameInterestsFriends.get(i).getEmail());
//			grid.add(fname, 0, row);
//			grid.add(lname, 1, row);
//			grid.add(em, 2, row);
//			row++;
//		}
		protected ScrollPane LeastAttendableEvents(
				int tabIndex) {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(30);
			grid.setVgap(30);
			ArrayList<Event> Events= this.controller.getLeastAttendableEvents();
			if(Events == null) {
				Label empty = new Label("No Friend Requests :");
				grid.add(empty, 0, 1);
				return new ScrollPane(grid);
			}
			Text scenetitle = new Text("Least Attendable Events");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0,0);
			Label name = new Label("Name :");
			grid.add(name, 0,1 );
			Label location = new Label("Location");
			grid.add(location, 1,1 );
			Label venue = new Label("Venue");
			grid.add(location, 2,1 );


	         int row=2;
			for(int i=0;i<Events.size();i++) {
				Text nameText = new Text(Events.get(i).getName());
				Text locationText = new Text(Events.get(i).getLoc().getName());
				Text venueText = new Text(Events.get(i).getVenue());
				grid.add(nameText, 0, row);
				grid.add(locationText, 1, row);
				grid.add(venueText, 2, row);
				row++;
			}

		// how does this work
		return new ScrollPane(grid);
	}
		protected ScrollPane SearchForFriendsNetworkWithBiggerAlbumthanX(
				int tabIndex ) {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(30);
			grid.setVgap(30);
			Text scenetitle = new Text("Give a number where we will find friends network that have bigger number of photos  in an album");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0,0);
			TextField putnumber = new TextField("1");
			grid.add(putnumber, 1,1);
			Button search = new Button("Search");
			search.setOnAction(event -> {
				int x =Integer.parseInt(putnumber.getText());
				this.controller.showFriendsWithNetworkAlbumBiggerThanXView(tabIndex, x);
			});
	         grid.add(search, 2, 1);

        // how does this work
return new ScrollPane(grid);
	}
		protected ScrollPane SearchForFriendsWithBiggerAlbumthanX(
				int tabIndex ) {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(30);
			grid.setVgap(30);
			Text scenetitle = new Text("Give a number where we will find friends that have bigger number of photos  in an album");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0,0);
			TextField putnumber = new TextField("1");
			grid.add(putnumber, 1,1);
			Button search = new Button("Search");
			search.setOnAction(event -> {
				int x =Integer.parseInt(putnumber.getText());
				this.controller.showFriendsWithAlbumBiggerThanXView(tabIndex, x);
			});
	         grid.add(search, 2, 1);

        // how does this work
return new ScrollPane(grid);
	}
		protected ScrollPane FriendNetworkWithBiggerthanXAlbums(
				int tabIndex,int z ) {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(30);
			grid.setVgap(30);
			ArrayList<User> FriendsNetworkwithAlbumBiggerThanX= this.controller.getNetworkFriendsWithAlbumBiggerThanX(this.controller.getUser().getId(), z);
			if(FriendsNetworkwithAlbumBiggerThanX == null) {
				Label empty = new Label("No Friend Requests :");
				grid.add(empty, 0, 1);
				return new ScrollPane(grid);
			}
			Text scenetitle = new Text("Friends with Album with more than "+z+" photos");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0,0);
			Label name = new Label("First Name :");
			grid.add(name, 0,1 );
			Label lastname = new Label("Last Name :");
			grid.add(lastname, 1,1 );
			Label Email = new Label("Email :");
			grid.add(Email, 2,1 );

	         int row=2;
			for(int i=0;i<FriendsNetworkwithAlbumBiggerThanX.size();i++) {
				Text fname = new Text(FriendsNetworkwithAlbumBiggerThanX.get(i).getFirstName());
				Text lname = new Text(FriendsNetworkwithAlbumBiggerThanX.get(i).getLastName());
				Text em = new Text(FriendsNetworkwithAlbumBiggerThanX.get(i).getEmail());
				grid.add(fname, 0, row);
				grid.add(lname, 1, row);
				grid.add(em, 2, row);
				row++;
			}

        // how does this work
return new ScrollPane(grid);
	}
		protected ScrollPane FriendWithBiggerthanXAlbums(
				int tabIndex,int z ) {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(30);
			grid.setVgap(30);
			ArrayList<User> FriendswithAlbumBiggerThanX= this.controller.getFriendsWithAlbumBiggerThanX(this.controller.getUser().getId(), z);
			if(FriendswithAlbumBiggerThanX == null) {
				Label empty = new Label("No Friend Requests :");
				grid.add(empty, 0, 1);
				return new ScrollPane(grid);
			}
			Text scenetitle = new Text("Friends with Album with more than "+z+" photos");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0,0);
			Label name = new Label("First Name :");
			grid.add(name, 0,1 );
			Label lastname = new Label("Last Name :");
			grid.add(lastname, 1,1 );
			Label Email = new Label("Email :");
			grid.add(Email, 2,1 );

	         int row=2;
			for(int i=0;i<FriendswithAlbumBiggerThanX.size();i++) {
				Text fname = new Text(FriendswithAlbumBiggerThanX.get(i).getFirstName());
				Text lname = new Text(FriendswithAlbumBiggerThanX.get(i).getLastName());
				Text em = new Text(FriendswithAlbumBiggerThanX.get(i).getEmail());
				grid.add(fname, 0, row);
				grid.add(lname, 1, row);
				grid.add(em, 2, row);
				row++;
			}

        // how does this work
return new ScrollPane(grid);
	}
		protected ScrollPane FriendWithCommonFriends(
				int tabIndex) {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.BASELINE_LEFT);
			grid.setHgap(30);
			grid.setVgap(30);
			ArrayList<User> FriendswithSameFriends= this.controller.getFriendswithsamefriends(this.controller.getUser().getId());
			if(FriendswithSameFriends == null) {
				Label empty = new Label("No Friend Requests :");
				grid.add(empty, 0, 1);
				return new ScrollPane(grid);
			}
			Text scenetitle = new Text("Most Popular Friends");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
			grid.add(scenetitle, 0,0);
			Label name = new Label("First Name :");
			grid.add(name, 0,1 );
			Label lastname = new Label("Last Name :");
			grid.add(lastname, 1,1 );
			Label Email = new Label("Email :");
			grid.add(Email, 2,1 );

	         int row=2;
			for(int i=0;i<FriendswithSameFriends.size();i++) {
				Text fname = new Text(FriendswithSameFriends.get(i).getFirstName());
				Text lname = new Text(FriendswithSameFriends.get(i).getLastName());
				Text em = new Text(FriendswithSameFriends.get(i).getEmail());
				grid.add(fname, 0, row);
				grid.add(lname, 1, row);
				grid.add(em, 2, row);
				row++;
			}

        // how does this work
return new ScrollPane(grid);
	}


	protected ScrollPane getOccurenceResultView(String Name, String slocation, String startDate, String endDate,
			int tabIndex) {
		GridPane grid = new GridPane();
		ArrayList<Event> Occurence = this.controller.getSpecificOccurences(Name, slocation, startDate, endDate);
		Button[] AddComents = new Button[Occurence.size()];
		for (int i = 0; i < Occurence.size(); i++) {
			int b = i;
			AddComents[i].setText("Add comment");
			AddComents[i].setOnAction(event -> {
				this.controller.goToEvent(Occurence.get(b), tabIndex);
			});
		}

		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);
		HashMap<String, Integer> locationHashmap = this.controller.getLocations();
		String[] locations = AuthenticationController.convert(locationHashmap.keySet());
		// how does this work
		return new ScrollPane(grid);
	}

	protected ScrollPane getItemViewMinor(int tabIndex, FBItem item, boolean canEdit, boolean mine,boolean isInsert) {
		GridPane grid = new GridPane();
		if (mine) {
			prepareMyItemScene(grid, item, tabIndex);
		} else {
			prepareForeignItemScene(grid, item, tabIndex);
		}

		Field[] all_fields = item.getClass().getDeclaredFields();
		ArrayList<Field> fields = HelperFunctions.getNonSensitiveFields(item, all_fields);
//			ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

		HelperFunctions.initXlevel = 0;
		HelperFunctions.initYlevel = HelperFunctions.initYlevel + 5;
		ArrayList<Node> retriveFields = new ArrayList<Node>();
		for (int field_index = 0; field_index < fields.size(); field_index++) {
			try {
				Field currentField = fields.get(field_index);
				HelperFunctions.addItemLabel(currentField, item, retriveFields, tabIndex, this,canEdit,mine,isInsert);
				HelperFunctions.addFielditemInGrid(grid, currentField.getName(), field_index, retriveFields);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//			HelperFunctions.initXlevel=HelperFunctions.initXlevel+1;
//			HelperFunctions.initYlevel=fields.size() +HelperFunctions.initYlevel;
		if (canEdit == true) {
			Button editButton = new Button("Edit");
			editButton.setOnAction(event -> {
				this.controller.showFormView(tabIndex, item,canEdit, mine,isInsert);
			});
			grid.add(editButton, HelperFunctions.initXlevel, fields.size() + HelperFunctions.initYlevel++);
		}
		return new ScrollPane(grid);
	}

	protected ScrollPane getItemView(int tabIndex, FBItem item, boolean canEdit, boolean mine,boolean isInsert) {
		GridPane grid = (GridPane) this.getItemViewMinor(tabIndex, item, canEdit, mine,isInsert).getContent();
//			if(doPrepareScene==true) {
//				if(item.equals(this.controller.getUser())) {
//					this.prepareProfileScene(grid, (User)item,tabIndex);
//				}
//				else
//					this.prepareItemScene(grid, item.getClass().getSimpleName(),tabIndex);
//			}
		return new ScrollPane(grid);
	}

	private void prepareScene(GridPane grid, int tabIndex) {
		HelperFunctions.initXlevel = 2;
		HelperFunctions.initYlevel = 0;
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));

		Button logOutButton = new Button("Log Out");
		logOutButton.setOnAction(event -> {
			this.controller.logOut();
		});

		ScrollPane sp = new ScrollPane(grid);
		sp.setFitToWidth(true);
		grid.setHgrow(sp, Priority.ALWAYS);
		sp.setContent(grid);
		grid.add(logOutButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel);

	}

	private void prepareMyItemScene(GridPane grid, FBItem item, int tabIndex) {
		prepareScene(grid, tabIndex);
		Text scenetitle = null;
		User myUser = this.controller.getUser();
		if (item.getClass().equals(User.class)) {
			scenetitle = new Text(myUser.getUsername() + "'s profile");
		} else {
			scenetitle = new Text(myUser.getUsername() + "'s" + item.getClass().getSimpleName());
		}
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		Button showProfileButton = new Button("Go Back to profile");
		showProfileButton.setOnAction(event -> {
			this.controller.showProfile(tabIndex);
		});
		grid.add(showProfileButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel + 1);

		grid.add(scenetitle, 1, 1, 10, 1);
	}

	private ScrollPane prepareForeignItemScene(GridPane grid, FBItem item, int tabIndex) {
			prepareScene(grid,tabIndex);
			Text scenetitle = null;
			if(item.getClass().equals(User.class)) {
				scenetitle = new Text(((User)item).username+"'s profile");
			}else {
				scenetitle = new Text("foreign "+ item.getClass().getSimpleName());
			}
			return new ScrollPane(grid);
		}

	private void prepareItemScene(GridPane grid, String className, int tabIndex) {
		HelperFunctions.initXlevel = 2;
		HelperFunctions.initYlevel = 0;
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = null;
		User myUser = this.controller.getUser();
		scenetitle = new Text(myUser.getUsername() + "'s " + className);

		scenetitle = new Text(className);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);
		Button logOutButton = new Button("Log Out");
		logOutButton.setOnAction(event -> {
			this.controller.logOut();
		});
		Button showProfileButton = new Button("Go Back to profile");
		showProfileButton.setOnAction(event -> {
			this.controller.showProfile(tabIndex);
		});
		ScrollPane sp = new ScrollPane(grid);
		sp.setFitToWidth(true);
		grid.setHgrow(sp, Priority.ALWAYS);
		sp.setContent(grid);
		grid.add(logOutButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel);
		grid.add(showProfileButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel + 1);

	}

	private void prepareProfileScene(GridPane grid, User user, int tabIndex) {
		HelperFunctions.initXlevel = 2;
		HelperFunctions.initYlevel = 0;
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text(user.getUsername() + "'s Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);
		Button logOutButton = new Button("Log Out");
		logOutButton.setOnAction(event -> {
			this.controller.logOut();
		});
		Button showProfileButton = new Button("Go Back to profile");
		showProfileButton.setOnAction(event -> {
			this.controller.showProfile(tabIndex);
		});
		ScrollPane sp = new ScrollPane(grid);
		sp.setFitToWidth(true);
		grid.setHgrow(sp, Priority.ALWAYS);
		sp.setContent(grid);
		grid.add(logOutButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel);
		grid.add(showProfileButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel + 1);

		grid.add(scenetitle, 1, 1, 10, 1);
	}

	protected ScrollPane getSearchUsersView(int tabIndex) {

		GridPane grid = new GridPane();

		HashMap<String, Integer> locationHashmap = UserController.getStringToIntLocations();
		locationHashmap.put("?", -1);
		String[] locations = AuthenticationController.convert(locationHashmap.keySet());

		int yLevelIndex = 3;
		int xStartinglevel = 2;

		Label name = new Label("Name :");
		grid.add(name, xStartinglevel, yLevelIndex);
		TextField nameField = new TextField();
		grid.add(nameField, xStartinglevel + 1, yLevelIndex++);

		Label surname = new Label("Surname :");
		grid.add(surname, xStartinglevel, yLevelIndex);
		TextField surnameField = new TextField();
		grid.add(surnameField, xStartinglevel + 1, yLevelIndex++);

//				Label genderLabel = new Label("Gender:");
//				grid.add(genderLabel, xStartinglevel, yLevelIndex);
//				String[] genders = { "Male", "Female" };
//				ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders));
//				genderBox.getSelectionModel().selectFirst();
//				grid.add(genderBox, xStartinglevel + 1, yLevelIndex++);

		Label hometownLabel = new Label("hometown:");
		grid.add(hometownLabel, xStartinglevel, yLevelIndex);
		ComboBox hometownBox = new ComboBox(FXCollections.observableArrayList(locations));
//		hometownBox.getSelectionModel().selectLast();
		hometownBox.getSelectionModel().select("?");
		grid.add(hometownBox, xStartinglevel + 1, yLevelIndex++);

		Label livesInLabel = new Label("live in :");
		grid.add(livesInLabel, xStartinglevel, yLevelIndex);
		ComboBox livesInBox = new ComboBox(FXCollections.observableArrayList(locations));
		livesInBox.getSelectionModel().select("?");
		grid.add(livesInBox, xStartinglevel + 1, yLevelIndex++);

		Label comptel = new Label("Email");
		grid.add(comptel, xStartinglevel, yLevelIndex);
		TextField emailField = new TextField();
		grid.add(emailField, xStartinglevel + 1, yLevelIndex++);

		Label comp = new Label("Website");
		grid.add(comp, xStartinglevel, yLevelIndex);
		TextField websiteField = new TextField();
		grid.add(websiteField, xStartinglevel + 1, yLevelIndex++);

//				Label linkLabel = new Label("link:");
//				grid.add(linkLabel, xStartinglevel, yLevelIndex);
//				TextField linkField = new TextField();
//				grid.add(linkField, xStartinglevel + 1, yLevelIndex++);

		Label birthdayLabel = new Label("birthday");
		grid.add(birthdayLabel, xStartinglevel, yLevelIndex);
		DatePicker datePicker = new DatePicker();
		datePicker.setValue(LocalDate.now()); // na perna null an to date en now
		grid.add(datePicker, xStartinglevel + 1, yLevelIndex++);

		Label workedAtLabel = new Label("previous employment Places * symbol between employments.");
		grid.add(workedAtLabel, xStartinglevel, yLevelIndex);
		TextField workedAtField = new TextField();
		grid.add(workedAtField, xStartinglevel + 1, yLevelIndex++);

		Label educationPlacesLabel = new Label("Education * symbol between employments.");
		grid.add(educationPlacesLabel, xStartinglevel, yLevelIndex);
		TextField educationField = new TextField();
		grid.add(educationField, xStartinglevel + 1, yLevelIndex++);

		Label quotesLabel = new Label("Quotes * symbol between employments.");
		grid.add(quotesLabel, xStartinglevel, yLevelIndex);
		TextField quotesField = new TextField();
		grid.add(quotesField, xStartinglevel + 1, yLevelIndex++);

//				Label verifiedLabel = new Label("verified:");
//				grid.add(verifiedLabel, xStartinglevel, yLevelIndex);
//				String[] verified = { "true", "false" };
//				ComboBox verifiedBox = new ComboBox(FXCollections.observableArrayList(verified));
//				verifiedBox.getSelectionModel().selectFirst();
//				grid.add(verifiedBox, xStartinglevel + 1, yLevelIndex++);

		Label label1 = new Label("Username:");
		grid.add(label1, xStartinglevel, yLevelIndex); // i am starting from xStartinglevel,xStartinglevel+1
		TextField UserNameField = new TextField();
		grid.add(UserNameField, xStartinglevel + 1, yLevelIndex++);

		Button SearchUserButton = new Button();
		SearchUserButton.setText("Search User");
		SearchUserButton.setOnAction(event -> {
			String firstName = nameField.getText();
			String lastName = surnameField.getText();
			String email = emailField.getText();
			String website = websiteField.getText();
//					String link = linkField.getText();
			String link = "";
			Date birthday = null;
			if (datePicker.getValue().equals(LocalDate.now())) {
				birthday = null;
			} else {
				birthday = Date.valueOf(datePicker.getValue());

			}

//					String strGender = (String) genderBox.getValue();
//					boolean gender = false;
//					if (strGender.equals("Male")) {
//						gender = true;
//					}
			boolean gender = false;
			String tempString = workedAtField.getText();
			if (tempString.equals("")) {
				ArrayList<String> workedForPlaces = null;
			}
			ArrayList<String> workedForPlaces = new ArrayList<String>(Arrays.asList(tempString.split("\\*", 0)));
			ArrayList<String> educationPlaces = new ArrayList<String>(
					Arrays.asList(educationField.getText().split("\\*", 0)));
			ArrayList<String> quotes = new ArrayList<String>(Arrays.asList(quotesField.getText().split("\\*", 0)));
			;
//					String ver = (String) verifiedBox.getValue();
//					boolean isVerified = false;
//					if (strGender.equals("true")) {
//						isVerified = true;
//					}
			boolean isVerified = false;

			String strHometown = (String) hometownBox.getValue();
			Location hometown = new Location(locationHashmap.get(strHometown), strHometown);
			String strLivesInLocation = (String) livesInBox.getValue();
			Location livesIn = new Location(locationHashmap.get(strLivesInLocation), strLivesInLocation);
			System.out.print(livesIn.id);
			// na ta valume tuta?
			String username = UserNameField.getText();

			User a = new User(firstName, lastName, email, website, link, birthday, gender, workedForPlaces,
					educationPlaces, quotes, isVerified, hometown, livesIn, username, "");
			this.controller.searchUsers(a, tabIndex);
		});

		grid.add(SearchUserButton, xStartinglevel, ++yLevelIndex);

		return new ScrollPane(grid);

	}
	
	public ScrollPane getSearchForItemsView(int tabIndex) {
		
		GridPane grid = new GridPane();

		int yLevelIndex = 3;
		int xStartinglevel = 2;

		Label Albumname = new Label("Album Name :");
		grid.add(Albumname, xStartinglevel, yLevelIndex);
		TextField AlbumField = new TextField();
		grid.add(AlbumField, xStartinglevel + 1, yLevelIndex++);
		
		Label Linkname = new Label("Link Name :");
		grid.add(Linkname, xStartinglevel, yLevelIndex);
		TextField LinknameField = new TextField();
		grid.add(LinknameField, xStartinglevel + 1, yLevelIndex++);
		
		Label Eventname = new Label("Event Name :");
		grid.add(Eventname, xStartinglevel, yLevelIndex);
		TextField EventnameField = new TextField();
		grid.add(EventnameField, xStartinglevel + 1, yLevelIndex++);
		
		Label picSource = new Label("Picture Source :");
		grid.add(picSource, xStartinglevel, yLevelIndex);
		TextField PictureSourceField = new TextField();
		grid.add(PictureSourceField, xStartinglevel + 1, yLevelIndex++);
		
		Label VideoMessage = new Label("Video message :");
		grid.add(VideoMessage, xStartinglevel, yLevelIndex);
		TextField VideoField = new TextField();
		grid.add(VideoField, xStartinglevel + 1, yLevelIndex++);
		
		Button SearchItemButton = new Button();
		SearchItemButton.setText("Search Item");
		SearchItemButton.setOnAction(event -> {
			String albumName = AlbumField.getText();
			String linkName = LinknameField.getText();
			String eventName = EventnameField.getText();
			String pictureSource = picSource.getText();
			String videoMessage = VideoField.getText();
			
			this.controller.searchForItems(tabIndex,albumName,linkName,eventName,pictureSource,videoMessage);

		});
			
			grid.add(SearchItemButton, xStartinglevel, ++yLevelIndex);

			return new ScrollPane(grid);
		
	}

	public ScrollPane getItemCrollView(FBItem[] items, int tabIndex, boolean canEditItems, boolean mine,boolean isInsert) {

		GridPane grid = new GridPane();
		HelperFunctions.initXlevel = 2;
		HelperFunctions.initYlevel = 0;
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		Button logOutButton = new Button("Log Out");
		logOutButton.setOnAction(event -> {
			this.controller.logOut();
		});
		ScrollPane sp = new ScrollPane(grid);
		sp.setFitToWidth(true);
		grid.setHgrow(sp, Priority.ALWAYS);
		sp.setContent(grid);
		grid.add(logOutButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel);
		if (tabIndex == 0) {
			Button showProfileButton = new Button("Go Back to profile");
			showProfileButton.setOnAction(event -> {
				this.controller.showProfile(tabIndex);
				grid.add(showProfileButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel + 1);
			});
		} else if (tabIndex == 2) {
			Button showSearchButton = new Button("Show search users View");
			showSearchButton.setOnAction(event -> {
				this.controller.showSearchUsersView(tabIndex);
				grid.add(showSearchButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel + 1);
			});

		}else if (tabIndex == 3) {
			Button showSearchButton = new Button("Show search items View");
			showSearchButton.setOnAction(event -> {
				this.controller.showSearchUsersView(tabIndex);
				grid.add(showSearchButton, HelperFunctions.initXlevel + 10, HelperFunctions.initYlevel + 1);
			});

		}
		else {

		}

//			prepareItemScene(grid, items[0].getClass().getSimpleName(), tabIndex);
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		if (items.length == 0) {
			Label nothingToShow = new Label("there is nothing to show Here!");
			grid.add(nothingToShow, HelperFunctions.initXlevel + 1, 1 + HelperFunctions.initYlevel);
		}
		for (int objectIndex = 0; objectIndex < items.length; objectIndex++) {
			FBItem object = items[objectIndex];
			String className = object.getClass().getSimpleName();
			grid.add(new Label(className), HelperFunctions.initXlevel, objectIndex + HelperFunctions.initYlevel);
			grid.add(new Label(object.getFBName()), HelperFunctions.initXlevel + 1,
					objectIndex + HelperFunctions.initYlevel);
			Button viewItemButton = new Button("View " + className);
			viewItemButton.setOnAction(event -> this.controller.showItemView(object, tabIndex, canEditItems, mine,isInsert));
			grid.add(viewItemButton, HelperFunctions.initXlevel + 2, objectIndex + HelperFunctions.initYlevel);
		}
		return new ScrollPane(grid);
	}
}
