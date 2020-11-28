package application;

import javafx.scene.control.Button;

public class FRequest{
     private int id;
     private String FirstName;
     private String LastName;
 


public FRequest(int ID, String Name , String SurName) {
	this.id=ID;
	this.FirstName=Name;
	this.LastName=SurName;
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

}