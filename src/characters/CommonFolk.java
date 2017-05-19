package characters;
import java.util.ArrayList;
import java.util.List;

import items.Weapon;

public class CommonFolk extends NPC {
	private List<Player> hostileTo = new ArrayList<>();
	private List<String> dialogue = new ArrayList<>();
	private int currentResponse = 0;
	
	public CommonFolk(String name, List<String> dialogue, Weapon weapon){
		this.name = name;
		this.dialogue = dialogue;
		if(dialogue.isEmpty()){
			dialogue.add("They have nothing to say\n");
		}
		this.weapon = weapon;
		this.addToEquipment(weapon);
		this.addToInventory(weapon);
	}
	
	public String talk(Player player){
		if(this.hostileTo.contains(player)){
			return "They're too angry to speak to you right now";
		}
		if(this.currentResponse>=this.dialogue.size()){
			this.currentResponse = 0;
		}
		String response = this.dialogue.get(this.currentResponse);
		this.currentResponse++;
		return response;
	}

	public void addToHostile(Player player) {
		this.hostileTo.add(player);
	}

	
	
}
