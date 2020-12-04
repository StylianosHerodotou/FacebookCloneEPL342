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
	protected int creatorID ;
	protected Privacy privacy;
	protected Location location;
	
	
	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	public Event(int id, String venue, String name, Timestamp startTime, Timestamp endTime, String description,
			int user_id, Privacy privacy, Location location) {
		super("this is an event");
		this.id = id;
		this.venue = venue;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.creatorID = user_id;
		this.location=location;
		this.privacy = privacy;
	}

public Location getLoc() {
	return this.location;
}
public void setLoc(Location loc) {
	this.location=loc;
}

	public int getUser_id() {
		return creatorID;
	}
  
	public void setUser_id(int user_id) {
		this.creatorID = user_id;
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
