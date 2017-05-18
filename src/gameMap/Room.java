package gameMap;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import characters.GameObject;

public class Room {
	List<Rectangle> blocks = new ArrayList<>();
	
	public void addBlock(int leftX, int topY, int rightX, int bottomY){
		Rectangle rect = new Rectangle(leftX, topY, rightX-leftX, bottomY-topY);
		blocks.add(rect);
	}
	public boolean containsObject(GameObject o){
		Point p = o.getLocation();
		for(Rectangle r : blocks){
			if(r.contains(p)){
				return true;
			}
		}
	
		return false;
	}
}
