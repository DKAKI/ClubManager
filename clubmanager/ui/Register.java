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
import clubmanager.dao.RegisterDAO;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class Register extends JFrame {
	private EventDAO eventDAO;
	private JPanel contentPane;
	private JTextField firstnametf;
	private JTextField lastnametf;
	private JTextField emailtf;
	private JTextField logintf;
	
	public String lblUsername1;
	public String lblPass11;
	public String lblpass21;
	public String lblFirstName1;
	public String lblLastName1;
	public String lblEmail1;
	
	private RegisterDAO registerDAO;
	private JPasswordField passwordtf1;
	private JPasswordField passwordtf2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setResizable(false);
		setTitle("ClubManager App                                                      Register");

		setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		
		// create the DAO
		try {
			registerDAO = new RegisterDAO();
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
		
		JLabel lbLoginHome = new JLabel("");
		lbLoginHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login window = new Login();
				window.frame.setVisible(true);
				setLocationRelativeTo(null);
				dispose();
			}
			
		});
		lbLoginHome.setIcon(new ImageIcon(Register.class.getResource("/clubmanager/ui/pictures/login123.png")));
		lbLoginHome.setBounds(10, 11, 50, 50);
		contentPane.add(lbLoginHome);
		
	
		
		logintf = new JTextField();
		logintf.setColumns(10);
		logintf.setBounds(241, 142, 258, 31);
		contentPane.add(logintf);
		
		firstnametf = new JTextField();
		firstnametf.setColumns(10);
		firstnametf.setBounds(241, 330, 258, 31);
		contentPane.add(firstnametf);
		
		lastnametf = new JTextField();
		lastnametf.setColumns(10);
		lastnametf.setBounds(241, 389, 258, 31);
		contentPane.add(lastnametf);
		
		emailtf = new JTextField();
		emailtf.setColumns(10);
		emailtf.setBounds(241, 452, 258, 31);
		contentPane.add(emailtf);
		
		JLabel lblUsername = new JLabel("Type your username");
		lblUsername.setBounds(58, 142, 173, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPass1 = new JLabel("Type your password");
		lblPass1.setBounds(58, 207, 158, 14);
		contentPane.add(lblPass1);
		
		JLabel lblpass2 = new JLabel("Type your password again");
		lblpass2.setBounds(58, 278, 173, 14);
		contentPane.add(lblpass2);
		
		JLabel lblFirstName = new JLabel("Type your first name");
		lblFirstName.setBounds(58, 333, 158, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Type your last name");
		lblLastName.setBounds(58, 392, 158, 14);
		contentPane.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Type your email");
		lblEmail.setBounds(58, 455, 140, 14);
		contentPane.add(lblEmail);
		
		JButton btnRegister = new JButton("");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 lblUsername1 = logintf.getText();
				 lblPass11 = passwordtf1.getText();
				 lblpass21 = passwordtf2.getText();
				 lblFirstName1 = firstnametf.getText();
				 lblLastName1 = lastnametf.getText();
				 lblEmail1 = emailtf.getText();
				 boolean matchPassword = lblPass11.equals(lblpass21);
				if (!matchPassword) {
					JOptionPane.showMessageDialog(Register.this,"the password fields doesn't match Try again ", "Error",JOptionPane.ERROR_MESSAGE);
					/*
					Login window = new Login();
					window.frame.setVisible(true);
					setLocationRelativeTo(null);
					*/
				}
				boolean emtyfield=(lblUsername1.equals("") || lblPass11.equals("") || lblpass21.equals("") || lblFirstName1.equals("") || lblLastName1.equals("") || lblEmail1.equals(""));
				if (emtyfield)
				{
					
					JOptionPane.showMessageDialog(Register.this,"Please fill all the fields ", "Error",JOptionPane.ERROR_MESSAGE);
					/*
					Login window = new Login();
					window.frame.setVisible(true);
					setLocationRelativeTo(null);
					*/
				}
				if (lblpass21.length()<6)
					JOptionPane.showMessageDialog(Register.this,"The number of characters in the password should be at least 6 chars ", "Error",JOptionPane.ERROR_MESSAGE);
				boolean validEmail = registerDAO.isValidEmailAddress(lblEmail1);
				if (!validEmail)
					JOptionPane.showMessageDialog(Register.this,"You Should Enter a valid email ", "Error",JOptionPane.ERROR_MESSAGE);
				else if (!emtyfield && matchPassword && validEmail )
				{
					try {
						registerDAO.addMember(lblUsername1,lblPass11,lblFirstName1,lblLastName1,lblEmail1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(Register.this,"Registred Successfully ", "Welcome",JOptionPane.INFORMATION_MESSAGE);
					Login window = new Login();
					window.frame.setVisible(true);
					setLocationRelativeTo(null);
					dispose();
				}
				
				
			}
		});
		btnRegister.setIcon(new ImageIcon(Register.class.getResource("/clubmanager/ui/pictures/registerbtn.PNG")));
		btnRegister.setBounds(269, 518, 200, 50);
		contentPane.add(btnRegister);
		
		passwordtf1 = new JPasswordField();
		passwordtf1.setBounds(241, 204, 258, 31);
		contentPane.add(passwordtf1);
		
		passwordtf2 = new JPasswordField();
		passwordtf2.setBounds(241, 270, 258, 31);
		contentPane.add(passwordtf2);
		
		JLabel bgClubManager = new JLabel("Type your password again");
		bgClubManager.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		bgClubManager.setBounds(0, -13, 615, 640);
		contentPane.add(bgClubManager);
	}
}