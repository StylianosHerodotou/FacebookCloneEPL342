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
import javafx.util.Callback;

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

//      Tab profileTab = new Tab("Profile",this.getMyProfileView(index++));
//      Tab changeProfileTab = new Tab("changeProfileTab",this.getFormView(index++, this.controller.getUser()));
//      Tab seeItemTab = new Tab("Picture View",this.getItemView(index++, this.controller.generateDummyPicture()));
//      Tab seeAlmbuTab = new Tab("Album View",this.getItemView(index++, this.controller.generatePictureAlbum()));
//
//
//      tabPane.getTabs().add(profileTab);
//      tabPane.getTabs().add(changeProfileTab);
//      tabPane.getTabs().add(seeItemTab);
//      tabPane.getTabs().add(seeAlmbuTab);

		Tab profileTab = new Tab("Profile", this.getMyProfileView(index++));
		Tab pictureTab = new Tab("Profile", this.getItemView(index++, this.controller.generateDummyPicture(), true));
		Tab AlbumTab = new Tab("Profile", this.getItemView(index++, this.controller.generateDummyPictureAlbum(), true));
		Tab videoTab = new Tab("Profile", this.getItemView(index++, this.controller.generateDummyVideo(), true));
		Tab EventTab = new Tab("Profile", this.getItemView(index++, this.controller.generateDummyEvent(), true));
		Tab LinkTab = new Tab("Profile", this.getItemView(index++, this.controller.generateDummyLink(), true));

		tabPane.getTabs().add(profileTab);
		tabPane.getTabs().add(pictureTab);
		tabPane.getTabs().add(AlbumTab);
		tabPane.getTabs().add(videoTab);
		tabPane.getTabs().add(EventTab);
		tabPane.getTabs().add(LinkTab);

	}







	protected ScrollPane tempView(int tabIndex) {
		HelperFunctions.initXlevel = 0;
		HelperFunctions.initYlevel = 2;
		GridPane grid = new GridPane();
		ArrayList<FBItem> users = new ArrayList<FBItem>(UserController.generateDummyUsers());
		this.getResultsView(grid,users );
		for(int index=0; index<users.size(); index++) {
			Button button = new Button("this is a button");
			button.setOnAction(event->{
				//
			});
			grid.add(button, HelperFunctions.initXlevel+2, index + HelperFunctions.initYlevel);


		}
		return new ScrollPane(grid);
	}

	protected void getResultsView(GridPane grid, ArrayList<FBItem> items) {
		// set dimentions of the grid.
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		for (int objectIndex = 0; objectIndex < items.size(); objectIndex++) {
			FBItem object = items.get(objectIndex);
//			System.out.println(object.toString());
			grid.add(new Label(object.getClass().getSimpleName()), HelperFunctions.initXlevel, objectIndex + HelperFunctions.initYlevel);
			grid.add(new Label(object.getFBName()), HelperFunctions.initXlevel+1, objectIndex + HelperFunctions.initYlevel);
//			Button somethingButton = new Button("something");
//			somethingButton.setOnAction(event-> this.controller.doSomething("this is a something message"));
//			grid.add(somethingButton, HelperFunctions.initXlevel+2, objectIndex + HelperFunctions.initYlevel);
		}

	}


	protected ScrollPane getMyProfileView(int tabIndex) {
		GridPane grid = (GridPane) this.getItemView(tabIndex, this.controller.getUser(), false).getContent();
		this.prepareProfileScene(grid, this.controller.getUser());
		Button showPicturesButton = new Button("show pictures");
		showPicturesButton.setOnAction(event->this.controller.showUserImagesView(tabIndex));
		Button showVideosButton = new Button("show videos");
		showVideosButton.setOnAction(event->this.controller.showUserVideosView(tabIndex));
		Button showAlbumsButton = new Button("show Albums");
		showAlbumsButton.setOnAction(event->this.controller.showUserAlbumsView(tabIndex));
		Button showLinksButton = new Button("show Links");
		showLinksButton.setOnAction(event->this.controller.showUserLinksView(tabIndex));
		Button showEventsButton = new Button("show Events");
		showEventsButton.setOnAction(event->this.controller.showUserEventsView(tabIndex));



		HelperFunctions.initYlevel++;
		grid.add(showPicturesButton, HelperFunctions.initXlevel, HelperFunctions.initYlevel);
		grid.add(showVideosButton, HelperFunctions.initXlevel+1, HelperFunctions.initYlevel++);
		grid.add(showAlbumsButton, HelperFunctions.initXlevel,HelperFunctions.initYlevel);
		grid.add(showLinksButton, HelperFunctions.initXlevel+1, HelperFunctions.initYlevel++);
		grid.add(showEventsButton, HelperFunctions.initXlevel, HelperFunctions.initYlevel);

		ScrollPane scene = new ScrollPane(grid);
		return scene;

	}
	// FRIEND REQUEST VIEW
	protected ScrollPane getFriendRequestView(int tabIndex ) {
		GridPane grid = new GridPane();
		ObservableList<FriendRequest> FriendRequests = this.controller.getFriendRequests(this.controller.getUser().getId());
		ObservableList<Button> addb = FXCollections.observableArrayList();
		ObservableList<Button> delb = FXCollections.observableArrayList();
		ObservableList<Button> ignb = FXCollections.observableArrayList();

		for(int i=0;i<FriendRequests.size();i++) {
			int b=i;
			Button add= new Button("Add");
			add.setOnAction(event ->{this.controller.addFriend(FriendRequests.get(b).getId(), tabIndex);});
			Button del= new Button("Delete");
			del.setOnAction(event ->{this.controller.denyFriend(FriendRequests.get(b).getId(), tabIndex);});
			Button ign= new Button("Ignore");
			ign.setOnAction(event ->{try {
				this.controller.ignoreFriend(FriendRequests.get(b).getId(), tabIndex);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
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
		grid.add(scenetitle, 0,0);
		TableColumn<FriendRequest, Integer> idcolumn = new TableColumn<>("ID");
		idcolumn.setMinWidth(100);
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<FriendRequest, String> namecolumn = new TableColumn<>("FirstName");
		namecolumn.setMinWidth(200);
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		TableColumn<FriendRequest, String> surnamecolumn = new TableColumn<>("LastName");
		surnamecolumn.setMinWidth(200);
		surnamecolumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		//TableColumn ID = new TableColumn("ID");
		TableColumn<Button, Button> addColumn = new TableColumn<>("Add");
		addColumn.setMinWidth(200);
		addColumn.setCellValueFactory(new PropertyValueFactory<Button,Button>("Add"));
		TableColumn<Button, Button> DeleteColumn = new TableColumn<>("Delete");
		DeleteColumn.setMinWidth(200);
		DeleteColumn.setCellValueFactory(new PropertyValueFactory<Button,Button>("Delete"));
		TableColumn<Button, Button> IgnoreColumn = new TableColumn<>("Ignore");
		IgnoreColumn.setMinWidth(200);
		IgnoreColumn.setCellValueFactory(new PropertyValueFactory<Button,Button>("Ignore"));

		TableView table = new TableView();
		TableView table2 = new TableView();
		TableView table3 = new TableView();
		TableView table4 = new TableView();
		table2.setItems(addb);
		table3.setItems(delb);
		table4.setItems(ignb);
		 table.setItems(FriendRequests);
table.getColumns().addAll(idcolumn,namecolumn,surnamecolumn);
table2.getColumns().addAll(addColumn);
table3.getColumns().addAll(DeleteColumn);
table4.getColumns().addAll(IgnoreColumn);
table.setEditable(true);
grid.add(table, 0, 1);
grid.add(table, 1, 1);
grid.add(table, 2, 1);
grid.add(table, 3, 1);
return new ScrollPane(grid);

	}
	protected ScrollPane getFriendView(int tabIndex ) {
		GridPane grid = new GridPane();
		ObservableList<FriendRequest> Friends= this.controller.getFriends(this.controller.getUser().getId());
		ObservableList<Button> delb = FXCollections.observableArrayList();
		for(int i=0;i<Friends.size();i++) {
			int b=i;
			Button del= new Button("Delete");
			del.setOnAction(event ->{this.controller.DeleteFriend(Friends.get(b).getId(), tabIndex);});
			delb.add(del);
		}
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Friends");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0,0);
		TableColumn<FriendRequest, Integer> idcolumn = new TableColumn<>("ID");
		idcolumn.setMinWidth(100);
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<FriendRequest, String> namecolumn = new TableColumn<>("FirstName");
		namecolumn.setMinWidth(200);
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		TableColumn<FriendRequest, String> surnamecolumn = new TableColumn<>("LastName");
		surnamecolumn.setMinWidth(200);
		surnamecolumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		//TableColumn ID = new TableColumn("ID");
		TableColumn<Button, Button> DeleteColumn = new TableColumn<>("Delete");
		DeleteColumn.setMinWidth(200);
		DeleteColumn.setCellValueFactory(new PropertyValueFactory<Button,Button>("Delete"));

		TableView table = new TableView();
		TableView table2 = new TableView();
		table2.setItems(delb);
		table2.getColumns().addAll(DeleteColumn);

		 table.setItems(Friends);
table.getColumns().addAll(idcolumn,namecolumn,surnamecolumn,DeleteColumn);

table.setEditable(true);
grid.add(table, 0, 1);
grid.add(table, 1, 1);
return new ScrollPane(grid);

	}
	protected ScrollPane getSearchOccurenceView(int tabIndex ) {
		GridPane grid = new GridPane();

		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		HashMap<String, Integer> locationHashmap = this.controller.getLocations();
		String [] locations= AuthenticationController.convert(locationHashmap.keySet());
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Search for Occurence");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0,0);
		Label name = new Label("Name :");
		grid.add(name, 0,3 );
		TextField nameField = new TextField();
		grid.add(nameField, 1, 3);

		Label location = new Label("Location :");
		grid.add(location, 0, 4);
		ComboBox LocationBox = new ComboBox(FXCollections.observableArrayList(locations));
		LocationBox.getSelectionModel().selectFirst();
		grid.add(LocationBox, 1, 4);
		Label startime = new Label("Start time :");
		grid.add(startime, 0,5 );
		TextField startField = new TextField();
		grid.add(startField, 1, 5);
		Label endtime = new Label("End time :");
		grid.add(endtime, 0,6 );
		TextField endField = new TextField();
		grid.add(endField, 1, 6);
		Button Searchbutton = new Button();
		Searchbutton.setText("Search");
		Searchbutton.setOnAction(event->{
			String firstName=nameField.getText();
			String slocation=(String) LocationBox.getValue();
			String startDate=startField.getText();
			String endDate=endField.getText();



			this.controller.searchOccurence(firstName,slocation,startDate,endDate,tabIndex);
		});
		grid.add(Searchbutton, 0, 8);

return new ScrollPane(grid);

	}

	
	protected ScrollPane getFormView(int tabIndex, FBItem object) {
		GridPane grid = new GridPane();
		String className= object.getClass().getSimpleName();
		if(className.equals("User")) {
			prepareProfileScene(grid,(User)object );
		}
		else {
			prepareItemScene(grid,className,tabIndex);
		}

		Field[] all_fields = object.getClass().getDeclaredFields();
		ArrayList<Field> fields= HelperFunctions.getNonSensitiveFields(object, all_fields);
//		ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

		HelperFunctions.initXlevel = 0;
		HelperFunctions.initYlevel = 2;
		ArrayList<Node> retriveFields = new ArrayList<Node>();
		for (int field_index = 0; field_index < fields.size(); field_index++) {
				try {
					Field currentField=fields.get(field_index);
					HelperFunctions.addItemField(currentField,object, retriveFields,field_index, this, false);
					HelperFunctions.addFielditemInGrid(grid, currentField.getName(),field_index, retriveFields);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		int submitButtonYPosition=fields.size()+1;
		Button submitButton = new Button("submit");
		grid.add(submitButton, HelperFunctions.initXlevel + 1,submitButtonYPosition +HelperFunctions.initYlevel);
		submitButton.setOnAction(event->{
			ArrayList<Object> newData = null;
			try {
				newData = HelperFunctions.getDataFromFields(object, fields, retriveFields, this);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.controller.updateItem(newData,className, object,tabIndex);
		});

		return new ScrollPane(grid);
	}

	protected ScrollPane mostPopularFriendsVuew(
			int tabIndex) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);
		ObservableList<User> PopularFriends= this.controller.getMostPopularFriends(this.controller.getUser().getId());
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

        // how does this work
return new ScrollPane(grid);
	}
	protected ScrollPane SameInterestsFriendsVuew(
			int tabIndex) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);
		ObservableList<User> SameInterestsFriends= this.controller.getSameInterestFriends(this.controller.getUser().getId());
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

        // how does this work
return new ScrollPane(grid);
	}
	protected GridPane LeastAttendableEvents(
			int tabIndex) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);
		ObservableList<Event> Events= this.controller.getLeastAttendableEvents();
		Text scenetitle = new Text("Least Attendable Events");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0,0);
		Label name = new Label("Name :");
		grid.add(name, 0,1 );
		Label location = new Label("Location");
		grid.add(location, 1,1 );


         int row=2;
		for(int i=0;i<Events.size();i++) {
			Text nameText = new Text(Events.get(i).getName());
			Text locationText = new Text(Events.get(i).getLoc_id());
			grid.add(nameText, 0, row);
			grid.add(locationText, 1, row);
			row++;
		}

        // how does this work
return grid;
	}
	protected GridPane FriendWithCommonFriends(
			int tabIndex) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);
		ObservableList<User> FriendswithSameFriends= this.controller.getFriendswithsamefriends(this.controller.getUser().getId());
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
return grid;
	}
	protected GridPane getOccurenceResultView(String Name, String slocation, String startDate, String endDate,
			int tabIndex) {
		GridPane grid = new GridPane();
		ObservableList<Event> Occurence= this.controller.getSpecificOccurences(Name,slocation,startDate,endDate);
		Button[] AddComents = new Button[Occurence.size()];
		for(int i=0;i<Occurence.size();i++) {
			int b=i;
			AddComents[i].setText("Add comment");
			AddComents[i].setOnAction(event -> {
				this.controller.goToEvent(Occurence.get(b), tabIndex);
				});
		}

		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(30);
		grid.setVgap(30);
		HashMap<String, Integer> locationHashmap = this.controller.getLocations();
		String [] locations= AuthenticationController.convert(locationHashmap.keySet());
        // how does this work
return grid;
	}

	private void prepareItemScene(GridPane grid, String className, int tabIndex) {
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = null;
		User myUser=this.controller.getUser();
		scenetitle = new Text(myUser.getUsername()+ "'s "+className);

		scenetitle = new Text(this.controller.getUser().getUsername()+"'s Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);
		Button logOutButton = new Button("Log Out");
		logOutButton.setOnAction(event->{
			this.controller.logOut();
		});
		Button showProfileButton = new Button("Go Back to profile");
		showProfileButton.setOnAction(event->{
			this.controller.showProfile(tabIndex);
		});
		ScrollPane sp = new ScrollPane(grid);
		sp.setFitToWidth(true);
		grid.setHgrow(sp, Priority.ALWAYS);
		sp.setContent(grid);
		grid.add(logOutButton, HelperFunctions.initXlevel+10,HelperFunctions.initYlevel);
		grid.add(showProfileButton,HelperFunctions.initXlevel+10,HelperFunctions.initYlevel+2);

	}

	private void prepareProfileScene(GridPane grid, User user) {
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text(user.getUsername()+ "'s Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);
		Button logOutButton = new Button("Log Out");
		logOutButton.setOnAction(event->{
			this.controller.logOut();
		});
		grid.add(logOutButton, 10,0);
	}

	protected GridPane getItemView(int tabIndex, FBItem item) {
		GridPane grid = new GridPane();

		Field[] all_fields = item.getClass().getDeclaredFields();
		ArrayList<Field> fields= HelperFunctions.getNonSensitiveFields(item, all_fields);
//		ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

		HelperFunctions.initXlevel = 0;
		HelperFunctions.initYlevel = 2;
		ArrayList<Node> retriveFields = new ArrayList<Node>();
		for (int field_index = 0; field_index < fields.size(); field_index++) {
				try {
					Field currentField=fields.get(field_index);
					HelperFunctions.addItemLabel(currentField,item, retriveFields);
					HelperFunctions.addFielditemInGrid(grid, currentField.getName(),field_index, retriveFields);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		HelperFunctions.initXlevel=HelperFunctions.initXlevel+1;
		HelperFunctions.initYlevel=fields.size() +HelperFunctions.initYlevel;
		Button editButton = new Button("Edit");
		editButton.setOnAction(event->{
			this.controller.showFormView( tabIndex, item);
		});
		grid.add(editButton,HelperFunctions.initXlevel+5 , fields.size() +HelperFunctions.initYlevel+1);
		return grid;
	}
	
	
	protected ScrollPane getItemView(int tabIndex, FBItem item,boolean doPrepareScene) {
		GridPane grid = this.getItemView(tabIndex, item);
		if(doPrepareScene==true) {
			this.prepareItemScene(grid, item.getClass().getSimpleName(),tabIndex);
		}
		return new ScrollPane(grid);
	}
	public ScrollPane getItemCrollView(FBItem[] items, int tabIndex, boolean canEditItems) {
		GridPane grid = new GridPane();
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		for (int objectIndex = 0; objectIndex < items.length; objectIndex++) {
			FBItem object = items[objectIndex];
			String className=object.getClass().getSimpleName();
			grid.add(new Label(className), HelperFunctions.initXlevel, objectIndex + HelperFunctions.initYlevel);
			grid.add(new Label(object.getFBName()), HelperFunctions.initXlevel+1, objectIndex + HelperFunctions.initYlevel);
			if(canEditItems==true) {
				Button viewItemButton = new Button("View "+className);
				viewItemButton.setOnAction(event-> this.controller.showItemView(object,tabIndex));
				grid.add(viewItemButton, HelperFunctions.initXlevel+2, objectIndex + HelperFunctions.initYlevel);
			}
		}
		return new ScrollPane(grid);
	}
}
