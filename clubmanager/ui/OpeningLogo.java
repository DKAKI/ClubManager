package clubmanager.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OpeningLogo extends JFrame{
	Container c;
	JButton e;
	BackgroundPanel back;

    private int progress = 0;
    private static Timer t;
    private JProgressBar pBar;

	
	
	/**
	 * 
	 */
	public OpeningLogo() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OpeningLogo.class.getResource("/clubmanager/ui/pictures/logoApp.png")));
		c = getContentPane();
		setBackground(new Color(0,0,0,0));
		
		//panel
		back = new BackgroundPanel(new ImageIcon("C://Users//NAHED//Desktop//clubManagerPictures/logoApp.png"));
		back.setBackground(new Color(0,0,0,0));
		
		back.setLayout(new GridLayout(4,1));
		
		setSize(512, 512);
		setLocationRelativeTo(null);

		c.add(back);
		t = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
           	 pBar = new JProgressBar(0,100);
      		getContentPane().add(pBar, BorderLayout.SOUTH); 
      		pBar.setStringPainted(true); 
      		 pBar.setValue(0);
      		for (int i=0;i <= 100;i++) {
      			pBar.setValue(i++);
      			
      			i++;
      		} 
            }
        });
		t.start();
	
 		
 		/*
        t = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
               
            }
        });
        // Setting the initial value of the progress bar
        pBar.setValue(progress);// c == 0
        // Showing a string progress bar
       
        add(pBar);
        
        setVisible(true);
        
		*/
		
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
	        	   frame.setVisible(false);
	        	   Login window = new Login();
	        	   window.frame.setVisible(true); 
	        	  
	        	   
	            }
	        });
	 		timer.start(); 
			timer.setRepeats(false);
			
            
	 		
			 
			
			;
			

	}

}