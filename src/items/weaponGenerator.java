package items;

import java.util.Random;

public class weaponGenerator {



	public static Weapon createWeapon(int level){
	
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
				name = dexWeaponName(1);
				break;
			case 2:
				hit =ranGen.nextInt(3-1+1)+1;
				name = dexWeaponName(2);
				break;
			case 3:
				hit =ranGen.nextInt(5-4+1)+4;
				name = dexWeaponName(3);
				break;
			
			default: 
				hit = 0;
			}
			break;
		case 3:
			switch(range){
			case 1:
				hit =ranGen.nextInt(0+1+1)-1;
				name= strWeaponName(1);
				break;
			case 2:
				hit =ranGen.nextInt(5-4+1)+4;
				name = strWeaponName(2);
				break;
			case 3:
				hit =ranGen.nextInt(3-1+1)+1;
				name = strWeaponName(3);
				break;
			
			default: 
				hit = 0;
			}
			break;
		case 4:
			switch(range){
			case 1:
				hit =ranGen.nextInt(5-4+1)+4;
				name = intelWeaponName(1);
				break;
			case 2:
				hit =ranGen.nextInt(3-1+1)+1;
				name = intelWeaponName(2);
				break;
			case 3:
				hit =ranGen.nextInt(0+1+1)-1;
				name = intelWeaponName(3);
				break;
			
			default: 
				hit = 0;
			}
			break;
		default:
			hit = 0;
				
		}
	//	System.out.println("Weapon"+type+" "+ damage+" "+range+" "+level+" "+name);
		Weapon myWeapon = new Weapon(damage,type,range,hit,level, name);
		
		return myWeapon;
	}
	
	
	public static String dexWeaponName(int range){
		Random ranGen = new Random();
		String names1[] = {"Dagger","Sickle", "Claws"};
		String names2[] = {"Darts","Whip","Throwing Stars"};
		String names3[] = {"Bow and Arrows","Crossbow","Blow Gun"};
		switch(range){
			case 1:
				return names1[ranGen.nextInt(names1.length-0+1)];
			case 2:
				return names2[ranGen.nextInt(names2.length-0+1)];
			case 3:
				return names3[ranGen.nextInt(names3.length-0+1)];			
		}	
		return "Error";	
	}
	
	
	public static String strWeaponName(int range){
		Random ranGen = new Random();
		String names1[] = {"Axe", "Club","Hammer"};
		String names2[] = {"Sword","Pike","Spear","Scimitar","Lance"};
		String names3[] = {"Javalin","Throwing Axe"};
		switch(range){
			case 1:
				return names1[ranGen.nextInt(names1.length-0+1)];
			case 2:
				return names2[ranGen.nextInt(names2.length-0+1)];
			case 3:
				return names3[ranGen.nextInt(names3.length-0+1)];			
		}	
		return "Error";	
	}
	
	public static String intelWeaponName(int range){
		Random ranGen = new Random();
		String names1[] = {"Staff","Magic Necklace"};
		String names2[] = {"Wand","Magic Ring"};
		String names3[] = {"Magic Ball","Magic Gloves"};
		switch(range){
			case 1:
				return names1[ranGen.nextInt(names1.length-0+1)];
			case 2:
				return names2[ranGen.nextInt(names2.length-0+1)];
			case 3:
				return names3[ranGen.nextInt(names3.length-0+1)];			
		}	
		return "Error";	
	}
}