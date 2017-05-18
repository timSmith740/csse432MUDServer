package characters;

import java.util.ArrayList;

public class Player extends GameCharacter {
	
	ArrayList<String> keys;
	//joe Method
	public Player(){
		this.keys = new ArrayList<String>();
	}
	
	
	
	public void deleteInventory(){
		Inventory.clear();
	}
	
	public void increaseStat(String stat){
		switch(stat){
			case "str":
				this.str++;
				break;
			case "dex":
				this.dex++;
				break;
			case "con":
				this.con++;
				break;
			case "intel":
				this.intel++;
				break;
		}
	}
	
	public ArrayList<String> getKeys(){
		return this.keys;
	}
}
