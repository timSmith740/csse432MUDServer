package characters;

import java.awt.Point;
import java.util.ArrayList;

import items.Item;

public abstract class GameObject {

	int respawnTime;
	Point respawnLocation;
	Point location;
	ArrayList<Item> Inventory = new ArrayList<Item>();
	
	public ArrayList<Item> getInventory(){
		return this.Inventory;
	}
	
	public void addToInventory(Item item){
		this.Inventory.add(item);
	}
	
	public void removeFromInventory(Item item){
		this.Inventory.remove(item);
	}

	public Point getLocation(){
		return this.location;
	}
}
 