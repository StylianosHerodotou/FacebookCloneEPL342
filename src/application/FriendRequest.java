package application;
import java.sql.Date;

public class FriendRequest {
	
	User senter;
	User receiver;
	Date sentDate;
	
	public FriendRequest(User senter, User receiver, Date sentDate) {
		this.senter=senter;
		this.receiver=receiver;
		this.sentDate=sentDate;
	}

}
