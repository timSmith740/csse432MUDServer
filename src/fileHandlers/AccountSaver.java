package fileHandlers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import characters.Player;
import items.Item;
import items.Weapon;
import server.Account;

public class AccountSaver implements Runnable {

	ArrayList<Account> accounts;

	public AccountSaver(ArrayList<Account> users) {
		this.accounts = users;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Start saving");
				BufferedWriter bw = new BufferedWriter(new FileWriter("src/saveFiles/accountsAndPlayers.txt", false));
				for (Account current : accounts) {
					String accountInfo = current.getUsername() + " " + current.getPass() + "\n";
					bw.write(accountInfo);
					Player player = current.getCharacter();
					String name = player.getName();
					int xLocation = player.getLocation().x;
					int yLocation = player.getLocation().y;
					int armor = player.getArmor();
					int gold = player.getGoldAmount();
					String playerInfo = name + " "+Integer.toString(xLocation)+
							" "+Integer.toString(yLocation)+" "+Integer.toString(armor)+
							" "+Integer.toString(gold)+"\n";
					bw.write(playerInfo);
					
					int str = player.getStat("str");
					int dex = player.getStat("dex");
					int con = player.getStat("con");
					int intel = player.getStat("intel");
					int health= player.getCurrentHealth();
					int totalHealth= player.getTotalHealth();
					String stats = Integer.toString(str)+" "+Integer.toString(str)+
							" "+Integer.toString(dex)+" "+Integer.toString(con)+" "+
							Integer.toString(intel)+" "+Integer.toString(health)+" "+
							Integer.toString(totalHealth)+"\n";
					bw.write(stats);
					
					ArrayList<Item> equipment = player.getEquiped();
					ArrayList<Item> inventory = player.getInventory();
					ArrayList<String> keys = player.getKeys();
					if (keys.isEmpty()){
						bw.write("\n");
					} else{
						for (int i = 0; i < keys.size(); i++){
							bw.write(keys.get(i));
							bw.write(" ");
						}
						bw.write("\n");
					}
					if (inventory.isEmpty()){
						bw.write("\n");
					} else {
						for (int i = 0; i < inventory.size(); i++){
							Weapon item = (Weapon) inventory.get(i);
							String itemName = item.getName();
							int damage = item.getDamageValue();
							int type = item.getWeaponType();
							int range =  item.getRange();
							int hit = item.getAttackBonus();
							int level = item.getLevel();
							String itemInfo = itemName +":"+ Integer.toString(damage)
							+":"+ Integer.toString(type) +":"+ Integer.toString(range)
							+":"+ Integer.toString(hit) +":"+ Integer.toString(level)+" ";
							bw.write(itemInfo);
						}
						bw.write(" \n");
					}
					if (equipment.isEmpty()){
						bw.write("\n");
					} else {
						for (int i = 0; i < equipment.size(); i++){
							Weapon item = (Weapon) equipment.get(i);
							String itemName = item.getName();
							int damage = item.getDamageValue();
							int type = item.getWeaponType();
							int range =  item.getRange();
							int hit = item.getAttackBonus();
							int level = item.getLevel();
							String itemInfo = itemName +":"+ Integer.toString(damage)
							+":"+ Integer.toString(type) +":"+ Integer.toString(range)
							+":"+ Integer.toString(hit) +":"+ Integer.toString(level)+" ";
							bw.write(itemInfo);
						}
						bw.write(" \n");
					}
				}
				bw.close();
				System.out.println("Finished Saving");
			} catch (FileNotFoundException fnfe) {
				System.out.println("File not found");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}
	}

}
