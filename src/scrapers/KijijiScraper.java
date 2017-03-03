package scrapers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.Rental;

public class KijijiScraper implements Scraper {

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {
		
		//Init new list of rentals
		ArrayList<Rental> rentals = new ArrayList<Rental>();	
	
		//Get website
		Document doc = Jsoup.connect("http://www.kijiji.ca/b-house-rental/kelowna/c43l1700228").get();
		Elements elements = doc.select(".info-container");		
			
		//Create and populate new rental objects, add them to the rentals Arraylist
		for(Element element: elements){
			Rental R = new Rental();
			R.setPrice(element.select(".price").text());
			R.setAddress(element.select(".location").text());
			R.setTitle(element.select(".title").text());
			
			
			rentals.add(R);
		}
		
		
		
		return rentals;
	}

}
