package characters;

public class Container extends GameObject {
	
	int level;
	boolean hasGold;

	public Container(String name, int level, boolean hasGold) {
		super( name);
		// TODO Auto-generated constructor stub
		this.level = level;
		this.hasGold = hasGold;
	}
	
	public String toString(){
		return name;
	}
	
	public boolean isEmpty(){
		return this.inventory.isEmpty();
	}
	
	public int getLevel(){
		return this.level;
	}
	public boolean containGold(){
		return hasGold;
	}

}
