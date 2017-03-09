package scrapers;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.Contact;
import main.Rental;

public class CastanetScraper implements Scraper {

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {
		//

		ArrayList<Rental> rentals = new ArrayList<Rental>();
		
		Contact C = new Contact();
		Document Startdoc = Jsoup.connect("http://classifieds.castanet.net/cat/rentals/shared_and_roommates/").get();
		
		System.out.println("Connected to: " + Startdoc.baseUri());

		Elements links = Startdoc.select("a.prod_container");

		
		int k = 0;
		for (Element l : links) {
			
			Rental R = new Rental();
			
			Document doc = Jsoup.connect(l.attr("abs:href")).get();

			R.setLink(doc.location());

			// title
			Elements title = doc.getElementsByTag("h1");
			R.setTitle(title.get(0).text());

			// description
			Elements desc = doc.select(".description");
			R.setDescription(desc.get(0).text());

			// price
			Elements price = doc.select(".price");
			String p = price.get(0).text();
			R.setPrice(p);

			// getting the data from the tables

			
			Elements td = doc.getElementsByTag("td");
			for (int i = 0; i < td.size(); i++) {

				String OGtxt = td.get(i).text();
				String txt = OGtxt.toLowerCase();
				String nxt = " ";

				// arraylists amiright
				if (i != td.size() - 1)
					nxt = td.get(i + 1).text();

				// since castanet doesn't label their tds we're gonna have a
				// bigass switch case let's go boyssss
				switch (txt) {
				case "address:":
					// put it in the db but i'm not dealing w/ that rn so just
					// print it
					R.setAddress(nxt);
					// System.out.println("Address: " + nxt);
					break;

				case "name:":
					C.setName(nxt);
					break;

				case "agent full name":
					C.setName(nxt);
					break;

				case "phone:":
					if (!nxt.contains("email")) {

						C.setPhone(nxt);
						// have to contact through castanet
					}
					break;
				}
			}
			R.setContact(C);
			rentals.add(R);
			
			System.out.printf("%.0f%%\n",(100*(float)k/(float)links.size()));
			k++;
		}
		/*
		 * testing patterns n shit
		 * 
		 * for(Rental r : rentals){ System.out.println(r.getTitle());
		 * System.out.println(r.getAddress()); System.out.println(r.getPrice());
		 * 
		 * 
		 * }
		 * 
		 * /*testing patterns n shit Pattern p =
		 * Pattern.compile("\\d{3,4}\\s\\w+\\s\\w+"); Matcher m =
		 * p.matcher("135 Ziprick Road"); System.out.println(m.matches());
		 */

		return rentals;
		// return null;
	}

}
