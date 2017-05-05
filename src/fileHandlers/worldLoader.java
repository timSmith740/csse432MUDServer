package fileHandlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import worldObjects.WorldObject;
import worldObjects.Obstacle;

public class worldLoader {

	public WorldObject[][] readFile(){
		WorldObject[][] map = new WorldObject[48][60];
		try{
			BufferedReader br = new BufferedReader(new FileReader("src/saveFiles/map.txt"));
			String lineRead = br.readLine();
			int x = 0, y = 0;
			while (lineRead != null){
				System.out.println(lineRead);
				for (y = 0; y < lineRead.length(); y++){
					char c = lineRead.charAt(y);
					switch (c){
					case 'W':
						map[x][y] = new WorldObject();
					case 'F':
						map[x][y] = new WorldObject();
					case 'D':
						map[x][y] = new Obstacle();
					case ' ':
						map[x][y] = new WorldObject();
					case 'G':
						map[x][y] = new Obstacle();
					default:
						map[x][y] = new WorldObject();
					}
				}
				lineRead = br.readLine();
				x++;
			}
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File not found");
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		return map;
	}
}
