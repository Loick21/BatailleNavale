package Panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Component.Button;

public class mainPanel extends JPanel {
	

	Button button;
    ImageIcon icon_button;
	//Image button_play;
	Image img;
	Image logo;
    private boolean screen = false;
    
    
	public mainPanel() {
		this.setLayout(null);
	    try {
	    	//button_play = ImageIO.read(new File("Ressources/button.png"));
			img 		= ImageIO.read(new File("Ressources/background_start_screen.jpg"));
			logo 		= ImageIO.read(new File("Ressources/bataille_navalle_logo.png"));

		} catch (IOException e) {
			e.printStackTrace();
		} 
	    button = new Button("Ressources/button.png");
	    button.addActionListener(new buttonListenner());
	    this.add(button);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0,this);
	    button = new Button("Ressources/button.png");
	    button.paintComponent(g);
		g.drawImage(logo, 180, 50,200,120,this);

	}
	
	public boolean isScreen() {
		return screen;
	}

	public void setScreen(boolean screen) {
		this.screen = screen;
	}

	public class buttonListenner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setScreen(true);
		}
		
	}

}
