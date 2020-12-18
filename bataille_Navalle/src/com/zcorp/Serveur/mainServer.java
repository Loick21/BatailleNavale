package com.zcorp.Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.zcorp.Thread.ThreadServeur;

public class mainServer {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ecoute = new ServerSocket(1234);
			while(true) {
				Socket client1 = ecoute.accept();
				Socket client2 = ecoute.accept();
				//new ThreadServeur(client1, client2).start();
				Partie partie = new Partie(client1, client2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
