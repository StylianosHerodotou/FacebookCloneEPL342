package application;

public class Comment extends FBItem{
	
	int id;
	FBItem item;
	User commenter;
	String comment;
	
	public Comment(int id, User commenter, String commnet,Video item) {
		super("this is a comment");
		this.id=id;
		this.commenter=commenter;
		this.comment=commnet;
		this.item=item;
	}
	public Comment(int id, User commenter, String commnet,Picture item) {
		super("this is a comment");
		this.id=id;
		this.commenter=commenter;
		this.comment=commnet;
		this.item=item;
	}
	public Comment(int id, User commenter, String commnet,PictureAlbum item) {
		super("this is a comment");
		this.id=id;
		this.commenter=commenter;
		this.comment=commnet;
		this.item=item;
	}
	
	public String toString() {
		return this.comment;
	}
	
	
	

}
