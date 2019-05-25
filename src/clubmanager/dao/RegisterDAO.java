/**
 * 
 */
package clubmanager.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties; 
import clubmanager.ui.*;
import clubmanager.core.Member;

/**
 * @author NAHED
 *
 */
public class RegisterDAO {

	public Connection myConn;
	public Register register;
	/*
	String lblUsername1;
	String lblPass11;
	String lblFirstName1;
	String lblLastName1;
	String lblEmail1;
	*/
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public RegisterDAO() throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		try {
			myConn = DriverManager.getConnection(dburl, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("DB connection successful to: " + dburl);
	}

	public void addMember(String a,String b,String c,String d,String e) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into members"
					+ " (login, password, first_name, last_name,e_mail)"
					+ " values (?, ?, ?, ?, ?)");
			
			
			// set params
			myStmt.setString(1, a);
			myStmt.setString(2, b);
			myStmt.setString(3, c);
			myStmt.setString(4, d);
			myStmt.setString(5, e);
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();// close without parameters what to do ? close() is already defined
		}

		if (myStmt != null) {//que faire ? //don't close
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	public static void main(String[] args) throws Exception {
		
		RegisterDAO Registerdao = new RegisterDAO();
		Registerdao.addMember(" a", "b", "c", "d", "e@sdfs.com");//this is just a try

	}
}
