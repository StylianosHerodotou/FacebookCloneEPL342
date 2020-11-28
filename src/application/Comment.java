package application;

public class Comment {
	
	int id;
	Object item;
	User commenter;
	String comment;
	
	public Comment(int id, User commenter, String commnet,Video item) {
		this.id=id;
		this.commenter=commenter;
		this.comment=commnet;
		this.item=item;
	}
	public Comment(int id, User commenter, String commnet,Picture item) {
		this.id=id;
		this.commenter=commenter;
		this.comment=commnet;
		this.item=item;
	}
	public Comment(int id, User commenter, String commnet,PictureAlbum item) {
		this.id=id;
		this.commenter=commenter;
		this.comment=commnet;
		this.item=item;
	}
	
	
	

}
