package characters;

import java.util.List;

import items.Equipment;
import items.Weapon;

public abstract class GameCharacter extends GameObject{
	private List<Weapon> weapons;
	private List<Equipment> equipment;
	
	int str, dex, con, intel;
	
	int getStat(String stat){
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
}
