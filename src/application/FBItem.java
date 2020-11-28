package application;

public abstract class FBItem {
	
	protected String FBName;
	
	public FBItem(String name) {
		this.FBName=name;
	}
	
	protected String getFBName() {
		return this.FBName;
	}

}
