/**
 * ekames mono sindesis tou event ke interests.
 */
package application;
import java.lang.reflect.Field;
import java.sql.Date;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Label;
public class User extends FBItem {
	
	
	protected int id;
//	protected int user_id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String website;
	protected String link;
	protected Date birthday;
	protected boolean gender;
	protected ArrayList<String> workedFor;
	protected ArrayList<String> educationPlaces;
	protected ArrayList<String> quotes;
	protected boolean isVerified;
	protected Location hometown;
	protected Location livesInLocation;
	//newer with pass and username
	protected String username;
	protected String password;

	
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
			String webiste, String link, Date birthday, boolean gender,
			ArrayList<String> workedFor, ArrayList<String> educationPlaces, 
			ArrayList<String> quotes,boolean isVerified, Location hometown, Location livesInLocation) {
		super("this is a user");

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
			String webiste, String link, Date birthday, boolean gender,
			ArrayList<String> workedFor, ArrayList<String> educationPlaces, 
			ArrayList<String> quotes,boolean isVerified, Location hometown,
			Location livesInLocation, String username, String password) {
		super("this is a user");

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
			String webiste, String link, Date birthday, boolean gender,
			ArrayList<String> workedFor, ArrayList<String> educationPlaces, 
			ArrayList<String> quotes,boolean isVerified, Location hometown,
			Location livesInLocation, String username, String password) {
		super("this is a user");

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
		super("this is a user");

		this.username=Username;
		this.password=password;
		this.gender=false;
	}
	
	public User(ArrayList<Object> newData, User oldUser) {
		super("this is a user");

		Field[] all_fields = this.getClass().getDeclaredFields();
		int newDataIndex=0;
		for (int field_index = 0; field_index < all_fields.length; field_index++) {
			try {
				Field currentField=all_fields[field_index];
				if(UserView.is_field_sensitive(currentField.getName())) {
					currentField.set(this,currentField.get(oldUser));
				}
				else
					currentField.set(this,newData.get(newDataIndex++));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id +"firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", website=" + website + ", link=" + link
				+ ", birthday=" + birthday + ", gender=" + gender + ", workedFor=" + workedFor + ", educationPlaces="
				+ educationPlaces + ", quotes=" + quotes + ", isVerified=" + isVerified + ", hometown=" + hometown
				+ ", livesInLocation=" + livesInLocation + ", username=" + username + ", password=" + password + "]";
	}
	
	

}
