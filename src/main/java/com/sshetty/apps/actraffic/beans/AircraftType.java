package com.sshetty.apps.actraffic.beans;

public enum AircraftType {
	
	PASSENGER(1),CARGO(3);
	private int  acTypeCode;
	
	private AircraftType(int acTypeCode){
		this.acTypeCode = acTypeCode;
	}
	
	public int getAcTypeCode(){
		return this.acTypeCode;
	}
}
