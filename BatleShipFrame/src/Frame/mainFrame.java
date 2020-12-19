package Frame;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Panel.mainPanel;

public class mainFrame extends JFrame{
	
	public mainPanel panel_main;
	ImageIcon image = new ImageIcon("Ressources/icon.png");
	
	public mainFrame() {
		panel_main = new mainPanel();
		ImageIcon image = new ImageIcon("Ressources/icon.png");

	    this.setTitle("Bataille navalle Menu");
		this.setSize(560, 640);
		this.setResizable(false);
		this.setLocation(500, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(panel_main);
	    this.setIconImage(image.getImage());
	    this.setVisible(true);

	}
	

}
