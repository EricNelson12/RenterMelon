package scrapers;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.Rental;

public class KijijiScraper implements Scraper {

	// Base URI
	final private String URL = "http://www.kijiji.ca/b-house-rental/kelowna/c43l1700228";

	// Index of attributes in table
	final private int DATE = 0;
	final private int PRICE = 1;
	final private int ADDRESS = 2;

	// Attribute [0] : 08-Mar-17
	// Attribute [1] : $1.00
	// Attribute [2] : Kelowna, BC V1Y9W1 View map
	// Attribute [3] :
	// Attribute [4] : 3 bedrooms
	// Attribute [5] : 2 bathrooms
	// Attribute [6] : Owner
	// Attribute [7] : No
	// Attribute [8] :

	// Init new list of rentals
	ArrayList<Rental> rentals = new ArrayList<Rental>();

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {

		// Get website
		Document doc = Jsoup.connect(URL).get();
		Elements elements = doc.select(".info-container");

		System.out.println("Connected to: " + doc.baseUri());

		// Explore all URLs to rentals on page
		for (Element element : elements) {

			// Create new rental object
			Rental R = new Rental();

			// Connect to url of individual ad
			String link = element.select("a.title").attr("abs:href");
			Document rentalPage = Jsoup.connect(link).get();
			System.out.println("\n\nConnected to: " + rentalPage.baseUri());

			// Get table with all of the attributes
			Elements attributes = rentalPage.select(".ad-attributes tr td");

			// Set attributes
			R.setDescription(rentalPage.select("#UserContent").text());
			R.setTitle(element.select(".title *").text());
			R.setLink(link);
			R.setPrice(attributes.get(PRICE).text());
			setDate(attributes.get(DATE).text(), R);
			setAddress(attributes.get(ADDRESS).text(), R);

			System.out.println(R.toString());

			// Add to list of rentals
			rentals.add(R);

		}

		return rentals;
	}

	// Advanced Parsing Methods
	private void setAddress(String s, Rental r) {
		String area;
		if (s.toLowerCase().contains("kel")) {
			area = "Kelowna";
		} else if (s.toLowerCase().contains("pen")) {
			area = "Penticton";
		} else {
			area = "Not Available";
		}

		r.setArea(area);
		r.setAddress(s.substring(0, s.length() - 8));

	}

	// http://stackoverflow.com/questions/1635681/parsing-a-date-with-short-month-without-dot
	private void setDate(String s, Rental r) {

		try {
			SimpleDateFormat parser = new SimpleDateFormat("dd-MMM-yy");
			java.util.Date d = parser.parse(s);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = formatter.format(d);
			System.out.println(formattedDate);

			r.setDate(Date.valueOf(formattedDate));

		} catch (ParseException e) {
			System.out.println("Couldn't parse date");
		}

	}


}
