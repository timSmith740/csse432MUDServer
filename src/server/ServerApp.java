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
		
		Server myServer = new Server(ServerProtocol.DEFAULT_PORT, ServerProtocol.CHAT_PORT);
		
		myServer.execute();
			
		
	}

}
