package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
	
	
	//Server Configuration
	private String userName = "root";
	private String password = "";
	private String dbms = "mysql";
	private String serverName = "localhost";	
	private int portNumber = 3306;
	
	static Connection conn = null;
	
	
	//Connect to the server
	public Connection getConnection() throws SQLException {

	    
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);
	    
	    conn = DriverManager.getConnection(
                "jdbc:" + this.dbms + "://" +
                this.serverName + 
                ":" + this.portNumber + "/",
                connectionProps);
	    
	    System.out.println("Connected to database");
	    String q = "USE localrentermelon";
	    Statement s = conn.createStatement();
	    s.executeQuery(q);
	    
	    return conn;
	}
	
	//Add a list of rentals
	public void addRentals(ArrayList<Rental> R) throws SQLException{
		
		String query = "INSERT into rental (title,area,price) VALUES (?,?,?)";
		
	    
		for(Rental r : R){
			PreparedStatement ps = conn.prepareStatement(query);
			String title = r.getTitle();
			title = title.substring(0, Math.min(title.length(), 9));
			String area = r.getAddress();
			area = area.substring(0, Math.min(area.length(), 9));
			ps.setString (1, title );
		    ps.setString (2, area);
		    ps.setFloat(3, 10.00f);
		    
		    ps.execute();
		      
		    
		}
		
		conn.close();
	}
}
