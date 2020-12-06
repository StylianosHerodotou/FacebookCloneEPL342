package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class handle_events {

//	public static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
//		if (!resultSet.isBeforeFirst())
//			return true;
//		else
//			return false;
//	}
//
//	public Event[] search_events(int id, String venue, String name, Date start, Date end, String descr, int creatorid,
//			int locid) {
//
//		ArrayList<Event> events = new ArrayList<Event>();
//		try {
//			ResultSet resultSet = null;
//			CallableStatement cstmt = AuthenticationModel.conn.prepareCall(
//					"{call SEARCH_USERS_TEMP2(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE,
//					ResultSet.CONCUR_READ_ONLY);
//			int columnIndex = 1;
//			cstmt.setInt(columnIndex++, id);
//			cstmt.setString(columnIndex++, venue);
//			cstmt.setString(columnIndex++, name);
//			cstmt.setDate(columnIndex++, start);
//			cstmt.setDate(columnIndex++, end);
//			cstmt.setString(columnIndex++, descr);
//			cstmt.setInt(columnIndex++, creatorid);
//			cstmt.setInt(columnIndex++, locid);
//			boolean results = cstmt.execute();
//			int rowsAffected = 0;
//
//			// Protects against lack of SET NOCOUNT in stored prodedure
//			while (results || rowsAffected != -1) {
//				if (results) {
//					resultSet = cstmt.getResultSet();
//					break;
//				} else {
//					rowsAffected = cstmt.getUpdateCount();
//				}
//				results = cstmt.getMoreResults();
//			}
//
//			if (isResultSetEmpty(resultSet))
//				return null;
//			else {
//				ArrayList<Event> a = turnresultSetToEvents(resultSet);
//				return (Event[]) a.toArray();
//			}
//		} catch (Exception e) {
//			return null;
//		}
//
//	}
}
