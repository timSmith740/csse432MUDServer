package server;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import characters.Player;
import fileHandlers.worldLoader;
import gameMap.GameMap;
import worldObjects.WorldObject;





/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Class for handling specific client requests.
 */
public class ClientHandler implements Runnable {
	Server myServer;
	Socket mySocket;
	OutputStream out;
	InputStream in;
	Player player;
	GameMap theWorld;
	Boolean loggedIn;
	ArrayList<Account> users;
	HashMap<String, String> accounts;
	
	public ClientHandler(Server theServer, Socket theSocket,GameMap theWorld, ArrayList<Account> theUsers, HashMap<String, String> theAccounts) throws IOException{
		this.myServer=theServer;
		this.mySocket= theSocket;
		this.in = this.mySocket.getInputStream();
		this.out =this.mySocket.getOutputStream();
		this.player = new Player();
		this.theWorld= theWorld;
		this.loggedIn = false;
		this.users = theUsers;
		this.accounts = theAccounts;
		
		//Temporarly 
		Point startingPoint = new Point(2,2);
		this.theWorld.AddGameObjectAtLocation(player, startingPoint);
		
	
	}
	
	@Override
	public void run() {
		//State that is is connected
		System.out.println("Connected to: "+ this.mySocket.getRemoteSocketAddress());
		
		//Recieve a command 
		while(true){
			command: try {
				int c = this.in.read();
				StringBuilder builder = new StringBuilder();
				while(c!='\n'&& c!=-1){
					builder.append((char)c);
					c = this.in.read();
				}
				String command = builder.toString();
				if (this.loggedIn == false){
					String result = ServerProtocol.logInHandler(command, this.users, this.accounts);
					if (result.equals("Logged on")){
						this.loggedIn = true;
					}
					this.out.write(result.getBytes());
					break command;
				}
				//Process Command in Command Handler
				String result = ServerProtocol.CommandHandler(command, this.player,this.theWorld);
				
				//Close Connection if necessary
				if(result.equals("quit")){
					System.out.println("Closing Connection To: "+ this.mySocket.getRemoteSocketAddress());
					try{
						this.mySocket.close();
						break;
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				this.out.write(result.getBytes());
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
