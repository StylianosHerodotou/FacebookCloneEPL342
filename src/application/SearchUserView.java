package application;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchUserView {
	Stage primaryStage;
	SearchUserController controller;
	TabPane tabPane;

	public SearchUserView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setController(SearchUserController controller) {
		this.controller = controller;
	}

	public void startView() {
		   Scene scene = new Scene(this.getSearchView(), 800,800);
	        this.primaryStage.setScene(scene);
	        primaryStage.setTitle("Search");
	        primaryStage.show();
	}

	public void prepareSceneSearch(GridPane grid) {

		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Registration ");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);

	}

	GridPane getSearchView() {

		GridPane grid = new GridPane();

		HashMap<String, Integer> locationHashmap = AuthenticationController.getLocations();
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

		Label genderLabel = new Label("Gender:");
		grid.add(genderLabel, xStartinglevel, yLevelIndex);
		String[] genders = { "Male", "Female" };
		ComboBox genderBox = new ComboBox(FXCollections.observableArrayList(genders));
		genderBox.getSelectionModel().selectFirst();
		grid.add(genderBox, xStartinglevel + 1, yLevelIndex++);

		Label hometownLabel = new Label("hometown:");
		grid.add(hometownLabel, xStartinglevel, yLevelIndex);
		ComboBox hometownBox = new ComboBox(FXCollections.observableArrayList(locations));
		hometownBox.getSelectionModel().selectLast();
		grid.add(hometownBox, xStartinglevel + 1, yLevelIndex++);

		Label livesInLabel = new Label("live in :");
		grid.add(livesInLabel, xStartinglevel, yLevelIndex);
		ComboBox livesInBox = new ComboBox(FXCollections.observableArrayList(locations));
		livesInBox.getSelectionModel().selectLast();
		grid.add(livesInBox, xStartinglevel + 1, yLevelIndex++);

		Label comptel = new Label("Email");
		grid.add(comptel, xStartinglevel, yLevelIndex);
		TextField emailField = new TextField();
		grid.add(emailField, xStartinglevel + 1, yLevelIndex++);

		Label comp = new Label("Website");
		grid.add(comp, xStartinglevel, yLevelIndex);
		TextField websiteField = new TextField();
		grid.add(websiteField, xStartinglevel + 1, yLevelIndex++);

		Label linkLabel = new Label("link:");
		grid.add(linkLabel, xStartinglevel, yLevelIndex);
		TextField linkField = new TextField();
		grid.add(linkField, xStartinglevel + 1, yLevelIndex++);

		Label birthdayLabel = new Label("birthday");
		grid.add(birthdayLabel, xStartinglevel, yLevelIndex);
		DatePicker datePicker = new DatePicker();
		datePicker.setValue(LocalDate.now());
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

		
		Label verifiedLabel = new Label("verified:");
		grid.add(verifiedLabel, xStartinglevel, yLevelIndex);
		String[] verified = { "true", "false" };
		ComboBox verifiedBox = new ComboBox(FXCollections.observableArrayList(verified));
		verifiedBox.getSelectionModel().selectFirst();
		grid.add(verifiedBox, xStartinglevel + 1, yLevelIndex++);	
	
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
			String link = linkField.getText();
			Date birthday = null;
			if (datePicker.getValue().equals(LocalDate.now())) {
				 birthday = null;
			} else {
				 birthday = Date.valueOf(datePicker.getValue());

			}
			
			String strGender = (String) genderBox.getValue();
			char gender = 'F';
			if (strGender.equals("Male")) {
				gender = 'M';
			}
			String tempString = workedAtField.getText();
			if (tempString.equals("")) {
				ArrayList<String> workedForPlaces = null;
			}
			ArrayList<String> workedForPlaces = new ArrayList<String>(Arrays.asList(tempString.split("\\*", 0)));
			ArrayList<String> educationPlaces = new ArrayList<String>(
					Arrays.asList(educationField.getText().split("\\*", 0)));
			ArrayList<String> quotes = new ArrayList<String>(Arrays.asList(quotesField.getText().split("\\*", 0)));
			;
			String ver = (String) verifiedBox.getValue();
			boolean isVerified = false;
			if (strGender.equals("true")) {
				isVerified = true;
			}
			
			String strHometown = (String) hometownBox.getValue();
			Location hometown = new Location(locationHashmap.get(strHometown), strHometown);
			String strLivesInLocation = (String) livesInBox.getValue();
			Location livesIn = new Location(locationHashmap.get(strLivesInLocation), strLivesInLocation);

			// na ta valume tuta?
			String username = UserNameField.getText();
			

			User newUser = new User(firstName, lastName, email, website, link, birthday, gender, workedForPlaces,
					educationPlaces, quotes, isVerified, hometown, livesIn, username, "");
			///
			this.controller.searchUser(newUser);
			
		});
		
		grid.add(SearchUserButton, xStartinglevel, ++yLevelIndex);
		
		return grid;

	}

}