package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur {

	public static void main(String[] args) {
		try {
			ServerSocket ecoute = new ServerSocket(1234);
			while(true) {
				Socket client1 = ecoute.accept();
				Socket client2 = ecoute.accept();
				System.out.println("Les deux clients connectés");
				new ThreadServeur(client1, client2).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
