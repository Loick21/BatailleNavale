package com.zcorp.Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.zcorp.Client.Coordinate;
import com.zcorp.Client.Ship;
import com.zcorp.map.Map;

public class ThreadServeur extends Thread {
	
	PrintWriter out1,out2;
	BufferedReader in1,in2;
	private boolean gameState;
	List<Ship> fleetShipServeur1,fleetShipServeur2;
	Map map1,map2;
	
	public ThreadServeur(Socket client1, Socket client2) throws IOException {
		
		 out1 = new PrintWriter(client1.getOutputStream(),true);
		 out2 = new PrintWriter(client2.getOutputStream(),true);
		 
		 in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
		 in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
		 
		 map1 = new Map();
		 map2 = new Map();
		 
		 List<Ship> fleetShipServeur1 = new ArrayList<>();
		 List<Ship> fleetShipServeur2 = new ArrayList<>();
	}
	
	@Override
	public void run() {
		
		gameState = true;

		out1.println("Vous êtes le joueur 1");
		out2.println("Vous êtes le joueur 2");
		
		try {
			
			fleetShipServeur1 = toShip(in1.readLine().split("-"));
			fleetShipServeur2 = toShip(in2.readLine().split("-"));
			map1.updateMap(fleetShipServeur1);
			map2.updateMap(fleetShipServeur2);
			
			System.out.println("Map Joueur 1 ");
			map1.showMap();
			System.out.println("Map Joueur 2 ");
			map2.showMap();
			
			out1.println(String.valueOf(gameState));
			out2.println(String.valueOf(gameState));
			
			boolean tour1 = true, tour2 = false;

			out1.println(String.valueOf(tour1));
			out2.println(String.valueOf(tour2));
			
			
			while(gameState) {
				boolean hit = false;
				
				String coord1 = in1.readLine();
				Coordinate attack1 = new Coordinate(coord1); 
				for (int i = 0; i < fleetShipServeur2.size(); i++) {
					if(fleetShipServeur2.get(i).getHit(attack1)) {
						System.out.println("touchée");
						map2.map[attack1.getLine()][attack1.getColumn()] = "x";
						hit = true; 
					}
				}
				
				out1.println(String.valueOf(hit));
				out2.println(String.valueOf(hit));				
				if(hit) out2.println(coord1);
				
				hit = false;
				tour1 = !tour1;
				tour2 = !tour2;
				out1.println(String.valueOf(tour1));
				out2.println(String.valueOf(tour2));
				if(fleetShipServeur1.get(0).getPartHit() == 17 || fleetShipServeur2.get(0).getPartHit() == 17) {
					gameState = false;
					out1.println(String.valueOf(gameState));
					out2.println(String.valueOf(gameState));
					break;
				}
				else {
					out1.println(String.valueOf(gameState));
					out2.println(String.valueOf(gameState));
				}
				System.out.println("Map 2");
				map2.showMap();
				
				
				
				String coord2 = in2.readLine();
				Coordinate attack2 = new Coordinate(coord2);
				for (int i = 0; i < fleetShipServeur1.size(); i++) {
					if(fleetShipServeur1.get(i).getHit(attack2)) {
						System.out.println("touchée");
						map1.map[attack2.getLine()][attack2.getColumn()] = "x";
						hit = true; 
					}
				}
				out1.println(String.valueOf(hit));
				out2.println(String.valueOf(hit));	
				if(hit)out1.println(coord2);

				System.out.println("Map 1");
				map1.showMap();
				tour1 = !tour1;
				tour2 = !tour2;
				out1.println(tour1);
				out2.println(tour2);
				
				if(fleetShipServeur1.get(0).getPartHit() == 17 || fleetShipServeur2.get(0).getPartHit() == 17) {
					gameState = false;
					out1.println(String.valueOf(gameState));
					out2.println(String.valueOf(gameState));
					break;
				}
				else {
					out1.println(String.valueOf(gameState));
					out2.println(String.valueOf(gameState));
				}
			}
			
			out1.close();
			out2.close();
			in1.close();
			in2.close();
		}catch (IOException e) {
			System.out.println("Client déconnecté");
			out1.close();
			out2.close();
			try {
				in1.close();
				in2.close();
			} catch (IOException e1) {}
		} 	

	}
	
	public List<Ship> toShip(String [] posJ) {
		
		List<Ship> fleetShipServeur = new ArrayList<>();
		
		for (int i = 0; i < posJ.length; i++) {
			if(posJ[i].length() == 4) {
				System.out.println(posJ[i]);
				Coordinate coord = new Coordinate(posJ[i].substring(0,2));
				int length = Integer.parseInt(posJ[i].substring(2,3));
				Ship ship = new Ship(coord,length,posJ[i].substring(3,4));
				fleetShipServeur.add(ship);
			}
			else {
				System.out.println(posJ[i]);
				Coordinate coord = new Coordinate(posJ[i].substring(0,3));
				int length = Integer.parseInt(posJ[i].substring(3,4));
				Ship ship = new Ship(coord,length,posJ[i].substring(4,5));
				fleetShipServeur.add(ship);
			}
		}
		
		return fleetShipServeur;
	}
	
	
	
	
	public void instruction() {
		out1.println("--- Bienvenue dans la Bataille Navalle ---");
		out1.println("\t les cordonnées sont sous le format : A1, B2 ...");
		out1.println("\t Deux navires ne peuvent pas se chevaucher");
		out1.println("\n*** Vous êtes le joueur 1");
		
		out2.println("--- Bienvenue dans la Bataille Navalle ---");
		out2.println("\t les cordonnées sont sous le format : A1, B2 ...");
		out2.println("\t Deux navires ne peuvent pas se chevaucher");
		out2.println("\n*** Vous êtes le joueur 2");
		
		try {
			String a = "Joueur 1" + in1.readLine();
			out2.println(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
