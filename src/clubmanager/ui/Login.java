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

public class Login {
	

	private JFrame frame;
	private JTextField loginField;
	private JButton btnLogin;
	private MemberDAO memberdao;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;



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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/clubmanager/ui/pictures/logo2.png")));
		frame.setBounds(100, 100, 631, 621);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		loginField = new JTextField();
		loginField.setBounds(238, 200, 214, 38);
		frame.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(238, 285, 214, 38);
		frame.getContentPane().add(passwordField);
		
		btnLogin = new JButton("Login Now");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/clubmanager/ui/pictures/loginblue3.PNG")));
		btnLogin.setIconTextGap(0);
		btnLogin.setToolTipText("");
		btnLogin.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 11));
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setForeground(new Color(0, 0, 0));
		
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//window.setVisible(false);
				String passwordField1=passwordField.getText();
				String loginField1=loginField.getText();
				
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
				);
		
		btnLogin.setBounds(272, 359, 101, 48);
		frame.getContentPane().add(btnLogin);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/clubmanager/ui/pictures/signIn.PNG")));
		lblNewLabel.setBounds(0, -13, 622, 611);
		frame.getContentPane().add(lblNewLabel);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
