package application;

import java.util.ArrayList;

public class PictureAlbum extends FBItem{
	private int id;
	private String name;
	private String description;
	private String link;
	private ArrayList<Picture> pictures;
	private ArrayList<Comment> comments;
	
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
