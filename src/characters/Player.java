package characters;

public class Player extends GameCharacter {
	
	//joe Method
	public Player(){
		this.str=1;
		this.dex=1;
		this.intel=1;
		this.con=1;
	}
	
	public void deleteInventory(){
		Inventory.clear();
	}
}
