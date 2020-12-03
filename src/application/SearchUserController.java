package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchUserController {
	SearchUserView view;
	SearchUserModel model;

	public static void startController(Stage primaryStage) {
		SearchUserController controller = new SearchUserController(primaryStage);
		controller.view.startView();
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
	
	public static HashMap<Integer, String> getIntToStringLocations() {
		ResultSet result= SearchUserModel.getLocations();
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

	public static String[] convert(Set<String> setOfString) {

		// Create String[] of size of setOfString
		String[] arrayOfString = new String[setOfString.size()];

		// Copy elements from set to string array
		// using advanced for loop
		int index = 0;
		for (String str : setOfString)
			arrayOfString[index++] = str;

		// return the formed String[]
		return arrayOfString;
	}
	
	public SearchUserController(Stage primaryStage) {
		this.view = new SearchUserView(primaryStage);
		this.model = new SearchUserModel();
		this.view.setController(this);
		this.model.setController(this);
	}

	public void showSearchUserView() {
		Scene newScene = new Scene(this.view.getSearchView(), 800, 700);
		this.view.primaryStage.setScene(newScene);
		this.view.primaryStage.show();
	}

	public static void displayPopUp(String message) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Pop Up Window");
		Label label1 = new Label(message);
		Button button1 = new Button("Close");
		button1.setOnAction(e -> popupwindow.close());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label1, button1);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 300, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();

	}

	

	public User[] searchUsers (User newUser) {
		User[] users = this.model.searchUsers(newUser);
		if (users==null) {
			String message = "Could not find Users";
			displayPopUp(message);
			return null;
		}
		
		return users;
		
		

	}

}
