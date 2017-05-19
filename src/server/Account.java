package server;

import characters.Player;

public class Account {
	String username;
	String password;
	Player character;
	ClientHandler handler;
	
	public Account(){
		
	}
	
	public Account(String user, String pass){
		this.username = user;
		this.password = pass;
	}
	
	public void addCharacter(Player player){
		this.character = player;
	}
	
	public Player getCharacter(){
		return this.character;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPass(){
		return this.password;
	}
	
	public void addPass(String pass){
		this.password = pass;
	}
	
	public void addUser(String user){
		this.username = user;
	}
	public void messageClient(String message){
		if(this.handler!=null){
			this.handler.chatMessage(message);
		}
	}
	public void setHandler(ClientHandler handler){
		this.handler=handler;
	}
}
