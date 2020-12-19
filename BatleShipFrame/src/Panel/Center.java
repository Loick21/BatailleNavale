package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Component.GridComponent;
import Component.GridComponentModel;

public class Center extends JPanel  {

	public GridComponent component;
	public GridComponent component_opponent;

	GridComponentModel componentModel 		   = new GridComponentModel();
	GridComponentModel componentModel_opponent = new GridComponentModel();

	public Center() {
		this.setLayout(new GridLayout(1, 2));
		component = new GridComponent(componentModel);
		component_opponent = new GridComponent(componentModel_opponent);
		this.add(component);
		this.add(component_opponent);

	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public void refresh() {
		component.repaint();
		component.repaint();
	}


}
