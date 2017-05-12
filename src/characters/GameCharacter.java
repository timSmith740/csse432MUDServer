package characters;

import java.util.ArrayList;
import java.util.List;

import items.Equipment;
import items.Weapon;

public abstract class GameCharacter extends GameObject{
	public GameCharacter() {
		super("Player");
	//	equipment = new ArrayList<Equipment>();
	//	weapons = new ArrayList<Weapon>();
		// TODO Auto-generated constructor stub
	}
	private List<Weapon> weapons;
	public List<Equipment> equipment;
	int str, dex, con, intel;
	
	public int getStat(String stat){
		switch(stat){
			case "str":
				return str;
			case "dex":
				return dex;
			case "con":
				return con;
			case "intel":
				return intel;
			default:
				return 0;
		}
	}
	
	void setStat(String stat, int val){
		switch(stat){
			case "str":
				str = val;
			case "dex":
				dex = val;
			case "con":
				con = val;
			case "intel":
				intel = val;
		}
	}
	
	//Joe Method
	public String characterStats(){
		//1 ,2 ,3, 4 is a filler for missing stat
		//health health/health ac armor strength con int dex
		return "&S:"+1+":"+2+":"+3+":"+4 +":"+str+":"+con+":"+intel+":"+dex;
	}
	
	//Joe Method
	public String getHealth(){
		return "&H:"+5+":"+6;
	}

	public int getAttackBonus() {
		return intel+dex;
	}
	//Need to add Armor
	public int getAC(){
		return str;	
	}
	
}
