/**
 * ekames mono sindesis tou event ke interests.
 */
package application;
import java.lang.reflect.Field;
import java.sql.Date;

import java.util.ArrayList;

import javafx.scene.control.Label;
public class User {
	protected int id;
	
	// na ta valume tuta?
	protected String username;
	protected String password;
	///
	
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String website;
	protected String link;
	protected Date birthday;
	protected char gender;
	protected ArrayList<String> workedFor;
	protected ArrayList<String> educationPlaces;
	protected ArrayList<String> quotes;
	protected boolean isVerified;
	protected Location hometown;
	protected Location livesInLocation;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public ArrayList<String> getWorkedFor() {
		return workedFor;
	}

	public void setWorkedFor(ArrayList<String> workedFor) {
		this.workedFor = workedFor;
	}

	public ArrayList<String> getEducationPlaces() {
		return educationPlaces;
	}

	public void setEducationPlaces(ArrayList<String> educationPlaces) {
		this.educationPlaces = educationPlaces;
	}

	public ArrayList<String> getQuotes() {
		return quotes;
	}

	public void setQuotes(ArrayList<String> quotes) {
		this.quotes = quotes;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Location getHometown() {
		return hometown;
	}

	public void setHometown(Location hometown) {
		this.hometown = hometown;
	}

	public Location getLivesInLocation() {
		return livesInLocation;
	}

	public void setLivesInLocation(Location livesInLocation) {
		this.livesInLocation = livesInLocation;
	}

	public User(int id, String firstName, String lastName, String email,
			String webiste, String link, Date birthday, char gender,
			ArrayList<String> workedFor, ArrayList<String> educationPlaces, 
			ArrayList<String> quotes,boolean isVerified, Location hometown, Location livesInLocation) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.website=webiste;
		this.link=link;
		this.birthday=birthday;
		this.gender=gender;
		this.workedFor=workedFor;
		this.educationPlaces=educationPlaces;
		this.quotes=quotes;
		this.isVerified=isVerified;
		this.hometown=hometown;
		this.livesInLocation=livesInLocation;
	}
	
	public User(int id, String firstName, String lastName, String email,
			String webiste, String link, Date birthday, char gender,
			ArrayList<String> workedFor, ArrayList<String> educationPlaces, 
			ArrayList<String> quotes,boolean isVerified, Location hometown,
			Location livesInLocation, String username, String password) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.website=webiste;
		this.link=link;
		this.birthday=birthday;
		this.gender=gender;
		this.workedFor=workedFor;
		this.educationPlaces=educationPlaces;
		this.quotes=quotes;
		this.isVerified=isVerified;
		this.hometown=hometown;
		this.livesInLocation=livesInLocation;
		this.username=username;
		this.password=password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String firstName, String lastName, String email,
			String webiste, String link, Date birthday, char gender,
			ArrayList<String> workedFor, ArrayList<String> educationPlaces, 
			ArrayList<String> quotes,boolean isVerified, Location hometown,
			Location livesInLocation, String username, String password) {
		this.id=-1;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.website=webiste;
		this.link=link;
		this.birthday=birthday;
		this.gender=gender;
		this.workedFor=workedFor;
		this.educationPlaces=educationPlaces;
		this.quotes=quotes;
		this.isVerified=isVerified;
		this.hometown=hometown;
		this.livesInLocation=livesInLocation;
		this.username=username;
		this.password=password;
	}
	
	public User(String Username, String password) {
		this.username=Username;
		this.password=password;
	}
	
	public String toString() {
		String ans="";
		Field[] all_fields = User.class.getDeclaredFields();
		for (int i = 0; i < all_fields.length; i++) {
			String field_name = all_fields[i].getName();
			Object fieldValue = null;
			try {
				fieldValue = all_fields[i].get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ans=ans+field_name;
			if(fieldValue!=null)
			{
				ans=ans+": "+ fieldValue.toString()+", ";
			}else {
				ans=ans+": null,";
			}
		}
		return ans;
	}
	
	

}
