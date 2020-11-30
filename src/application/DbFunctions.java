package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions {
	//GET LOC HASHES
	public User convertObjArrToUser(Object[] obj) {
		char gender = 'M';
		if (!(boolean)obj[7]) {
			gender = 'F';
		}
		boolean isVerified = false;
		if (!(boolean)obj[11]) {
			isVerified = true;
		}
		return new User((int)obj[0], (String) obj[1], (String) obj[2], (String) obj[3], 
				(String) obj[4], (String) obj[5], (Timestamp) obj[6], gender, null, null, null, isVerified, hometown, livesInLocation)
				
		
	}
	public static List<Object[]> resultSetToList(ResultSet rs){
		List<Object[]> results = new ArrayList<Object[]>();
		try {
		if (rs.next() == false) {
			return null;
		} else {
			int cols = rs.getMetaData().getColumnCount();
			Object[] arr = new Object[cols];
			for (int i = 0; i < cols; i++) {
				Object obj = rs.getObject(i + 1);
				System.out.println(obj.toString());
				arr[i] = obj;
			}
			results.add(arr);
		}
		}catch (Exception e) {
			return null;
		}
		
		return results;
	}
	
	 
	private static List<Object[]> getDataFromDB(String query) {
		List<Object[]> results = new ArrayList<Object[]>();

		ResultSet rs = null;

		try {
			PreparedStatement stmt = AuthenticationModel.conn.prepareStatement(query);

			rs = stmt.executeQuery();
			int cols = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Object[] attributes = new Object[cols];
				for (int i = 0; i < cols; i++)
					attributes[i] = rs.getObject(i + 1);
				results.add(attributes); // many attributes make a record
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs == null)
					return null; // In case of a query UPDATE
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return results;

	}
	public static User covertObjectArray(Object[] temp) {
		return null;
//		return new User((int) temp[0], (String) temp[1], (String) temp[2], (String) temp[3], temp[4].toString(),
//				(boolean) (temp[7]), (String) temp[5], (String) temp[6]);
	}
}
