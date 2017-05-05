package server;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;

import characters.GameCharacter;
import characters.Player;
import gameMap.GameMap;
import server.ServerProtocol.Direction;


/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Static Methods across all server files
 */
public class ServerProtocol {


	public static int DEFAULT_PORT = 5555;
	public static String SERVER_INFO = "localhost";
	public static final String INVALID_SYNTAX="Incorrect Syntax";
	
	public enum Direction{
		North, South, East, West
	}
	
	//To recieve a file
	public static byte[] recieve(InputStream in) throws IOException{
		int nRead=-1;
		byte [] result = new byte[0];
		byte [] tempBuff = new byte[1024];
		int y = 0;
		do{
			y = (nRead = in.read(tempBuff,0, tempBuff.length));
			byte [] newBuff = new byte[result.length+nRead];
			System.arraycopy(result, 0, newBuff, 0, result.length);
			System.arraycopy(tempBuff, 0, newBuff, result.length, nRead);
			result = newBuff;
		}while(y>=1024);
		return result;
	}
	
	//parse commands
	public static String CommandHandler(String command, Player player,GameMap map){
		
		String[] subparts=command.split(" ");
		switch(subparts[0].toLowerCase()){
		case "quit":
			return "quit";
					
		case "attack":
			if(subparts.length==2){
			String sendback = "attack:"+subparts[1];
			return(sendback);
			}
			return(ServerProtocol.INVALID_SYNTAX);
			
		
			
		case "run":
			if(subparts.length==2){
			String sendback = "run:"+subparts[1];
			return(sendback);
			}
			return(ServerProtocol.INVALID_SYNTAX);
			

			
		case "move":
			String sendback = "Cannot Move to that position";
			if(subparts.length==3||subparts.length==2){
				String dirCommand = subparts[1].toLowerCase();
				Direction dir;
				switch(dirCommand){
					case "north": 
						dir=Direction.North;
						break;
					case "south":
						dir=Direction.South;	
						break;
					case "west":
						dir=Direction.West;
						break;
					case "east":
						dir = Direction.East;
						break;
					default:
						return(ServerProtocol.INVALID_SYNTAX);
				}
				
				int distance;
				if(subparts.length==3){
					distance = Integer.parseInt(subparts[2]);
				}else{
					distance = 1;
				}
				
				boolean sucessfull = map.moveByDistance(player,  dir,distance);
				if(sucessfull){
					sendback = "Your character has moved to that position";
				}else{
					sendback = "Cannot Move to that position";
				}
				return(sendback);
			}
			return(ServerProtocol.INVALID_SYNTAX);
		
		case "status":
			Point spot = player.getLocation();
			sendback = "The character is at cordinates:"+spot.x+" "+spot.y;
			return (sendback);
			
			
		default:
			return(ServerProtocol.INVALID_SYNTAX);
		}
		
	}
	
	

}
