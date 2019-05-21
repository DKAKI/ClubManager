package clubmanager.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import clubmanager.core.Event;
import clubmanager.dao.EventDAO;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventDialog extends JDialog {
	private JTextField officialSponsorTextField;
	private JTextField placeTextField;
	private JTextField dateTextField;
	private JTextField nameEventTextField;
	private EventDAO eventDAO;
	private EventApp eventApp;
	private Event previousEvent = null;
	private boolean updateMode = false;

	/**
	 * Launch the application.
	 */
	public EventDialog(EventApp theEventApp,EventDAO theeventDAO, Event thePreviousEvent, boolean theUpdateMode) {
		this();
	
		eventDAO = theeventDAO;
		eventApp = theEventApp;
		previousEvent = thePreviousEvent;
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Events");
			
			populateGui(previousEvent);
			
		}
	}
	public EventDialog(EventApp theEventApp, EventDAO eventDAO) {
		this(theEventApp, eventDAO, null, false);
	}


	private void populateGui(Event theEvent) {
		dateTextField.setText(Integer.toString(theEvent.getDate_of_event()));
		nameEventTextField.setText(theEvent.getName_event());
		officialSponsorTextField.setText(theEvent.getOfficial_sponsor());
		placeTextField.setText(theEvent.getPlace());
		//getId_event()  getDate_of_event() getName_event() getOfficial_sponsor() getPlace()

	}


	/**
	 * Create the dialog.
	 */
	public EventDialog() {
		setResizable(false);
		setTitle("ClubManager App                                                      Events");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EventDialog.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 621);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			dateTextField = new JTextField();
			dateTextField.setBounds(230, 222, 322, 29);
			getContentPane().add(dateTextField);
			dateTextField.setColumns(10);
		}
		dateTextField.setColumns(10);
		{
			nameEventTextField = new JTextField();
			nameEventTextField.setBounds(230, 152, 322, 29);
			getContentPane().add(nameEventTextField);
		}
		{
			officialSponsorTextField = new JTextField();
			officialSponsorTextField.setBounds(230, 302, 322, 29);
			getContentPane().add(officialSponsorTextField);
			officialSponsorTextField.setColumns(10);//taille du textfield
		}
		{
			JLabel lblLogin = new JLabel("Name of event");
			lblLogin.setBounds(113, 159, 82, 14);
			getContentPane().add(lblLogin);
		}
		{
			JLabel lblPassword = new JLabel("date");
			lblPassword.setBounds(113, 229, 82, 14);
			getContentPane().add(lblPassword);
		}
		{
			JLabel lblFirstName = new JLabel("Official Sponsor");
			lblFirstName.setBounds(113, 309, 82, 14);
			getContentPane().add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Place");
			lblLastName.setBounds(113, 386, 69, 14);
			getContentPane().add(lblLastName);
		}
		{
			placeTextField = new JTextField();
			placeTextField.setBounds(230, 379, 322, 29);
			getContentPane().add(placeTextField);
			placeTextField.setColumns(10);
		}
		{
			JButton okButton = new JButton("Save");
			okButton.setBounds(286, 499, 105, 23);
			getContentPane().add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					saveEvent();
					eventApp.refreshEventsView();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(401, 499, 102, 23);
			getContentPane().add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelButton.setVisible(false);
					dispose();//?
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ManageInterface frame1 = new ManageInterface();
				frame1.setVisible(true);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(EventDialog.class.getResource("/clubmanager/ui/pictures/home.png")));
		label_1.setBounds(10, 11, 55, 59);
		getContentPane().add(label_1);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		label.setBounds(0, 0, 615, 626);
		getContentPane().add(label);
	}


	protected void saveEvent() {
		try {
			int date_of_event=Integer.parseInt(dateTextField.getText());
		}
		catch ( NumberFormatException JKLH) {
			JOptionPane.showMessageDialog(eventApp,"You should Enter a valid date ", "Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		int date_of_event=0;
		// get the Event info from gui	
		if (dateTextField.getText().equals(""))
			date_of_event=0;
		date_of_event =Integer.parseInt(dateTextField.getText());
		
		String name_event =nameEventTextField.getText();
		String official_sponsor = officialSponsorTextField.getText();
		String place = placeTextField.getText();
		Event tempEvent = null;
		//getId_event()  getDate_of_event() getName_event() getOfficial_sponsor() getPlace()
		//setId_event setDate_of_event setName_event setOfficial_sponsor
	//  id_event int date_of_event, String name_event, String official_sponsor, String place
		if (updateMode) {
			tempEvent = previousEvent;
			tempEvent.setDate_of_event(date_of_event);
			tempEvent.setName_event(name_event);
			tempEvent.setOfficial_sponsor(official_sponsor);
			tempEvent.setPlace(place);
		} else {
			tempEvent = new Event(date_of_event, name_event, official_sponsor ,place);
		}

		try {
			// save to the database
			if (updateMode) {
					if (date_of_event==0 || name_event.equals("") ||  official_sponsor.equals("") ||  place.equals("")) {
					
					JOptionPane.showMessageDialog(eventApp,"You should fill out all the fields ", "Error",JOptionPane.ERROR_MESSAGE);
					return;}
					else 
						eventDAO.updateEvent(tempEvent);
					
			} else {
					if (date_of_event==0 || name_event.equals("") ||  official_sponsor.equals("") ||  place.equals("")) {
					
					JOptionPane.showMessageDialog(eventApp,"You should fill out all the fields ", "Error",JOptionPane.ERROR_MESSAGE);
					return;}
					else 
						eventDAO.addEvent(tempEvent);
			}

			// close dialog
			setVisible(false);
			dispose();//???

			// refresh gui list
			eventApp.refreshEventsView();

			// show success message
			JOptionPane.showMessageDialog(eventApp,
					"Event saved succesfully.", "Event Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(eventApp,
					"Error saving Event: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}