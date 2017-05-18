package server;

import characters.Player;

public class Account {
	String username;
	String password;
	Player character;
	
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
		return username;
	}
	
	public String getPass(){
		return password;
	}
}
