package com.zcorp.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.zcorp.map.Map;

public class Controller {
	
	Map map,mapFight;
	List<Ship>  fleetShip = new ArrayList<Ship>();
	Scanner sc = new Scanner(System.in);
	String posShip = "";
	String attack;
	
	public Controller() {
		map 	 = new Map();
		mapFight = new Map();
		map.showMap();
	}
	
	public void game(){
		System.out.print("Entrer les coordonnées de votre attaque : ");
		attack = sc.nextLine();
		while(!Coordinate.checkCoordinate(attack)) {
			System.out.println ("*** Entrer incorrect *** ");
			System.out.print("Entrer les coordonnées de votre attaque : ");
			attack = sc.nextLine();
		}
	}
		
	
	public void deployShips() {
		
		int k [] = {5,4,3,3,2};

		for (int i = 0; i < 5; i++) {
			
			boolean construction = true;
			
			while (construction) {
				System.out.println("\n--- Entrez un Navire de longueur : " + k[i] + " ---");
				System.out.print("\tEntrez la première coordonnée : ");
				String c1 = sc.nextLine();
				
				if(!Coordinate.checkCoordinate(c1)) {
					System.out.println("*** Mauvaise entrée de Coordonné **** ");
					continue;
				}
				
				System.out.print("\tEntrez l'horientation du navire : ");
				String orientation  = sc.nextLine();
				if(!Ship.checkOrientation(orientation)) {
					System.out.println("\n*** Mauvaise entrée de Coordonné **** ");
					continue;
				}
				
				if(!Ship.isValidShip(new Coordinate(c1), k[i],orientation)) {
					System.out.println("\n*** Le bateau ne pas être placé sur la grille **** ");
					continue;
				}
				
				if(Ship.isOverlaps(new Ship(new Coordinate(c1),k[i],orientation), this.fleetShip)) {
					System.out.println("\n*** Le bateau chevauche d'autres navires **** ");
					continue;
				}
				 
				posShip += c1 + String.valueOf(k[i]) + orientation + "-";
				fleetShip.add(new Ship(new Coordinate(c1),k[i],orientation));
				map.updateMap(fleetShip);
				map.showMap();
				construction = false;
			}
			
		}

	}
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
