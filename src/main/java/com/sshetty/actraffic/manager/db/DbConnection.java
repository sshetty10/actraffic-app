package com.sshetty.actraffic.manager.db;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author Sameeksha
 * 7/4/2014
 * Interface for DB connectivity
 * Current extended by the DbConnectionImpl
 */
public interface DbConnection {
	/**
	 * @return
	 * creates in memory DB if does not exist and creates the table if does not exist
	 */
	public ResultSet initialLoad();
	/**
	 * @return
	 * Get Connection Object
	 */
	public Connection getConnection();
	/**
	 * @param query
	 * @return ResultSet : null in case of exceptions
	 * Select queries
	 */
	public ResultSet executeQuery(String query);
	/**
	 * @param query
	 * Update and Insert queries
	 */
	public void executeUpdate( String query);
	
}
