package scrapers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.Rental;

public class CraiglistScraper implements Scraper {

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {
		
		//Init new list of rentals
		ArrayList<Rental> rentals = new ArrayList<Rental>();	
	
		//Get website
		Document doc = Jsoup.connect("https://kelowna.craigslist.ca/search/apa").get();
		Elements elements = doc.select(".page-container");		
			
		//Create and populate new rental objects, add them to the rentals Arraylist
		for(Element element: elements){
			Rental R = new Rental();
			R.setPrice(element.select(".result-price").text());
			R.setAddress(element.select(".nearbyArea").text());
			R.setTitle(element.select(".title").text());
			
			
			rentals.add(R);
		}
		
		
		for(Rental r : rentals){
			System.out.println(r.getTitle());
			System.out.println(r.getAddress());
			System.out.println(r.getPrice());
			
			
		}
		
		
		return rentals;
	}

}
