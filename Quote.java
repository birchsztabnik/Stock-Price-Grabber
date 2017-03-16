package birch.stocks

public class Quote {
	final private String time;
	final private double price;
	
	public Quote(double price, String time){
		this.price = price;
		if(time.contains("EST")){
			time = time.substring(0,time.length()-3);
			this.time = time.trim();
		}else
			this.time = time;
	}
	
	public double price(){
		return price;
	}
	public String time(){
		return time;
	}
	public String toString(){
		return price+" "+time;
	}
}