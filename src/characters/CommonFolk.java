package characters;
import java.util.ArrayList;
import java.util.List;

public class CommonFolk extends NPC {
	private List<Player> hostileTo = new ArrayList<>();
	private List<String> dialogue = new ArrayList<>();
	private int currentResponse = 0;
	
	public CommonFolk(String name, List<String> dialogue){
		this.name = name;
		this.dialogue = dialogue;
		if(dialogue.isEmpty()){
			dialogue.add("They have nothing to say\n");
		}
	}
	
	public String talk(){
		if(this.currentResponse>=this.dialogue.size()){
			this.currentResponse = 0;
		}
		String response = this.dialogue.get(this.currentResponse);
		this.currentResponse++;
		return response;
	}
	
	
}
