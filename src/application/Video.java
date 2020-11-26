package application;

public class Video {
	private int id;
	private String message;
	private String description;
	private double length;
	private String source;
	
	public Video(int id, String message, String description, double length, String source) {
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

	public void setLength(double length) {
		this.length = length;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
