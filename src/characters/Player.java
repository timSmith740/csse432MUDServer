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
}
