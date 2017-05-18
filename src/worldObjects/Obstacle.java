package worldObjects;

import java.util.List;

import characters.GameCharacter;
import characters.Player;
import items.Item;

/*temporay change to nonabstract*/
public class Obstacle extends WorldObject {
	
	
	public Obstacle(boolean passable) {
		super(passable);
	}
	
	public Obstacle(){
		super(false);
	}

	private String key;
	/*private List<Item> keys;
	
	public boolean isKey(Item item){
		return this.keys.contains(item);
	}*/
	
	public void addKey(String item){
		this.key = item;
	}
	
	public boolean canPass(Player character){
		if (this.passable){
			return true;
		} else {
			if (character.getKeys().contains(this.key)){
				return true;
			} else{
				return false;
			}
		}
	}
	
	/*@Override
	public boolean canPass(GameCharacter character){
		for(Item item : character.getInventory()){
			if(isKey(item)){
				return true;
			}
		}
		return false;
	}*/

}
