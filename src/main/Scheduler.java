package main;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;

import scrapers.CastanetScraper;

import scrapers.KijijiScraper;

public class Scheduler {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		
		Database db = new Database();
		db.getConnection();	
		//db.clearAllRentals();
		
		CastanetScraper cc = new CastanetScraper();
		KijijiScraper ks = new KijijiScraper();
				

				
		db.addRentals(ks.scrapeAll()); 
		db.addRentals(cc.scrapeAll()); 
		
		System.out.println("Finished Scraping :)");
		
		db.closeConnection();

		
	//	ks.scrapeAll();


	}

}
