package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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

	static String dbConnString = "jdbc:sqlserver://mssql.cs.ucy.ac.cy;user=pchris08;password=9NWxfehB;";
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
			if (conn == null) {
				conn = DriverManager.getConnection(dbConnString);
				conn.setAutoCommit(true);
				conn.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
			}
			else if (conn.isClosed()) {
				conn = DriverManager.getConnection(dbConnString);
				conn.setAutoCommit(true);
				conn.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
			}
			return true;
		} catch (SQLException e) {
			System.out.print("Cannot connect to the DB!\nGot error: ");
			System.out.print(e.getErrorCode());
			System.out.print("\nSQL State: ");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public static boolean disconnectToDB() {
		try {
			if (!conn.isClosed()) {
				System.out.print("Disconnecting from database...");
				conn.close();
				System.out.println("Done\n\nBye !");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
	public static String translateArrayListToString(ArrayList<String> lista) {
		String ans= new String();
		for(int index=0; index<lista.size(); index++) {
			String word=lista.get(index);
			if(index==lista.size()-1)
				ans=ans+word;
			else
				ans=ans+word+"&&&";
		}
		return ans;
	}
	
	public boolean registerUser(User newUser) {
		CallableStatement cstmt=null;
		try {
//			 int id=newUser.id;
			 String firstName= newUser.firstName;
			 String lastName=newUser.lastName;
			 String email=newUser.email;
			 String website=newUser.website;
			 String link=newUser.link;
			 Date birthday=newUser.birthday;
			 boolean gender=newUser.gender;
			 String  workedFor=translateArrayListToString(newUser.workedFor);
			 String  educationPlaces=translateArrayListToString(newUser.educationPlaces);
			 String  quotes=translateArrayListToString(newUser.quotes);
			 boolean isVerified=newUser.isVerified;
			 int hometownFK=newUser.hometown.getId();
			 int livesInLocationFK= newUser.livesInLocation.getId();
			//newer with pass and username
			 String username=newUser.username;
			 String password=newUser.password;
			
			
				cstmt = conn.prepareCall("{call dbo.DoesUserWithThisUsernameExists(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?)}");
				int columnIndex=1;		
				cstmt.setString(columnIndex++, firstName);
				cstmt.setString(columnIndex++, lastName);
				cstmt.setString(columnIndex++, username);
				cstmt.setString(columnIndex++, password);
				cstmt.setString(columnIndex++, email);
				cstmt.setString(columnIndex++, website);
				cstmt.setString(columnIndex++, link);
				cstmt.setDate(columnIndex++, birthday);
				cstmt.setBoolean(columnIndex++, gender);
				cstmt.setString(columnIndex++, workedFor);
				cstmt.setString(columnIndex++, educationPlaces);
				cstmt.setString(columnIndex++, quotes);
				cstmt.setBoolean(columnIndex++, isVerified);
				cstmt.setInt(columnIndex++, hometownFK);
				cstmt.setInt(columnIndex++, livesInLocationFK);

				
				cstmt.registerOutParameter(columnIndex, java.sql.Types.BIT);
				cstmt.execute();
				if(cstmt.getInt(columnIndex)==1) {
					return true;
				}
				else {
					return false;
				}
		}
		 catch (SQLException e) {
				return false;
			}
		finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean doesAnyUserWithUsernameExistInDB(String username) {
		CallableStatement cstmt=null;
		try {
		cstmt = conn.prepareCall("{call DoesUserWithThisUsernameExists(?,?)}");
				cstmt.setString(1, username);
				cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
				cstmt.execute();

				if(cstmt.getInt(2)==1) {
					AuthenticationController.displayPopUp("He exists");
					return true;
				}
				else {
					AuthenticationController.displayPopUp("He does not exists");
					return false;
				}
		}
		 catch (SQLException e) {
			 e.printStackTrace();
			}
		finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public ResultSet getLocations() {
		String SPsql = "EXEC retrieveLocations ";   // for stored proc taking 2 parameters
		ResultSet resultSet=null;
		try {
		PreparedStatement ps = conn.prepareStatement(SPsql);
		ps.setEscapeProcessing(true);
		resultSet = ps.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		e.printStackTrace();
	}
		return resultSet;
	}
}
