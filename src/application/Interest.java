package application;

public class Interest extends FBItem{
	private int id;
	private String name;
	
	public Interest(int id, String name) {
		super("this is an interest ");
		this.id=id;
		this.name=name;
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
	
	
	

}
