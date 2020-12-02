package application;

import javafx.scene.control.Button;

public class Friend{
     private int id;
     private String firstName;
     private String lastName;
     private Button Delete ; //NO WHAT IS THIS BOI NOOO
public Friend(int ID, String Name , String SurName  ) {
	this.id=ID;
	this.firstName=Name;
	this.lastName=SurName;
	this.setDelete(new Button("Delete"));
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
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