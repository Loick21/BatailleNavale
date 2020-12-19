package Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Controller.Controller;
import Panel.gamePanel;

public class gameFrame extends JFrame implements WindowListener{
	
	ImageIcon image = new ImageIcon("Ressources/icon.png");
	gamePanel panel_game;

	public gameFrame(Controller c) throws IOException {
		panel_game = new gamePanel(c);
	    this.setTitle("Bataille navalle game");
		this.setSize(940, 640);
		this.setResizable(false);
		this.setLocation(500, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(panel_game.getPanel());
	    this.setIconImage(image.getImage());
	    this.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
