package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Component.AbstractGridModel;
import Component.Coordinate;
import Component.GridComponent;
import Component.GridComponentListener;
import Component.GridComponentModel;
import Component.Ship;
import Controller.Controller;

public class gamePanel extends AbstractPanel implements ActionListener {
	
	private JLabel label_Colonne = new JLabel("Colonne");
	private JLabel label_ligne 	 = new JLabel("Ligne");
	private JLabel label_ship_number = new JLabel("Bateau restant");
	private JLabel label_ship 	     = new JLabel("5");
	private JButton button_submit    = new  JButton("Placer");
	private JCheckBox jcB_isVertical;
	
	private JComboBox combo_ligne,combo_colonne;

	private String option_line []    = {"1","2","3","4","5","6","7","8","9","10"};
	private String option_colonne [] = {"A","B","C","D","E","F","G","H","I","J"}; 
	private String posShip = "";
	private String column_s = "A";
    Font police = new Font("Arial", Font.BOLD, 14);

	JPanel top    = new JPanel();
	Center center = new Center();
    JPanel grid = new JPanel();
	
	private int column = 1; 
	private int ligne = 1;
	boolean isVertical = false;
	private String isVertical_s = "H";
	
	
	public gamePanel(Controller c) throws IOException {
		super(c);
		
		this.getPanel().setLayout(new BorderLayout());
		initTop();
		this.getPanel().add(top,BorderLayout.NORTH);
		this.getPanel().add(center,BorderLayout.CENTER);
		initListenner();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		while(true) {
			if(!getController().tour) {
				System.out.println("Le joueur 2");
			}
		}
	}
	
	public void shootCase(Coordinate coord) throws IOException {
		getController().setShooted(coord);
		center.component_opponent.getModel().getGrille().addShip(coord);

		if(getController().hit) {
			//center.component_opponent.getModel().getGrille().getHit(coord);
			System.out.println("Missile tiré point : " + center.component_opponent.getModel().getGrille().recevoirMissile(coord));
			center.refresh();
		}
		else {
			center.component_opponent.getModel().getGrille().recevoirMissile(coord);
		}
			
		getController().hit = false;
	}
	
	public void paintComponents(Graphics g){

	}
	
	public void initListenner(){
		
		center.component_opponent.addGridComponentListener(new GridComponentListener() {
			
			@Override
			public void caseSelected(Coordinate coordinates) {
				if(getController().k==0 && getController().gameState)
					try {
						shootCase(coordinates);
					} catch (IOException e) {
					}
			}
		});
	}
	
	
	public void initTop() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(10);
		top.setLayout(flowLayout);
		
		combo_ligne = new JComboBox(option_line);
		combo_colonne = new JComboBox(option_colonne);
		
		
		jcB_isVertical = new JCheckBox("isVertical");
		jcB_isVertical.setFont(police);
		jcB_isVertical.addActionListener(new checkBoxListenner());
		
		combo_ligne.setPreferredSize(new Dimension(70, 20));
		combo_colonne.setPreferredSize(new Dimension(70, 20));
		combo_ligne.addActionListener(new ItemAction_ligne());
		combo_colonne.addActionListener(new ItemAction_colonne());

		label_Colonne.setFont(police);
		label_ligne.setFont(police);
		label_ship_number.setFont(police);
		label_ship.setFont(police);
		
		button_submit.setFont(police);
		button_submit.setBackground(Color.LIGHT_GRAY);
		button_submit.addActionListener(new buttonListenner());
		
		top.add(label_ligne);
		top.add(combo_ligne);
		top.add(label_Colonne);
		top.add(combo_colonne);
		top.add(jcB_isVertical);
		top.add(button_submit);
		top.add(label_ship_number);
		top.add(label_ship);
		
	}
	
	
	public class buttonListenner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(column > 0 || ligne > 0) {
				Ship s = new Ship(new Coordinate(column,ligne),getController().k,isVertical);
				Coordinate c = new Coordinate(column,ligne);				
				if(center.component.getModel().getGrille().positionnerBateau(s,c, isVertical)) {
					posShip += String.valueOf(column_s) + String.valueOf(ligne) + String.valueOf(getController().k) + isVertical_s + "-";
					//System.out.println(posShip);
					getController().k-- ;
				}
				center.refresh();
			}
			
			if(getController().k == 0) {
				button_submit.setEnabled(false);
				getController().joueur.sendPosShip(posShip);
				center.refresh();
			}
		}
	}
	
	public class checkBoxListenner implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			isVertical = ((JCheckBox)e.getSource()).isSelected();
			isVertical_s = (isVertical)?"V":"H";
		}
		
	}
	
	 public class ItemAction_ligne implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
			      //System.out.println("événement déclenché sur : " + combo_ligne.getSelectedItem());
			      ligne = Integer.parseInt((String) combo_ligne.getSelectedItem());
			     // System.out.println("ligne : " + ligne + " " + combo_ligne.getSelectedItem());
			}               
	}
	 
	 public class ItemAction_colonne implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
			      //System.out.println("événement déclenché sur : " + combo_colonne.getSelectedItem());
				String a = (String) combo_colonne.getSelectedItem();
				column_s = a;
			//	System.out.println("a : " + a);
			    column = a.charAt(0)-64;
			   // System.out.println("column : " + column + ", " + combo_colonne.getSelectedItem());
			}               
	}



}
