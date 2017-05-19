package characters;

import gameMap.GameMap;

public abstract class NPC extends GameCharacter {
	private int maxDistance;
	
	public void setStats(int str, int dex, int con, int intel){
		this.str=str;
		this.dex=dex;
		this.con=con;
		this.intel=intel;
		
		this.health = 10+con*2;
		this.totalHealth = this.health;
	}
	@Override
	public void respawn(GameMap map){
		super.respawn(map);
		if(!this.getInventory().contains(this.getWeapon())){
			this.weapon = this.weapon.copyWeapon();
		}
	}

}
