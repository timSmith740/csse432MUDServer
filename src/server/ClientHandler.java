package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import characters.Player;
import gameMap.GameMap;





/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Class for handling specific client requests.
 */
public class ClientHandler implements Runnable {
	Server myServer;
	Socket mySocket;
	Socket chatSocket;
	OutputStream out;
	InputStream in;
	OutputStream chatOut;
	public Player player;
	GameMap theWorld;
	boolean loggedIn;
	boolean registering;
	Account currentUser;
	ArrayList<Account> users;
	ArrayList<Account> loggedOn;
	ArrayList<Account> loggedOff;
	HashMap<String, String> accounts;
	
	public ClientHandler(Server theServer, Socket theSocket, Socket chatSocket) throws IOException{
		this.myServer=theServer;
		this.mySocket= theSocket;
		this.in = this.mySocket.getInputStream();
		this.out =this.mySocket.getOutputStream();
		this.player = new Player();
		this.theWorld= theServer.getTheWorld();
		this.loggedIn = false;
		this.registering = false;
		this.users = theServer.getUsers();
		this.accounts = theServer.getAccounts();
		this.loggedOff = theServer.getLoggedOff();
		this.loggedOn = theServer.getLoggedOn();
		this.chatSocket = chatSocket;
		this.chatOut = this.chatSocket.getOutputStream();
	
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
				if (this.registering){
					Player result = ServerProtocol.makePlayer(command, this.users, this.accounts, this.theWorld);
					if (result == null){
						this.out.write("Problem occured making character. Please try again.".getBytes());
						break command;
					}
					this.player = result;
					this.registering = false;
					this.loggedIn = true;
					String user = command.split(" ")[4];
					Account foundAccount = ServerProtocol.findAccount(user, this.users);
					this.currentUser = foundAccount;
					this.currentUser.setHandler(this);
					this.loggedOn.add(this.currentUser);
					this.loggedOff.remove(this.currentUser);
					String myString = ServerProtocol.update(this.player,"Logged on");
					this.out.write(myString.getBytes());
					break command;
				}
				if (this.loggedIn == false){
					String result = ServerProtocol.logInHandler(command, this.users, this.accounts, this.player, this.theWorld);
					if (result.equals("Logged on")){
						this.loggedIn = true;
						String user = command.split(" ")[1];
						Account foundAccount = ServerProtocol.findAccount(user, this.users);
						if (this.loggedOn.contains(foundAccount)){
							this.out.write("User already logged on".getBytes());
							this.loggedIn = false;
							break command;
						}
						System.out.println(foundAccount);
						this.player = foundAccount.getCharacter();
						this.currentUser = foundAccount;
						this.currentUser.setHandler(this);
						this.loggedOn.add(this.currentUser);
						this.loggedOff.remove(this.currentUser);
					}
					String registerString = result.split(" ")[0];
					if (registerString.equals("Registered.")){
						this.loggedIn = false;
						this.registering = true;
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
						this.chatOut.write("quit".getBytes());
						this.chatSocket.close();
						this.currentUser.setHandler(null);
						this.loggedOff.add(this.currentUser);
						this.loggedOn.remove(this.currentUser);
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

	public void chatMessage(String message) {
		try {
			this.chatOut.write(message.getBytes());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
	}

}
