package com.sshetty.apps.actraffic.beans;

public enum AircraftSize {
	
	LARGE(5),SMALL(11);
	private int  acSizeCode;
	
	private AircraftSize(int acSizeCode){
		this.acSizeCode = acSizeCode;
	}
	
	public int getAcSizeCode(){
		return this.acSizeCode;
	}
}
