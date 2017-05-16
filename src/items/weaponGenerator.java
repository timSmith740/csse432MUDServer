package items;

import java.util.Random;

public class weaponGenerator {



	public static Weapon weaponGenerator(int level){
	
		Random ranGen = new Random();
		//int result = ranGen.nextInt(max-min+1)+min;
		int type = ranGen.nextInt(4-2+1)+2;
		int damage = 0;
		switch(level){
		case 1:
		case 2:
		case 3:
			damage = ranGen.nextInt(7-3+1)+3;
			break;
		case 4:
		case 5:
		case 6:
			damage = ranGen.nextInt(9-5+1)+5;
			break;
		case 7:
		case 8:
		case 9: 
			damage = ranGen.nextInt(11-7+1)+7;
		case 10:
		case 11:
		case 12:
			damage= ranGen.nextInt(16-9+1)+9;
			default:
				damage = 0;
		}
		
		int range =  ranGen.nextInt(3-1+1)+1;
		int hit = 0;
		String name = "The Might Error Message";
		switch (type){
		case 2:
			switch(range){
			case 1:
				hit =ranGen.nextInt(0+1+1)-1;
				name = "Dagger";
				break;
			case 2:
				hit =ranGen.nextInt(3-1+1)+1;
				name = "Darts";
				break;
			case 3:
				hit =ranGen.nextInt(5-4+1)+4;
				name = "Bow and Arrows";
				break;
			
			default: 
				hit = 0;
			}
			break;
		case 3:
			switch(range){
			case 1:
				hit =ranGen.nextInt(0+1+1)-1;
				name= "Staff";
				break;
			case 2:
				hit =ranGen.nextInt(5-4+1)+4;
				name = "Short Wand";
				break;
			case 3:
				hit =ranGen.nextInt(3-1+1)+1;
				name = "Wand";
				break;
			
			default: 
				hit = 0;
			}
			break;
		case 4:
			switch(range){
			case 1:
				hit =ranGen.nextInt(5-4+1)+4;
				name = "Axe";
				break;
			case 2:
				hit =ranGen.nextInt(3-1+1)+1;
				name = "Sword";
				break;
			case 3:
				hit =ranGen.nextInt(0+1+1)-1;
				name = "Javalin";
				break;
			
			default: 
				hit = 0;
			}
			break;
		default:
			hit = 0;
				
		}
		
		Weapon myWeapon = new Weapon(damage,type,range,hit,name);
		return myWeapon;
	}
}