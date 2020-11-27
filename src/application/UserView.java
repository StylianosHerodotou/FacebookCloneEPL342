package application;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.Date;
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
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");
//		fields.put("", "String");

		
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

      Tab profileTab = new Tab("Profile",this.getProfileView(index++));
      Tab changeProfileTab = new Tab("changeProfileTab",this.getFormView(index++));

      


      tabPane.getTabs().add(profileTab);
      tabPane.getTabs().add(changeProfileTab);



	}
	protected static boolean is_field_sensitive(String filed_name) {
		String[] sensitive_info = { "password", "SSN", "isAdmin" };
		boolean is_sensitive = false;
		for (int index = 0; index < sensitive_info.length; index++) {
			if (filed_name.equals(sensitive_info[index])) {
				is_sensitive = true;
				break;
			}
		}
		return is_sensitive;
	}

	private void prepareProfileScene(GridPane grid) {
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text(this.controller.getUser().getUsername()+"'s Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);
	}
	protected GridPane getProfileView(int tabIndex) {
		GridPane grid = new GridPane();
		this.prepareProfileScene(grid);
		int xlevel=1;
		int ylevel=2;
		// set dimentions of the grid.
		grid.setHgap(8); // horizontal
		grid.setVgap(10);// vertical

		Field[] all_fields = User.class.getDeclaredFields();
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
			if (this.is_field_sensitive(field_name) == false) {
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
//			grid.add(showButton, rowCount + initXlevel + 1, user_index + initYlevel); // adding 2 because i start from
//																						// (2,3)
//
//			if (user.isAdmin() == true) {
//				Button removeAdminCap = new Button("Remove Admin Capabilities");
//				removeAdminCap.setOnAction(event -> this.controller.removeAdminCapabilities(tabIndex, user.id));
//				grid.add(removeAdminCap, rowCount + initXlevel + 2, user_index + initYlevel); // adding 2 because i
//																								// start from (2,3)
//			} else {
//				Button giveAdminCap = new Button("Give Admin Capabilities");
//				giveAdminCap.setOnAction(event -> this.controller.giveAdminCapabilities(tabIndex, user.id));
//				grid.add(giveAdminCap, rowCount + initXlevel + 2, user_index + initYlevel); // adding 2 because i start
//																							// from (2,3)
//			}
//			Button fireUserButton = new Button("Fire User");
//			fireUserButton.setOnAction(event -> this.controller.fireUser(tabIndex, user.id));
//			grid.add(fireUserButton, rowCount + 3 + initXlevel, user_index + initYlevel); // adding 2 because i start
//																							// from (2,3)
//		}
//		Button logout = new Button("Logout");
//		grid.add(logout, rowCount + 3, 0);
		return grid;
	
	}
	private void prepareEditProfileScene(GridPane grid) {
		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Edid "+ this.controller.getUser().getUsername()+"'s Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);
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
	
	protected static void addStringRow(GridPane grid, String fieldName, String fieldValue, int field_index) {
		if(fieldValue!=null)
		{
			grid.add(new Label(fieldName+": "), initXlevel + 1,
					field_index +initYlevel);// adding 2 because i start from (2,3)
			grid.add(new TextField(fieldValue), initXlevel + 2,
					field_index +initYlevel);// adding 2 because i start from (2,3)
		}else {
			grid.add(new Label(fieldName+": "), initXlevel + 1,
					field_index +initYlevel);// adding 2 because i start from (2,3)
			grid.add(new TextField(), initXlevel + 2,
					field_index +initYlevel);// adding 2 because i start from (2,3)
		}
	}
	protected static void addBooleanRow(GridPane grid, String fieldName, boolean fieldValue, int field_index) {
		
		
		Label genderLabel = new Label("Is Verified:");
		grid.add(genderLabel, initXlevel + 2,field_index +initYlevel);
		String[] genders= {"True","False"};
		ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders)); 
		if(fieldValue==true) {
			genderBox.getSelectionModel().selectFirst();
		}
		else {
			genderBox.getSelectionModel().select(1); // select second.
		}
		grid.add(genderBox, initXlevel + 2,field_index +initYlevel);
	}
	
	protected static void addGenderRow(GridPane grid,String fieldName, char fieldValue, int field_index ) {
		
		Label genderLabel = new Label("Gender:");
		grid.add(genderLabel, initXlevel + 1,field_index +initYlevel);
		String[] genders= {"Male","Female"};
		ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders)); 
		if(fieldValue=='M') {
			genderBox.getSelectionModel().selectFirst();
		}
		else {
			genderBox.getSelectionModel().select(1); // select second.
		}
		grid.add(genderBox, initXlevel + 2,field_index +initYlevel);
		
	}
	protected static void addLocationRow(GridPane grid, String fieldName,Location location, int field_index ) {
		
		HashMap<String, Integer> locationHashmap = AuthenticationController.getLocations();
		String [] locations= AuthenticationController.convert(locationHashmap.keySet());
		int locationIndex=0;
		if(location!=null) {
			locationIndex= location.getId();
		}
		
		Label hometownLabel = new Label("hometown:");
		grid.add(hometownLabel, initXlevel + 1,field_index +initYlevel);
		ComboBox hometownBox = new ComboBox(FXCollections.observableArrayList(locations)); 
		hometownBox.getSelectionModel().select(locationIndex);
		grid.add(hometownBox,  initXlevel + 2,field_index +initYlevel);
	}
	protected static void addDateRow(GridPane grid, String fieldName, Date fieldValue, int field_index) {
		Label birthdayLabel = new Label("birthday");
		grid.add(birthdayLabel,  initXlevel + 1,field_index +initYlevel);//
		if(fieldValue!=null)
		{
			DatePicker datePicker = new DatePicker();
			datePicker.setValue(fieldValue.toLocalDate());		
			grid.add(datePicker, initXlevel + 1,field_index +initYlevel);// adding 2 because i start from (2,3)
		}else {
			DatePicker datePicker = new DatePicker();
			grid.add(datePicker, initXlevel + 2,field_index +initYlevel);// adding 2 because i start from (2,3)
		}
}
	

	
	protected static void addFormRow(GridPane grid, Field field, Object object, int field_index) throws IllegalArgumentException, IllegalAccessException {
		
		String fieldName=field.getName();
		String fieldType=(String) allPossibleFields.get(fieldName);
		

		boolean isNull=false;
		if(object==null) {
			isNull=true;
		}
		if(fieldType.equals("String")) {
			String fieldValue=(String) field.get(object);
			addStringRow(grid,fieldName,fieldValue,field_index);
		}
		else if(fieldType.equals("int")) {
			String fieldValue=Integer.toString((int)field.get(object));
			addStringRow(grid,fieldName,fieldValue,field_index);
		}
		else if((fieldType.equals("char"))&&field.getName().equals("gender") ) {
			char fieldValue=(char)field.get(object);
			addGenderRow(grid, fieldName, fieldValue, field_index);
		}
		else if(fieldType.equals("ArrayList<String>")) {
			String fieldValue=ArrayListToString((ArrayList<String>) field.get(object));
			addStringRow(grid,fieldName,fieldValue,field_index);
		}
		else if(fieldType.equals("Date")) {
			Date fieldValue=(Date)field.get(object);
			addDateRow(grid,fieldName,fieldValue,field_index);
		}
		else if(fieldType.equals("boolean")) {
			boolean fieldValue=(boolean )field.get(object);
			addBooleanRow(grid,fieldName,fieldValue,field_index);
		}
		else if(fieldType.equals("Location")) {
			Location fieldValue=(Location )field.get(object);
			addLocationRow(grid,fieldName,fieldValue,field_index);
		}
		else {
			System.out.print("there was a new field "+field.getName() );
		}
	}
	
	protected GridPane getFormView(int tabIndex) {
		GridPane grid = new GridPane();
		User user= this.controller.getUser();
		Object object=user;
		
		Field[] all_fields = object.getClass().getDeclaredFields();
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
			if (this.is_field_sensitive(field_name) == false) {
				fields.add(all_fields[i]);
//				grid.addColumn(fields.size(), new Label(field_name));
			}
		}

		int initXlevel = 0;
		int initYlevel = 2;
		
			for (int field_index = 0; field_index < fields.size(); field_index++) {
					try {
						this.addFormRow(grid, fields.get(field_index),object, field_index);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		return grid;
	
	}
//	protected GridPane getFormView(int tabIndex) {
//		GridPane grid = new GridPane();
//		User user= this.controller.getUser();
//		 int id;
//		
//		// na ta valume tuta?
//		 String username= user.username;
//		 if(username=null) {
//			 this.user
//		 }
//		 String password=user.password;
//		///
//		
//		 String firstName=user.firstName;
//		 String lastName=user.lastName;
//		 String email=user.email;
//		 String website=user.website;
//		 String link=user.link;
//		 Date birthday=user.birthday;
//		 char gender=user.gender;
//		 ArrayList<String> workedFor=user.workedFor;
//		 ArrayList<String> educationPlaces=user.educationPlaces;
//		 ArrayList<String> quotes=user.quotes;
//		 boolean isVerified=user.isVerified;
//		 Location hometown=user.hometown;
//		 Location livesInLocation=user.livesInLocation;
////		switch (tabIndex) {
////		case 0: 
////			
////		case 1:
////		case 2:
////		}
//		
//		
//		this.prepareEditProfileScene(grid);
//		int xlevel=1;
//		int ylevel=2;
//		// set dimentions of the grid.
//		grid.setHgap(8); // horizontal
//		grid.setVgap(10);// vertical
//
//		int yLevelIndex=3;
//		int xStartinglevel=2;
//
//		Label name = new Label("Name :");
//		grid.add(name, xStartinglevel,yLevelIndex );
//		TextField nameField = new TextField();
//		grid.add(nameField, xStartinglevel+1, yLevelIndex++);
//		
//		Label surname = new Label("Surname :");
//		grid.add(surname, xStartinglevel, yLevelIndex);
//		TextField surnameField = new TextField();
//		grid.add(surnameField, xStartinglevel+1, yLevelIndex++);
//		
//		Label genderLabel = new Label("Gender:");
//		grid.add(genderLabel, xStartinglevel, yLevelIndex);
//		String[] genders= {"Male","Female"};
//		ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders)); 
//		genderBox.getSelectionModel().selectFirst();
//		grid.add(genderBox, xStartinglevel+1, yLevelIndex++);
//		
//		Label hometownLabel = new Label("hometown:");
//		grid.add(hometownLabel, xStartinglevel, yLevelIndex);
//		ComboBox hometownBox = new ComboBox(FXCollections.observableArrayList(locations)); 
//		hometownBox.getSelectionModel().selectFirst();
//		grid.add(hometownBox, xStartinglevel+1, yLevelIndex++);
//		
//		Label livesInLabel = new Label("live in :");
//		grid.add(livesInLabel, xStartinglevel, yLevelIndex);
//		ComboBox livesInBox = new ComboBox(FXCollections.observableArrayList(locations)); 
//		livesInBox.getSelectionModel().selectFirst();
//		grid.add(livesInBox, xStartinglevel+1, yLevelIndex++);
//		
//		Label comptel = new Label("Email");
//		grid.add(comptel, xStartinglevel, yLevelIndex);
//		TextField emailField = new TextField();
//		grid.add(emailField, xStartinglevel+1, yLevelIndex++);
//		
//		Label comp = new Label("Website");
//		grid.add(comp, xStartinglevel, yLevelIndex);
//		TextField websiteField = new TextField();
//		grid.add(websiteField, xStartinglevel+1, yLevelIndex++);
//		
//		Label linkLabel = new Label("link:");
//		grid.add(linkLabel, xStartinglevel, yLevelIndex);
//		TextField linkField = new TextField();
//		grid.add(linkField, xStartinglevel+1, yLevelIndex++);
//		
//		Label birthdayLabel = new Label("birthday");
//		grid.add(birthdayLabel, xStartinglevel, yLevelIndex);
//		DatePicker datePicker = new DatePicker();
//		grid.add(datePicker, xStartinglevel+1, yLevelIndex++);
//		
//		Label workedAtLabel = new Label("previous employment Places * symbol between employments.");
//		grid.add(workedAtLabel, xStartinglevel, yLevelIndex);
//		TextField workedAtField = new TextField();
//		grid.add(workedAtField, xStartinglevel+1, yLevelIndex++);
//		
//		Label educationPlacesLabel = new Label("Education * symbol between employments.");
//		grid.add(educationPlacesLabel, xStartinglevel, yLevelIndex);
//		TextField educationField = new TextField();
//		grid.add(educationField, xStartinglevel+1, yLevelIndex++);
//		
//		Label quotesLabel = new Label("Quotes * symbol between employments.");
//		grid.add(quotesLabel, xStartinglevel, yLevelIndex);
//		TextField quotesField = new TextField();
//		grid.add(quotesField, xStartinglevel+1, yLevelIndex++);
//		
//		
//		Label label1 = new Label("Username:");
//		grid.add(label1, xStartinglevel, yLevelIndex); // i am starting from xStartinglevel,xStartinglevel+1
//		TextField UserNameField = new TextField();
//		grid.add(UserNameField, xStartinglevel+1, yLevelIndex++);
//		
//		Label pw = new Label("Password:");
//		grid.add(pw, xStartinglevel, yLevelIndex);
//		PasswordField passwordField = new PasswordField();
//		grid.add(passwordField, xStartinglevel+1, yLevelIndex++);
//		
//		Button button = new Button();
//		button.setText("Create account");
//		button.setOnAction(event->{
//			String firstName=nameField.getText();
//			String lastName=surnameField.getText();
//			String email=emailField.getText();
//			String website=websiteField.getText();
//			String link=linkField.getText();
//			Date birthday=Date.valueOf(datePicker.getValue());
//			String strGender=(String) genderBox.getValue();
//			char gender='F';
//			if(strGender.equals("Male")) {
//				gender='M';
//			}
//			String tempString=workedAtField.getText();
//			if(tempString==null) {
//				System.out.println("null");
//			}
//			else if(tempString.equals("")){
//			System.out.println("it is empty");
//			ArrayList<String> workedForPlaces=null;
//		}
//			ArrayList<String> workedForPlaces = new ArrayList<String>(Arrays.asList(tempString.split("\\*",0)));
//			ArrayList<String> educationPlaces=new ArrayList<String>(Arrays.asList(educationField.getText().split("\\*",0)));
//			ArrayList<String> quotes=new ArrayList<String>(Arrays.asList(quotesField.getText().split("\\*",0)));;
//			boolean isVerified=false;
//			String strHometown= (String) hometownBox.getValue();
//			Location hometown=new Location(locationHashmap.get(strHometown), strHometown);
//			String strLivesInLocation=(String) livesInBox.getValue();
//			Location livesIn=new Location(locationHashmap.get(strLivesInLocation), strLivesInLocation);
//
//			
//			// na ta valume tuta?
//			String username= UserNameField.getText();
//			String password=passwordField.getText();
//			
//			User newUser = new User(firstName, lastName, email, website,
//					link, birthday, gender,workedForPlaces,educationPlaces,quotes,
//					isVerified,hometown,livesIn, username, password);
//			///
//			
//			this.controller.registerUser(newUser);
//		});
//		grid.add(button, xStartinglevel, ++yLevelIndex);
//		return grid;
//	
//	}



	

}
