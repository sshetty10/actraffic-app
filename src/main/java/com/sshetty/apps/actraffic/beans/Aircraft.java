/**
 * 
 */
package com.sshetty.apps.actraffic.beans;

/**
 * @author Sameeksha
 * Aircraft / AC object
 */
public class Aircraft {
	private AircraftType acType;
	private AircraftSize acSize;
	private String number;
	
	private long timeStamp;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public AircraftType getAcType() {
		return acType;
	}
	public void setAcType(AircraftType acType) {
		this.acType = acType;
	}
	public AircraftSize getAcSize() {
		return acSize;
	}
	public void setAcSize(AircraftSize acSize) {
		this.acSize = acSize;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
