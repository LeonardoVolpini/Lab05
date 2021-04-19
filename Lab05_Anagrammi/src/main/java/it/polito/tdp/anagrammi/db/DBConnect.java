package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {

	static private final String jdbcURL= "jdbc:mysql://localhost/dizionario?user=root&password=Leonardo00";
	static private HikariDataSource ds = null;
	
	public static Connection getConnection() throws SQLException {
		
		if(ds==null) {
			ds= new HikariDataSource();
			ds.setJdbcUrl(jdbcURL);
			//username e password li posso specificare o nella URL o con setUsername e setPassword
			//ds.setUsername("root");
			//ds.setPassword("Leonardo00");
		}
		try {
			Connection connection= ds.getConnection();
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore DB",e);
		}
	}
	
}
