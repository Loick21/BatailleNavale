package Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Button extends JButton {
	
	private String path;
	private Image image;
	
	public Button() {
		super();
	}
	
	public Button(String path) {
		super();
	    try {
			 image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	    this.setSize(180, 100);
	    this.setLocation(200, 290);
	    this.setOpaque(false);
	    this.setBorder(null);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
	    g2d.drawImage(image,200, 290,180,100,this);
	}
	
	

}
