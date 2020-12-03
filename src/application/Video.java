package application;

import java.util.ArrayList;

public class Video extends FBItem{
	protected int id;
	protected String message;
	protected String description;
	protected int length;
	protected String source;
	protected int user_id;
	protected Privacy privacy;
	protected ArrayList<Comment> comments;


	//new constr with user id
	public Video(int id, String message, String description, int length, String source, int user_id,
			Privacy privacy,ArrayList<Comment> comments) {
		super("this is a video");
		this.id = id;
		this.message = message;
		this.description = description;
		this.length = length;
		this.source = source;
		this.user_id = user_id;
		this.privacy=privacy;
		this.comments = comments;
		
	}
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	public Video(int id, String message, String description, int length, String source) {
		super("this is a video");

		this.id=id;
		this.message=message;
		this.description=description;
		this.length=length;
		this.source=source;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
