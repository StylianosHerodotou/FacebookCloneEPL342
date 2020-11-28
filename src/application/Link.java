package application;

public class Link extends FBItem{
	private int id;
	private String name;
	private String URL;
	private String message;
	private String description;
	private String caption;
	
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
	

}
