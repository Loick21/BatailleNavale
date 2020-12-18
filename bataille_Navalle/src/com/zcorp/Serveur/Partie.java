package com.zcorp.Serveur;

import java.io.IOException;
import java.net.Socket;

import com.zcorp.Thread.ThreadServeur;

public class Partie {
	
	private int id = 0;
	Socket client1;
	Socket client2;
	
	public Partie(Socket client1,Socket client2) {
		this.id++;
		this.client1 = client1;
		this.client2 = client2;
		try {
			new ThreadServeur(client1, client2).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Socket getClient1() {
		return client1;
	}

	public void setClient1(Socket client1) {
		this.client1 = client1;
	}

	public Socket getClient2() {
		return client2;
	}

	public void setClient2(Socket client2) {
		this.client2 = client2;
	}
	
	

}
