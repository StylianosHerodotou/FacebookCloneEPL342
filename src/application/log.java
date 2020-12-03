package application;

import java.sql.Timestamp;

public class log extends FBItem{
	
	int objId;
	String objName;
	String action;
	String obj_type;
	Timestamp time;
	int userId ;
	
	public log(int objId, String objName, String action, String obj_type, Timestamp time, int userId) {
		super("this is  a log");
		this.objId = objId;
		this.objName = objName;
		this.action = action;
		this.obj_type = obj_type;
		this.time = time;
		this.userId = userId;
	}
	
	

}
