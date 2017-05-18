package items;

public abstract class Item {
	String name;
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public String getName(){
		return this.name;
	}

}
