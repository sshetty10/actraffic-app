/**
 * 
 */
package com.sshetty.actraffic.manager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author Sameeksha
 *7/4/2014
 * 
 * implements DB queries using simple JDBC
 * Database: In memory H2
 */
public class DbConnectionImpl implements DbConnection {
	private static final Logger logger = Logger.getLogger(DbConnectionImpl.class);
	private Properties dbprop;
	private String propertyName = "dbconnection.properties";
	/**
	 * @param args
	 * loads connection properties from dbConnection Properties
	 */
	public DbConnectionImpl(){
		loadDbProperties();
	}
	

	public Connection getConnection(){
		try {
			
			Class.forName((String) dbprop.getProperty("jdbc.driver.class"));
	        String url = (String) dbprop.getProperty("jdbc.url");
	        String username = (String) dbprop.getProperty("jdbc.username");
	        String password = (String) dbprop.getProperty("jdbc.password");
			Connection connection = DriverManager.getConnection(url, username,password);
			return connection;
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return null;
		}
	}
	
	public ResultSet executeQuery(String query){
		Connection connection = getConnection();
		try{Statement statement = connection.createStatement();
			return statement.executeQuery(query);
		}
		 catch (SQLException e) {
				return null;
			}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}
	}
	
	public void executeUpdate( String query){
		Connection connection = getConnection();
		try{
			Statement statement = connection.createStatement();
			 statement.execute(query);
		}
		 catch (SQLException e) {
				logger.error(e);
			}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}
	}
	
	public ResultSet initialLoad(){
		Connection connection = getConnection();
		try{
			String query = "select * from aircrafts where deleted ='n'";
			ResultSet rs = executeQuery(query);
			if(rs==null){
				Statement statement = connection.createStatement();
				statement.execute("CREATE TABLE aircrafts ( AC_NUMBER VARCHAR(30) NOT NULL,"
	            		+ "TYPE VARCHAR(50), SIZE VARCHAR(30), TIMESTAMP_AS_NUM BIGINT, DELETED VARCHAR(1), PRIMARY KEY (AC_NUMBER,TIMESTAMP_AS_NUM));");
			}
			return rs;
		}
		catch (SQLException e) {
			logger.error(e);
			return null;
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}
	}
	
	/**
	 * Load Database Connection paramters from dbConnection.properties
	 */
	private void loadDbProperties(){
		try{
			dbprop = new Properties();
			dbprop.load(ClassLoader.getSystemResourceAsStream(propertyName));
		}
		catch(Exception e){
			logger.error("Error in loading the property file " + propertyName);
			logger.error(e);
		}
	}

}
