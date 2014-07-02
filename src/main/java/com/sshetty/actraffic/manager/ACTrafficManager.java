/**
 * 
 */
package com.sshetty.actraffic.manager;

import com.sshetty.apps.actraffic.beans.Aircraft;
import com.sshetty.apps.actraffic.beans.AircraftSize;
import com.sshetty.apps.actraffic.beans.AircraftType;

/**
 * @author Sameeksha
 *
 */
public class ACTrafficManager {
	
	public static void main(String args[]){
		Aircraft ac = new Aircraft();
		ac.setAcType(AircraftType.valueOf("PASSENGER"));
		ac.setAcSize(AircraftSize.valueOf("LARGE"));
		
	}
	
	private boolean aqm_request_process(){
		//addtoQueue();
		return true;
	}

}
