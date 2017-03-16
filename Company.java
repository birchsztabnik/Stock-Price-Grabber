package birch.stocks;

public class Company {
	private final String name;
	private final String symbol;
	private final double base_price;
	private double price;
	private final String yahoo_URL;
	private final String mw_URL;
	
	public Company(String name, String symbol, double base_price, double price){
		this.name = name;
		this.symbol = symbol;
		this.base_price = base_price;
		this.price = price;
		this.yahoo_URL = "http://finance.yahoo.com/q?s="+symbol;
		this.mw_URL = "http://www.marketwatch.com/investing/fund/"+symbol;
	}
	
	public String get_name(){
		return name;
	}
	public String get_symbol(){
		return symbol;
	}
	public double get_price(){
		return price;
	}
	public void set_price(double new_price){
		price = new_price;
	}
	public String get_yahoo_URL(){
		return yahoo_URL;
	}
	public String get_mw_URL(){
		return mw_URL;
	}
	public double find_percent_change(){
		double ans = ((price-base_price)/(double)price)*100.00D; 
		return ans;
	}
	public String find_percent_change_format(){
		return StockMath.df.format(find_percent_change());
	}
}
