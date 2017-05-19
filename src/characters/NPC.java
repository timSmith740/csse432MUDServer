package characters;

public abstract class NPC extends GameCharacter {
	private int maxDistance;
	
	public void setStats(int str, int dex, int con, int intel){
		this.str=str;
		this.dex=dex;
		this.con=con;
		this.intel=intel;
		
		this.health = 10+con*3;
		this.totalHealth = this.health;
	}

}
