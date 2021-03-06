package items;

import characters.GameCharacter;

public class Weapon extends Equipment {

	public int damage;
	public int type; 
	//2= dex
	//3= Intel
	//4 = Str
	public int range; 
	public int hit;
	public int level; 
	
	public Weapon(int damage, int type, int range, int hit,int level, String name){
		this.damage=damage;
		this.type = type;
		this.range =  range;
		this.hit = hit;
		this.name = name;
		this.level = level;
	}
	
	public int getRange(){
		return this.range;
	}
	
	public int getDamage(GameCharacter character){
//		System.out.println(this.dex+" "+this.str+" "+this.intel);
//		System.out.println(character.getStat("dex")+" "+character.getStat("str")+" "+character.getStat("intel"));
//		int damage =getMin(this.str,character.getStat("str"))+getMin(this.dex,character.getStat("dex"))+getMin(this.intel,character.getStat("intel"));
//		return damage;
		switch(this.type){
		case 2: return getMin(this.damage,character.getStat("dex"));
		case 3: return getMin(this.damage,character.getStat("intel"));
		case 4: return getMin(this.damage,character.getStat("str"));
		}
		
		return 0;
	}
	
	public int getMin(int a, int b){
		
		if(a<b){
			return a;
		}
		return b;
	}

	public int getAttackBonus() {
		return this.hit;
	}
	
	@Override
	public String toString(){
		return this.name+": "+this.damage;
	}
	
	public int getDamageValue(){
		return this.damage;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public int getWeaponType(){
		return this.type;
	}

	public Weapon copyWeapon() {
		return new Weapon(this.damage, this.type, this.range, this.hit,this.level, this.name);
	}

}
