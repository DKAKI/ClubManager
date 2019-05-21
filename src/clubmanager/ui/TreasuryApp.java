package clubmanager.ui;

import java.awt.BorderLayout;
import java.awt.Component;
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

import clubmanager.core.Treasury;
import clubmanager.dao.TreasuryDAO;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TreasuryApp extends JFrame {
	private TreasuryDAO treasuryDAO;
	private JTable table;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreasuryApp frame = new TreasuryApp();
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
	public TreasuryApp() {
		setTitle("ClubManager App                                                      Treasury");

		setIconImage(Toolkit.getDefaultToolkit().getImage(TreasuryApp.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		
		// create the DAO
		try {
			treasuryDAO = new TreasuryDAO();
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
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 149, 508, 244);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAddTreasury = new JButton("Add Sum");
		btnAddTreasury.setBounds(52, 493, 142, 23);
		contentPane.add(btnAddTreasury);
		
		JButton btnUpdateTreasury = new JButton("Update Sum");
		btnUpdateTreasury.setBounds(243, 493, 142, 23);
		contentPane.add(btnUpdateTreasury);
		
		JButton btnDeleteTreasury = new JButton("Delete Sum");
		btnDeleteTreasury.setBounds(425, 493, 135, 23);
		contentPane.add(btnDeleteTreasury);
		
		JLabel lblEnterResponsibleTreasury = new JLabel("Enter the name of the Responsible of Treasury ");
		lblEnterResponsibleTreasury.setBounds(52, 103, 248, 14);
		contentPane.add(lblEnterResponsibleTreasury);
		
		JTextField responsibleTreasuryTextField = new JTextField();
		responsibleTreasuryTextField.setBounds(299, 100, 142, 20);
		contentPane.add(responsibleTreasuryTextField);
		responsibleTreasuryTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(451, 99, 109, 23);
		contentPane.add(btnSearch);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManageInterface frame = new ManageInterface();
				frame.setVisible(true);
				dispose();			}
		});
		label_1.setIcon(new ImageIcon(TreasuryApp.class.getResource("/clubmanager/ui/pictures/home.png")));
		label_1.setBounds(10, 11, 55, 50);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ManageInterface.class.getResource("/clubmanager/ui/pictures/CLUB MANAGER BG.jpg")));
		label.setBounds(0, -13, 615, 640);
		contentPane.add(label);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get treasury for the last name

				// If last name is empty, then get all treasury

				// Print out treasury				
				
				try {
					String responsible_treasury = responsibleTreasuryTextField.getText();

					List<Treasury> treasury = null;// declare the list of treasury

					if (responsible_treasury != null ) {
						treasury = treasuryDAO.searchTreasury(responsible_treasury);
					} else {
						treasury = treasuryDAO.getAllTreasury();
					}
					
					// create the model and update the "table"
					TreasuryTableModel model = new TreasuryTableModel(treasury);//save the treasury we found after search
					
					
					table.setModel(model);//show the treasury we found after search
					
					
			
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(TreasuryApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		btnDeleteTreasury.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(TreasuryApp.this, 
								"You must select a sum", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							TreasuryApp.this, "Delete this sum?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current treasury
					Treasury tempTreasury = (Treasury) table.getValueAt(row, TreasuryTableModel.OBJECT_COL);//why cast ??
					//EmployeeTableModel.OBJECT_COL has a default value of -1 to refer the object treasury
					// delete the treasury
					treasuryDAO.deleteTreasury(tempTreasury.getId_treasury());
					

					// refresh GUI
					refreshTreasuryView();

					// show success message
					JOptionPane.showMessageDialog(TreasuryApp.this,
							"Sum deleted succesfully.", "Sum Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(TreasuryApp.this,
							"Error deleting Sum: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		btnUpdateTreasury.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = table.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {// the default value of row must be negative
					JOptionPane.showMessageDialog(TreasuryApp.this, "You must select a Sum", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current treasury
				Treasury tempTreasury =  (Treasury) table.getValueAt(row, TreasuryTableModel.OBJECT_COL);// why cast ?
				
				// create dialog
				TreasuryDialog dialog = new TreasuryDialog(TreasuryApp.this, treasuryDAO, tempTreasury, true);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		btnAddTreasury.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				TreasuryDialog dialog = new TreasuryDialog(TreasuryApp.this, treasuryDAO);//when we reverse the place of this

				// show dialog
				dialog.setVisible(true);
			
			}
		});
	}

	public void refreshTreasuryView() {

		try {
			List<Treasury> treasury = treasuryDAO.getAllTreasury();

			// create the model and update the "table"
			TreasuryTableModel model = new TreasuryTableModel(treasury);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
