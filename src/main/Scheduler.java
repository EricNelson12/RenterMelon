package main;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;

import scrapers.CastanetScraper;

import scrapers.KijijiScraper;

public class Scheduler {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		
		CastanetScraper cc = new CastanetScraper();

		//KijijiScraper ks = new KijijiScraper();
		Database db = new Database();
		db.getConnection();
		db.addRentals(cc.scrapeAll()); 

		//KijijiScraper ks = new KijijiScraper();
		//ks.scrapeAll();
		



	}

}
