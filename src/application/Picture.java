package application;

public class Picture {
	private int id;
	private double width;
	private double height;
	private String link;
	private String source;
	
	public Picture(int id, double width, double height, String link, String source) {
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
