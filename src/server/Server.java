package server;


import java.awt.Point;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import characters.Player;
import characters.Container;
import characters.GameCharacter;
import fileHandlers.worldLoader;
import gameMap.GameMap;
import items.Weapon;
import items.weaponGenerator;
import worldObjects.WorldObject;


/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Main class for creating a server
 */
public class Server {
	int Port;
	private ServerSocket mySocket;
	GameMap theWorld;
	ArrayList<Account> users;
	HashMap<String, String> accounts;
	
	
	public Server(int Port){
		this.Port=Port;
		//Load world
		worldLoader loader = new worldLoader();
		WorldObject[][] theWorld = loader.readFile();
		this.theWorld = new GameMap(theWorld);
		Account test = new Account("tester", "test");
		this.users = new ArrayList<Account>();
		this.users.add(test);
		this.accounts = new HashMap<String, String>();
		this.accounts.put("tester", "test");
		Container chest = new Container("Joe's Box");
		for(int counter=0; counter<5; counter++){
			Weapon sword = weaponGenerator.createWeapon(1);
			chest.addToInventory(sword);
		}
		this.theWorld.AddGameObjectAtLocation(chest, new Point(3,2));
	}
	
	public void execute(){
		//Connect to server
		try{
			mySocket = new ServerSocket(Port);
			System.out.println("Server running on port: "+Port);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		while(true){
			//When new client connects, add new client handler
			
			try {
				Socket client = this.mySocket.accept();
				ClientHandler myHandler = new ClientHandler(this, client,this.theWorld, this.users, this.accounts);
				Thread runner = new Thread(myHandler);
				runner.start();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	}
}
