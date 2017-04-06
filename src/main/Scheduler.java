package main;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

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
		
		ArrayList<Rental> rl = new ArrayList<>();
		
		rl.addAll(cs.scrapeAll());
		rl.addAll(cc.scrapeAll());
		rl.addAll(ks.scrapeAll());
		
		System.out.println("Scraping done, getting coordinates of addresses");
		convertAddress(rl);
		System.out.println("Coordinates aquired, adding to database");
		
		db.addRentals(rl);				
		
		
		System.out.println("Finished Scraping :)");

		
		//db.closeConnection();

		
	//	ks.scrapeAll();


	}
	
	// Converts adress to long and lat and adds to rental object
	public static void convertAddress(ArrayList<Rental> r){
		
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD5VuOQYcpsyakc9w43OPVe70v5Llb1mYw");
		
		for(Rental x : r){
			
			GeocodingResult[] results;
			try {
				results = GeocodingApi.geocode(context,x.getAddress()+"kelowna BC Canada").await();
				System.out.println(results[0].formattedAddress);
				
				x.lng = results[0].geometry.location.lng;
				x.lat = results[0].geometry.location.lat;
				
			} catch (Exception e) {
				System.out.println("Couldn't Geocode address: "+ x.getAddress());
			}
			
		}
		
		
	}

}
