package server;

/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Application File For Server
 */
public class ServerApp {

	public static void main(String[] args) {
		System.out.println("Server Running");
		int port =ServerProtocol.DEFAULT_PORT;
		int chatPort=ServerProtocol.CHAT_PORT;
		if(args.length==2){
			port= Integer.parseInt(args[0]);
			chatPort=Integer.parseInt(args[1]);
		}
		Server myServer = new Server(port, chatPort);
		
		myServer.execute();
		
	}

}
