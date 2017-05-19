package server;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import characters.CommonFolk;
import characters.GameCharacter;
import characters.GameObject;
import characters.Player;
import characters.ShopKeeper;
import gameMap.GameMap;
import items.Gold;
import items.Item;
import items.Weapon;


/*
 * Joseph Militello
 * Logan Erexson
 * Tim Smith
 * Static Methods across all server files
 */
public class ServerProtocol {


	public static int DEFAULT_PORT = 5555;
	public static int CHAT_PORT = 5556;
	public static String SERVER_INFO = "localhost";
	public static final String INVALID_SYNTAX="Incorrect Syntax";
	public static final String INVALID_TARGET="Invalid Target";
	public static final int RESPAWN_TIME=30000;
	
	public enum Direction{
		North, South, East, West
	}
	
	//To receive a file
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
	
	public static String update(Player player, String message){
		return player.characterStats()+"@"+player.getInvString()+"@"+player.getEquString()+"@"+message;
	}
	
	//parse commands
	public static String CommandHandler(String command, Player player,GameMap map){
		
		String[] subparts=command.split(" ");
		switch(subparts[0].toLowerCase()){
		case "quit":
			return "quit";
					
		case "attack":
			if (subparts.length == 3){
				int position = Integer.parseInt(subparts[1]) - 1;
				List<GameObject> objects = map.checkForObjects(player);
				GameObject defendObject = objects.get(position);
				if(!GameCharacter.class.isAssignableFrom(defendObject.getClass())){
					return "You can't attack an object!";
				}
				if(Player.class.isAssignableFrom(defendObject.getClass())){
					return "You can't attack another adventurer!";
				}
				GameCharacter defender = (GameCharacter) defendObject;
				Weapon attackingWeapon= (Weapon) player.getEquipped(Integer.parseInt(subparts[2]) - 1);
				int damage = determineAttack(player,defender,attackingWeapon);
				
				StringBuilder result = new StringBuilder();
				if(damage == 0){
					result.append("You missed "+defender.getName()+"!\n");
				}
				else{
					result.append("You hit " +defender.getName()+ " for " +damage+ " points of damage.\n");
					defender.dealDamage(damage, map);
				}
				if(CommonFolk.class.isAssignableFrom(defendObject.getClass())){
					CommonFolk npc = (CommonFolk) defendObject;
					npc.addToHostile(player);
				}
				if(!defender.isAlive()){
					result.append(defender.getName()+" died!\n");
				}
				else{
					int npcDamage =determineAttack(defender, player, defender.getWeapon());
					if(npcDamage==0){
						result.append(defender.getName()+" attempted to attack you and missed!");
					}
					else{
						result.append(defender.getName()+ " attacked you for "+ npcDamage+ " points of damage");
						player.dealDamage(npcDamage, map);
			
					}
					if(!player.isAlive()){
						result.append("\n"+defender.getName()+" killed you!\nYou have been respawned");
					}
				}				
				return result.toString();
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
			
		case "update":
			//String update=  player.characterStats()+"@"+player.getInvString()+"@"+player.getEquString()+"@Stats returned";
			return update(player,"Stats returned");
		
		case "health":
			return player.getHealth()+"@HealthReturned";
			
		case "check":
			if (subparts.length == 2){
				int position = Integer.parseInt(subparts[1]) - 1;
				List<GameObject> objects = map.checkForObjects(player);
				GameObject obj = objects.get(position);
				if(GameCharacter.class.isAssignableFrom(obj.getClass())){
					GameCharacter character = (GameCharacter) obj;
					if(character.isAlive()){
						return "They aren't going to let you just look through their things!";
					}
				}
				if (obj.getGoldAmount() > 0){
					sendback = obj.getInventory().toString() + " Gold=" +Integer.toString(objects.get(position).getGoldAmount());
				} else {
					sendback = obj.getInventory().toString();
				}
				return sendback;
			} 
			else if(subparts.length == 3){
				if(subparts[1].equals("shop")){
					List<GameObject> objects = map.checkForObjects(player);
					String name = subparts[2];
					ShopKeeper shop = null;
					for(GameObject o : objects){
						if(o.getName().equals(name)){
							if(ShopKeeper.class.isAssignableFrom(o.getClass())){
								shop = (ShopKeeper) o;
								break;
							}
							return INVALID_TARGET;
						}
					}
					if(shop==null){
						return INVALID_TARGET;
					}
					return shop.getWaresString();
				}
			}
			else if(subparts.length == 1){
				return map.checkForObjects(player).toString();
			}
			return INVALID_SYNTAX;
		case "take":
			if (subparts.length == 3){
				int containerPosition = Integer.parseInt(subparts[1]) - 1;
				int itemPosition = Integer.parseInt(subparts[2]) - 1;
				List<GameObject> objects = map.checkForObjects(player);
				try{
					GameObject obj = objects.get(containerPosition);
					if(GameCharacter.class.isAssignableFrom(obj.getClass())){
						GameCharacter character = (GameCharacter) obj;
						if(character.isAlive()){
							return "They don't want to give that to you.";
						}
					}
					Item chosenItem = obj.getInventory().get(itemPosition);
					obj.removeFromInventory(chosenItem);
					player.addToInventory(chosenItem);
					return  update(player,chosenItem.toString()+" added to Inventory");
				} catch (IndexOutOfBoundsException e){
					if (objects.get(containerPosition).getGoldAmount() > 0){
						player.addToInventory(new Gold(objects.get(containerPosition).getGoldAmount()));
						objects.get(containerPosition).removeFromInventory(new Gold(objects.get(containerPosition).getGoldAmount()));
						return (Integer.toString(objects.get(containerPosition).getGoldAmount())+" Gold added to Inventory");
					}
					return("No item in that slot");
				}
			}
			return(ServerProtocol.INVALID_SYNTAX );
			
		case "equip":
			int itemPosition = Integer.parseInt(subparts[1]) - 1;
			Item chosenItem = player.getInventory().get(itemPosition);
			player.addToEquipment(chosenItem);
			player.removeFromInventory(chosenItem);
			return  update(player,chosenItem.toString()+" added to Equipment");
		case "talk":
			List<GameObject> objects = map.checkForObjects(player);
			String name = subparts[1];
			CommonFolk folk = null;
			for(GameObject o : objects){
				if(o.getName().equals(name)){
					if(CommonFolk.class.isAssignableFrom(o.getClass())){
						folk = (CommonFolk) o;
						break;
					}
					return INVALID_TARGET;
				}
			}
			if(folk==null||!folk.isAlive()){
				return INVALID_TARGET;
			}
			return folk.talk(player);
		case "look":
			List<GameObject> roomObjects = map.checkCurrentRoom(player);
			roomObjects.remove(player);
			return roomObjects.toString();
			
		case "gold":
			return player.getGoldAmount() + " Gold";
		case "buy":
			if(subparts.length==3){
				List<GameObject> gameObjects = map.checkForObjects(player);
				String shopName = subparts[1];
				ShopKeeper shop = null;
				for(GameObject o : gameObjects){
					if(o.getName().equals(shopName)){
						if(ShopKeeper.class.isAssignableFrom(o.getClass())){
							shop = (ShopKeeper) o;
							break;
						}
						return INVALID_TARGET;
					}
				}
				if(shop==null){
					return INVALID_TARGET;
				}
				return shop.purchaseItem(subparts[2], player);
			}
			return INVALID_SYNTAX;
		case "chat":
			StringBuilder sb = new StringBuilder();
			sb.append(": \"");
			sb.append(command.substring(subparts[0].length()+1));
			sb.append("\"");
			
			List<GameObject> recipients = map.checkCurrentRoom(player);
			recipients.remove(player);
			for(GameObject o : recipients){
				if(Player.class.isAssignableFrom(o.getClass())){
					Player otherPlayer = (Player) o;
					otherPlayer.hear(player.getName()+sb.toString());
				}
			}
			return "YOU"+ sb.toString() ;
			
		default:
			return(ServerProtocol.INVALID_SYNTAX);
		}
		
	}
	
	public static String logInHandler(String command, ArrayList<Account> users, HashMap<String, String> accounts, Player player, GameMap map){
		String[] subparts = command.split(" ");
		
		//Wrong command
		//if (subparts.length != 3){
		//	return(ServerProtocol.INVALID_SYNTAX);
		//}
		switch(subparts[0].toLowerCase()){
		case "login":
			String user = subparts[1];
			String pass = subparts[2];
			if (!accounts.containsKey(user)){
				return("No account. Please make an account");
			}
			
			if (!pass.equals(accounts.get(user))){
				return("Wrong password. Please try again");
			}
			return("Logged on");
		case "register":
			user = subparts[1];
			pass = subparts[2];
			if (accounts.containsKey(user)){
				return("Username already used. Please use a different name.");
			}
			Account account = new Account(user, pass);
			users.add(account);
			accounts.put(user, pass);
			return("Registered. Please choose a class: Fighter, Thief, or Wizard. Choose a preset and allocate two points.\n"
					+ "Save your option by entering [class] [stat] [stat] [Character name] [username]");
		default:
			return(ServerProtocol.INVALID_SYNTAX);
		}
		
	}
	
	public static Player makePlayer(String command, ArrayList<Account> users, HashMap<String, String> accounts, GameMap map){
		String[] subparts = command.split(" ");
		Player character = new Player();
		switch(subparts[0].toLowerCase()){
		case "fighter":
			if (subparts.length == 1){
				//return("CON: 2\nINT: 1\nSTR: 3\nDEX: 2");
			}
			if (subparts.length == 5){
				character.setStat("con", 2);
				character.setStat("dex", 2);
				character.setStat("intel", 1);
				character.setStat("str", 3);
			}
			break;
		case "thief":
			if (subparts.length == 1){
				//return("CON: 1\nINT: 2\nSTR: 2\nDEX: 3");
			}
			if (subparts.length == 5){
				character.setStat("con", 1);
				character.setStat("dex", 3);
				character.setStat("intel", 2);
				character.setStat("str", 2);
			}
			break;
		case "wizard":
			if (subparts.length == 1){
				//return("CON: 2\nINT: 3\nSTR: 1\nDEX: 2");
			}
			if (subparts.length == 5){
				character.setStat("con", 2);
				character.setStat("dex", 2);
				character.setStat("intel", 3);
				character.setStat("str", 1);
			}
			break;
		
		default:
			return(null);
		}
		character.increaseStat(subparts[1].toLowerCase());
		character.increaseStat(subparts[2].toLowerCase());
		character.setName(subparts[3]);
		Point startingPoint = new Point(2,2);
		character.setLocation(startingPoint);
		map.AddGameObjectAtLocation(character, startingPoint);
		Account foundAccount = findAccount(subparts[4], users);
		if (foundAccount == null){
			return(null);
		}
		foundAccount.addCharacter(character);
		character.setAccount(foundAccount);
		
		// REMOVE LATER
		character.addToInventory(new Gold(1));
		// REMOVE LATER
		return(character);
	}
	
	public static int determineAttack(GameCharacter character,GameCharacter defense, Weapon attackingWeapon){
		int playerAttack = character.getAttackBonus()+attackingWeapon.getAttackBonus();
		int defender = defense.getArmor();
		
		//Need to modify
		int max = playerAttack+defender;
		int min = 0;
//		System.out.println(playerAttack+" "+defender+" "+max);
		Random ranGen = new Random();
		int result = ranGen.nextInt(max-min+1)+min;
		if(result>=defender){
			int damage= attackingWeapon.getDamage(character);
	
			return damage;
		}
		
		//miss
		return 0;
		
	}
	
	public static Account findAccount(String user, ArrayList<Account> users){
		System.out.println(users.toString());
		for (Account current : users){
			if (current.username.equals(user)){
				return current;
			}
		}
		return null;
	}

}
