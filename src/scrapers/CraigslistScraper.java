package scrapers;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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

		// result-image gallery

		// Init new list of rentals
		ArrayList<Rental> rentals = new ArrayList<Rental>();

		int k = 0;
		for (Element l : links) {

			try{
			// Get website
			String link = l.attr("abs:href");
			Document rentalPage = Jsoup.connect(link).get();

			// make our stuff
			Rental R = new Rental();
			Contact C = new Contact();

			// setting link
			R.setLink(rentalPage.location());

			// setting image
			String url = "no image";
			if (rentalPage.getElementsByClass("slide first visible").size() > 0) {
				Elements image = rentalPage.getElementsByClass("slide first visible");
				Element im = image.get(0).child(0);
				url = im.absUrl("src");
			}
			R.setImg(url);

			// setting address
			Elements adr = rentalPage.select(".mapaddress");
			if (adr.size() > 0) {
				String a = adr.get(0).text();
				R.setAddress(a);
			}

			Element d = rentalPage.select(".timeago").first();
			String dat = d.text();
			// System.out.println(dat);
			Date post = parDate(dat);
			R.setDate(post);

			// PRICE
			Elements p = rentalPage.select(".price");
			if (p.size() > 0) {
				String price = p.get(0).text();
				R.setPrice(price);
			}
			
			Elements b = rentalPage.select(".attrgroup span").first().children();
			String bedrms = b.first().text();
			R.bed = Integer.parseInt(bedrms.substring(0, 1)); 
			String bathrms = b.last().text();
			R.bath = Integer.parseInt(bathrms.substring(0, 1)); 

			// title
			String title = rentalPage.select("#titletextonly").text();
			R.setTitle(title);

			String desc = rentalPage.select("#postingbody").text();
			desc = desc.substring(24, desc.length() - 1);
			R.setDescription(desc);

			if (!desc.toLowerCase().contains("no smoking"))
				R.smoking = true;
			if (desc.toLowerCase().contains(" furnished") && !desc.toLowerCase().contains("not furnished"))
				R.furnished = true;
			if (!desc.toLowerCase().contains("no pet"))
				R.pets = true;

			R.setContact(C);
			rentals.add(R);
			System.out.printf("%.0f%%\n", (100 * (float) k / (float) links.size()));
			k++;
			}catch(Exception e){
				System.out.println(e.toString());
			}
			

		}

		return rentals;
	}

	public Date parDate(String dat) {
		try {
			DateFormat d1 = new SimpleDateFormat("yyyy-MM-dd h:mma", Locale.US);
			java.util.Date D1;
			D1 = d1.parse(dat);
			DateFormat d2 = new SimpleDateFormat("yyyy-MM-dd");
			String returnDate = d2.format(D1);

			return Date.valueOf(returnDate);
		} catch (ParseException e) {
			// fixed
			System.out.println("oops");
			return null;
		}

	}

}
