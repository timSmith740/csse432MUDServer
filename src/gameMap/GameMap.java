package gameMap;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import characters.GameCharacter;
import characters.GameObject;
import worldObjects.WorldObject;

public class GameMap {
	private WorldObject[][] worldGrid;
	private Map<Point, List<GameObject>> objectMap;
	
	public GameMap(WorldObject[][] worldGrid){
		this.worldGrid = worldGrid;
		this.objectMap = new HashMap<>();
	}
	
	public void AddGameObjectAtLocation(GameObject obj, Point location){
		if(!this.objectMap.containsKey(location)){
			this.objectMap.put(new Point(location), new ArrayList<GameObject>());
		}
		this.objectMap.get(location).add(obj);
	}
	
	public Point moveCharacter(GameCharacter character, Point destination){
		WorldObject atLocation = this.worldGrid[destination.x][destination.y];
		
		Point charLocation = character.getLocation();
		if(atLocation!=null){
			this.objectMap.get(charLocation).remove(character);
			AddGameObjectAtLocation(character, destination);
			return destination;
		}
		return charLocation;
	}
	
	public Point moveToObject(GameCharacter toMove, GameObject target){
		return moveCharacter(toMove, target.getLocation());
	}
}
