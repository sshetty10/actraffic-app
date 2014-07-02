/**
 * 
 */
package com.sshetty.actraffic.manager;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.sshetty.apps.actraffic.beans.Aircraft;

/**
 * @author Sameeksha
 *
 */
public class AcManagerQueue {
	static class AcSort implements Comparator<Aircraft> {
		public int compare(Aircraft ac1, Aircraft ac2) {
			int ac1Val = ac1.getAcType().getAcTypeCode() + ac1.getAcSize().getAcSizeCode();
			int ac2Val = ac2.getAcType().getAcTypeCode() + ac2.getAcSize().getAcSizeCode();
			
			if(ac1Val == ac2Val){
				return (ac1.getTimeStamp() < ac2.getTimeStamp()?-1:1);
			}
			else{
				return (ac1Val - ac2Val);
			}
		}
	}
	private static PriorityQueue<Integer> acQueue;
	private AcManagerQueue(){
		
	}

}
