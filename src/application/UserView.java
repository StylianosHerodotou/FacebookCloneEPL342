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

	private static int initXlevel = 0;
	private static int initYlevel = 2;


	Stage primaryStage;
	TabPane tabPane;
	UserController controller;

	public void setController(UserController controller) {
		this.controller = controller;
//		this.allPossibleFields=getAllFields();
	}
//	public static HashMap<String,String> getAllFields(){
//		HashMap<String, String> fields = new HashMap<String,String>();
//		fields.put("id", "int");
//		fields.put("username", "String");
//		fields.put("password", "String");
//		fields.put("firstName", "String");
//		fields.put("lastName", "String");
//		fields.put("email", "String");
//		fields.put("website", "String");
//		fields.put("link", "String");
//		fields.put("birthday", "Date");
//		fields.put("gender", "boolean");
//		fields.put("workedFor", "ArrayList<String>");
//		fields.put("educationPlaces", "ArrayList<String>");
//		fields.put("quotes", "ArrayList<String>");
//		fields.put("isVerified", "boolean");
//		fields.put("hometown", "Location");
//		fields.put("livesInLocation", "Location");
//		fields.put("privacy", "Privacy");
//		fields.put("width", "double");
//		fields.put("height", "double");
//		fields.put("source", "String");
//		fields.put("comments", "ArrayList<Comment>");
//		fields.put("message", "String");
//		fields.put("description", "String");
//		fields.put("length", "double");
//		fields.put("user_id", "int");
//		fields.put("name", "String");
//		fields.put("pictures", "ArrayList<Picture>");
//		fields.put("taken_loc_id", "int");
//		fields.put("LocationTaken", "Location");
//		fields.put("URL", "String");
//		fields.put("caption", "String");
//		fields.put("senter", "User");
//		fields.put("receiver", "User");
//		fields.put("sentDate", "Date");
//		fields.put("venue", "String");
//		fields.put("startTime", "Timestamp");
//		fields.put("endTime", "Timestamp");
//		fields.put("loc_id", "int");
//		fields.put("commenter", "User");
//		fields.put("comment", "String");
//		fields.put("item", "FBItem");
//		fields.put("name", "String");
//		fields.put("name", "String");
//		fields.put("name", "String");
//		fields.put("name", "String");
//
//
//
//		return fields;
//	}
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

	public void generateTabPane()  {
		this.tabPane= new TabPane();
		int index=0;

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
		
      Tab profileTab = new Tab("Profile",this.getMyProfileView(index++));
      Tab pictureTab = new Tab("Profile",this.getItemView(index++, this.controller.generateDummyPicture(),true));
      Tab AlbumTab = new Tab("Profile",this.getItemView(index++, this.controller.generateDummyPictureAlbum(),true));
      Tab videoTab = new Tab("Profile",this.getItemView(index++, this.controller.generateDummyVideo(),true));
      Tab EventTab = new Tab("Profile",this.getItemView(index++, this.controller.generateDummyEvent(),true));
      Tab LinkTab = new Tab("Profile",this.getItemView(index++, this.controller.generateDummyLink(),true));

    tabPane.getTabs().add(profileTab);
    tabPane.getTabs().add(pictureTab);
    tabPane.getTabs().add(AlbumTab);
    tabPane.getTabs().add(videoTab);
    tabPane.getTabs().add(EventTab);
    tabPane.getTabs().add(LinkTab);





	}
	protected static boolean is_field_sensitive(String filed_name) {
		String[] sensitive_info = { "password", "SSN","id" };
		boolean is_sensitive = false;
		for (int index = 0; index < sensitive_info.length; index++) {
			if (filed_name.equals(sensitive_info[index])) {
				is_sensitive = true;
				break;
			}
		}
		return is_sensitive;
	}

	protected static int translateStringTypeToInt(String fieldType) {
		int ans=0;
		switch(fieldType) {
		case "String":return ans++;
		case "int":return ans++;
		case "char":return ans++;
		case "ArrayList<String>":return ans++;
		case "Date":return ans++;
		case "boolean":return ans++;
		case "Location":return ans++;
		default: return -1;
		}
	}

		protected static int translateTypeToInt(String ClassName) {
			int ans=1;
			switch(ClassName) {
			case "User":return ans++;
			case "Picture":return ans++;
			case "PictureAlmbum":return ans++;
			case "Video":return ans++;
			case "Link":return ans++;
			case "Event":return ans++;
			default: return ans++;
			}
	}

	protected ScrollPane tempView(int tabIndex) {
		initXlevel = 0;
		initYlevel = 2;
		GridPane grid = new GridPane();
		ArrayList<FBItem> users = new ArrayList<FBItem>(UserController.generateDummyUsers());
		this.getResultsView(grid,users );
		for(int index=0; index<users.size(); index++) {
			Button button = new Button("this is a button");
			button.setOnAction(event->{
				//
			});
			grid.add(button, initXlevel+2, index + initYlevel);


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
			grid.add(new Label(object.getClass().getSimpleName()), initXlevel, objectIndex + initYlevel);
			grid.add(new Label(object.getFBName()), initXlevel+1, objectIndex + initYlevel);
//			Button somethingButton = new Button("something");
//			somethingButton.setOnAction(event-> this.controller.doSomething("this is a something message"));
//			grid.add(somethingButton, initXlevel+2, objectIndex + initYlevel);
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



		UserView.initYlevel++;
		grid.add(showPicturesButton, initXlevel, initYlevel);
		grid.add(showVideosButton, initXlevel+1, initYlevel++);
		grid.add(showAlbumsButton, initXlevel,initYlevel);
		grid.add(showLinksButton, initXlevel+1, initYlevel++);
		grid.add(showEventsButton, initXlevel, initYlevel);

		ScrollPane scene = new ScrollPane(grid);
		return scene;

	}
	// FRIEND REQUEST VIEW
	protected ScrollPane getFriendRequestView(int tabIndex ) {
		GridPane grid = new GridPane();
		ArrayList<User> FriendRequests = this.controller.getFriendRequests(this.controller.getUser().getId());
		ArrayList<Button> addb = new ArrayList<Button>();
		ArrayList<Button> delb = new ArrayList<Button>();
		ArrayList<Button> ignb = new ArrayList<Button>();
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
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

	private void addItemLabel(Field field, Object object, ArrayList<Node> retriveFields) {
//		System.out.print(object.toString());
		String fieldName=field.getName();
//		String fieldType=(String) allPossibleFields.get(fieldName);
		String fieldType= field.getType().getSimpleName();
		
		if(fieldType==null) {
			System.out.println(fieldName);
		}

		Object fieldValue = null;
		try {
			fieldValue = field.get(object);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(fieldValue!=null) {
			try {
				if(fieldName.equals("gender")){
					if((boolean)fieldValue==true)
						addTextLabelRow("Female", retriveFields);
					else
						addTextLabelRow("Male", retriveFields);
				}
				else {
				addTextLabelRow(fieldValue.toString(), retriveFields);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			addTextLabelRow("null", retriveFields);
		}

	}

	protected static String ArrayListToString(ArrayList<Object> lista) {
		String s= new String();
		if(lista!=null) {
			for (int index=0; index<lista.size(); index++) {
				String item=lista.get(index).toString();
				if(index==lista.size()-1) {
					s=s+item+"\n";
				}
				else {
					s=s+item+" * ";
				}
			}
		}
		return s;
	}

	protected static void addFielditemInGrid(GridPane grid, String fieldName, int field_index,ArrayList<Node> nodes) {
		grid.add(new Label(fieldName+": "), initXlevel + 1,
				field_index +initYlevel);// adding 2 because i start from (2,3)
		grid.add( nodes.get(field_index), initXlevel + 2,
				field_index +initYlevel);// adding 2 because i start from (2,3)
	}

	protected static void addTextFieldRow( String fieldValue,ArrayList<Node> nodes) {
		if(fieldValue!=null)
			nodes.add(new TextField(fieldValue));
		else
			nodes.add(new TextField());
	}

	protected static void addTextLabelRow( String fieldValue,ArrayList<Node> nodes) {
		if(fieldValue!=null)
			nodes.add(new Label(fieldValue));
		else
			nodes.add(new Label());
	}
	protected static void addIsVerifiedField( boolean fieldValue,ArrayList<Node> nodes) {
		String[] genders= {"True","False"};
		ComboBox isVerifiedBox = new ComboBox(FXCollections.observableArrayList(genders));
		if(fieldValue==true)
			isVerifiedBox.getSelectionModel().selectFirst();
		else
			isVerifiedBox.getSelectionModel().select(1); // select second.
		nodes.add(isVerifiedBox);
	}

	protected static void addGenderField( boolean b,ArrayList<Node> nodes ) {
		String[] genders= {"Male","Female"};
		ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders));
		if(b==false) {
			genderBox.getSelectionModel().selectFirst();
		}
		else {
			genderBox.getSelectionModel().select(1); // select second.
		}
		nodes.add(genderBox);

	}

//	protected String[] locationToStringArray(HashMap<String, Integer> locationHashmap ) {
//
//		String[] array= new String[locationHashmap.size()];
//		for(int index=0; index<array.length; index++) {
//			array[index]=
//		}
//
//	}

	protected void addLocationField(Location location, ArrayList<Node> nodes ) {
		HashMap<String, Integer> locationHashmap = this.controller.getLocations();
		String [] locations= AuthenticationController.convert(locationHashmap.keySet());
		String locationName=locations[0];
		if(location!=null) {
			locationName= location.getName();
		}
		ComboBox hometownBox = new ComboBox(FXCollections.observableArrayList(locations));
		hometownBox.getSelectionModel().select(locationName);
		nodes.add(hometownBox);
		}
	protected static void addDateField( Date fieldValue, ArrayList<Node> nodes) {
		DatePicker datePicker = new DatePicker();
		if(fieldValue!=null)
		{
			datePicker.setValue(fieldValue.toLocalDate());
		}else {
			datePicker = new DatePicker(LocalDate.now());;
		}
		nodes.add(datePicker);
}
	protected  void addPictureField( Picture fieldValue, ArrayList<Node> nodes, int tabIndex) {
		HBox box = new HBox(this.getItemView(tabIndex, fieldValue));
		nodes.add(box);
}


	protected void addItemField(Field field, Object object, ArrayList<Node> retriveFields) throws IllegalArgumentException, IllegalAccessException {
		String fieldName=field.getName();
//		String fieldType=(String) allPossibleFields.get(fieldName);
		String fieldType= field.getType().getSimpleName();
		Object fieldValue=null;
		if(fieldType==null) {
			System.out.print("field name "+fieldName);
		}

		switch(fieldType) {
			case "String":
				addTextFieldRow((String) field.get(object),retriveFields);
			break;
			case "int" :
				addTextFieldRow(Integer.toString((int)field.get(object)),retriveFields);
			break;
			case "double" :
				addTextFieldRow(Double.toString((double)field.get(object)),retriveFields);
			break;
//			case "char":
//				if(field.getName().equals("gender")) {
//				addGenderField((char)field.get(object),retriveFields);
//				}
//			break;
			case "ArrayList":
				addTextFieldRow(ArrayListToString((ArrayList<Object>) field.get(object)),retriveFields);
			break;
//			case "ArrayList<Comment>":
//				addTextFieldRow(ArrayListToString((ArrayList<Object>) field.get(object)),retriveFields);
//			break;
//			case "ArrayList<Picture>":
//				addTextFieldRow(ArrayListToString((ArrayList<Object>) field.get(object)),retriveFields);
//			break;
			case "Date":
				addDateField((Date)field.get(object),retriveFields);
			break;
			case "Timestamp":
				addTextFieldRow(field.get(object).toString(),retriveFields);
			break;
			case "boolean":
				if(field.getName().equals("gender"))
				{
					addGenderField((boolean)field.get(object),retriveFields);

				}else if((field.getName().equals("isVerified"))) {
					addIsVerifiedField((boolean )field.get(object),retriveFields);

				}
			break;
			case "Location":
				addLocationField((Location )field.get(object),retriveFields);
			break;
			case "Picture":
				addPictureField((Picture) field.get(object),retriveFields,0);
			break;
			case "Privacy":
				addTextFieldRow(((Privacy) field.get(object)).name, retriveFields);
			break;

			default:
				System.out.print("there was a new field "+fieldName + "field type "+fieldType );
			break;

		}

	}

	private Object retriveDataFromField(Object object, Field field,Node node) {
		String fieldName=field.getName();
		String fieldType= field.getType().getSimpleName();

		
		
		
		
		switch(fieldType) {
			case "String":
				return ((TextField) node).getText();
			case "Privacy":
				return new Privacy(((TextField) node).getText());
		case "int" :
				return Integer.parseInt(((TextField) node).getText());
		case "double" :
			return Double.parseDouble(((TextField) node).getText());
//		case "char":
//				if(field.getName().equals("gender")) {
//					String strGender=(String) ((ComboBox)node).getValue();
//					char gender='F';
//					if(strGender.equals("Male")) {
//						gender='M';
//					}
//					return gender;
//				}
//			break;
			case "ArrayList":
				if(fieldName.equals("comments")) {
					return null;
				}else if (fieldName.equals("pictures")){
					return null;
				}else {
					return new ArrayList<String>(Arrays.asList(((TextField) node).getText().split("\\*",0)));
				}
		case "Date":
				return Date.valueOf( ((DatePicker)node).getValue());
		case "Timestamp":
			return Timestamp.valueOf(((TextField) node).getText());
		case "boolean":
			if(field.getName().equals("gender")) {
			String strGender=(String) ((ComboBox)node).getValue();
			boolean gender=true;
			if(strGender.equals("Male")) {
				gender=false;
			}
			return gender;
		}
			else if(field.getName().equals("isVerified")) {
				String isVerified= (String) ((ComboBox)node).getValue();
				if(isVerified.equals("True"))
					return true;
				else
					return false;
			}

		case "Location":
				HashMap<String, Integer> locationHashmap = this.controller.getLocations();
				String strLocation= (String) ((ComboBox)node).getValue();
				Location hometown=new Location(locationHashmap.get(strLocation), strLocation);
				return hometown;
		

			default:
				System.out.print("there was a new field "+field.getName() );
				return null;
		}
	}

	private ArrayList<Object> getDataFromFields(Object object,ArrayList<Field> fields, ArrayList<Node> retriveFields){
		 ArrayList<Object> data= new ArrayList<Object>();
		 for (int fieldIndex=0; fieldIndex<retriveFields.size(); fieldIndex++) {
			 data.add(this.retriveDataFromField(object, fields.get(fieldIndex), retriveFields.get(fieldIndex)));
		 }
		 return data;

	}



	private static ArrayList<Field> getNonSensitiveFields(Object object, Field[] all_fields){
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
			if (is_field_sensitive(field_name) == false) {
				fields.add(all_fields[i]);
//				grid.addColumn(fields.size(), new Label(field_name));
			}
		}
		return fields;

	}

	private static ArrayList<Field> getBothSensitiveAndNonSensitiveFields(Object object, Field[] all_fields){
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
				fields.add(all_fields[i]);
//				grid.addColumn(fields.size(), new Label(field_name));
		}
		return fields;

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
		ArrayList<Field> fields= getNonSensitiveFields(object, all_fields);
//		ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

		initXlevel = 0;
		initYlevel = 2;
		ArrayList<Node> retriveFields = new ArrayList<Node>();
		for (int field_index = 0; field_index < fields.size(); field_index++) {
				try {
					Field currentField=fields.get(field_index);
					this.addItemField(currentField,object, retriveFields);
					addFielditemInGrid(grid, currentField.getName(),field_index, retriveFields);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		int submitButtonYPosition=fields.size()+1;
		Button submitButton = new Button("submit");
		grid.add(submitButton, initXlevel + 1,submitButtonYPosition +initYlevel);
		submitButton.setOnAction(event->{
			ArrayList<Object> newData = getDataFromFields(object, fields, retriveFields);
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
		grid.add(logOutButton, this.initXlevel+10,this.initYlevel);
		grid.add(showProfileButton, this.initXlevel+10,this.initYlevel+2);

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
		ArrayList<Field> fields= getNonSensitiveFields(item, all_fields);
//		ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

		initXlevel = 0;
		initYlevel = 2;
		ArrayList<Node> retriveFields = new ArrayList<Node>();
		for (int field_index = 0; field_index < fields.size(); field_index++) {
				try {
					Field currentField=fields.get(field_index);
					this.addItemLabel(currentField,item, retriveFields);
					addFielditemInGrid(grid, currentField.getName(),field_index, retriveFields);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		initXlevel=initXlevel+1;
		initYlevel=fields.size() +initYlevel;
		Button editButton = new Button("Edit");
		editButton.setOnAction(event->{
			this.controller.showFormView( tabIndex, item);
		});
		grid.add(editButton,initXlevel+5 , fields.size() +initYlevel+1);
		return grid;
	}
	
	
	protected ScrollPane getItemView(int tabIndex, FBItem item,boolean doPrepareScene) {
		GridPane grid = this.getItemView(tabIndex, item);
		if(doPrepareScene==true) {
			this.prepareItemScene(grid, item.getClass().getSimpleName(),tabIndex);
		}
		return new ScrollPane(grid);
	}
	public ScrollPane getItemCrollView(FBItem[] items, int tabIndex) {
		GridPane grid = new GridPane();
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		for (int objectIndex = 0; objectIndex < items.length; objectIndex++) {
			FBItem object = items[objectIndex];
			String className=object.getClass().getSimpleName();
			grid.add(new Label(className), initXlevel, objectIndex + initYlevel);
			grid.add(new Label(object.getFBName()), initXlevel+1, objectIndex + initYlevel);
			Button viewItemButton = new Button("View "+className);
			viewItemButton.setOnAction(event-> this.controller.showItemView(object,tabIndex));
			grid.add(viewItemButton, initXlevel+2, objectIndex + initYlevel);
		}

		return new ScrollPane(grid);

	}

}
