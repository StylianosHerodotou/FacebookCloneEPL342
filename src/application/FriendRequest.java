package application;
import java.sql.Date;

public class FriendRequest extends FBItem{
	
	User senter;
	User receiver;
	Date sentDate;
	
	public FriendRequest(User senter, User receiver, Date sentDate) {
		super("this is a friend request");
		this.senter=senter;
		this.receiver=receiver;
		this.sentDate=sentDate;
	}

}
