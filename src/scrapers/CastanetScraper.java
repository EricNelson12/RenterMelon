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
		Document doc = Jsoup.connect("http://classifieds.castanet.net/details/2_bed_2_bath_condo_opposite_costco/2982667/").get();
		Elements desc = doc.select(".description");
		System.out.println(desc.get(0).text());
		
		/*testing patterns n shit
		Pattern p = Pattern.compile("\\d{3,4}\\s\\w+\\s\\w+");
		Matcher m = p.matcher("135 Ziprick Road");
		System.out.println(m.matches()); */
		return null;
	}

}
