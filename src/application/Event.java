package application;
import java.sql.Time;
import java.sql.Timestamp;
public class Event extends FBItem{
	private int id;
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Privacy getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	private String venue;
	private String name;
	private Timestamp startTime;
	private Timestamp endTime;
	private String description;
	private User createdBy;
	private Privacy privacy;
	private Location location;
	
	public Event(int id, String venue, String name, Timestamp startTime,
			Timestamp endTime, String description, User createdBy,
			Privacy privacy, Location location) {
		super("this is an event");

		this.id=id;
		this.venue=venue;
		this.name=name;
		this.startTime=startTime;
		this.endTime=endTime;
		this.description=description;		
		this.createdBy=createdBy;
		this.privacy=privacy;
		this.location=location;
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
