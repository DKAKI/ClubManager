/**
 * 
 */
package clubmanager.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import clubmanager.core.Treasury;

/**
 * @author NAHED
 *
 */
public class TreasuryDAO {
	
	public Connection myConn;
	Treasury theTreasury;

	/**
	 * 
	 */
	public TreasuryDAO()  throws Exception  {
		// TODO Auto-generated constructor stub
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

	public void deleteTreasury(int id_treasury) throws SQLException {
		PreparedStatement myStmt = null;
		//we get employeeId from THE TABLE using getValueAt(row, object)
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from treasury where id_treasury=?");
			
			// set param
			myStmt.setInt(1, id_treasury);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		//ou est le bloc catch ??
		finally {
			close(myStmt);
		}
	}
	
	public void updateTreasury(Treasury theTreasury) throws SQLException {
		PreparedStatement myStmt = null;
		//int id_treasury;int year;int sum;String state_of_treasury; String responsible_treasury;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update treasury"
					+ " set year=?, sum=?, state_of_treasury=?, responsible_treasury=?"
					+ " where id_treasury=?");
			//getId_treasury() getYear()  getSum() getState_of_treasury() getResponsible_treasury()
			// set params
			myStmt.setInt(1, theTreasury.getYear());
			myStmt.setInt(2, theTreasury.getSum());
			myStmt.setString(3, theTreasury.getState_of_treasury());
			myStmt.setString(4, theTreasury.getResponsible_treasury());
			myStmt.setInt(5, theTreasury.getId_treasury());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addTreasury(Treasury theTreasury) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into treasury"
					+ " (year, sum, state_of_treasury, responsible_treasury)"
					+ " values (?, ?, ?, ?)");
			
			// set params
			myStmt.setInt(1, theTreasury.getYear());
			myStmt.setInt(2, theTreasury.getSum());
			myStmt.setString(3, theTreasury.getState_of_treasury());
			myStmt.setString(4, theTreasury.getResponsible_treasury());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<Treasury> getAllTreasury() throws Exception {
		List<Treasury> list = new ArrayList<Treasury>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from treasury order by id_treasury");
			
			while (myRs.next()) {
				Treasury tempTreasury = convertRowToMember(myRs);
				list.add(tempTreasury);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}

	public List<Treasury> searchTreasury(String responsible_treasury) throws Exception {
		List<Treasury> list = new ArrayList<Treasury>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			responsible_treasury = "%"+responsible_treasury+"%";//pour pouvoir rechercher les mot commençant par %partOfLastname
			myStmt = myConn.prepareStatement("select * from treasury where responsible_treasury like ?  order by responsible_treasury");
			
			myStmt.setString(1, responsible_treasury);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Treasury tempTreasury = convertRowToMember(myRs);
				list.add(tempTreasury);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public Treasury convertRowToMember(ResultSet myRs) throws SQLException {
		//int id_treasury;int year;int sum;String state_of_treasury; String responsible_treasury;
		int id_treasury = myRs.getInt("id_treasury");
		int year = myRs.getInt("year");
		int sum = myRs.getInt("sum");
		String state_of_treasury = myRs.getString("state_of_treasury");
		String responsible_treasury = myRs.getString("responsible_treasury");

		Treasury tempTreasury = new Treasury(id_treasury, year, sum, state_of_treasury, responsible_treasury);
		
		return tempTreasury;
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
		
		TreasuryDAO dao = new TreasuryDAO();
		Treasury tres = new Treasury(2001,3,"sdf","sdf");
		
		dao.updateTreasury(tres);
		System.out.println(dao.searchTreasury("souh"));//this is just a try

		System.out.println(dao.getAllTreasury());
	}

}

