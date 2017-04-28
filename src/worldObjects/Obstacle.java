package worldObjects;

import java.util.List;

import characters.GameCharacter;
import items.items;

public abstract class Obstacle extends WorldObject {
	private List<items> keys;
	
	public boolean isKey(items item){
		return this.keys.contains(item);
	}
	
	@Override
	public boolean canPass(GameCharacter character){
		for(items item : character.getInventory()){
			if(isKey(item)){
				return true;
			}
		}
		return false;
	}

}
