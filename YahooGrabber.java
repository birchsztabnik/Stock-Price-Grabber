package birch.stocks;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class YahooGrabber {
	public static double current_price(Company company){
		try {
			String url = company.get_yahoo_URL();
			Document webpage = Jsoup.connect(url).get();
			double current_price = Double.parseDouble(webpage.select("span.time_rtq_ticker").text());
			//Success
			return current_price;
		} catch (IOException e) {
			System.out.println("Error connecting to "+company.get_yahoo_URL());
			e.printStackTrace();
		}
		//Failure
		return -1;
	}
	
	
	public static double current_price(String symbol){
		try {
			String url = "http://finance.yahoo.com/q?s="+symbol;
			Document webpage = Jsoup.connect(url).get();
			double current_price = Double.parseDouble(webpage.select("span.time_rtq_ticker").text());
			//Success
			return current_price;
		} catch (IOException e) {
			System.out.println("Error connecting to http://finance.yahoo.com/q?s="+symbol);
			e.printStackTrace();
		}
		//Failure
		return -1;
	}

	public static double afterhour_price(Company company){
		try {
			String url = company.get_yahoo_URL();
			Document webpage = Jsoup.connect(url).get();
			double current_price = Double.parseDouble(webpage.select("span.yfs_rtq_quote").text());
			//Success
			return current_price;
		} catch (IOException e) {
			System.out.println("Error connecting to "+company.get_yahoo_URL());
			e.printStackTrace();
		}
		//Failure
		return -1;
	}
	
	public static String time(Company company, Time market_time){
		try {
			String url = company.get_yahoo_URL();
			Document webpage = Jsoup.connect(url).get();
			String date;
			if(market_time == Time.MARKET){
				//Market time
				date = webpage.select("span.time_rtq #yfs_t53_"+company.get_symbol()).text();
			}else{
				//After-market time
				date = webpage.select("span.time_rtq #yfs_t54_"+company.get_symbol()).text();
			}
			//Success
			return date;
		} catch (IOException e) {
			System.out.println("Error connecting to "+company.get_yahoo_URL());
			e.printStackTrace();
		}
		//Failure
		return "cannot retrieve date";
	}
	
	public static String time(String symbol){
		try {
			String url = "http://finance.yahoo.com/q?s="+symbol;
			Document webpage = Jsoup.connect(url).get();
			String date;
			date = webpage.select("span.time_rtq #yfs_t53_"+symbol.toLowerCase()).text();
			//Success
			return date;
		} catch (IOException e) {
			System.out.println("Error connecting to http://finance.yahoo.com/q?s="+symbol);
			e.printStackTrace();
		}
		//Failure
		return "cannot retrieve date";
	}
	
	public static Quote get_quote(Company company){
		try {
			String url = company.get_yahoo_URL();
			Document webpage = Jsoup.connect(url).get();
			double current_price = Double.parseDouble(webpage.select("span.time_rtq_ticker").text());
			String date = webpage.select("span.time_rtq #yfs_t53_"+company.get_symbol()).text();
			return new Quote(current_price, date);
		} catch (IOException e) {
			System.out.println("Error connecting to "+company.get_yahoo_URL());
			e.printStackTrace();
		}
		//Failure
		return new Quote(-1.0D, "Failure to get quote");
	}
	
	public static Quote get_quote(String symbol){
		try {
			String url = "http://finance.yahoo.com/q?s="+symbol;
			Document webpage = Jsoup.connect(url).get();
			double current_price = Double.parseDouble(webpage.select("span.time_rtq_ticker").text());
			String date = webpage.select("span.time_rtq #yfs_t53_"+symbol).text();
			return new Quote(current_price, date);
		} catch (IOException e) {
			System.out.println("Error connecting to http://finance.yahoo.com/q?s="+symbol);
			e.printStackTrace();
		}
		//Failure
		return new Quote(-1.0D, "Failure to get quote");
	}
}