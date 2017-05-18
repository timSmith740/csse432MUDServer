package fileHandlers;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import characters.Container;
import characters.Player;
import items.Item;
import server.Account;

public class AccountSaver implements Runnable {

	ArrayList<Account> accounts;

	public AccountSaver(ArrayList<Account> users) {
		this.accounts = users;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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