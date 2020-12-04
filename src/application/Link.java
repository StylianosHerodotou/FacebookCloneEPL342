package application;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Link extends FBItem{
	protected int id;
	protected String name;
	protected String URL;
	protected String message;
	protected String description;
	protected String caption;
	protected int user_id;
	public int getUser_id() {
		return user_id;
	}

	public Link(int id, String name, String uRL, String message, String description, String caption, int user_id) {
		super("this is a link");
		this.id = id;
		this.name = name;
		URL = uRL;
		this.message = message;
		this.description = description;
		this.caption = caption;
		this.user_id = user_id;
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

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
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

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	public Link(int id, String name, String URL, String message,
			String description, String caption) {
		super("this is a link");
		this.id=id;
		this.URL=URL;
		this.message=message;
		this.description=description;
		this.caption=caption;
	}

	public Link() {
		super("this is a link");
	}
	public Link(ArrayList<Object> newData, Link object) {
		super("this is a link");

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

}
