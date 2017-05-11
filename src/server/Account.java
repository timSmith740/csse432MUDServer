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
}
