package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

public class Database {

	// Server Configuration
	private String userName = "root";
	private String password = "";
	private String dbms = "mysql";
	private String serverName = "localhost";
	private int portNumber = 3306;

	static Connection conn = null;

	// Connect to the server
	public Connection getConnection() throws SQLException {

		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/",
				connectionProps);

		System.out.println("Connected to database");
		String q = "USE localrentermelon";
		Statement s = conn.createStatement();
		s.executeQuery(q);

		return conn;
	}

	// Add a list of rentals
	public void addRentals(ArrayList<Rental> R) throws SQLException, ParseException {

		String query = "INSERT into rental (title, address, price, description, link, area) VALUES (?,?,?,?,?,?)";

		for (Rental r : R) {
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			String area = r.getArea();
			
			String title = r.getTitle();
			title = title.substring(0, Math.min(title.length(), 49));
			
			

			String addr = r.getAddress();
			if (addr != null)
				addr = addr.substring(0, Math.min(addr.length(), 69));

			Float price = 0f; // Initialize to Zero, because parse below will throw
								// errors when jerk-faces put "contact for
								// price" instead of a number..
			try {
				NumberFormat format = NumberFormat.getCurrencyInstance();
				Number number = format.parse(r.getPrice());
				price = number.floatValue();
			} catch (Exception e) {}

			

			String desc = r.getDescription();
			desc = desc.substring(0, Math.min(desc.length(), 49));

			// desc = desc.substring(0, Math.min(desc.length(), 399));

			String sourceUrl = r.getLink();

			ps.setString(1, title);
			ps.setString(2, addr);
			ps.setFloat(3, price);
			ps.setString(4, desc);
			ps.setString(5, sourceUrl);
			ps.setString(6, area);

			ps.execute();

			// inserting into Contacts

			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			int auto_id = keys.getInt(1);

			// addContact(r, auto_id);
		}

		conn.close();
	}
	
	public void clearAllRentals() throws SQLException{
		Statement s = conn.createStatement();
		s.execute("TRUNCATE TABLE rental");
	}

	public void addContact(Rental r, int id) throws SQLException {

		Contact c = r.getContact();
		String q = "INSERT into contact (rID, name, phone, email) VALUES (?,?,?,?)";
		PreparedStatement pps = conn.prepareStatement(q);

		pps.setInt(1, id);
		pps.setString(2, c.getName());
		pps.setString(3, c.getPhone());
		pps.setString(4, c.getEmail());

		pps.execute();
	}

}
