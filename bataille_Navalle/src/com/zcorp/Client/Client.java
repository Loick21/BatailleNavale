package com.zcorp.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.zcorp.Thread.ListeningThread;

public class Client {
	
	public static void main(String[] args) {
				
		
		try {
			Socket s 		  = new Socket("127.0.0.1", 1234);
			PrintWriter out   = new PrintWriter(s.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			System.out.println(in.readLine());
			boolean tour, gameState;
			
			Controller c = new Controller();
			c.deployShips();
			out.println(c.posShip);
			
			gameState = Boolean.parseBoolean(in.readLine());
			tour 	  = Boolean.parseBoolean(in.readLine());
			
			while(gameState) {
				boolean hit;
				if(tour) {
					c.game();
					Coordinate coord = new Coordinate(c.attack);
					out.println(c.attack);
					hit = Boolean.parseBoolean(in.readLine());
					if(hit) {
						System.out.println("Cible touchée");
						c.mapFight.map[coord.getLine()][coord.getColumn()] = "x";
					}
					else {
						System.out.println("Cible manquée");
						c.mapFight.map[coord.getLine()][coord.getColumn()] = "|";
					}
					c.map.showMap();
					c.mapFight.showMap();
				}
				else {
					hit = Boolean.parseBoolean(in.readLine());
					System.out.println(hit);
					if(hit) {
						System.out.println("Navire touché");
						Coordinate coord = new Coordinate(in.readLine());
						c.map.map[coord.getLine()][coord.getColumn()] = "x";
						c.map.showMap();
					}
					
				}
				tour      = Boolean.parseBoolean(in.readLine());
				gameState = Boolean.parseBoolean(in.readLine());
			}
		
			s.close();
		} catch (Exception e) {
			System.out.println("Serveur déconnecté");
		}
		
	}

}
