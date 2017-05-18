package fileHandlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import worldObjects.Obstacle;
import worldObjects.WorldObject;

public class DoorLoader {
	
	WorldObject[][] theWorld;

	public DoorLoader(WorldObject[][] world){
		this.theWorld = world;
	}
	
	public void lockDoors(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("src/saveFiles/lockedDoors.txt"));
			String lineRead = br.readLine();
			while (lineRead != null){
				String[] subparts = lineRead.split(" ");
				String name = subparts[0];
				int row = Integer.parseInt(subparts[1]);
				int column = Integer.parseInt(subparts[2]);
				Obstacle door = (Obstacle) this.theWorld[row][column];
				door.addKey(name);
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
