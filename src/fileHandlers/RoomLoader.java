package fileHandlers;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gameMap.Room;

public class RoomLoader {
	public List<Room> readFile(){
		List<Room> rooms = new ArrayList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader("src/saveFiles/rooms.txt"));
			String lineRead = br.readLine();
			while(lineRead != null){
				Room r = new Room(new ArrayList<Rectangle>());
				String[] blocks = lineRead.split(";");
				for(String b : blocks){
					String[] coord = b.split(" ");
					Rectangle rect =new Rectangle(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
							Integer.parseInt(coord[2]), Integer.parseInt(coord[3]));
					r.addBlock(rect);
				}
				rooms.add(r);
			}
			br.close();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File not found");
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		return rooms;
	}
}
