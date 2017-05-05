package gameMap;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import characters.GameCharacter;
import characters.GameObject;
import server.ServerProtocol.Direction;
import worldObjects.WorldObject;

public class GameMap {
	private WorldObject[][] worldGrid;
	private Map<Point, List<GameObject>> objectMap;
	
	public GameMap(WorldObject[][] worldGrid){
		this.worldGrid = worldGrid;
		this.objectMap = new HashMap<>();
	}
	
	public void AddGameObjectAtLocation(GameObject obj, Point location){
		Point objLocation = obj.getLocation();
		if(objectMap.containsKey(objLocation)){
			this.objectMap.get(objLocation).remove(obj);
		}
		if(!this.objectMap.containsKey(location)){
			
			this.objectMap.put(new Point(location), new ArrayList<GameObject>());
		}
		obj.setLocation(location);
		this.objectMap.get(location).add(obj);
	}
	
	public boolean moveCharacter(GameCharacter character, Point destination){
		if(checkMove(character,destination)){
			AddGameObjectAtLocation(character, destination);
			return true;
		}
		return false;
	}
	
	public boolean moveToObject(GameCharacter toMove, GameObject target){
		return moveCharacter(toMove, target.getLocation());
	}
	
	public boolean checkMove(GameCharacter character, Point destination){
		WorldObject atLocation = this.worldGrid[destination.x][destination.y];
		return (atLocation!=null&&atLocation.canPass(character));
	}
	
	public boolean moveByDistance(GameCharacter character, Direction dir, int distance){
		Point charLocation = character.getLocation();
		Point moveToLocation = null;
//		switch(dir){
//			case North:
//				moveToLocation.setLocation(charLocation.x, distance+charLocation.y);
//				break;
//			case South:
//				moveToLocation.setLocation(charLocation.x, charLocation.y-distance);
//				break;
//			case East:
//				moveToLocation.setLocation(charLocation.x+distance, charLocation.y);
//				break;
//			case West:
//				moveToLocation.setLocation(charLocation.x-distance, charLocation.y);
//				break;
//			default:
//				return false;
//		}
		
		Point currentLocation = charLocation;
		for(int counter =0; counter<distance;counter++){
			Point nextLocation = new Point(0,0);
			switch(dir){
			case North:
				 nextLocation.setLocation(currentLocation.x, currentLocation.y-1);
				break;
			case South:
				 nextLocation.setLocation(currentLocation.x, currentLocation.y+1);
				break;
			case East:
				 nextLocation.setLocation(currentLocation.x+1, currentLocation.y);
				break;
			case West:
				 nextLocation.setLocation(currentLocation.x-1, currentLocation.y);
				break;
			default:
				return false;
			}
			WorldObject atLocation = this.worldGrid[nextLocation.x][nextLocation.y];
			
			if(!atLocation.canPass(character)){
				return false;
			}
			currentLocation = nextLocation;	
		}
		moveToLocation = currentLocation;
		return moveCharacter(character,moveToLocation);
		
		
	}
}
