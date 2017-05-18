package fileHandlers;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import characters.Container;
import gameMap.GameMap;

public class ContainerLoader {
	
	GameMap theWorld;
	Map<Container, Point> containers;
	
	public ContainerLoader(GameMap world, Map<Container, Point> containers){
		this.theWorld = world;
		this.containers = containers;
	}
	
	public void fill(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("src/saveFiles/containers.txt"));
			String lineRead = br.readLine();
			while (lineRead != null){
				String[] subparts = lineRead.split(" ");
				String name = subparts[0];
				int level = Integer.parseInt(subparts[1]);
				boolean containsGold = Boolean.parseBoolean(subparts[2]);
				int row = Integer.parseInt(subparts[3]);
				int column = Integer.parseInt(subparts[4]);
				Container box = new Container(name, level, containsGold);
				Point boxPoint = new Point(column,row);
				this.containers.put(box, boxPoint);
				this.theWorld.AddGameObjectAtLocation(box, boxPoint);
				lineRead = br.readLine();
			}
			br.close();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File not found");
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
