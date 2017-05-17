package characters;

public class Container extends GameObject {
	
	int level;

	public Container(String name, int level) {
		super( name);
		// TODO Auto-generated constructor stub
		this.level = level;
	}
	
	public String toString(){
		return name;
	}
	
	public boolean isEmpy(){
		return this.Inventory.isEmpty();
	}
	
	public int getLevel(){
		return this.level;
	}

}
