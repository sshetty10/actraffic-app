/**
 * 
 */
package com.sshetty.apps.actraffic.beans;

import java.util.Comparator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author Sameeksha
 * Singleton class which provides methods to maintain
 * a static PriorityQueue object which will contain the list of all the active aircrafts
 * 
 */
public class AcManagerQueue {
	private static final Logger logger = Logger.getLogger(AcManagerQueue.class);
	public static AcSort acComparator = new AcSort();
	
	//Comparator to handle the removal precedence of aircrafts from the queue
	static class AcSort implements Comparator<Aircraft> {
	public int compare(Aircraft ac1, Aircraft ac2) {
	
			if(ac1.getAcType().getAcTypeCode() == ac2.getAcType().getAcTypeCode()){	// If types are equal, type has higher precedence over size
				if(ac1.getAcSize().getAcSizeCode() == ac2.getAcSize().getAcSizeCode()){	// If sizes are equal
					return (ac1.getTimeStamp() > ac2.getTimeStamp()?1:-1); //removal precedence based on timestamp
				}
				else{
					return ac1.getAcSize().getAcSizeCode() - ac2.getAcSize().getAcSizeCode(); // if sizes unequal then removal precedence based on size
				}
			}
			else{
				return ac1.getAcType().getAcTypeCode() - ac2.getAcType().getAcTypeCode(); // if types unequal then removal precedence based on type
			}
		}
	}
	public static AcManagerQueue acmgrQueue;
	
	//PriorityQueue object which will maintain the lsit of all the active aircrafts
	public static PriorityQueue<Aircraft> acQueue;

	//HashSet acSet (acSet) mused to avoid duplication of aircrafts in a given active queue
	private static Set<String> acSet;
	
	private AcManagerQueue(){
		acQueue = new PriorityQueue<Aircraft>(11,acComparator);
		acSet = new HashSet<String>();
	}
	
	public static synchronized AcManagerQueue getInstance(){
		if(acmgrQueue==null){
			acmgrQueue = new AcManagerQueue();
		}
		return acmgrQueue;
	}
	
	/**
	 * @param ac
	 * Add element to the active queue
	 */
	public void addQueue(Aircraft ac){
			acQueue.add(ac);
	}
	
	
	/**
	 * @param ac
	 * @return false if aircraft with the given number already exists in the queue
	 */
	public boolean checkIfNotExists(Aircraft ac){
		return acSet.add(ac.getNumber().toUpperCase());
	}
	
	
	/**
	 * @return the removed Aircraft object
	 * remove aircraft from the active queue
	 */
	public Aircraft removeFromQ(){
		try{
			Aircraft ac = acQueue.remove();
			acSet.remove(ac.getNumber().toUpperCase());
			logger.info("Aircraft to be removed:" + ac.getNumber());
			return ac;
		}
		catch(NoSuchElementException ex){
			System.out.println("Aircraft queue is empty:");
			return null;
		}
	}
	
	/**
	 * View all aircrafts in the active queue
	 */
	public void viewQueue(){
		Object[] acArr= acQueue.toArray();
		int i=0;
		if(acArr.length==1)
			System.out.println("There is "+acArr.length + " aircraft in the queue:");
		else if(acArr.length==0)
			System.out.println("There are no aircrafts in the queue:");
		else
			System.out.println("There are "+acArr.length + " aircrafts in the queue:");
		while(i< acArr.length){
			Aircraft ac = (Aircraft) acArr[i];
			System.out.println(ac.getNumber() + " : A "+  ac.getAcSize().name()+  " " + ac.getAcType().name() +" Aircraft added at " + ac.getTimeStamp() + " (time in milliseconds)");
			i++;
		}
	}

}
