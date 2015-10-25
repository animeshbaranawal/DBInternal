package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String getName(String User,String Pass) {
		Connection connection=null;
		
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select name from users where user_id=? and password=?");
			pstmt.setString(1, User);
			pstmt.setString(2, Pass);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next())return rs.getString(1);
			else return "";
			
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting name");
		} finally{
			closeConnection(connection);
		}
		return "";
	}
	
	public static Boolean insert(String name,String user,String email,String pass,String dob,String phone,String gender){
		Connection connection=null;
		
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("insert into users values (?,?,?,?,?,?::gender,?)");
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, gender);
			pstmt.setDate(7, java.sql.Date.valueOf(dob));
			pstmt.executeUpdate();
			closeConnection(connection);
			return true;
			
		} catch(SQLException sqle){
			System.out.println("SQL exception when inserting user"+sqle.getMessage());
			closeConnection(connection);
			return false;
		} finally{
			closeConnection(connection);
		}
	}
	
	public static List<String> searchName(String User) {
		Connection connection=null;
		List<String> result=new ArrayList<String>();
		
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select user_id, name, email from users where name like ?");
			pstmt.setString(1, "%"+User+"%");
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()){ 
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
			}
			
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting name");
		} finally{
			closeConnection(connection);
		}
		//System.out.println(result.size());
		return result;
	}
	
	public static List<String> searchID(String User_id) {
		Connection connection=null;
		List<String> result=new ArrayList<String>();
		
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select user_id, name, email from users where user_id=?");
			pstmt.setString(1, User_id);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()){ 
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
			}
			
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting user_id");
		} finally{
			closeConnection(connection);
		}
		return result;
	}
	
	public static List<String> searchemail(String email) {
		Connection connection=null;
		List<String> result=new ArrayList<String>();
		
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select user_id, name, email from users where email=?");
			pstmt.setString(1, email);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()){ 
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
			}
			
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting email");
		} finally{
			closeConnection(connection);
		}
		return result;
	}
	
	public static List<String> timeline(String user_id) {
		Connection connection=null;
		List<String> result=new ArrayList<String>();
		
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select time_ins, description from owner natural join"
					+ " memory where owner.user_id=? order by time_ins limit 10");
			pstmt.setString(1, user_id);
			ResultSet rs= pstmt.executeQuery();
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
			while(rs.next()){ 
				result.add(df.format(rs.getDate(1)));
				result.add(rs.getString(2));
			}
			
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting email");
		} finally{
			closeConnection(connection);
		}
		return result;
	}
	
	static Connection getConnection() {
		String dbURL = "jdbc:postgresql://10.105.1.12/cs387";
        String dbUser = "db130050020";
        String dbPass = "chandr@soni";
        Connection connection=null;
        try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        } catch(ClassNotFoundException cnfe){
        	System.out.println("JDBC Driver not found");
        } catch(SQLException sqle){
        	System.out.println("Error in getting connetcion from the database");
        }
        
        return connection;
	}
	
	static void closeConnection(Connection connection) {
		try{
			connection.close();
		} catch(SQLException sqle) {
			System.out.println("Error in close database connetcion");
		}
	}
}