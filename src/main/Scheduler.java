package main;

import java.io.IOException;

import scrapers.CastanetScraper;
import scrapers.KijijiScraper;

public class Scheduler {

	public static void main(String[] args) throws IOException {
		
		//KijijiScraper ks = new KijijiScraper();
		//ks.scrapeAll();
		
		CastanetScraper cc = new CastanetScraper();
		cc.scrapeAll();
	}

}
