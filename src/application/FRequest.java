package application;

import javafx.scene.control.Button;

public class FRequest{
     private int id;
     private String FirstName;
     private String LastName;
     private Button Add ;
     private Button Delete ;
     private Button Ignore ;
public FRequest(int ID, String Name , String SurName , UserController use ) {
	this.id=ID;
	this.FirstName=Name;
	this.LastName=SurName;
	this.setAdd(new Button("Add"));
	this.setDelete(new Button("Delete"));
	this.setIgnore(new Button("Ignore"));
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


public Button getAdd() {
	return Add;
}


public void setAdd(Button add) {
	Add = add;
}


public Button getDelete() {
	return Delete;
}


public void setDelete(Button delete) {
	Delete = delete;
}


public Button getIgnore() {
	return Ignore;
}


public void setIgnore(Button ignore) {
	Ignore = ignore;
}




}