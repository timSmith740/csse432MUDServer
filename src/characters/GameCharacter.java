package characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gameMap.GameMap;
import items.Equipment;
import items.Weapon;

public abstract class GameCharacter extends GameObject{
	public int str;
	public int dex;
	public int con;
	public int intel;
	public int armor=0;
	public int health=0;
	public int totalHealth=1;
	ArrayList<String> keys = new ArrayList<>();
	public GameCharacter() {
		super("Player");
	}
	protected Weapon weapon;
	public List<Equipment> equipment;
	
	public int getStat(String stat){
		switch(stat){
			case "str":
				return this.str;
			case "dex":
				return this.dex;
			case "con":
				return this.con;
			case "intel":
				return this.intel;
			default:
				return 0;
		}
	}
	
	public void setStat(String stat, int val){
		switch(stat){
			case "str":
				this.str = val;
				break;
			case "dex":
				this.dex = val;
				break;
			case "con":
				this.con = val;
				this.health = 10+2*this.con;
				this.totalHealth = 10+2*this.con;
				break;
			case "intel":
				this.intel = val;
				break;
		}
	}
	
	//Joe Method
	public String characterStats(){
		//1 ,2 ,3, 4 is a filler for missing stat
		//name health health/health ac armor strength con int dex
		return "&S:"+this.name+":"+this.health+":"+this.totalHealth+":"+this.armor +":"+this.str+":"+this.con+":"+this.intel+":"+this.dex;
	}
	
	//Joe Method
	public String getHealth(){
		return "&H:"+5+":"+6;
	}

	public int getAttackBonus() {
		return this.intel+this.dex;
	}

	public ArrayList<String> getKeys(){
		return this.keys;
	}
	
	public void addKey(String key){
		this.keys.add(key);
	}
	
	public int getCurrentHealth(){
		return this.health;
	}
	
	public void setCurrentHealth(int value){
		this.health = value;
	}
	
	public int getTotalHealth(){
		return this.totalHealth;
	}
	
	public void setTotalHealth(int value){
		this.totalHealth = value;
	}
	
	public void dealDamage(int value, GameMap map){
		this.health -= value;
		if(this.health<=0){
			Timer timer = new Timer();
			timer.schedule(new RespawnChar(this, map),this.respawnTime);
		}
	}
	
	public boolean isAlive(){
		return this.health>0;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	@Override
	public String toString(){
		if(!this.isAlive()){
			return this.getName()+"'s corpse";
		}
		return this.getName();
	}
	
	public void respawn(GameMap map){
		setCurrentHealth(this.totalHealth);
		map.AddGameObjectAtLocation(this, this.respawnLocation);
	}
	
	class RespawnChar extends TimerTask {
		private GameCharacter character;
		private GameMap map;
		public RespawnChar(GameCharacter character, GameMap map){
			this.character = character;
			this.map = map;
		}
		@Override
		public void run() {
			this.character.respawn(this.map);
		}
	}
	
}
