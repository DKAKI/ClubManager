package clubmanager.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ManageInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageInterface frame = new ManageInterface();
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
	public ManageInterface() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManageInterface.class.getResource("/clubmanager/ui/pictures/logo2.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 621);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageMembers = new JButton("Members");
		btnManageMembers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MemberApp frame = new MemberApp();
				frame.setVisible(true);
			}
		});
		btnManageMembers.setBounds(263, 140, 196, 76);
		contentPane.add(btnManageMembers);
		
		JButton btnManageTreasury = new JButton("Treasury");
		btnManageTreasury.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TreasuryApp frame = new TreasuryApp();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnManageTreasury.setBounds(263, 299, 196, 76);
		contentPane.add(btnManageTreasury);
		
		JButton btnManageEvents = new JButton("Events");
		btnManageEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EventApp frame = new EventApp();
				frame.setVisible(true);
			}	
		});
		btnManageEvents.setBounds(263, 449, 196, 76);
		contentPane.add(btnManageEvents);
		
		JLabel lblMember = new JLabel("");
		lblMember.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/member2s.png")));
		lblMember.setBounds(84, 127, 132, 93);
		contentPane.add(lblMember);
		
		JLabel lblEvent = new JLabel("");
		lblEvent.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/EVENT.png")));
		lblEvent.setBounds(80, 422, 125, 113);
		contentPane.add(lblEvent);
		
		JLabel lbltreasury = new JLabel("");
		lbltreasury.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/finance_-68-512.png")));
		lbltreasury.setBounds(84, 269, 121, 110);
		contentPane.add(lbltreasury);
		
		JLabel labelbg = new JLabel("");
		labelbg.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		labelbg.setBounds(-21, -11, 636, 640);
		contentPane.add(labelbg);
	}
}
