package application;

public class Location extends FBItem{
	protected int id;
	protected String name;
	
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

	public Location(int id, String name) {
		super("this is a location");

		this.id=id;
		this.name=name;
	}
	
	public String toString() {
		return this.name;
	}
}
