package characters;

import java.util.ArrayList;

import items.items;

public abstract class GameObject {
	ArrayList<items> Inventory = new ArrayList<items>();
	
	public ArrayList<items> getInventory(){
		return this.Inventory;
	}
	
	public void addToInventory(items item){
		this.Inventory.add(item);
	}
	
	public void removeFromInventory(items item){
		this.Inventory.remove(item);
	}
}
 