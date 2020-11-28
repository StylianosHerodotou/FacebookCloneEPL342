package application;

import java.io.FileInputStream;
import java.lang.reflect.Field;
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
		fields.put("gender", "char");
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
	
	protected void getResultsView(GridPane grid, ArrayList<Object> objects) {
		// set dimentions of the grid.
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical
		initXlevel = 0;
		initYlevel = 2;
		for (int objectIndex = 0; objectIndex < objects.size(); objectIndex++) {
			Object object = objects.get(objectIndex);
//			grid.add(new Label(object.getClass().getSimpleName()+" "+object.), initXlevel, objectIndex + initYlevel);
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

//			Button showButton = new Button("Show Password");
//			showButton.setOnAction(event -> {
//				this.controller.showUserPassword(user);
//			});
//			grid.add(showButton, rowCount + initXlevel + 1, objectIndex + initYlevel); // adding 2 because i start from
//																						// (2,3)
//
//			if (user.isAdmin() == true) {
//				Button removeAdminCap = new Button("Remove Admin Capabilities");
//				removeAdminCap.setOnAction(event -> this.controller.removeAdminCapabilities(tabIndex, user.id));
//				grid.add(removeAdminCap, rowCount + initXlevel + 2, objectIndex + initYlevel); // adding 2 because i
//																								// start from (2,3)
//			} else {
//				Button giveAdminCap = new Button("Give Admin Capabilities");
//				giveAdminCap.setOnAction(event -> this.controller.giveAdminCapabilities(tabIndex, user.id));
//				grid.add(giveAdminCap, rowCount + initXlevel + 2, objectIndex + initYlevel); // adding 2 because i start
//																							// from (2,3)
//			}
//			Button fireUserButton = new Button("Fire User");
//			fireUserButton.setOnAction(event -> this.controller.fireUser(tabIndex, user.id));
//			grid.add(fireUserButton, rowCount + 3 + initXlevel, objectIndex + initYlevel); // adding 2 because i start
//																							// from (2,3)
//		}
//		Button logout = new Button("Logout");
//		grid.add(logout, rowCount + 3, 0);
		return grid;
	
	}
	protected GridPane getFriendRequestView(int tabIndex ) {
		GridPane grid = new GridPane();
		ArrayList <String> FriendRequests = this.controller.getFriendRequests(this.controller.getUser().getId());
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Friend Requests");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 9, 1);
	    
	}
	protected GridPane getItemView(int tabIndex, int itemType) {
		GridPane grid = new GridPane();
		this.prepareItemScene(grid, 2);
		User user= this.controller.getUser();
		Object object=user;
		
		Field[] all_fields = object.getClass().getDeclaredFields();
		ArrayList<Field> fields= getNonSensitiveFields(object, all_fields);
//		ArrayList<Field> fields= getBothSensitiveAndNonSensitiveFields(object, all_fields);

		initXlevel = 0;
		initYlevel = 2;
		ArrayList<Node> retriveFields = new ArrayList<Node>();
		for (int field_index = 0; field_index < fields.size(); field_index++) {
				try {
					Field currentField=fields.get(field_index);
					this.addItemLabel(currentField,object, retriveFields);
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
		


//		switch(fieldType) {
//			case "String":
//				addTextLabelRow((String) field.get(object),retriveFields);
//			break;
//			case "int" :
//				addTextLabelRow(Integer.toString((int)field.get(object)),retriveFields);
//			break;
//			case "char":
//				if(field.getName().equals("gender")) {
//					addTextLabelRow(String.valueOf(((char)field.get(object))),retriveFields);
//				}
//			break;
//			case "ArrayList<String>":
//				addTextLabelRow(ArrayListToString((ArrayList<String>) field.get(object)),retriveFields);
//			break;
//			case "Date":
//				addTextLabelRow(((Date)field.get(object)).toString(),retriveFields);
//			break;
//			case "boolean":
//				addTextLabelRow(String.valueOf((boolean )field.get(object)),retriveFields);
//			break;
//			case "Location":
//				addTextLabelRow((Location)field.get(object),retriveFields);
//			break;
//			default:
//				System.out.print("there was a new field "+field.getName() );
//			break;

		
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
	
	protected static void addGenderField( char fieldValue,ArrayList<Node> nodes ) {
		String[] genders= {"Male","Female"};
		ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders)); 
		if(fieldValue=='M') {
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

		switch(fieldType) {
			case "String":
				addTextFieldRow((String) field.get(object),retriveFields);
			break;
			case "int" :
				addTextFieldRow(Integer.toString((int)field.get(object)),retriveFields);
			break;
			case "char":
				if(field.getName().equals("gender")) {
				addGenderField((char)field.get(object),retriveFields);
				}
			break;
			case "ArrayList<String>":
				addTextFieldRow(ArrayListToString((ArrayList<String>) field.get(object)),retriveFields);
			break;
			case "Date":
				addDateField((Date)field.get(object),retriveFields);
			break;
			case "boolean":
				addIsVerifiedField((boolean )field.get(object),retriveFields);
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
		case "char":
				if(field.getName().equals("gender")) {
					String strGender=(String) ((ComboBox)node).getValue();
					char gender='F';
					if(strGender.equals("Male")) {
						gender='M';
					}
					return gender;
				}
			break;
			case "ArrayList<String>":
				return new ArrayList<String>(Arrays.asList(((TextField) node).getText().split("\\*",0)));
		case "Date":
				return Date.valueOf( ((DatePicker)node).getValue());
		case "boolean":
				String isVerified= (String) ((ComboBox)node).getValue();
				if(isVerified.equals("True"))
					return true;
				else 
					return false;
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

	

}
