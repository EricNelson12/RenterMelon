package main;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.Statement;

public class UnitTest {
	Database db = new Database();
	
	
	@Test 
	public void dbCon(){
		boolean con = false;
		
		try {
			db.getConnection();
			con = true;
		} catch (SQLException e) {
			// Sql will get mad if we can't connect. 
			e.printStackTrace();
		}	
		db.closeConnection();
		assertTrue(con);
	}
}
