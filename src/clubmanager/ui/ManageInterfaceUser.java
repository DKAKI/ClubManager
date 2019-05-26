package clubmanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

import clubmanager.dao.MemberDAO;

public class ManageInterfaceUser extends JFrame {

	private JPanel contentPane;
	private MemberDAO memberDAO;
	private Login login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageInterfaceUser frame1 = new ManageInterfaceUser();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageInterfaceUser() {
		try {
			memberDAO = new MemberDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); //what does this method perform with these 4 parameters??
		//why sometimes we write just this without refering to the the class or the object
		}
		setResizable(false);
		setTitle("ClubManager App                                                      Main Menu");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManageInterfaceUser.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 621);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JButton btnManageMembers = new JButton("Members");
		btnManageMembers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
					MemberAppUser frame = new MemberAppUser();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				
			}
		});
		btnManageMembers.setBounds(263, 140, 196, 76);
		contentPane.add(btnManageMembers);
		
		JButton btnManageTreasury = new JButton("Treasury");
		btnManageTreasury.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TreasuryAppUser frame = new TreasuryAppUser();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
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
		btnManageTreasury.setBounds(263, 299, 196, 76);
		contentPane.add(btnManageTreasury);
		
		JButton btnManageEvents = new JButton("Events");
		btnManageEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EventAppUser frame = new EventAppUser();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
				
			}	
		});
		btnManageEvents.setBounds(263, 449, 196, 76);
		contentPane.add(btnManageEvents);
		
		JLabel lblMember = new JLabel("");
		lblMember.setIcon(new ImageIcon(ManageInterfaceUser.class.getResource("/clubmanager/ui/pictures/member2s.png")));
		lblMember.setBounds(84, 127, 132, 93);
		contentPane.add(lblMember);
		
		JLabel lblEvent = new JLabel("");
		lblEvent.setIcon(new ImageIcon(ManageInterfaceUser.class.getResource("/clubmanager/ui/pictures/EVENT.png")));
		lblEvent.setBounds(80, 422, 125, 113);
		contentPane.add(lblEvent);
		
		JLabel lbltreasury = new JLabel("");
		lbltreasury.setIcon(new ImageIcon(ManageInterfaceUser.class.getResource("/clubmanager/ui/pictures/finance_-68-512.png")));
		lbltreasury.setBounds(84, 269, 121, 110);
		contentPane.add(lbltreasury);
		
		JLabel labelbg = new JLabel("");
		labelbg.setIcon(new ImageIcon(ManageInterfaceUser.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		labelbg.setBounds(-21, -11, 636, 640);
		contentPane.add(labelbg);
	}
}