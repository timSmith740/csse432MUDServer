package items;

import characters.GameCharacter;

public class Weapon extends Equipment {
	private int range;
	
	public int str;
	public int dex; 
	public int intel;
	public int modifier;
	public String name;
	
	public Weapon(int value,int str1, int dex1, int intel1, int modifier, String name){
		range = value;
		this.str=str1;
		this.dex=dex1;
		this.intel = intel1;
		this.modifier=modifier;
		this.name = name;
		System.out.println(this.dex+" "+this.str+" "+this.intel);
	}
	
	public int getRange(){
		return range;
	}
	
	public int GetDammage(GameCharacter character){
//		System.out.println(this.dex+" "+this.str+" "+this.intel);
//		System.out.println(character.getStat("dex")+" "+character.getStat("str")+" "+character.getStat("intel"));
		int damage =getMin(this.str,character.getStat("str"))+getMin(this.dex,character.getStat("dex"))+getMin(this.intel,character.getStat("intel"));
		return damage;
	}
	
	public int getMin(int a, int b){
		
		if(a<b){
			return a;
		}
		return b;
	}

	public int getAttackBonus() {
		return this.modifier;
	}
	
	public String toString(){
		return this.name;
	}

}
