package characters;

import java.awt.Point;
import java.util.ArrayList;

import items.Item;

public abstract class GameObject {

	int respawnTime;
	String name;
	Point respawnLocation;
	Point location;
	ArrayList<Item> Inventory = new ArrayList<Item>();
	int AC;

	
	//Joe  
	ArrayList<Item> Equiped = new ArrayList<Item>();
	
	public GameObject(String name){
		this.AC=1;
		this.name=name;
	}
	public GameObject(int AC, String name){
		this.AC=AC;
		this.name=name;
	}
	
	public ArrayList<Item> getInventory(){
		return this.Inventory;
	}
	
	public void addToInventory(Item item){
		this.Inventory.add(item);
	}
	
	public void removeFromInventory(Item item){
		this.Inventory.remove(item);
	}
	
	public void addToEquipment(Item item){
		this.Equiped.add(item);
	}
	
	//joe
	public Item getEquiped(int index){
		return this.Equiped.get(index);
	}
	
	public void removeFromEquipment(Item item){
		this.Equiped.remove(item);
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

	public int getAC() {
		// TODO Auto-generated method stub
		return this.AC;
	}
}
 