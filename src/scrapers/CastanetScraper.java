package scrapers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import main.Rental;

public class CastanetScraper implements Scraper {

	@Override
	public ArrayList<Rental> scrapeAll() throws IOException {
		Document doc = Jsoup.connect("http://classifieds.castanet.net/details/2_bed_2_bath_condo_opposite_costco/2982667/").get();
		Elements desc = doc.select(".description");
		System.out.println(desc.get(0).text());
		return null;
	}

}
