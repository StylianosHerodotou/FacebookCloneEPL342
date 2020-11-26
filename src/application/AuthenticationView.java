package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.sun.scenario.effect.impl.prism.PrImage;
import com.sun.security.auth.NTSidPrimaryGroupPrincipal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public  class AuthenticationView{
	Stage primaryStage;
	AuthenticationController controller;
	
	public AuthenticationView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void setController(AuthenticationController controller) {
		this.controller=controller;
	}
	
	public void startView() throws FileNotFoundException {
	    Scene scene = new Scene(this.getLogInView(), 600, 275);
        this.primaryStage.setScene(scene);
        primaryStage.setTitle("BookFace");
        primaryStage.show();
	}

	
	public void prepareSceneLogin(GridPane grid) throws FileNotFoundException {

		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Welcome to BookFace");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);


	}
	
	public GridPane getLogInView() throws FileNotFoundException {

		GridPane grid = new GridPane();
		this.prepareSceneLogin(grid);
		//grid.setAlignment(Pos.CENTER);
		//grid.setHgap(10);
		//grid.setVgap(10);
		//grid.setPadding(new Insets(25, 25, 25, 25));
		//Text scenetitle = new Text("Welcome to ATOS Help Desk");
		//scenetitle.setTextAlignment(TextAlignment.CENTER);
		//scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		//grid.add(scenetitle, 0, 0, 10, 1);

		Label userNameLabel = new Label("User Name:");
		grid.add(userNameLabel, 2, 3);

		TextField userTextField = new TextField();
		userTextField.setAlignment(Pos.CENTER);
		grid.add(userTextField, 4, 3);

		Label passwordLabel = new Label("Password:");
		grid.add(passwordLabel, 2, 4);

		PasswordField passwordField = new PasswordField();
		passwordField.setAlignment(Pos.CENTER);
		grid.add(passwordField, 4, 4);

		Button signInButton = new Button("Sign in");
		signInButton.setOnAction(event-> {
			String username =userTextField.getText();
			String password = passwordField.getText();
			try {
				this.controller.signIn(username,password);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});
		HBox horizonalBox= new HBox(50);
		horizonalBox.setAlignment(Pos.BOTTOM_RIGHT);

		Button register_btn = new Button("Sign up");
		register_btn.setOnAction(event-> {
			try {
				this.controller.showRegisterView();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} );
		horizonalBox.getChildren().add(register_btn);
		horizonalBox.getChildren().add(signInButton);
		grid.add(horizonalBox, 4, 6);
		
		
		return grid;
	}
	
	
	
	
	public void prepareSceneRegister(GridPane grid) throws FileNotFoundException {

		grid.setAlignment(Pos.BASELINE_LEFT);
		grid.setHgap(18);
		grid.setVgap(18);
		// grid.setPadding(new Insets(00, 00, 00, 00));
		Text scenetitle = new Text("Registration ");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(scenetitle, 1, 1, 10, 1);

	}
	
	
	
	
	public GridPane getRegisterView() throws FileNotFoundException {

		GridPane grid = new GridPane();
		this.prepareSceneRegister(grid);
		//lay.setPadding(new Insets(10, 10, 10, 10));
		//lay.setVgap(10);
		//lay.setHgap(10);
		// LABEL
		//Text scenetitle = new Text("Registration ");
		//lay.add(scenetitle, 0, 0, 10, 1);
		//scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label label1 = new Label("Username:");
		grid.add(label1, 2, 3); // i am starting from 2,3
		// TEXTBOX
		TextField textlabel1 = new TextField();
		grid.add(textlabel1, 3, 3);
		Label pw = new Label("Password:");
		grid.add(pw, 2, 4);
		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 3, 4);
		Label name = new Label("Name :");
		grid.add(name, 2, 5);
		// TEXTBOX
		TextField textname = new TextField();
		grid.add(textname, 3, 5);
		Label surname = new Label("Surname :");
		grid.add(surname, 2, 6);
		// TEXTBOX
		TextField textsurname = new TextField();
		grid.add(textsurname, 3, 6);
		Label comptel = new Label("Company telephone number:");
		grid.add(comptel, 2, 7);
		// TEXTBOX
		TextField textcomptel = new TextField();
		grid.add(textcomptel, 3, 7);
		Label comp = new Label("Company Name");
		grid.add(comp, 2, 8);
		// TEXTBOX
		TextField textcomp = new TextField();
		grid.add(textcomp, 3, 8);
		Label email = new Label("Email:");
		grid.add(email, 2, 9);
		// TEXTBOX
		TextField textemail = new TextField();
		grid.add(textemail, 3, 9);
		Button button = new Button();
		button.setText("Create account");
		button.setOnAction(event->{
			try {
				this.controller.signUp();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		grid.add(button, 2, 10);
		
		prepareSceneRegister(grid);
		return grid;
	}

}
