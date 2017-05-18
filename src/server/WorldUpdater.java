package server;

import java.awt.Point;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import characters.Container;
import gameMap.GameMap;
import items.Gold;
import items.Weapon;
import items.weaponGenerator;

public class WorldUpdater implements Runnable {

	GameMap theWorld;
	Map<Container, Point> containers;// This might be changed to an arraylist
	
	public WorldUpdater(GameMap theWorld, Map<Container, Point> containers){
		this.theWorld = theWorld;
		this.containers = containers;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Entry<Container, Point> container : this.containers.entrySet()) {
				if (container.getKey().isEmpy()){
					refillContainer(container.getKey());
				}
			}
		}
		
	}
	
	public void refillContainer(Container container){
		int level = container.getLevel();
		Random ranGen = new Random();
		switch(level){
		case 1:
			Weapon sword = weaponGenerator.createWeapon(1);
			container.addToInventory(sword);
			if (container.containGold()){
				int goldAmount = ranGen.nextInt(2)+1;
				container.addToInventory(new Gold(goldAmount));
			}
			break;
		case 2:
			sword = weaponGenerator.createWeapon(4);
			container.addToInventory(sword);
			if (container.containGold()){
				int goldAmount = ranGen.nextInt(4)+1;
				container.addToInventory(new Gold(goldAmount));
			}
			break;
		case 3:
			sword = weaponGenerator.createWeapon(7);
			container.addToInventory(sword);
			if (container.containGold()){
				int goldAmount = ranGen.nextInt(9)+2;
				container.addToInventory(new Gold(goldAmount));
			}
			break;
		case 4:
			sword = weaponGenerator.createWeapon(10);
			container.addToInventory(sword);
			if (container.containGold()){
				int goldAmount = ranGen.nextInt(12)+4;
				container.addToInventory(new Gold(goldAmount));
			}
			break;
		default:
			sword = weaponGenerator.createWeapon(1);
			container.addToInventory(sword);
			if (container.containGold()){
				int goldAmount = ranGen.nextInt(2)+1;
				container.addToInventory(new Gold(goldAmount));
			}
			break;
		}
			
	}

}
