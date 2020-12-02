package application;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Picture extends FBItem{
	protected int id;
	protected double width;
	protected double height;
	protected String link;
	protected String source;
	protected Privacy privacy;
	protected ArrayList<Comment> comments;
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	public Picture(int id, double width, double height, String link, String source, Privacy privacy,
			ArrayList<Comment> comments) {
		super("this is a picture");
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

	public Picture(ArrayList<Object> newData, Picture object) {
		super("this is a picture");

		Field[] all_fields = this.getClass().getDeclaredFields();
		int newDataIndex=0;
		for (int field_index = 0; field_index < all_fields.length; field_index++) {
			try {
				Field currentField=all_fields[field_index];
				if(UserView.is_field_sensitive(currentField.getName())) {
					currentField.set(this,currentField.get(object));
				}
				else
					currentField.set(this,newData.get(newDataIndex++));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
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
