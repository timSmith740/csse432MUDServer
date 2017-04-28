package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Main class for creating a server
 */
public class Server {
	int Port;
	private ServerSocket mySocket;
	
	public Server(int Port){
		this.Port=Port;
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
				ClientHandler myHandler = new ClientHandler(this, client);
				Thread runner = new Thread(myHandler);
				runner.start();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
	}
}
