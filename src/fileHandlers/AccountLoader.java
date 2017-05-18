package fileHandlers;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import characters.Player;
import gameMap.GameMap;
import items.Gold;
import items.Item;
import items.Weapon;
import server.Account;

public class AccountLoader {
	
	public void loadAccounts(ArrayList<Account> accounts, GameMap map, HashMap<String, String> users){
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/saveFiles/accountsAndPlayers.txt"));
			String lineRead = br.readLine();
			int counter = 0;
			Player player = new Player();
			int count = 0;
			Account account = new Account();
			while (lineRead != null){
				switch(counter){
				case  0:
					account = new Account();
					String[] subarray = lineRead.split(" ");
					account.addUser(subarray[0]);
					account.addPass(subarray[1]);
					users.put(subarray[0], subarray[1]);
					break;
				case 1:
					player = new Player();
					subarray = lineRead.split(" ");
					player.setName(subarray[0]);
					int xLocation = Integer.parseInt(subarray[1]);
					int yLocation = Integer.parseInt(subarray[2]);
					Point playerLocation = new Point(xLocation, yLocation);
					player.setLocation(playerLocation);
					map.AddGameObjectAtLocation(player, playerLocation);//Need to add map
					player.setArmor(Integer.parseInt(subarray[3]));
					player.addToInventory(new Gold(Integer.parseInt(subarray[4])));
					break;
				case 2:
					subarray = lineRead.split(" ");
					player.setStat("str", Integer.parseInt(subarray[0]));
					player.setStat("dex", Integer.parseInt(subarray[1]));
					player.setStat("con", Integer.parseInt(subarray[2]));
					player.setStat("intel", Integer.parseInt(subarray[3]));
					player.setCurrentHealth(Integer.parseInt(subarray[4]));
					player.setTotalHealth(Integer.parseInt(subarray[5]));
					break;
				case 3:
					subarray = lineRead.split(" ");
					if (subarray.length == 0){
						break;
					}
					for(int i = 0; i < subarray.length; i++){
						player.addKey(subarray[i]);
					}
					break;
				case 4:
					subarray = lineRead.split(" ");
					if (subarray.length == 0){
						break;
					}
					for(int i = 0; i < subarray.length; i++){
						String[] itemArray = subarray[i].split(":");
						Weapon weapon = new Weapon(Integer.parseInt(itemArray[1]),
								Integer.parseInt(itemArray[2]), Integer.parseInt(itemArray[3]),
								Integer.parseInt(itemArray[4]), Integer.parseInt(itemArray[5]),
								itemArray[0]);
						player.addToInventory(weapon);
					}
					break;
				case 5:
					subarray = lineRead.split(" ");
					if (subarray.length == 0){
						account.addCharacter(player);
						accounts.add(account);
						break;
					}
					for(int i = 0; i < subarray.length; i++){
						String[] itemArray = subarray[i].split(":");
						if (itemArray.length == 6){
							Weapon weapon = new Weapon(Integer.parseInt(itemArray[1]),
									Integer.parseInt(itemArray[2]), Integer.parseInt(itemArray[3]),
									Integer.parseInt(itemArray[4]), Integer.parseInt(itemArray[5]),
									itemArray[0]);
							player.addToEquipment(weapon);
						}
					}
					counter = -1;
					account.addCharacter(player);
					accounts.add(account);
					break;
				default:
					System.out.println("Doh! This shouldn't happen");
					break;
				}
				count++;
				counter++;
				lineRead = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
