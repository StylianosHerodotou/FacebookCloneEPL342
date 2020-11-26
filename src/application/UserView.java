package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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

	Stage primaryStage;
	TabPane tabPane;
	UserController controller;

	public void setController(UserController controller) {
		this.controller = controller;
	}

	public UserView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void generateTabPane() throws FileNotFoundException {
		this.tabPane= new TabPane();
		int index=0;

//      Tab TicketsTab = new Tab("Create a Ticket",this.getViewCreateTicket(index++));


//      tabPane.getTabs().add(TicketsTab);


	}

	public void startView() {
		VBox vBox = new VBox(this.tabPane);
		Scene scene = new Scene(vBox, 600, 275);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BookFace");
		primaryStage.show();
	}



	

}
