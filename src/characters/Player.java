package characters;

public class Player extends GameCharacter {
	
	//joe Method
	public Player(){
	}
	
	
	
	public void deleteInventory(){
		Inventory.clear();
	}
	
	public void increaseStat(String stat){
		switch(stat){
			case "str":
				this.str++;
			case "dex":
				this.dex++;
			case "con":
				this.con++;
			case "intel":
				this.intel++;
		}
	}
}
