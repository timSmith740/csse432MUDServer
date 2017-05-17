package items;

public class Gold extends Item {
	private int amount =0;
	public Gold(int amount){
		this.amount = amount;
		this.name = "gold";
	}
	
	public void add(Gold coins){
		this.amount+=coins.getAmount();
	}
	public void subtract(Gold coins){
		this.amount-=coins.getAmount();
		if(this.amount<0){
			this.amount=0;
		}
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	@Override
	public String toString(){
		return this.amount +" "+this.name;
	}
}
