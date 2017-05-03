package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import characters.Player;





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
	
	public ClientHandler(Server theServer, Socket theSocket) throws IOException{
		this.myServer=theServer;
		this.mySocket= theSocket;
		this.in = this.mySocket.getInputStream();
		this.out =this.mySocket.getOutputStream();
		this.player = new Player();
	
	}
	
	@Override
	public void run() {
		//State that is is connected
		System.out.println("Connected to: "+ this.mySocket.getRemoteSocketAddress());
		
		//Recieve a command 
		while(true){
			try {
				int c = this.in.read();
				StringBuilder builder = new StringBuilder();
				while(c!='\n'&& c!=-1){
					builder.append((char)c);
					c = this.in.read();
				}
				String command = builder.toString();
				//Process Command in Command Handler
				String result = ServerProtocol.CommandHandler(command, this.player);
				
				//Close Connection if necassary
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
