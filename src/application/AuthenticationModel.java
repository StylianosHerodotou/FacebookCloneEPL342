package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationModel {
	AuthenticationController controller;
	
	public AuthenticationModel(AuthenticationController controller) {
		this.controller= controller;
	}
	public AuthenticationModel() {
	}
	public void setController(AuthenticationController controller) {
		this.controller=controller;
	}

	static String dbConnString = "jdbc:sqlserver://localhost:1433;databaseName=LAPTOP-FOP56MFB;integratedSecurity=true;";

	static Connection conn = null;
	private static boolean dbDriverLoaded = false;

	public static boolean connectToDB() {

		if (!dbDriverLoaded)
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dbDriverLoaded = true;
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot load DB driver!");
				return false;
			}

		try {
			if (conn == null)
				conn = DriverManager.getConnection(dbConnString);
			else if (conn.isClosed())
				conn = DriverManager.getConnection(dbConnString);
		} catch (SQLException e) {
			System.out.print("Cannot connect to the DB!\nGot error: ");
			System.out.print(e.getErrorCode());
			System.out.print("\nSQL State: ");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		return true;
	}

	public static String authenticate(String username, String password) {
		List<Object[]> results = new ArrayList<Object[]>();
		ResultSet rs = null;

		try (Statement stmt = AuthenticationModel.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);) {

			String query = "SELECT *\r\n" + "FROM EMPLOYEE\r\n" + "WHERE EMP_USERNAME =" + username
					+ "AND EMP_PASSWORD =" + password;
			rs = stmt.executeQuery(query);

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
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

			}
		}

		Object[] temp = results.get(0);
		if ((boolean) temp[5])
			return "admin";
		else
			return "user";

	}

//	public static boolean register(Registration registration) {
//		// collumns
//		int count = 0;
//
//		String query = "INSERT INTO REGISTRATION (R_USERNAME,R_PASSWORD,R_PHONE,R_EMAIL,R_F_NAME,R_L_NAME)\r\n"
//				+ "VALUES (?,?,?,?,?,?)";
//
//		try (PreparedStatement stmt = AuthenticationModel.conn.prepareStatement(query);) {
//			stmt.setString(1, registration.getUsername());
//			stmt.setString(2, registration.getPassword());
//			stmt.setString(3, registration.getPhone());
//			stmt.setString(4, registration.getEmail());
//			stmt.setString(5, registration.getF_Name());
//			stmt.setString(6, registration.getL_Name());
//
//			count = stmt.executeUpdate(query);
//			stmt.close();
//
//		} catch (SQLException e) {
//			return false;
//		}
//		if (count >= 1)
//			return true;
//		else
//			return false;
//	}
}
