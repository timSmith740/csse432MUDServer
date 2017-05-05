package worldObjects;

import characters.GameCharacter;

public class WorldObject {
	private boolean passable;
	
	public WorldObject(boolean passable){
		this.passable=passable;
	}
	
	public boolean canPass(GameCharacter character){
		return this.passable;
	}
	
	public void setPassable(boolean isPassable){
		this.passable = isPassable;
	}
}
