package Serveur;

import java.util.List;

public class Map {
	
	public String map [][];

	public Map() {
		map = new String [11][11];
		this.createMap();
	}
	
	private void createMap() {
		
		String columnMap [] = {" ","A","B","C","D","E","F","G","H","I","J"};   
		for (int i = 0; i < map.length; i++) {
			map[0][i] = columnMap[i] ; 
		}
		for (int i = 1; i < map.length; i++) {
			map[i][0] = String.valueOf(i); 
		}
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				map[i][j] = "~";
			}
		}
		
	}
	
	public void showMap() {
		
		System.out.println("--- Map ---" + "\n");
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	public void updateMap(List<Ship>fleetShip) {
		
		for (Ship ship : fleetShip) {
			if(ship.isVertical()) {
				for (int i1 = ship.getStart().getLine(); i1 < ship.getLength() + ship.getStart().getLine(); i1++) {
					map[i1][ship.getStart().getColumn()] = "■";
					System.out.println("V");
				}
			}
			else {
				for (int i1 = ship.getStart().getColumn(); i1 < ship.getLength() + ship.getStart().getColumn(); i1++) {
					System.out.println("H");
					map[ship.getStart().getLine()][i1] = "■";
				}
			}
		} 
	}
	



}
