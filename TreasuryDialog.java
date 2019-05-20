package clubmanager.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import clubmanager.core.Treasury;
import clubmanager.dao.TreasuryDAO;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TreasuryDialog extends JDialog {
	private JTextField stateOfTreasuryTextField;
	private JTextField responsibleTreasuryTextField;
	private JTextField yearTextField;
	private JTextField sumTextField;
	private TreasuryDAO treasuryDAO;

	private TreasuryApp treasuryApp;

	private Treasury previousMember = null;
	private boolean updateMode = false;

	/**
	 * Launch the application.
	 */
	public TreasuryDialog(TreasuryApp theTreasuryApp,
			TreasuryDAO TreasuryDAO, Treasury thePreviousTreasury, boolean theUpdateMode) {
		this();
	
		treasuryDAO = TreasuryDAO;
		treasuryApp = theTreasuryApp;
		previousMember = thePreviousTreasury;
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Treasury");
			
			populateGui(previousMember);
			
		}
	}
	public TreasuryDialog(TreasuryApp theTreasuryApp, TreasuryDAO TreasuryDAO) {
		this(theTreasuryApp, TreasuryDAO, null, false);
	}


	private void populateGui(Treasury theTreasury) {
		yearTextField.setText(Integer.toString(theTreasury.getYear()));
		sumTextField.setText(Integer.toString(theTreasury.getSum()));
		stateOfTreasuryTextField.setText(theTreasury.getState_of_treasury());
		responsibleTreasuryTextField.setText(theTreasury.getResponsible_treasury());
		//getId_treasury() getYear()  getSum() getState_of_treasury() getResponsible_treasury()
	}


	/**
	 * Create the dialog.
	 */
	public TreasuryDialog() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TreasuryDialog.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("ClubManager App                                                      Treasury");

		setBounds(100, 100, 608, 621);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			yearTextField = new JTextField();
			yearTextField.setBounds(230, 145, 322, 29);
			getContentPane().add(yearTextField);
			yearTextField.setColumns(10);
		}
		yearTextField.setColumns(10);
		{
			sumTextField = new JTextField();
			sumTextField.setBounds(230, 222, 322, 29);
			getContentPane().add(sumTextField);
		}
		{
			stateOfTreasuryTextField = new JTextField();
			stateOfTreasuryTextField.setBounds(230, 302, 322, 29);
			getContentPane().add(stateOfTreasuryTextField);
			stateOfTreasuryTextField.setColumns(10);//taille du textfield
		}
		{
			JLabel lblLogin = new JLabel("College year");
			lblLogin.setBounds(113, 152, 82, 14);
			getContentPane().add(lblLogin);
		}
		{
			JLabel lblPassword = new JLabel("Sum");
			lblPassword.setBounds(113, 229, 82, 14);
			getContentPane().add(lblPassword);
		}
		{
			JLabel lblFirstName = new JLabel("State");
			lblFirstName.setBounds(113, 309, 82, 14);
			getContentPane().add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Responsible");
			lblLastName.setBounds(113, 386, 69, 14);
			getContentPane().add(lblLastName);
		}
		{
			responsibleTreasuryTextField = new JTextField();
			responsibleTreasuryTextField.setBounds(230, 379, 322, 29);
			getContentPane().add(responsibleTreasuryTextField);
			responsibleTreasuryTextField.setColumns(10);
		}
		{
			JButton okButton = new JButton("Save");
			okButton.setBounds(286, 499, 105, 23);
			getContentPane().add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveTreasury();
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
		{
			JLabel label_1 = new JLabel("");
			label_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ManageInterface frame = new ManageInterface();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();				}
			});
			label_1.setIcon(new ImageIcon(TreasuryDialog.class.getResource("/clubmanager/ui/pictures/home.png")));
			label_1.setBounds(10, 10, 56, 50);
			getContentPane().add(label_1);
		}
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		label.setBounds(0, 0, 615, 626);
		getContentPane().add(label);
	}


	protected void saveTreasury() {

		// get the Treasury info from gui
		int year =Integer.parseInt(yearTextField.getText());
		int sum = Integer.parseInt(sumTextField.getText());
		String stateOfTreasury = stateOfTreasuryTextField.getText();
		String responsibleTreasury = responsibleTreasuryTextField.getText();

		Treasury tempTreasury = null;

		if (updateMode) {
			tempTreasury = previousMember;
			//setId_treasury() setYear() setSum() setState_of_treasury() setResponsible_treasury()
			tempTreasury.setYear(year);
			tempTreasury.setSum(sum);
			tempTreasury.setState_of_treasury(stateOfTreasury);
			tempTreasury.setResponsible_treasury(responsibleTreasury);
			
			// getIdMember() getLogin() getPassword() getFirstName() getLastName() getEmail()
			//id_member login password first_name last_name e_mail
		} else {
			tempTreasury = new Treasury(year, sum, stateOfTreasury ,responsibleTreasury);
		}

		try {
			// save to the database
			if (updateMode) {
				treasuryDAO.updateTreasury(tempTreasury);
			} else {
				treasuryDAO.addTreasury(tempTreasury);
			}

			// close dialog
			setVisible(false);
			dispose();//???

			// refresh gui list
			treasuryApp.refreshTreasuryView();

			// show success message
			JOptionPane.showMessageDialog(treasuryApp,
					"Treasury saved succesfully.", "Treasury Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(treasuryApp,
					"Error saving Treasury: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	private int ParseInt(String text) {
		// TODO Auto-generated method stub
		return 0;
	}
}
