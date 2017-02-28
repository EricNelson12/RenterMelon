package scrapers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import main.Rental;

public class KijijiScraper implements Scraper {

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {
	
		
		Document doc = Jsoup.connect("http://www.kijiji.ca/b-house-rental/kelowna/c43l1700228").get();
		Elements newsHeadlines = doc.select(".info-container");
		System.out.println(newsHeadlines.get(1).text());
		
		return null;
	}

}
