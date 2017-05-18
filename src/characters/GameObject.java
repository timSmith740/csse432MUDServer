package characters;

import java.awt.Point;
import java.util.ArrayList;

import items.Gold;
import items.Item;

public abstract class GameObject {

	int respawnTime;
	String name;
	Point respawnLocation;
	Point location;
	ArrayList<Item> Inventory = new ArrayList<Item>();
	int armor;
	Gold gold;

	
	//Joe  
	ArrayList<Item> Equiped = new ArrayList<Item>();
	
	public GameObject(String name){
		this.armor=1;
		this.name=name;
		this.gold =new Gold(0);
	}
	public GameObject(int armor, String name){
		this.armor=armor;
		this.name=name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Item> getInventory(){
		return this.Inventory;
	}
	
	
	public void addToInventory(Item item){
		if(item.getClass().equals(Gold.class)){
			this.gold.add((Gold) item);
		}
		else{
			this.Inventory.add(item);
		}
		//this.Inventory.add(item);
	}
	
	public void removeFromInventory(Item item){
		if(item.getClass().equals(Gold.class)){
			this.gold.subtract((Gold) item);
		}
		else{
			this.Inventory.remove(item);
		}
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
	public int getGoldAmount(){
		return this.gold.getAmount();
	}
	
	//Joe Method
	public String getInvString(){
		String result = "";
		for(int counter=0; counter<this.Inventory.size(); counter++){
			result = result + this.Inventory.get(counter)+ "\n";
		}
		return result;
	}
	
	//Joe Method
	public String getEquString(){
		String result = "";
		for(int counter=0; counter<this.Equiped.size(); counter++){
			result = result + this.Equiped.get(counter)+ "\n";
		}
		return result;
	}

	public int getArmor() {
		// TODO Auto-generated method stub
		return this.armor;
	}
	@Override
	public String toString(){
		return this.getName();
	}
}
 