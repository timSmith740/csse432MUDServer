package characters;

import server.Account;

public class Player extends GameCharacter {
	
	private Account account;

	
	public void deleteInventory(){
		this.inventory.clear();
	}
	public void hear(String message){
		if(this.account!=null){
			this.account.messageClient(message);
		}
	}
	public void setAccount(Account account){
		this.account=account;
	}
	
	public void increaseStat(String stat){
		switch(stat){
			case "str":
				this.str++;
				break;
			case "dex":
				this.dex++;
				break;
			case "con":
				this.con++;
				this.health+=2;
				this.totalHealth+=2;
				break;
			case "intel":
				this.intel++;
				break;
			default:
				System.out.println(stat +" is not a stat");
		}
	}
}
