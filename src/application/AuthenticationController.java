package application;

import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthenticationController {
	AuthenticationView view;
	AuthenticationModel model;

	public AuthenticationController(Stage primaryStage) {
		this.view = new AuthenticationView(primaryStage);
		this.model = new AuthenticationModel();
		this.view.setController(this);
		this.model.setController(this);
	}

	public void showRegisterView() throws FileNotFoundException {
		Scene newScene = new Scene(this.view.getRegisterView());
		this.view.primaryStage.setScene(newScene);
		this.view.primaryStage.show();
	}

	public void showLogInView() throws FileNotFoundException {
		Scene newScene = new Scene(this.view.getLogInView());
		this.view.primaryStage.setScene(newScene);
		this.view.primaryStage.show();
	}

	public void signIn(String username, String password) throws FileNotFoundException {
//		User user=this.model.getUser(username);
//		if (User.getPassword().equals(password)) {
//			if(User.isAdmin()==true) {
//				Admin
//			}
//			else {
//				
//			}
//		}
//		else {
//			//oste na men tou pis oti exi tuto to username. gia na spamari meta. gia safty
//			AdminController.displayPopUp("There is no user with this username or with this password\n");
//			// TODO Auto-generated method stub
//		}
//		AdminController.displayPopUp("There is no user with this username or with this password\n");
//		User user = new User(0, "Giannis", "password1234", "hiBOI@ucy.ac.cy", "98999999", false, "kostis", "andreou");
//		 UserController.startController(this.view.primaryStage, user);
//		AdminController.startController(this.view.primaryStage, user);
	}

	public static void startController(Stage primaryStage) throws FileNotFoundException {
		AuthenticationController controller = new AuthenticationController(primaryStage);
		controller.view.startView();
	}

	public void signUp() throws FileNotFoundException {
		// other stuff
		this.showLogInView();
	}
}
