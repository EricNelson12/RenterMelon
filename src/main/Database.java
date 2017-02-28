package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	
	
	//Server Configuration
	private String userName = "root";
	private String password = "";
	private String dbms = "mysql";
	private String serverName = "localhost";	
	private int portNumber = 3306;
	
	
	
	public Connection getConnection() throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);
	    
	    conn = DriverManager.getConnection(
                "jdbc:" + this.dbms + "://" +
                this.serverName +
                ":" + this.portNumber + "/",
                connectionProps);
	    
	    System.out.println("Connected to database");
	    return conn;
	}
}
