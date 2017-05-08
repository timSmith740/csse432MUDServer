package characters;

import java.awt.Point;
import java.util.ArrayList;

import items.Item;

public abstract class GameObject {

	int respawnTime;
	Point respawnLocation;
	Point location;
	ArrayList<Item> Inventory = new ArrayList<Item>();
	
	//Joe  
	ArrayList<Item> Equiped = new ArrayList<Item>();
	
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
	
	public void setLocation(Point location){
		if(this.location!=null){
			this.location.move(location.x, location.y);
		}else{
			this.location=new Point(location.x, location.y);
		}
	}
	
	//Joe Method
	public String getInvString(){
		String result = "";
		for(int counter=0; counter<this.Inventory.size(); counter++){
			result = this.Inventory.get(counter)+ "\n";
		}
		return result;
	}
	
	//Joe Method
	public String getEquString(){
		String result = "";
		for(int counter=0; counter<this.Equiped.size(); counter++){
			result = this.Equiped.get(counter)+ "\n";
		}
		return result;
	}
}
 