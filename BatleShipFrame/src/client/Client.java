package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	
	Socket s;		  
	public PrintWriter out;   
	public BufferedReader in; 
	
	public Client() {
		 try {
			s 	   = new Socket("127.0.0.1", 1234);
			out   = new PrintWriter(s.getOutputStream(),true);
			 in    = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void lecture() throws IOException {
		System.out.println(in.readLine());
	}
	
	public void sendPosShip(String posShip) {
		out.println(posShip);
	}
	
	public void closeSockets() throws IOException {
		s.close();
	}
	
//	public static void main(String[] args) {
//		try {
//			Socket s 		  = new Socket("127.0.0.1", 1234);
//			PrintWriter out   = new PrintWriter(s.getOutputStream(),true);
//			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//			
//			System.out.println(in.readLine());
//			boolean tour, gameState;
//			
//			Controller c = new Controller();
//			c.deployShips();
//			out.println(c.posShip);
//			
//			gameState = Boolean.parseBoolean(in.readLine());
//			tour 	  = Boolean.parseBoolean(in.readLine());
//			
//			while(gameState) {
//				boolean hit;
//				if(tour) {
//					c.game();
//					Coordinate coord = new Coordinate(c.attack);
//					out.println(c.attack);
//					hit = Boolean.parseBoolean(in.readLine());
//					if(hit) {
//						System.out.println("Cible touchée");
//						c.mapFight.map[coord.getLine()][coord.getColumn()] = "x";
//					}
//					else {
//						System.out.println("Cible manquée");
//						c.mapFight.map[coord.getLine()][coord.getColumn()] = "|";
//					}
//					c.map.showMap();
//					c.mapFight.showMap();
//				}
//				else {
//					hit = Boolean.parseBoolean(in.readLine());
//					if(hit) {
//						System.out.println("Navire touché");
//						Coordinate coord = new Coordinate(in.readLine());
//						c.map.map[coord.getLine()][coord.getColumn()] = "x";
//						c.map.showMap();
//					}
//					
//				}
//				tour      = Boolean.parseBoolean(in.readLine());
//				gameState = Boolean.parseBoolean(in.readLine());
//			}
//		
//			s.close();
//		} catch (Exception e) {
//			
//		}
//
//	}

}
