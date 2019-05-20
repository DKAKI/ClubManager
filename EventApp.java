package clubmanager.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clubmanager.core.Event;
import clubmanager.dao.EventDAO;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventApp extends JFrame {
	private EventDAO eventDAO;
	private JTable table;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventApp frame = new EventApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EventApp() {
		setResizable(false);
		setTitle("ClubManager App                                                      Events");

		setIconImage(Toolkit.getDefaultToolkit().getImage(EventApp.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		
		// create the DAO
		try {
			eventDAO = new EventDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); //what does this method perform with these 4 parameters??
		//why sometimes we write just this without refering to the the class or the object
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 621);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 149, 508, 244);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.setBounds(52, 493, 142, 23);
		contentPane.add(btnAddEvent);
		
		JButton btnUpdateEvent = new JButton("Update Event");
		btnUpdateEvent.setBounds(243, 493, 142, 23);
		contentPane.add(btnUpdateEvent);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.setBounds(425, 493, 135, 23);
		contentPane.add(btnDeleteEvent);
		
		JLabel lblEnterResponsibleEvent = new JLabel("Enter the name of the Name of Event ");
		lblEnterResponsibleEvent.setBounds(52, 103, 241, 14);
		contentPane.add(lblEnterResponsibleEvent);
		
		JTextField nameEventTextField = new JTextField();
		nameEventTextField.setBounds(288, 100, 142, 20);
		contentPane.add(nameEventTextField);
		nameEventTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(455, 99, 105, 23);
		contentPane.add(btnSearch);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageInterface frame = new ManageInterface();
				frame.setVisible(true);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(EventApp.class.getResource("/clubmanager/ui/pictures/home.png")));
		label_1.setBounds(10, 11, 53, 50);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		label.setBounds(0, -13, 615, 640);
		contentPane.add(label);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get event for the last name

				// If last name is empty, then get all event

				// Print out event				
				
				try {
					String name_event = nameEventTextField.getText();

					List<Event> event = null;// declare the list of event

					if (name_event != null ) {
						event = eventDAO.searchEvent(name_event);
					} else {
						event = eventDAO.getAllEvents();
					}
					
					// create the model and update the "table"
					EventTableModel model = new EventTableModel(event);//save the event we found after search
					
					
					table.setModel(model);//show the event we found after search
					
					
			
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(EventApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(EventApp.this, 
								"You must select a sum", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							EventApp.this, "Delete this sum?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current event
					Event tempEvent = (Event) table.getValueAt(row, EventTableModel.OBJECT_COL);//why cast ??
					//EmployeeTableModel.OBJECT_COL has a default value of -1 to refer the object event
					// delete the event
					eventDAO.deleteEvent(tempEvent.getId_event());
					

					// refresh GUI
					refreshEventsView();

					// show success message
					JOptionPane.showMessageDialog(EventApp.this,
							"Sum deleted succesfully.", "Sum Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(EventApp.this,
							"Error deleting Sum: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		btnUpdateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {// the default value of row must be negative
					JOptionPane.showMessageDialog(EventApp.this, "You must select a Sum", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current event
				Event tempEvent =  (Event) table.getValueAt(row, EventTableModel.OBJECT_COL);// why cast ?
				
				// create dialog
				EventDialog dialog = new EventDialog(EventApp.this, eventDAO, tempEvent, true);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				EventDialog dialog = new EventDialog(EventApp.this, eventDAO);//when we reverse the place of this

				// show dialog
				dialog.setVisible(true);
			}
		});
	}

	public void refreshEventsView() {

		try {
			List<Event> event = eventDAO.getAllEvents();

			// create the model and update the "table"
			EventTableModel model = new EventTableModel(event);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}