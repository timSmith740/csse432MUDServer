package items;

public class Weapon extends Equipment {
	private int range;
	
	public Weapon(int value){
		range = value;
	}
	
	public int getRange(){
		return range;
	}

}
