package classStructure;

import java.util.ArrayList;

public abstract class Character {
	ArrayList<String> Inventory = new ArrayList<String>();
	
	ArrayList<String> getInventory(){
		return Inventory;
	}
	
	void addToInventory(String item){
		Inventory.add(item);
	}
	
	void removeFromInventory(String item){
		Inventory.remove(item);
	}
}
