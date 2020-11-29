package application;

import javafx.scene.control.Button;

public class Friend{
     private int id;
     private String FirstName;
     private String LastName;
     private Button Delete ;
public Friend(int ID, String Name , String SurName  ) {
	this.id=ID;
	this.FirstName=Name;
	this.LastName=SurName;
	this.setDelete(new Button("Delete"));
}


public String getFirstName() {
	return FirstName;
}


public void setFirstName(String firstName) {
	FirstName = firstName;
}


public String getLastName() {
	return LastName;
}


public void setLastName(String lastName) {
	LastName = lastName;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}



public Button getDelete() {
	return Delete;
}


public void setDelete(Button delete) {
	Delete = delete;
}






}