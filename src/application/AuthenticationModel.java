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
import java.util.HashMap;
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

	static String dbConnString = "jdbc:sqlserver://mssql.cs.ucy.ac.cy;user=sherod01;password=B8rkpvJE;";
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
	
	public static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
		if (!resultSet.isBeforeFirst() )    
		    return true;
		else
			return false;
	}

	public ArrayList<String> getQuotesOfUser(int UserID){
		
		String SPsql = "EXEC getQuotesOfUser ?";   // for stored proc taking 2 parameters
		PreparedStatement ps;
		ResultSet resultSet=null;
		ArrayList<String> quotes = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(SPsql);
		
		ps.setEscapeProcessing(true);
		int index=1;
		ps.setInt(index++, UserID);
		resultSet= ps.executeQuery();
		while(resultSet.next()) {
			String quote=resultSet.getString("Quote");
			quotes.add(quote);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quotes;
		
	}
	
	public ArrayList<String> getWorkOfUser(int UserID){
		
		String SPsql = "EXEC getWorkOfUser ?";   // for stored proc taking 2 parameters
		PreparedStatement ps;
		ResultSet resultSet=null;
		ArrayList<String> workPlaces = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(SPsql);
		
		ps.setEscapeProcessing(true);
		int index=1;
		ps.setInt(index++, UserID);
		resultSet= ps.executeQuery();
		while(resultSet.next()) {
			String workplace=resultSet.getString("Workplace");
			workPlaces.add(workplace);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workPlaces;
		
	}
	
	public ArrayList<String> getEducationOfUser(int UserID){
		
		String SPsql = "EXEC getEducationOfUser ?";   // for stored proc taking 2 parameters
		PreparedStatement ps;
		ResultSet resultSet=null;
		ArrayList<String> educationPlaces = new ArrayList<String>();
		try {
			ps = conn.prepareStatement(SPsql);
		
		ps.setEscapeProcessing(true);
		int index=1;
		ps.setInt(index++, UserID);
		resultSet= ps.executeQuery();
		while(resultSet.next()) {
			String educationplace=resultSet.getString("Education_Place");
			educationPlaces.add(educationplace);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return educationPlaces;
		
	}
	public User CreateUser(ResultSet resultSet) throws SQLException {
		if(isResultSetEmpty(resultSet))
			return null;
		else {
			HashMap<Integer, String> locations= this.controller.getIntToStringLocations();
			
			resultSet.next();//metafero to ston xristi.
			int id=resultSet.getInt("UserID");
			String firstName= resultSet.getString("First_Name");
			String lastName=resultSet.getString("Last_Name");
			String email=resultSet.getString("Email");
			String website=resultSet.getString("Website");
			String link=resultSet.getString("Link");
			Date birthday=resultSet.getDate("Birthday");
			boolean gender=resultSet.getBoolean("Gender");
			boolean isVerified=resultSet.getBoolean("Is_verified");
			int hometownFK=resultSet.getInt("Hometown_LOC_ID");
			Location hometown=new Location(hometownFK, locations.get(hometownFK));
			int livesInLocationFK= resultSet.getInt("Current_LOC_ID");
			Location livesHereNow = new Location(livesInLocationFK, locations.get(livesInLocationFK));
			String username=resultSet.getString("Username");
			String password=resultSet.getString("pass");
			
			ArrayList<String>  workedFor=this.getWorkOfUser(id); // lathos na men ta fenri oulla
			
			ArrayList<String>  educationPlaces=this.getEducationOfUser(id);
			ArrayList<String>  quotes=this.getQuotesOfUser(id);
			//newer with pass and username
			
			User user = new User(id, firstName, lastName, email,
					website, link, birthday, gender, 
					workedFor, educationPlaces, quotes, isVerified, hometown, 
					livesHereNow, username, password);
			return user;
		}
	}
	

	public User authenticate(String username, String password) {
		String SPsql = "EXEC AuthenticateUser ?,?";   // for stored proc taking 2 parameters
		PreparedStatement ps;
		ResultSet resultSet=null;
		try {
			ps = conn.prepareStatement(SPsql);
		ps.setEscapeProcessing(true);
		int index=1;
		ps.setString(index++, username);
		ps.setString(index, password);
		resultSet= ps.executeQuery();
		return this.CreateUser(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}
	public static String translateArrayListToString(ArrayList<String> lista) {
		String ans= new String();
		for(int index=0; index<lista.size(); index++) {
			String word=lista.get(index);
			if(index==lista.size()-1)
				ans=ans+word;
			else
				ans=ans+word+"&";
		}
		return ans;
	}
	
	public boolean registerUser(User newUser) {
		CallableStatement cstmt=null;
		try {
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
			
			
				cstmt = conn.prepareCall("{call registerUser(?,?,?,?, ?,?,?,?, ?,?,?,?, ?,?,?,? )}");
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
			 System.out.print(e);
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
					return true;
				}
				else {
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
