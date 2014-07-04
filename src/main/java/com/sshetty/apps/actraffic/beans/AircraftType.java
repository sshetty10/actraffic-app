package com.sshetty.apps.actraffic.beans;

/**
 * @author Sameeksha
 * Aircraft Type enumeration
 */
public enum AircraftType {
	
	PASSENGER(1),CARGO(2);
	private int  acTypeCode;
	
	private AircraftType(int acTypeCode){
		this.acTypeCode = acTypeCode;
	}
	
	public int getAcTypeCode(){
		return this.acTypeCode;
	}
}
