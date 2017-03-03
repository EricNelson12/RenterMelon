package scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import main.Rental;

public class CastanetScraper implements Scraper {

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {
		//REMINDER TO SELF: GRAB ALL TDS FROM CASTANET 
		Document doc = Jsoup.connect("http://classifieds.castanet.net/details/avail_april_1_2_bed_2_bath_2_pk/2984834/").get();
		
		//description
		Elements desc = doc.select(".description");
		System.out.println(desc.get(0).text());
		
		//price 
		Elements price = doc.select(".price");
		System.out.println(price.get(0).text());
		
		
		//getting the data from the tables 
		
		Elements td = doc.getElementsByTag("td");
		for (int i = 0; i < td.size(); i++){
			String OGtxt = td.get(i).text();
			//System.out.println(OGtxt);
			
			String txt = OGtxt.toLowerCase();
			//System.out.println(txt);
			
			String nxt = " ";
			
			//arraylists amiright
			if (i != td.size() - 1)
				nxt = td.get(i+1).text();
			//since castanet doesn't label their tds we're gonna have a bigass switch case let's go boyssss
			switch (txt) {
			case "address:":
				//put it in the db but i'm not dealing w/ that rn so just print it
				System.out.println("Address: " + nxt);
				break;
				
			case "agent full name":
				//db
				System.out.println("Name is " + nxt);
				break;
				
			case "phone:":
				if (nxt.contains("email")){
					//have to contact through castanet
					System.out.println("Email protected :(");
				} else {
					System.out.println("Phone Number " + nxt);
				}
				break;
			}
		}
		
		/*testing patterns n shit
		Pattern p = Pattern.compile("\\d{3,4}\\s\\w+\\s\\w+");
		Matcher m = p.matcher("135 Ziprick Road");
		System.out.println(m.matches()); */
		return null;
	}

}
