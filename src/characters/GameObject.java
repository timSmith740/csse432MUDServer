package characters;

import java.awt.Point;
import java.util.ArrayList;

import items.Gold;
import items.Item;

public abstract class GameObject {

	protected int respawnTime;
	protected String name;
	protected Point respawnLocation;
	protected Point location;
	protected ArrayList<Item> inventory = new ArrayList<>();
	protected int armor;
	protected Gold gold;

	ArrayList<Item> equipped = new ArrayList<>();
	
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
		return this.inventory;
	}
	
	public void addToInventory(Item item){
		if(item.getClass().equals(Gold.class)){
			this.gold.add((Gold) item);
		}
		else{
			this.inventory.add(item);
		}
		//this.Inventory.add(item);
	}
	
	public void removeFromInventory(Item item){
		if(item.getClass().equals(Gold.class)){
			this.gold.subtract((Gold) item);
		}
		else{
			this.inventory.remove(item);
		}
	}
	
	public void addToEquipment(Item item){
		this.equipped.add(item);
	}
	
	//joe
	public Item getEquiped(int index){
		return this.equipped.get(index);
	}
	
	public void removeFromEquipment(Item item){
		this.equipped.remove(item);
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
		for(int counter=0; counter<this.inventory.size(); counter++){
			result = result + this.inventory.get(counter)+ "\n";
		}
		return result;
	}
	
	//Joe Method
	public String getEquString(){
		String result = "";
		for(int counter=0; counter<this.equipped.size(); counter++){
			result = result + this.equipped.get(counter)+ "\n";
		}
		return result;
	}

	public int getArmor() {
		// TODO Auto-generated method stub
		return this.armor;
	}
	public void setArmor(int value){
		this.armor = value;
	}
	@Override
	public String toString(){
		return this.getName();
	}
	
	public ArrayList<Item> getEquiped(){
		return this.equipped;
	}
}
 