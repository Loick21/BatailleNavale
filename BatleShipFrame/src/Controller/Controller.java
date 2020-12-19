package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Component.Coordinate;
import Component.Ship;
import Frame.gameFrame;
import Frame.mainFrame;
import client.Client;

public class Controller extends Thread {
	
	mainFrame frame_main;
	gameFrame frame_game;
	List<Ship>  fleetShip = new ArrayList<Ship>();

	boolean isVertical = false;
	private boolean placeShip = false;
	boolean changeScreen;
	public int k = 5;
	public Client joueur;
	public boolean hit;
	public boolean gameState,tour;
	boolean shoot = false;
	Coordinate c;
	
	public Controller() throws IOException {
		frame_main = new mainFrame();
		changeFrame();
	}
	
	public void changeFrame() throws IOException {
		while(!frame_main.panel_main.isScreen()){}
		frame_main.dispose();
		joueur = new Client();
		joueur.lecture();
		frame_game = new gameFrame(this);
	}
	
	public boolean isPlaceShip() {
		return placeShip;
	}

	public void setPlaceShip(boolean placeShip) {
		this.placeShip = placeShip;
	}
	
	public void getstate(){
		try {
			gameState = Boolean.parseBoolean(joueur.in.readLine());
			tour =  Boolean.parseBoolean(joueur.in.readLine());
			System.out.println("gameState " + gameState );
			System.out.println("joueur tour " + tour );
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void setShooted(Coordinate c) throws IOException {
//		System.out.println("Tir depuis le constroller");
//		gameState = Boolean.parseBoolean(joueur.in.readLine()); 
//		tour =  Boolean.parseBoolean(joueur.in.readLine());	
		
		if(gameState) {
			if(tour) {
				String coord = String.valueOf((char)(c.getColonne() + 64) )+ String.valueOf(c.getLigne());
				System.out.println(coord);
				joueur.out.println(coord);
				hit = Boolean.parseBoolean(joueur.in.readLine());
				System.out.println("Hit :" + hit );
				if(hit) {
					System.out.println("Cicle touché");
				}
				else System.out.println("Cicle manquée");
			}
			else {
				hit = Boolean.parseBoolean(joueur.in.readLine());
				System.out.println("hit : " + hit);
				if(hit) {
					System.out.println("Navire touché");
					Coordinate coord = new Coordinate(joueur.in.readLine());
					System.out.println(coord.getColonne() + "" + coord.getLigne() + "");
				}
			}
			
			tour =  Boolean.parseBoolean(joueur.in.readLine());
			gameState = Boolean.parseBoolean(joueur.in.readLine());
			System.out.println("tour" + tour);
			System.out.println("stzte" + gameState);
		}
	
	}
	
	
	@Override
	public void run() {
		currentThread().setName("Joueur");;

		boolean a = true;
		while(a) {
			if(k==0) {
				getstate();
				a = false;
			}
		}

	}


}
