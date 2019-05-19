package clubmanager.dao;

import java.util.*;

import javax.swing.JOptionPane;

import clubmanager.core.*;
import clubmanager.ui.Login;
import clubmanager.ui.ManageInterface;
import clubmanager.ui.MemberApp;

import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

/**
 * 
 * @author souhail & abderrahim
 *
 */
public class MemberDAO {

	public Connection myConn;
	Login login;
	
	public MemberDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	
	public void deleteMember(int idMember) throws SQLException {
		PreparedStatement myStmt = null;
		//we get employeeId from THE TABLE using getValueAt(row, object)
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from members where id_member=?");
			
			// set param
			myStmt.setInt(1, idMember);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		//ou est le bloc catch ??
		finally {
			close(myStmt);
		}
	}
	
	public void updateMember(Member theMember) throws SQLException {
		PreparedStatement myStmt = null;
		//id_member login password first_name last_name e_mail
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update members"
					+ " set login=?, password=?, first_name=?, last_name=?, e_mail=?"
					+ " where id_member=?");
			// getIdMember() getLogin() getPassword() getFirstName() getLastName() getEmail()
			// set params
			myStmt.setString(1, theMember.getLogin());
			myStmt.setString(2, theMember.getPassword());
			myStmt.setString(3, theMember.getFirstName());
			myStmt.setString(4, theMember.getLastName());
			myStmt.setString(5, theMember.getEmail());
			myStmt.setInt(6, theMember.getIdMember());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addMember(Member theMember) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into members"
					+ " (login, password, first_name, last_name,e_mail)"
					+ " values (?, ?, ?, ?, ?)");
			
			// set params
			myStmt.setString(1, theMember.getLogin());
			myStmt.setString(2, theMember.getPassword());
			myStmt.setString(3, theMember.getFirstName());
			myStmt.setString(4, theMember.getLastName());
			myStmt.setString(5, theMember.getEmail());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<Member> getAllMembers() throws Exception {
		List<Member> list = new ArrayList<Member>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from members order by last_name");
			
			while (myRs.next()) {
				Member tempMember = convertRowToMember(myRs);
				list.add(tempMember);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	public void loginOrNot(String loginField1,String passwordField1)  throws SQLException {
		
	  //  Connection myConn=null;
	    ResultSet rs=null;
	    PreparedStatement pst=null;
   
	    try {
	
        pst=myConn.prepareStatement("select * from login where login=? and password=?");
        System.out.println("test");
        pst.setString(1,loginField1); 
        pst.setString(2, passwordField1);
        rs=pst.executeQuery();
       if (rs.next()){
        	String validLogin=rs.getString(1);
        	String validPassword=rs.getString(2);
            JOptionPane.showMessageDialog(null, "Log in Successful.");
        	/*
            rs.close();
            pst.close();   
            myConn.close();
		*///	if (!loginField1.equals(validLogin) || !passwordField1.equals(validPassword)) {
	
        	ManageInterface frame = new ManageInterface();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
			}
			else {
			JOptionPane.showMessageDialog(null, "The Login you entererd or the password is incorrect. Try again");
			
		}
     
        }

	    catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
        
    
	/*
	public void loginOrNot(String userLogin, String userPassword) throws SQLException {
		System.out.println("test");
		Statement myStmt = myConn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from members where login= ? and password=? ");
	
			Member tempMember = convertRowToMember(myRs);
			String validLogin=tempMember.getLogin();
			String validPassword =tempMember.getPassword();
			
		if (!userLogin.equals(validLogin) || !userPassword.equals(validPassword)) {

		IncorrectLoginOrPassword dialog = new IncorrectLoginOrPassword();
		dialog.setVisible(true);
		}
		else {
		
			MemberApp frame = new MemberApp();
			frame.setVisible(true);
		}	
			}
			*/
	public List<Member> searchMembers(String lastName) throws Exception {
		List<Member> list = new ArrayList<Member>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName = "%"+lastName+"%";//pour pouvoir rechercher les mot commençant par %partOfLastname
			myStmt = myConn.prepareStatement("select * from members where last_name like ?  order by last_name");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Member tempMember = convertRowToMember(myRs);
				list.add(tempMember);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public Member convertRowToMember(ResultSet myRs) throws SQLException {
		// id_member login password first_name last_name e_mail
		int idMember = myRs.getInt("id_member");
		String login = myRs.getString("login");
		String password = myRs.getString("password");
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		String email = myRs.getString("e_mail");
		
		Member tempMember = new Member(idMember,login, password, firstName, lastName, email);
		
		return tempMember;
	}

	//pq cette methode est static ... car elle ne depend que da EmployeeDAO
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
	//can't we not use the main method here
	
	public static void main(String[] args) throws Exception {
		
		MemberDAO dao = new MemberDAO();
		System.out.println(dao.searchMembers("thom"));//this is just a try

		System.out.println(dao.getAllMembers());
	}

}
