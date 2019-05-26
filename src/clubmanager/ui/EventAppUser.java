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

public class EventAppUser extends JFrame {
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
					EventAppUser frame = new EventAppUser();
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
	public EventAppUser() {
		setResizable(false);
		setTitle("ClubManager App                                                      Events");

		setIconImage(Toolkit.getDefaultToolkit().getImage(EventAppUser.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		
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
		scrollPane.setBounds(52, 149, 508, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			JLabel lblLogout = new JLabel("");
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Login window = new Login();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
					dispose();
				}
			});
			lblLogout.setIcon(new ImageIcon(EventDialog.class.getResource("/clubmanager/ui/pictures/LOOG.png")));
			lblLogout.setBounds(532, 11, 70, 70);
			getContentPane().add(lblLogout);
		}
		{
			JLabel lblLogOut = new JLabel("Log out");
			lblLogOut.setBounds(542, 81, 46, 14);
			getContentPane().add(lblLogOut);
		}
		
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
				ManageInterfaceUser frame = new ManageInterfaceUser();
				frame.setVisible(true);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(EventAppUser.class.getResource("/clubmanager/ui/pictures/home.png")));
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
					JOptionPane.showMessageDialog(EventAppUser.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
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