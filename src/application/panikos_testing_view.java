package application;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class panikos_testing_view extends Application {

	

	private SearchUserController controller;

	public void start(Stage primaryStage) {
		try {
			this.controller = new SearchUserController(primaryStage);
			Scene scene = new Scene(this.getSearchView(), 800, 700);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Search User");
			primaryStage.show();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private GridPane getSearchView() {
			
			AuthenticationModel.connectToDB();
			

			GridPane grid = new GridPane();
			
			HashMap<String, Integer> locationHashmap = SearchUserController.getLocations();
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
				boolean gender = false;
				if (strGender.equals("Male")) {
					gender = true;
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
				
				User a = new User(firstName, lastName, email, website, link, birthday, gender, workedForPlaces, educationPlaces, quotes, isVerified, hometown, livesIn, username, "");
				
			
			this.controller.searchUsers(a);
				
			});
			
			grid.add(SearchUserButton, xStartinglevel, ++yLevelIndex);
			
			return grid;
			
			

		}

	private static ArrayList<Field> getBothSensitiveAndNonSensitiveFields(Object object, Field[] all_fields) {
		ArrayList<Field> fields = new ArrayList<Field>();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
			fields.add(all_fields[i]);
//				grid.addColumn(fields.size(), new Label(field_name));
		}
		return fields;

	}
}
