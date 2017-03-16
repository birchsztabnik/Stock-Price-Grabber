package birch.stocks;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MWGrabber {
	public static double current_price(Company company){
		try {
			String url = company.get_mw_URL();
			Document webpage = Jsoup.connect(url).get();
			Elements e=webpage.getElementsByClass("bgLast");
			double current_price = Double.parseDouble(e.text().substring(0,e.text().indexOf(" ")));
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
			String url = "http://www.marketwatch.com/investing/fund/"+symbol;
			Document webpage = Jsoup.connect(url).get();
			Elements e=webpage.getElementsByClass("bgLast");
			double current_price = Double.parseDouble(e.text().substring(0,e.text().indexOf(" ")));
			//Success
			return current_price;
		} catch (IOException e) {
			System.out.println("Error connecting to MarketWatch");
			e.printStackTrace();
		}
		//Failure
		return -1;
	}
	public static Quote get_quote(String symbol){
		String time = "";
		try {
			String url = "http://www.marketwatch.com/investing/fund/"+symbol;
			Document webpage = Jsoup.connect(url).get();
			Elements e=webpage.getElementsByClass("bgTimeStamp");
			time = fix_time(e.text());
		} catch (IOException e) {
			System.out.println("Error connecting to MarketWatch");
			e.printStackTrace();
		}
		return new Quote(current_price(symbol), time);
	}
	private static String fix_time(String date){
		String month = date.substring(0, date.indexOf(" "));
		date = date.substring(date.indexOf(" ")+1);
		String day = date.substring(0, date.indexOf(","));
		
		String time = date.substring(date.indexOf(":")-2, date.indexOf(":")+3).trim();
		String time2 = date.substring(date.indexOf(":")+3);
		if(time2.contains("p")){
			time2 = "PM";
		}else{
			time2 = "AM";
		}
		return month+" "+day+", "+time+time2;
	}
}