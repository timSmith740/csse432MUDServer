package characters;

import java.awt.Point;
import java.util.ArrayList;

import items.Gold;
import items.Item;
import server.ServerProtocol;

public abstract class GameObject {

	protected int respawnTime = ServerProtocol.RESPAWN_TIME;
	protected String name;
	protected Point respawnLocation = new Point(1, 1);
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
	public Item getEquipped(int index){
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
	public Point getRespawnLocation() {
		return this.respawnLocation;
	}
	public void setRespawnLocation(Point location){
		this.respawnLocation = location;
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
		return this.armor;
	}
	public void setArmor(int value){
		this.armor = value;
	}
	@Override
	public String toString(){
		return this.getName();
	}
	
	public ArrayList<Item> getEquipped(){
		return this.equipped;
	}
}
 