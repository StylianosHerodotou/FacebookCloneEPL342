package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserController {

	private User user;
	private UserView view;
	private UserModel model;

	public UserController(Stage primaryStage, User user) throws FileNotFoundException {
		this.view = new UserView(primaryStage);
		this.model = new UserModel();
		this.view.setController(this);
		this.view.generateTabPane();
		this.model.setController(this);
		this.user = user;
	}

	public static void startController(Stage primaryStage, User user) throws FileNotFoundException {
		UserController controller = new UserController(primaryStage, user);
		controller.view.startView();
	}


}
