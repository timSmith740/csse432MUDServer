package gameMap;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import characters.GameObject;

public class Room {
	List<Rectangle> blocks;
	
	public Room(List<Rectangle> blocks){
		this.blocks = blocks;
	}
	public void addBlock(Rectangle rect){
		this.blocks.add(rect);
	}
	public boolean containsObject(GameObject o){
		Point p = o.getLocation();
		for(Rectangle r : this.blocks){
			if(r.contains(p)){
				return true;
			}
		}
		return false;
	}
	public List<Rectangle> getBlocks(){
		return this.blocks;
	}
}
