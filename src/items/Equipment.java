package items;

import java.util.ArrayList;
import java.util.HashMap;

import characters.GameCharacter;

public class Equipment extends Item {
	ArrayList<Effect> effects = new ArrayList<Effect>();

	public boolean addEffects(GameCharacter character){
		
		return true;
	}
	
	public boolean removeEffects(GameCharacter character){
		
		return true;
	}
	
	public HashMap<String, Integer> getEffects(){
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		for (Effect e: effects){
			hmap.put(e.getStat(), e.getChange());
		}
		return hmap;
	}
}
