package worldObjects;

import java.util.List;

import characters.GameCharacter;
import items.Item;

/*temporay change to nonabstract*/
public class Obstacle extends WorldObject {
	
	
	public Obstacle(boolean passable) {
		super(passable);
	}
	
	public Obstacle(){
		super(false);
	}

	private List<Item> keys;
	
	public boolean isKey(Item item){
		return this.keys.contains(item);
	}
	
	@Override
	public boolean canPass(GameCharacter character){
		for(Item item : character.getInventory()){
			if(isKey(item)){
				return true;
			}
		}
		return false;
	}

}
