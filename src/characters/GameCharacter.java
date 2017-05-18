package characters;

import java.util.ArrayList;
import java.util.List;

import items.Equipment;
import items.Weapon;

public abstract class GameCharacter extends GameObject{
	public int str;
	public int dex;
	public int con;
	public int intel;
	public int armor=0;
	public int health=0;
	public int totalHealth=1;
	ArrayList<String> keys = new ArrayList<String>();
	public GameCharacter() {
		super("Player");
	//	equipment = new ArrayList<Equipment>();
	//	weapons = new ArrayList<Weapon>();
		// TODO Auto-generated constructor stub
	}
	private List<Weapon> weapons;
	public List<Equipment> equipment;
	
	public int getStat(String stat){
		switch(stat){
			case "str":
				return this.str;
			case "dex":
				return this.dex;
			case "con":
				return this.con;
			case "intel":
				return this.intel;
			default:
				return 0;
		}
	}
	
	public void setStat(String stat, int val){
		switch(stat){
			case "str":
				this.str = val;
				break;
			case "dex":
				this.dex = val;
				break;
			case "con":
				this.con = val;
				break;
			case "intel":
				this.intel = val;
				break;
		}
	}
	
	//Joe Method
	public String characterStats(){
		//1 ,2 ,3, 4 is a filler for missing stat
		//name health health/health ac armor strength con int dex
		return "&S:"+this.name+":"+this.health+":"+this.totalHealth+":"+armor +":"+str+":"+con+":"+intel+":"+dex;
	}
	
	//Joe Method
	public String getHealth(){
		return "&H:"+5+":"+6;
	}

	public int getAttackBonus() {
		return intel+dex;
	}

	public ArrayList<String> getKeys(){
		return this.keys;
	}
	
	public void addKey(String key){
		this.keys.add(key);
	}
	
}
