
package main;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
//import okhttp3.*;

public class test {

	public static void main(String[] args) throws Exception {
		
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD5VuOQYcpsyakc9w43OPVe70v5Llb1mYw");
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		    "1258 Bowes Rd, Kelowna BC").await();
		System.out.println(results[0].geometry.location.lat);
	}

}
