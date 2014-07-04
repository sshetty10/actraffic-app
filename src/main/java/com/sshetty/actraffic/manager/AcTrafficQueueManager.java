/**
 * 
 */
package com.sshetty.actraffic.manager;

import com.sshetty.apps.actraffic.beans.Aircraft;

/**
 * @author Sameeksha
 * Interface for the Airtraffic Subsytem 
 * 1. On Starting the Subsytem initial Load all the active records before previous shutdowns into the current active queue
 * 2. Add an Aircraft
 * 3. Remove an Aircraft
 * 4. View all the current active elements in the aircraft
 * 5. Check if an aircraft already exisits in the queue.
 * 
 * Once an aircraft is removed it can be added again
 */
public interface AcTrafficQueueManager {

	/**
	 * @return
	 * creates in memory DB if does not exist and creates the table if does not exist
	 * and loads all the non deleted records in the active Aircrat queue
	 */
	public boolean initialLoadFromDb();
	/**
	 * @param ac
	 * @return
	 * Add Aircraft Object to the active Aircaft Queue
	 */
	public boolean addToQueue(Aircraft ac);
	/**
	 * @param ac
	 * @return
	 * Check if The aircraft object already exists in the activeQueue
	 */
	public boolean checkIfNotExists(Aircraft ac);
	/**
	 * @return
	 * Remove the aircraft from the active aircraft queue based on removal precedence
	 */
	public Aircraft removeFromQ();
	/**
	 * @return
	 * View all the elements in the active aircraft queue
	 */
	public boolean viewQueue();
}
