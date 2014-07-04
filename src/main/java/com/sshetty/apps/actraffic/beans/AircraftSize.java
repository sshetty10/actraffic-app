package com.sshetty.apps.actraffic.beans;

/**
 * @author Sameeksha
 * Aircraft size enumeration
 */
public enum AircraftSize {
	
	LARGE(1),SMALL(2);
	private int  acSizeCode;
	
	private AircraftSize(int acSizeCode){
		this.acSizeCode = acSizeCode;
	}
	
	public int getAcSizeCode(){
		return this.acSizeCode;
	}
}
