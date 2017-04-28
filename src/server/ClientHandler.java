package server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;





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
	
	public ClientHandler(Server theServer, Socket theSocket) throws IOException{
		this.myServer=theServer;
		this.mySocket= theSocket;
		in = mySocket.getInputStream();
		out =mySocket.getOutputStream();
	
	}
	
	@Override
	public void run() {
		//State that is is connected
		System.out.println("Connected to: "+ mySocket.getRemoteSocketAddress());
		
		//Recieve a command 
		while(true){
			try {
				int c = in.read();
				StringBuilder builder = new StringBuilder();
				while(c!='\n'&& c!=-1){
					builder.append((char)c);
					c = in.read();
				}
				String command = builder.toString();
				//Process Command in Command Handler
				String result = ServerProtocol.CommandHandler(command);
				
				//Close Connection if necassary
				if(result.equals("quit")){
					System.out.println("Closing Connection To: "+ mySocket.getRemoteSocketAddress());
					try{
						mySocket.close();
						break;
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				out.write(result.getBytes());
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
