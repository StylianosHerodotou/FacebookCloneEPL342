package application;
import java.sql.Time;
import java.sql.Timestamp;

import javafx.scene.control.Button;
public class Event extends FBItem{
	protected int id;
	protected String venue;
	protected String name;
	protected Timestamp startTime;
	protected Timestamp endTime;
	protected String description;
	protected int user_id ;
	protected int loc_id;
	protected Privacy privacy;
	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	

	

	public Event(int id, String venue, String name, Timestamp startTime, Timestamp endTime, String description,
			int user_id, int loc_id, Privacy privacy) {
		super("this is an event");
		this.id = id;
		this.venue = venue;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.user_id = user_id;
		this.loc_id = loc_id;
		this.privacy = privacy;
	}

	public int getUser_id() {
		return user_id;
	}
  
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getLoc_id() {
		return loc_id;
	}

	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
