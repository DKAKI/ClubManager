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
import clubmanager.core.Member;
import clubmanager.dao.EventDAO;
import clubmanager.dao.MemberDAO;
import clubmanager.dao.RegisterDAO;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class ForgotPassword extends JFrame {
	private MemberDAO memberDAO;
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
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		setResizable(false);
		setTitle("ClubManager App                                                      Forgot Password");

		setIconImage(Toolkit.getDefaultToolkit().getImage(ForgotPassword.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		
		// create the DAO
		try {
			memberDAO = new MemberDAO();
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
		lbLoginHome.setIcon(new ImageIcon(ForgotPassword.class.getResource("/clubmanager/ui/pictures/login123.png")));
		lbLoginHome.setBounds(10, 11, 50, 50);
		contentPane.add(lbLoginHome);
		
		//logintf firstnametf lastnametf emailtf
		
		logintf = new JTextField();
		logintf.setColumns(10);
		logintf.setBounds(241, 152, 258, 31);
		contentPane.add(logintf);
		
		firstnametf = new JTextField();
		firstnametf.setColumns(10);
		firstnametf.setBounds(241, 242, 258, 31);
		contentPane.add(firstnametf);
		
		lastnametf = new JTextField();
		lastnametf.setColumns(10);
		lastnametf.setBounds(241, 336, 258, 31);
		contentPane.add(lastnametf);
		
		emailtf = new JTextField();
		emailtf.setColumns(10);
		emailtf.setBounds(241, 428, 258, 31);
		contentPane.add(emailtf);
		
		JLabel lblUsername = new JLabel("Type your username");
		lblUsername.setBounds(58, 160, 173, 14);
		contentPane.add(lblUsername);
		
		JLabel lblFirstName = new JLabel("Type your first name");
		lblFirstName.setBounds(58, 250, 158, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Type your last name");
		lblLastName.setBounds(58, 344, 158, 14);
		contentPane.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Type your email");
		lblEmail.setBounds(58, 436, 140, 14);
		contentPane.add(lblEmail);
		
		JLabel lblGetPasswordHint = new JLabel("Get Password Hint");
		lblGetPasswordHint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					List<Member> list = memberDAO.getAllMembers();
					for (int i=0; i<list.size();i++)
					{
						
					if (list.get(i).getLogin().equals(logintf.getText()) && list.get(i).getFirstName().equals(firstnametf.getText()) &&
							list.get(i).getLastName().equals(lastnametf.getText()) && list.get(i).getEmail().equals(emailtf.getText()))
							{
						JOptionPane.showMessageDialog(ForgotPassword.this,"the hint of your password is "+list.get(i).getPassword().substring(0, 3), "Event Saved",JOptionPane.INFORMATION_MESSAGE);
							
							}
					}
					}
				 catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
		
					/*
						try {
					List<Member> list = memberDAO.getAllMembers();
					for (int i=0; i<list.size();i++) {
					if (list.get(i).getLogin().equals(logintf.getText()) && list.get(i).getFirstName().equals(firstnametf.getText()) &&
							list.get(i).getLastName().equals(lastnametf.getText()) && list.get(i).getEmail().equals(emailtf.getText()))
							{
						JOptionPane.showMessageDialog(ForgotPassword.this,"the hint of your password is "+list.get(i).getPassword().substring(0, 3), "Event Saved",JOptionPane.INFORMATION_MESSAGE);
						
								return;
							}
					
					else if (list.get(i).getLogin().equals("") || list.get(i).getFirstName().equals("") || list.get(i).getLastName().equals("") || list.get(i).getEmail().equals(""))
						JOptionPane.showMessageDialog(ForgotPassword.this, "You should fill out all the fields to receive the hint " , "Error", JOptionPane.ERROR_MESSAGE);
						return;
					
					
					else 
						JOptionPane.showMessageDialog(ForgotPassword.this, "The informations you provided are incorrects: " , "Error", JOptionPane.ERROR_MESSAGE);
					return;
					
					
					}
				}
					
					*/
					
							
			
			}
		});
		lblGetPasswordHint.setFont(new Font("Calibri Light", Font.PLAIN, 30));
		lblGetPasswordHint.setBounds(251, 529, 244, 41);
		contentPane.add(lblGetPasswordHint);
		
		JLabel bgClubManager = new JLabel("Type your password again");
		bgClubManager.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		bgClubManager.setBounds(0, -13, 615, 640);
		contentPane.add(bgClubManager);
	}


}