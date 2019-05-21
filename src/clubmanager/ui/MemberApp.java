package clubmanager.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java.util.List;

import clubmanager.core.Member;
import clubmanager.dao.MemberDAO;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberApp extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAddEmployee;
	private JButton btnUpdateEmployee;
	private JButton btnDeleteEmployee;
	
	
	private MemberDAO memberDAO;//important attribut used to have the ability to perform 
	private JLabel lblNewLabel;
	//all methods of EmployeDAO on the object employeDAO	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//what does this method perform ?
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberApp frame = new MemberApp();
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
	public MemberApp() {
		setResizable(false);
		setTitle("ClubManager App                                Members");

		setIconImage(Toolkit.getDefaultToolkit().getImage(MemberApp.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		
		// create the DAO
		try {
			memberDAO = new MemberDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); //what does this method perform with these 4 parameters??
		//why sometimes we write just this without refering to the the class or the object
		}
		
		setTitle("Club Manager App                                                      Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 621);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 149, 508, 244);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnAddEmployee = new JButton("Add Member");
		btnAddEmployee.setBounds(52, 493, 142, 23);
		contentPane.add(btnAddEmployee);
		
		btnUpdateEmployee = new JButton("Update Member");
		btnUpdateEmployee.setBounds(243, 493, 142, 23);
		contentPane.add(btnUpdateEmployee);
		
		btnDeleteEmployee = new JButton("Delete Member");
		btnDeleteEmployee.setBounds(425, 493, 135, 23);
		contentPane.add(btnDeleteEmployee);
		
		JLabel lblEnterLastName = new JLabel("Enter the last name ");
		lblEnterLastName.setBounds(52, 103, 127, 14);
		contentPane.add(lblEnterLastName);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(189, 100, 210, 20);
		contentPane.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(425, 99, 135, 23);
		contentPane.add(btnSearch);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ManageInterface frame = new ManageInterface();
				frame.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(MemberApp.class.getResource("/clubmanager/ui/pictures/home.png")));
		lblNewLabel.setBounds(10, 11, 48, 50);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		label.setBounds(0, -13, 615, 640);
		contentPane.add(label);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get members for the last name

				// If last name is empty, then get all members

				// Print out members				
				
				try {
					String lastName = lastNameTextField.getText();

					List<Member> members = null;// declare the list of members

					if (lastName != null ) {
						members = memberDAO.searchMembers(lastName);
					} else {
						members = memberDAO.getAllMembers();
					}
					
					// create the model and update the "table"
					MemberTableModel model = new MemberTableModel(members);//save the members we found after search
					
					table.setModel(model);//show the members we found after search
					//what is setModel for?
					
					/*
					for (Employee temp : members) {
						System.out.println(temp);
					}
					*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(MemberApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(MemberApp.this, 
								"You must select an member", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							MemberApp.this, "Delete this member?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current member
					Member tempMember = (Member) table.getValueAt(row, MemberTableModel.OBJECT_COL);//why cast ??
					//EmployeeTableModel.OBJECT_COL has a default value of -1 to refer the object member
					// delete the member
					memberDAO.deleteMember(tempMember.getIdMember());
					// getIdMember() getLogin() getPassword() getFirstName() getLastName() getEmail()

					// refresh GUI
					refreshEmployeesView();

					// show success message
					JOptionPane.showMessageDialog(MemberApp.this,
							"Employee deleted succesfully.", "Member Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(MemberApp.this,
							"Error deleting member: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		btnUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {// the default value of row must be negative
					JOptionPane.showMessageDialog(MemberApp.this, "You must select an member", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current member
				Member tempMember = (Member) table.getValueAt(row, MemberTableModel.OBJECT_COL);// why cast ?
				
				// create dialog
				MemberDialog dialog = new MemberDialog(MemberApp.this, memberDAO, tempMember, true);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				MemberDialog dialog = new MemberDialog(MemberApp.this, memberDAO);//when we reverse the place of this

				// show dialog
				dialog.setVisible(true);
			}
		});
	}

	public void refreshEmployeesView() {

		try {
			List<Member> members = memberDAO.getAllMembers();

			// create the model and update the "table"
			MemberTableModel model = new MemberTableModel(members);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
