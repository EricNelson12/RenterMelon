package main;

import java.io.IOException;
import java.sql.SQLException;

import scrapers.KijijiScraper;

public class Scheduler {

	public static void main(String[] args) throws IOException, SQLException {
		
		KijijiScraper ks = new KijijiScraper();
		Database db = new Database();
		db.getConnection();
		db.addRentals(ks.scrapeAll());
	}

}
