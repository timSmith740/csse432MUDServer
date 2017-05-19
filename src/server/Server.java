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
import fileHandlers.AccountLoader;
import fileHandlers.AccountSaver;
import fileHandlers.ContainerLoader;
import fileHandlers.DoorLoader;
import fileHandlers.RoomLoader;
import fileHandlers.WorldLoader;
import gameMap.GameMap;
import gameMap.Room;
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
		WorldLoader loader = new WorldLoader();
		WorldObject[][] world = loader.readFile();
		DoorLoader doorLoader = new DoorLoader(world);
		doorLoader.lockDoors();
		
		RoomLoader roomLoader = new RoomLoader();
		List<Room> rooms = roomLoader.readFile();
		
		this.theWorld = new GameMap(world, rooms);
		this.users = new ArrayList<>();
		this.accounts = new HashMap<>();
		this.accounts.put("tester", "test");
		this.containers = new HashMap<>();
		this.loggedOn = new ArrayList<>();
		this.loggedOff = new ArrayList<>();
		ContainerLoader containerLoader = new ContainerLoader(this.theWorld, this.containers);
		containerLoader.fill();
		AccountLoader accountLoader = new AccountLoader();
		accountLoader.loadAccounts(this.users, this.theWorld, this.accounts);
		
		List<String> dialogue = new ArrayList<>();
		dialogue.add("What's up");
		dialogue.add("Hello");
		
		CommonFolk wilkin = new CommonFolk("Wilkin", dialogue, weaponGenerator.createWeapon(1));
		wilkin.setStats(1, 1, 1, 1);
		ShopKeeper sid = new ShopKeeper("Sid", dialogue, weaponGenerator.createWeapon(1));
		sid.setStats(1, 1, 1, 1);
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
			AccountSaver saver = new AccountSaver(this.users);
			Thread accountSaver = new Thread(saver);
			accountSaver.start();
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
