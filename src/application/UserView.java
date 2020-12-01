package application;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.security.Timestamp;
import java.sql.Date;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UserView {
	
	private static HashMap <String, String>allPossibleFields;
	private static int initXlevel = 0;
	private static int initYlevel = 2;
	
	
	Stage primaryStage;
	TabPane tabPane;
	UserController controller;

	public void setController(UserController controller) {
		this.controller = controller;
		this.allPossibleFields=getAllFields();
	}
	public static HashMap<String,String> getAllFields(){
		HashMap<String, String> fields = new HashMap<String,String>();
		fields.put("id", "int");
		fields.put("username", "String");
		fields.put("password", "String");
		fields.put("firstName", "String");
		fields.put("lastName", "String");
		fields.put("email", "String");
		fields.put("website", "String");
		fields.put("link", "String");
		fields.put("birthday", "Date");
		fields.put("gender", "boolean");
		fields.put("workedFor", "ArrayList<String>");
		fields.put("educationPlaces", "ArrayList<String>");
		fields.put("quotes", "ArrayList<String>");
		fields.put("isVerified", "boolean");
		fields.put("hometown", "Location");
		fields.put("livesInLocation", "Location");
		return fields;

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

	public void generateTabPane()  {
		this.tabPane= new TabPane();
		int index=0;

      Tab profileTab = new Tab("Profile",this.getItemView(index++,0));
      Tab changeProfileTab = new Tab("changeProfileTab",this.getFormView(index++, 1));
      Tab seeItemTab = new Tab("item view",this.getItemView(index++, 2));
      Tab resultsTab = new Tab("help", this.tempView(index++));


      

      tabPane.getTabs().add(resultsTab);
      tabPane.getTabs().add(profileTab);
      tabPane.getTabs().add(changeProfileTab);
      tabPane.getTabs().add(seeItemTab);
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
		if(fieldType.equals("String")) {
			return 0;
		}
		else if(fieldType.equals("int")) {
			return 1;
		}
		else if((fieldType.equals("char"))) {
			return 2;
		}
		else if(fieldType.equals("ArrayList<String>")) {
			return 3;
		}
		else if(fieldType.equals("Date")) {
			return 4;
		}
		else if(fieldType.equals("boolean")) {
			return 5;
		}
		else if(fieldType.equals("Location")) {
			return 6;
		}else
			return -1;
	}
	
	protected GridPane tempView(int tabIndex) {
		initXlevel = 0;
		initYlevel = 2;
		GridPane grid = new GridPane();
		ArrayList<FBItem> users = new ArrayList<FBItem>(UserController.generateDummyUser());
		this.getResultsView(grid,users );
		for(int index=0; index<users.size(); index++) {
			Button button = new Button("this is a button");
			button.setOnAction(event->{
				//
			});
			grid.add(button, initXlevel+2, index + initYlevel);
			
			
		}
		return grid;
	}
	
	protected void getResultsView(GridPane grid, ArrayList<FBItem> items) {
		// set dimentions of the grid.
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		for (int objectIndex = 0; objectIndex < items.size(); objectIndex++) {
			FBItem object = items.get(objectIndex);
			System.out.println(object.toString());
			grid.add(new Label(object.getClass().getSimpleName()), initXlevel, objectIndex + initYlevel);
			grid.add(new Label(object.getFBName()), initXlevel+1, objectIndex + initYlevel);
//			Button somethingButton = new Button("something");
//			somethingButton.setOnAction(event-> this.controller.doSomething("this is a something message"));
//			grid.add(somethingButton, initXlevel+2, objectIndex + initYlevel);
		}

	}


	protected GridPane getMyProfileView(int tabIndex) {
		GridPane grid = new GridPane();
		this.prepareProfileScene(grid, this.controller.getUser());
		int xlevel=1;
		int ylevel=2;
		// set dimentions of the grid.
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		Field[] all_fields = User.class.getDeclaredFields();
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
			if (is_field_sensitive(field_name) == false) {
				fields.add(all_fields[i]);
//				grid.addColumn(fields.size(), new Label(field_name));
			}
		}
		
		initXlevel = 0;
		initYlevel = 2;
		int rowCount = fields.size() + 1;
		User user = this.controller.getUser();

			for (int field_index = 0; field_index < fields.size(); field_index++) {
				try {
					Object field=fields.get(field_index).get(user);
					if(field!=null)
					{
						grid.add(new Label(fields.get(field_index).getName()+": "+field.toString()+" "), initXlevel + 1,
								field_index +initYlevel);// adding 2 because i start from (2,3)
					}else {
						grid.add(new Label("field "+ fields.get(field_index).getName()+" is null "),  initXlevel + 1,
								field_index +initYlevel);// adding 2 because i start from (2,3)
					}
					}
				catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		return grid;
	
	}
	// FRIEND REQUEST VIEW 
	protected GridPane getFriendRequestView(int tabIndex ) {
		GridPane grid = new GridPane();
		ObservableList<FRequest> FriendRequests = this.controller.getFriendRequests(this.controller.getUser().getId());
		for(int i=0;i<FriendRequests.size();i++) {
			int b=i;
			FriendRequests.get(i).getAdd().setOnAction(event ->{this.controller.addFriend(FriendRequests.get(b).getId(),tabIndex);});
			FriendRequests.get(i).getDelete().setOnAction(event ->{this.controller.denyFriend(FriendRequests.get(b).getId(),tabIndex);});
			FriendRequests.get(i).getIgnore().setOnAction(event ->{try {
				this.controller.ignoreFriend(FriendRequests.get(b).getId(),tabIndex);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
		}
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Friend Requests");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0,0);
		TableColumn<FRequest, Integer> idcolumn = new TableColumn<>("ID");
		idcolumn.setMinWidth(100);
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<FRequest, String> namecolumn = new TableColumn<>("FirstName");
		namecolumn.setMinWidth(200);
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		TableColumn<FRequest, String> surnamecolumn = new TableColumn<>("LastName");
		surnamecolumn.setMinWidth(200);
		surnamecolumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		//TableColumn ID = new TableColumn("ID");
		TableColumn<FRequest, Button> addColumn = new TableColumn<>("Add");
		addColumn.setMinWidth(200);
		addColumn.setCellValueFactory(new PropertyValueFactory<FRequest,Button>("Add"));
		TableColumn<FRequest, Button> DeleteColumn = new TableColumn<>("Delete");
		DeleteColumn.setMinWidth(200);
		DeleteColumn.setCellValueFactory(new PropertyValueFactory<FRequest,Button>("Delete"));
		TableColumn<FRequest, Button> IgnoreColumn = new TableColumn<>("Ignore");
		IgnoreColumn.setMinWidth(200);
		IgnoreColumn.setCellValueFactory(new PropertyValueFactory<FRequest,Button>("Ignore"));

		TableView table = new TableView();
		
		 table.setItems(FriendRequests);
table.getColumns().addAll(idcolumn,namecolumn,surnamecolumn,addColumn,DeleteColumn,IgnoreColumn);

table.setEditable(true);
grid.add(table, 0, 1);
return grid;

	}
	protected GridPane getFriendView(int tabIndex ) {
		GridPane grid = new GridPane();
		ObservableList<FRequest> Friends= this.controller.getFriends(this.controller.getUser().getId());
		for(int i=0;i<Friends.size();i++) {
			int b=i;
			Friends.get(i).getDelete().setOnAction(event ->{this.controller.DeleteFriend(Friends.get(b).getId(),tabIndex);});
		}
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Friends");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 0,0);
		TableColumn<FRequest, Integer> idcolumn = new TableColumn<>("ID");
		idcolumn.setMinWidth(100);
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<FRequest, String> namecolumn = new TableColumn<>("FirstName");
		namecolumn.setMinWidth(200);
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		TableColumn<FRequest, String> surnamecolumn = new TableColumn<>("LastName");
		surnamecolumn.setMinWidth(200);
		surnamecolumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		//TableColumn ID = new TableColumn("ID");
		TableColumn<FRequest, Button> DeleteColumn = new TableColumn<>("Delete");
		DeleteColumn.setMinWidth(200);
		DeleteColumn.setCellValueFactory(new PropertyValueFactory<FRequest,Button>("Delete"));

		TableView table = new TableView();
		
		 table.setItems(Friends);
table.getColumns().addAll(idcolumn,namecolumn,surnamecolumn,DeleteColumn);

table.setEditable(true);
grid.add(table, 0, 1);
return grid;

	}	
	protected GridPane getSearchOccurenceView(int tabIndex ) {
		GridPane grid = new GridPane();
	    
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		HashMap<String, Integer> locationHashmap = AuthenticationController.getLocations();
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

return grid;

	}	
	protected GridPane getItemView(int tabIndex, int itemType) {
		GridPane grid = new GridPane();
		this.prepareItemScene(grid, 2);
		User user= this.controller.getUser();
		FBItem item=user;
		
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
		return grid;
	
	}
	private void addItemLabel(Field field, Object object, ArrayList<Node> retriveFields) {
		System.out.print(object.toString());
		String fieldName=field.getName();
		String fieldType=(String) allPossibleFields.get(fieldName);
		
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
				if(fieldValue.equals("boolean") || fieldValue.equals("char")) {
					addTextLabelRow(String.valueOf(fieldValue), retriveFields);
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

	protected static String ArrayListToString(ArrayList<String> lista) {
		String s= new String();
		if(lista!=null) {
			for (int index=0; index<lista.size(); index++) {
				String item=lista.get(index);
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
	protected static void addLocationField(Location location, ArrayList<Node> nodes ) {
		HashMap<String, Integer> locationHashmap = AuthenticationController.getLocations();
		String [] locations= AuthenticationController.convert(locationHashmap.keySet());
		int locationIndex=0;
		if(location!=null) {
			locationIndex= location.getId();
		}
		ComboBox hometownBox = new ComboBox(FXCollections.observableArrayList(locations)); 
		hometownBox.getSelectionModel().select(locationIndex);
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

	
	protected static void addItemField(Field field, Object object, ArrayList<Node> retriveFields) throws IllegalArgumentException, IllegalAccessException {
		String fieldName=field.getName();
		String fieldType=(String) allPossibleFields.get(fieldName);
		
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
//			case "char":
//				if(field.getName().equals("gender")) {
//				addGenderField((char)field.get(object),retriveFields);
//				}
//			break;
			case "ArrayList<String>":
				addTextFieldRow(ArrayListToString((ArrayList<String>) field.get(object)),retriveFields);
			break;
			case "Date":
				addDateField((Date)field.get(object),retriveFields);
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
			default:
				System.out.print("there was a new field "+field.getName() );
			break;

		}

	}
	
	private static Object retriveDataFromField(Object object, Field field,Node node) {
		String fieldName=field.getName();
		String fieldType=(String) allPossibleFields.get(fieldName);
		
		switch(fieldType) {
			case "String":
				return ((TextField) node).getText();
		case "int" :
				return Integer.parseInt(((TextField) node).getText());
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
			case "ArrayList<String>":
				return new ArrayList<String>(Arrays.asList(((TextField) node).getText().split("\\*",0)));
		case "Date":
				return Date.valueOf( ((DatePicker)node).getValue());
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
				HashMap<String, Integer> locationHashmap = AuthenticationController.getLocations();
				String strLocation= (String) ((ComboBox)node).getValue();
				Location hometown=new Location(locationHashmap.get(strLocation), strLocation);
			break;
			default:
				System.out.print("there was a new field "+field.getName() );
			break;
		}
		return null;
	}
	
	private static ArrayList<Object> getDataFromFields(Object object,ArrayList<Field> fields, ArrayList<Node> retriveFields){
		 ArrayList<Object> data= new ArrayList<Object>();
		 for (int fieldIndex=0; fieldIndex<retriveFields.size(); fieldIndex++) {
			 data.add(retriveDataFromField(object, fields.get(fieldIndex), retriveFields.get(fieldIndex)));
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
	
	protected GridPane getFormView(int tabIndex, int formType) {
		GridPane grid = new GridPane();
		this.prepareItemScene(grid, formType);
		User user= this.controller.getUser();
		Object object=user;
		
		Field[] all_fields = object.getClass().getDeclaredFields();
//		ArrayList<Field> fields= getNonSensitiveFields(object, all_fields);
		ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

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
			switch( formType) {
			  case 1:
			    User updateduser = new User(newData);
			    this.controller.UpdateUser(updateduser);
				System.out.print(updateduser.toString());
			    break;
			  case 2:
			    // code block
			    break;
			  case 3:
				    // code block
				    break;
			  case 4:
				    // code block
				    break;
			  case 5:
				    // code block
				    break;
			  case 6:
				    // code block
				    break;
			  default:
			    // code block
			}
			
			
		});
		return grid;
	}

	private void prepareItemScene(GridPane grid, int formType) {
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = null;
		User myUser=this.controller.getUser();
		switch(formType) {
		case 2:
			scenetitle = new Text(myUser.getUsername()+ "'s Almum");
		break;
		case 3:
			scenetitle = new Text(myUser.getUsername()+ "'s Picture");
		break;
		case 4:
			scenetitle = new Text(myUser.getUsername()+ "'s Video");
		break;
		case 5:
			scenetitle = new Text(myUser.getUsername()+ "'s Link");
		break;
		case 6:
			scenetitle = new Text(myUser.getUsername()+ "'s Event");
		break;
		}
		
		scenetitle = new Text(this.controller.getUser().getUsername()+"'s Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);
		Button logOutButton = new Button("Log Out");
		logOutButton.setOnAction(event->{
			this.controller.logOut();
		});
		grid.add(logOutButton, 10,0);
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
		grid.add(logOutButton, 0,0);
	}
	protected GridPane mostPopularFriendsVuew(
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
return grid;
	}
	protected GridPane SameInterestsFriendsVuew(
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
return grid;
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
		HashMap<String, Integer> locationHashmap = AuthenticationController.getLocations();
		String [] locations= AuthenticationController.convert(locationHashmap.keySet());
        // how does this work 
return grid;
	}

	

}
