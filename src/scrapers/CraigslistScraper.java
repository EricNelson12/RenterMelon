package scrapers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.Contact;
import main.Rental;

public class CraigslistScraper implements Scraper {

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {
		
		Document Startdoc = Jsoup.connect("https://kelowna.craigslist.ca/search/apa").get();
		System.out.println("Connected to: " + Startdoc.baseUri());


		Elements links = Startdoc.select("a.result-image");

		
		//result-image gallery
		
		//Init new list of rentals
		ArrayList<Rental> rentals = new ArrayList<Rental>();	
		
		int k = 0;
		for (Element l : links) {
	
		//Get website
		String link = l.attr("abs:href");
		Document rentalPage = Jsoup.connect(link).get();
		
		
		
		//make our stuff
		Rental R = new Rental();
		Contact C = new Contact();
		
		//setting link
		R.setLink(rentalPage.location());
		
		//setting image
		String url = "no image";
		if (rentalPage.getElementsByClass("slide first visible").size() > 0){
			Elements image = rentalPage.getElementsByClass("slide first visible");
			Element im = image.get(0).child(0);
			url = im.absUrl("src");
		}
		R.setImg(url);
		
		
		//setting address 
		Elements adr = rentalPage.select(".mapaddress");
		if (adr.size() > 0) {
			String a = adr.get(0).text();
			R.setAddress(a);
		}
		
		
		//PRICE
		Elements p = rentalPage.select(".price");
		if (p.size() > 0){
			String price = p.get(0).text();
			R.setPrice(price);
		}
		
		//title
		String title = rentalPage.select("#titletextonly").text();
		R.setTitle(title);
		
		String desc = rentalPage.select("#postingbody").text();
		desc = desc.substring(24, desc.length()-1);
		R.setDescription(desc);
			
		
		rentals.add(R);
		
		
		}
		
		
		return rentals;
	}

}
