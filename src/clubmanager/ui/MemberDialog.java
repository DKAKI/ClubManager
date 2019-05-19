package clubmanager.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import clubmanager.core.Member;
import clubmanager.dao.MemberDAO;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MemberDialog extends JDialog {
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JTextField loginTextField;
	private JTextField passwordTextField;
	private MemberDAO memberDAO;

	private MemberApp memberApp;

	private Member previousMember = null;
	private boolean updateMode = false;

	public MemberDialog(MemberApp theMemberApp,
			MemberDAO MemberDAO, Member thePreviousEmployee, boolean theUpdateMode) {
		this();
		//a quoi sert this dans ce cas ? elle fait appel a un autre constructeur par defaut pour générer l'interface graphique car on ne peut appeler qu'un seul constructeur a la fois 
		memberDAO = MemberDAO;
		memberApp = theMemberApp;

		previousMember = thePreviousEmployee;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Member");
			
			populateGui(previousMember);
			
		}
	}
	public MemberDialog(MemberApp theMemberApp,
			MemberDAO MemberDAO) {
		this(theMemberApp, MemberDAO, null, false);
	}


	private void populateGui(Member theMember) {
		loginTextField.setText(theMember.getLogin());
		passwordTextField.setText(theMember.getPassword());
		firstNameTextField.setText(theMember.getFirstName());
		lastNameTextField.setText(theMember.getLastName());
		emailTextField.setText(theMember.getEmail());
		
	}


	/**
	 * Create the dialog.
	 */
	public MemberDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MemberDialog.class.getResource("/clubmanager/ui/pictures/logo2.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Add Member");
		setBounds(100, 100, 631, 621);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		passwordTextField = new JTextField();
		passwordTextField.setBounds(199, 194, 322, 29);
		getContentPane().add(passwordTextField);
		{
			loginTextField = new JTextField();
			loginTextField.setBounds(199, 126, 322, 29);
			getContentPane().add(loginTextField);
			loginTextField.setColumns(10);
		}
		{
			loginTextField.setColumns(10);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setBounds(199, 258, 322, 29);
			getContentPane().add(firstNameTextField);
			firstNameTextField.setColumns(10);//taille du textfield
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBounds(199, 331, 322, 29);
			getContentPane().add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			emailTextField = new JTextField();
			emailTextField.setBounds(199, 411, 322, 29);
			getContentPane().add(emailTextField);
			emailTextField.setColumns(10);
		}
		{
			JLabel lblLogin = new JLabel("Login");
			lblLogin.setBounds(68, 133, 58, 14);
			getContentPane().add(lblLogin);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(68, 201, 82, 14);
			getContentPane().add(lblPassword);
		}
		{
			JLabel lblFirstName = new JLabel("First Name");
			lblFirstName.setBounds(68, 265, 82, 14);
			getContentPane().add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			lblLastName.setBounds(68, 338, 69, 14);
			getContentPane().add(lblLastName);
		}
		{
			JLabel lblNewLabel = new JLabel("Email");
			lblNewLabel.setBounds(68, 418, 62, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			JButton okButton = new JButton("Save");
			okButton.setBounds(259, 530, 90, 23);
			getContentPane().add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveMember();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(359, 530, 98, 23);
			getContentPane().add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelButton.setVisible(false);
					dispose();//?
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		label.setBounds(0, 0, 615, 617);
		getContentPane().add(label);
	}


	protected void saveMember() {

		// get the Member info from gui
		String login = loginTextField.getText();
		String password = passwordTextField.getText();
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String email = emailTextField.getText();

		Member tempMember = null;

		if (updateMode) {
			tempMember = previousMember;
			
			tempMember.setLogin(login);
			tempMember.setPassword(password);
			tempMember.setFirstName(firstName);
			tempMember.setLastName(lastName);
			tempMember.setEmail(email);
			
			// getIdMember() getLogin() getPassword() getFirstName() getLastName() getEmail()
			//id_member login password first_name last_name e_mail
		} else {
			tempMember = new Member(login, password, firstName ,lastName , email);// WE USED THE CONSTUCTOR WITH ONLY 4 PARAMETERS
		}

		try {
			// save to the database
			if (updateMode) {
				memberDAO.updateMember(tempMember);
			} else {
				memberDAO.addMember(tempMember);
			}

			// close dialog
			setVisible(false);
			dispose();//???

			// refresh gui list
			memberApp.refreshEmployeesView();

			// show success message
			JOptionPane.showMessageDialog(memberApp,
					"Member saved succesfully.", "Member Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(memberApp,
					"Error saving Member: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
