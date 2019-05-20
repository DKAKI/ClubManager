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
import clubmanager.core.Event;

/**
 * @author NAHED
 *
 */
public class EventDAO {
	
	public Connection myConn;
	Event theEvent;

	/**
	 * 
	 */
	public EventDAO()  throws Exception  {
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
	//getId_event()  getDate_of_event() getName_event() getOfficial_sponsor() getPlace()
	//setId_event setDate_of_event setName_event setOfficial_sponsor
//  id_event int date_of_event, String name_event, String official_sponsor, String place
	public void deleteEvent(int id_event) throws SQLException {
		PreparedStatement myStmt = null;
		//we get employeeId from THE TABLE using getValueAt(row, object)
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from events where id_event=?");
			
			// set param
			myStmt.setInt(1, id_event);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		//ou est le bloc catch ??
		finally {
			close(myStmt);
		}
	}
	
	public void updateEvent(Event theEvent) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update events"
					+ " set date_of_event=?, name_event=?, official_sponsor=?, place=?"
					+ " where id_event=?");
			myStmt.setInt(1, theEvent.getDate_of_event());
			myStmt.setString(2, theEvent.getName_event());
			myStmt.setString(3, theEvent.getOfficial_sponsor());
			myStmt.setString(4, theEvent.getPlace());
			myStmt.setInt(5, theEvent.getId_event());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addEvent(Event theEvent) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into events"
					+ " (date_of_event, name_event, official_sponsor, place)"
					+ " values (?, ?, ?, ?)");
			
			// set params
			myStmt.setInt(1, theEvent.getDate_of_event());
			myStmt.setString(2, theEvent.getName_event());
			myStmt.setString(3, theEvent.getOfficial_sponsor());
			myStmt.setString(4, theEvent.getPlace());
		
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<Event> getAllEvents() throws Exception {
		List<Event> list = new ArrayList<Event>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from events order by id_event");
			
			while (myRs.next()) {
				Event tempEvent = convertRowToEvent(myRs);
				list.add(tempEvent);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}

	public List<Event> searchEvent(String name_event) throws Exception {
		List<Event> list = new ArrayList<Event>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name_event = "%"+name_event+"%";//pour pouvoir rechercher les mot commençant par %partOfLastname
			myStmt = myConn.prepareStatement("select * from events where name_event like ?  order by date_of_event");
			
			myStmt.setString(1, name_event);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Event tempEvent = convertRowToEvent(myRs);
				list.add(tempEvent);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public Event convertRowToEvent(ResultSet myRs) throws SQLException {
		int id_event = myRs.getInt("id_event");
		int date_of_event = myRs.getInt("date_of_event");
		String name_event = myRs.getString("name_event");
		String official_sponsor = myRs.getString("official_sponsor");
		String place = myRs.getString("place");

		Event tempEvent = new Event(id_event, date_of_event, name_event, official_sponsor, place);
		// id_event int date_of_event, String name_event, String official_sponsor, String place
		return tempEvent;
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
		
		EventDAO dao = new EventDAO();
		Event tres = new Event(2001,3,"sdf","sdf","sdf");
		dao.updateEvent(tres);
		dao.addEvent(tres);
		dao.deleteEvent(12);
		System.out.println(dao.searchEvent("sdf"));//this is just a try

		System.out.println(dao.getAllEvents());
	}

}

