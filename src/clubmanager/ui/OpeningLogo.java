package clubmanager.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.*;

public class OpeningLogo extends JFrame{
	Container c;
	JButton e;
	BackgroundPanel back;
	
	public OpeningLogo() {
		c = getContentPane();
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		
		//panel
		back = new BackgroundPanel(new ImageIcon("C://Users//NAHED//Desktop//clubManagerPictures/logoApp.png"));
		back.setBackground(new Color(0,0,0,0));
		
		back.setLayout(new GridLayout(4,1));
		
		setSize(512, 512);
		setLocationRelativeTo(null);

		c.add(back);
		
		
	}

	

	
	
	class BackgroundPanel extends JPanel{
		ImageIcon icon;
		
		public BackgroundPanel(ImageIcon icon) {
			this.icon = icon;
		}

		public void setIcon(ImageIcon icon) {
			this.icon = icon;
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (this.icon != null) {
				g.drawImage(icon.getImage(),0,0,this);
			}
		}
	}

	public static void main(String[] args) {
		
			OpeningLogo frame = new OpeningLogo();
	 		frame.setVisible(true);
	 		Timer timer = new Timer(4000, new ActionListener(){      // Timer 4 seconds
	           @Override 
	           public void actionPerformed(ActionEvent e) {
	        	   frame.dispose(); 	   
	            }
	        });
			timer.start();  
			Login window = new Login();
			window.frame.setVisible(true);
	
	}

}