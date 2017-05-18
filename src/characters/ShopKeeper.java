package characters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import items.Gold;
import items.Item;

public class ShopKeeper extends CommonFolk {
	private Map<Item, Integer> wares = new HashMap<>();
	
	public ShopKeeper(String name, List<String> dialogue){
		super(name, dialogue);
	}
	public void addWares(Item item, int cost){
		this.wares.put(item, new Integer(cost));
	}
	
	public String purchaseItem(String itemName, Player p){
		Item item=null;
		for(Item i : this.wares.keySet()){
			if(i.getName().equals(itemName)){
				item = i;
			}
		}
		if(item==null){
			return "I don't have that!\n";
		}
		int price = this.wares.get(item);
		if(price>p.getGoldAmount()){
			return "You can't afford that!\n";
		}
		this.addToInventory(new Gold(price));
		p.removeFromInventory(new Gold(price));
		this.wares.remove(item);
		p.addToInventory(item);
		return "Excellent choice! That "+ itemName+" will treat you well!\n";
	}
	public String getWaresString() {
		StringBuilder sb = new StringBuilder();
		for(Item item : this.wares.keySet()){
			sb.append(item.getName()+" - "+this.wares.get(item)+"; ");
		}
		sb.append("\n");
		return sb.toString();
	}
}
