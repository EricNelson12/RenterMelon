// I'm thinking that this should be changed to an abstract class, not really sure at this point. 


package scrapers;

import java.io.IOException;
import java.util.ArrayList;

import main.Rental;

//Define what it is to be a scraper. Currently, just some class that returns an ArrayList of Rentals
public interface Scraper {
	
		ArrayList<Rental> scrapeAll() throws IOException;	
		
}

