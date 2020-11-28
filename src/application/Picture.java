package application;

import java.util.ArrayList;

public class Picture extends FBItem{
	private int id;
	private double width;
	private double height;
	private String link;
	private String source;
	private String privacy;
	private ArrayList<Comment> comments;
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	public Picture(int id, double width, double height, String link, String source, String privacy,
			ArrayList<Comment> comments) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.link = link;
		this.source = source;
		this.privacy = privacy;
		this.comments = comments;
	}

	public Picture(int id, double width, double height, String link, String source) {
		super("this is a picture");
		this.id=id;
		this.width=width;
		this.height=height;
		this.link=link;
		this.source=source;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
