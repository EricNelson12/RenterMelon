package main;

import java.io.IOException;
import scrapers.KijijiScraper;

public class Scheduler {

	public static void main(String[] args) throws IOException {
		
		KijijiScraper ks = new KijijiScraper();
		ks.scrapeAll();
	}

}
