package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Application File For Server
 */
public class ServerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Sever Running");
		
		Server myServer = new Server(ServerProtocol.DEFAULT_PORT);
		
		myServer.execute();
			
		
	}

}
