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

public class MemberAppUser extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	
	
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
					MemberAppUser frame = new MemberAppUser();
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
	public MemberAppUser() {
		setResizable(false);
		setTitle("ClubManager App                                Members");

		setIconImage(Toolkit.getDefaultToolkit().getImage(MemberAppUser.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		
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
		scrollPane.setBounds(52, 156, 508, 365);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
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
				ManageInterfaceUser frame = new ManageInterfaceUser();
				frame.setVisible(true);
				dispose();
			}
		});
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
		lblNewLabel.setIcon(new ImageIcon(MemberAppUser.class.getResource("/clubmanager/ui/pictures/home.png")));
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
					JOptionPane.showMessageDialog(MemberAppUser.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
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
