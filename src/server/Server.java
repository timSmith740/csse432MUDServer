package server;


import java.awt.Point;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import characters.CommonFolk;
import characters.Container;
import characters.ShopKeeper;
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
	Map<Container, Point> containers;
	ArrayList<Account> loggedOn;
	ArrayList<Account> loggedOff;
	
	
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
		this.containers = new HashMap<Container, Point>();
		this.loggedOn = new ArrayList<Account>();
		this.loggedOff = new ArrayList<Account>();
		Container chest = new Container("Joe's Box", 1);
		for(int counter=0; counter<2; counter++){
			Weapon sword = weaponGenerator.createWeapon(1);
			chest.addToInventory(sword);
		}
		System.out.println(chest.getInvString());
		Point chestPoint = new Point(3,2);
		containers.put(chest, chestPoint);
		this.theWorld.AddGameObjectAtLocation(chest, chestPoint);
		List<String> dialogue = new ArrayList<>();
		dialogue.add("What's up");
		dialogue.add("Hello");
		
		CommonFolk wilkin = new CommonFolk("Wilkin", dialogue);
		ShopKeeper sid = new ShopKeeper("Sid Stamm", dialogue);
		this.theWorld.AddGameObjectAtLocation(wilkin, new Point(2, 3));
		this.theWorld.AddGameObjectAtLocation(sid, new Point(2, 3));
		sid.addWares(weaponGenerator.createWeapon(1), 1);
	}
	
	public void execute(){
		//Connect to server
		try{
			mySocket = new ServerSocket(Port);
			WorldUpdater updater = new WorldUpdater(this.theWorld, this.containers);
			Thread updateRunner = new Thread(updater);
			updateRunner.start();
			System.out.println("Server running on port: "+Port);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		while(true){
			//When new client connects, add new client handler
			
			try {
				Socket client = this.mySocket.accept();
				ClientHandler myHandler = new ClientHandler(this, client,this.theWorld, this.users, this.accounts, this.loggedOn, this.loggedOff);
				Thread runner = new Thread(myHandler);
				runner.start();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	}
}
