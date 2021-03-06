package application;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PictureAlbum extends FBItem{
	protected int id;
	protected String name;
	protected String description;
	protected String link;
	protected ArrayList<Picture> pictures;
	protected Location LocationTaken; 
	protected int user_id;
	protected Privacy privacy;
	protected ArrayList<Comment> comments;
	
	//newer constructor with fields missing

	
	public PictureAlbum(int id, String name, String description, String link, ArrayList<Picture> pictures,
			Location location, int user_id, Privacy privacy, ArrayList<Comment> comments) {
		super("this is an album");
		this.id = id;
		this.name = name;
		this.description = description;
		this.link = link;
		this.pictures = pictures;
		this.LocationTaken = location;
		this.user_id = user_id;
		this.privacy = privacy;
		this.comments = comments;
	}


	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public PictureAlbum(int id, String name, String description, String link,
			ArrayList<Picture> pictures) {
		super("this is an almbum");

		this.id=id;
		this.name=name;
		this.description=description;
		this.link=link;
		this.pictures=pictures;
	}

	public PictureAlbum() {
		super("this is an almbum");
}
	
	public PictureAlbum(ArrayList<Object> newData, PictureAlbum object) {
		super("this is an almbum");

		Field[] all_fields = this.getClass().getDeclaredFields();
		int newDataIndex=0;
		for (int field_index = 0; field_index < all_fields.length; field_index++) {
			try {
				Field currentField=all_fields[field_index];
				if(HelperFunctions.is_field_sensitive(currentField.getName())) {
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



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	

}
