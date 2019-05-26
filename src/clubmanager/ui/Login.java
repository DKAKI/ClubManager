package clubmanager.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.mysql.jdbc.*;
import clubmanager.core.Member;
import clubmanager.dao.MemberDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import clubmanager.dao.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

public class Login {
	

	public JFrame frame;
	public JTextField loginField;
	private MemberDAO memberdao;
	public JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JCheckBox chckbxshowPassword;
	private JLabel label;
	private JLabel lbllogin;
	private JLabel lblForgotPass;
	private JLabel lblRegister;
	/*
	String loginField3=loginField.getText();
	String passwordField3=passwordField.getText();
	*/

	/**
	 * @return the loginField
	 */
	public JTextField getLoginField() {
		return loginField;
	}

	/**
	 * @return the passwordField
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		// create the DAO
		try {
			memberdao = new MemberDAO();
		} catch (Exception exc) {
			System.out.println();
			//JOptionPane.showMessageDialog(Login.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); //what does this method perform with these 4 parameters??
		//why sometimes we write just this without refering to the the class or the object
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("ClubManager App                                                      Sign In");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		frame.setBounds(100, 100, 608, 621);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		// Set the frame background color to a transparent color
	
		
		loginField = new JTextField();
		loginField.setBounds(218, 196, 214, 38);
		frame.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(218, 279, 214, 38);
		frame.getContentPane().add(passwordField);
		
		chckbxshowPassword = new JCheckBox("");
		chckbxshowPassword.setBackground(new Color(51, 153, 255));
		chckbxshowPassword.setBorder(null);
		chckbxshowPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				passwordField.setEchoChar('*');
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				passwordField.setEchoChar((char)0);
			}
		});
		chckbxshowPassword.setBounds(166, 425, 13, 13);
		frame.getContentPane().add(chckbxshowPassword);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/clubmanager/ui/pictures/shpass.PNG")));
		label.setBounds(195, 422, 80, 16);
		frame.getContentPane().add(label);
		
		lbllogin = new JLabel("");
		lbllogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String passwordField1="";
				//window.setVisible(false);
				char[] passwordField2=passwordField.getPassword();//jPasswordField.getPassword();
				for(int i=0;i<passwordField2.length;i++) {
				//String passwordField1="";
				passwordField1+=passwordField2[i];
				}
				
				String loginField1=loginField.getText();
				if (passwordField1.equals("") || loginField1.equals("") ) {
					
					JOptionPane.showMessageDialog(frame,
							"Please fill all the fields ", "Error",
							JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					
					try {
						memberdao.loginOrNot(loginField1, passwordField1);
						Login window = new Login();
						frame.setVisible(false);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		lbllogin.setBounds(206, 352, 200, 50);
		frame.getContentPane().add(lbllogin);
		
		lblForgotPass = new JLabel("");
		lblForgotPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgotPassword frame = new ForgotPassword();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		lblForgotPass.setBounds(339, 425, 104, 16);
		frame.getContentPane().add(lblForgotPass);
		
		lblRegister = new JLabel("");
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register frame1 = new Register();
				frame1.setVisible(true);
				frame1.setLocationRelativeTo(null);
				frame.dispose();
			}
		});
		lblRegister.setBounds(319, 529, 86, 16);
		frame.getContentPane().add(lblRegister);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBorder(new EmptyBorder(0, 1, 2, 0));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/clubmanager/ui/pictures/signIn.PNG")));
		lblNewLabel.setBounds(-20, -19, 622, 611);
		frame.getContentPane().add(lblNewLabel);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
