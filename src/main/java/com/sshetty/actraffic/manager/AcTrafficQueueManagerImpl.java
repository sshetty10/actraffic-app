/**
 * 
 */
package com.sshetty.actraffic.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sshetty.actraffic.manager.db.DbConnection;
import com.sshetty.actraffic.manager.db.DbConnectionImpl;
import com.sshetty.apps.actraffic.beans.AcManagerQueue;
import com.sshetty.apps.actraffic.beans.Aircraft;
import com.sshetty.apps.actraffic.beans.AircraftSize;
import com.sshetty.apps.actraffic.beans.AircraftType;

/**
 * @author Sameeksha
 *
 *Implementation the core Airtraffic Susbsytem functionalities
 */
public class AcTrafficQueueManagerImpl implements AcTrafficQueueManager {
	private static final Logger logger = Logger.getLogger(AcTrafficQueueManagerImpl.class);
	AcManagerQueue acQueue;
	DbConnection dbconn;
	Connection conn;
	
	
	/**
	 * Instantiate the singleton Object carrying the queue
	 * laod the DbConnection object
	 */
	public AcTrafficQueueManagerImpl(){
		acQueue = AcManagerQueue.getInstance();
		dbconn = new DbConnectionImpl();
	}
	
	/**
	 * @param dbConn
	 * generated for injecting mock Connection object in Tests
	 */
	public AcTrafficQueueManagerImpl(DbConnection dbConn){
		acQueue = AcManagerQueue.getInstance();
		dbconn = dbConn;
	}

	/* (non-Javadoc)
	 * @see com.sshetty.actraffic.manager.AcTrafficQueueManager#addQueue(com.sshetty.apps.actraffic.beans.Aircraft)
	 */
	public boolean addToQueue(Aircraft ac) {
		ac.setTimeStamp(System.currentTimeMillis());
		acQueue.addQueue(ac);
		String query = "insert into aircrafts values ('"+ac.getNumber()+"','"+ac.getAcType().name()+ "','"+ ac.getAcSize().name()+ "',"+ ac.getTimeStamp()+",'n')";
		dbconn.executeUpdate(query);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.sshetty.actraffic.manager.AcTrafficQueueManager#checkIfAlreadyExists(com.sshetty.apps.actraffic.beans.Aircraft)
	 */
	public boolean checkIfNotExists(Aircraft ac) {
		return acQueue.checkIfNotExists(ac);
	}

	/* (non-Javadoc)
	 * @see com.sshetty.actraffic.manager.AcTrafficQueueManager#removeFromQ()
	 */
	public Aircraft removeFromQ() {
		Aircraft ac = acQueue.removeFromQ();
		String query = "update aircrafts set deleted ='y' where ac_number='"+ac.getNumber()+"'";
		dbconn.executeUpdate(query);
		return ac;
	}

	/* (non-Javadoc)
	 * @see com.sshetty.actraffic.manager.AcTrafficQueueManager#viewQueue()
	 */
	public boolean viewQueue() {
		acQueue.viewQueue();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.sshetty.actraffic.manager.AcTrafficQueueManager#initialLoadFromDb()
	 */
	public boolean initialLoadFromDb(){
		try {
			ResultSet rs = dbconn.initialLoad();
			if(rs!=null){
				while(rs.next()){
					Aircraft ac = new Aircraft();
					ac.setNumber(rs.getString(1));
					ac.setAcType(AircraftType.valueOf(rs.getString(2)));
					ac.setAcSize(AircraftSize.valueOf(rs.getString(3)));
					ac.setTimeStamp(rs.getLong(4));
					if(checkIfNotExists(ac))
						acQueue.addQueue(ac);
				}
				acQueue.viewQueue();
			}
			return true;
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}
		
	}

}
