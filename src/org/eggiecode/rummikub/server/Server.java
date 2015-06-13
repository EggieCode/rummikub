package org.eggiecode.rummikub.server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

	public static void main(String[] args) {
		new Server();

	}
	public Server(){
		
		try{
			ServerSocket serverSocket = new ServerSocket(8000);
			int client = 1;
			while (true){
				Socket socket = serverSocket.accept();
				
				HandleAGame task = new HandleAGame(socket, socket);
				
				
			}
		}
		catch(IOException ex){
			
		}
		
		
		
		
	}
	
}
class HandleAGame implements Runnable{
private Socket player1;
private Socket player2;
private DataInputStream fromPlayer1; 
private DataOutputStream toPlayer1; 
private DataInputStream fromPlayer2; 
private DataOutputStream toPlayer2;
public HandleAGame(Socket player1 , Socket player2){
	this.player1 = player1;
	this.player2 = player2;
}

	@Override
	public void run() {
		try{
			DataInputStream fromPlayer1	= new DataInputStream(player1.getInputStream());
			DataOutputStream toPlayer1  = new DataOutputStream(player1.getOutputStream());
			DataInputStream fromPlayer2 = new DataInputStream(player2.getInputStream());
			DataOutputStream toPlayer2 = new DataOutputStream(player2.getOutputStream());
			
		}
		catch(IOException e){
			System.err.println(e); 
		}
		
	}
	
	
	
}
