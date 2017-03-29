package main;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;

import scrapers.CastanetScraper;
import scrapers.CraigslistScraper;
import scrapers.KijijiScraper;

public class Scheduler {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		
		Database db = new Database();
		db.getConnection();	
		//db.clearAllRentals();
		CraigslistScraper cs = new CraigslistScraper();
		
		CastanetScraper cc = new CastanetScraper();
		KijijiScraper ks = new KijijiScraper();
		
		db.addRentals(cc.scrapeAll());				
		db.addRentals(ks.scrapeAll()); 
		db.addRentals(cs.scrapeAll()); 
		
		System.out.println("Finished Scraping :)");

		
		//db.closeConnection();

		
	//	ks.scrapeAll();


	}

}
