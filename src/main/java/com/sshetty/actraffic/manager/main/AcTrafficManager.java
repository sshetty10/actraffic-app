/**
 * 
 */
package com.sshetty.actraffic.manager.main;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.sshetty.actraffic.manager.AcTrafficQueueManager;
import com.sshetty.actraffic.manager.AcTrafficQueueManagerImpl;
import com.sshetty.apps.actraffic.beans.Aircraft;
import com.sshetty.apps.actraffic.beans.AircraftSize;
import com.sshetty.apps.actraffic.beans.AircraftType;

/**
 * @author Sameeksha
 *
 */
public class AcTrafficManager {
	
	public AcTrafficQueueManager acQManager;
	public AcTrafficManager(){
		acQManager = new AcTrafficQueueManagerImpl();
	}
	
	public static void main(String args[]){
		AcTrafficManager acManager = new AcTrafficManager();
		acManager.printMainMenu();
	}
	
	private void printMainMenu(){
		// Display menu graphics
		int choice =-1;
		do{
			System.out.println();
		    System.out.println("========================================");
		    System.out.println("  WELCOME TO AC TRAFFIC MANAGEMENT		");
		    System.out.println("========================================");
		    System.out.println(" Options:                 ");
		    System.out.println("        1. Start Subsystem");
		    System.out.println("        2. Exit           ");
		    System.out.println("========================================");
		    System.out.println("Enter your choice:");
		    choice = readInt();
		    if(choice==-1)
		    	System.out.println("Invalid Choice");
		}while(choice==-1);
		switch (choice) {
	    case 1:
	      this.acQManager.initialLoadFromDb();
	      System.out.println("Started AirTraffic Subsystem");
	      printSubSystemMenu();
	      break;
	    case 2:
	      System.out.println("Closing AirTraffic Subsystem");
	      System.exit(0);
	      break;
	    default:
	      System.out.println("Invalid Choice");
	      break; // This break is not really necessary
	    }
	}
	
	private void printTypeMenu(Aircraft ac){
		// Display menu graphics
		int choice =-1;
		do{
			System.out.println();
			System.out.println("PLEASE SELECT AN AIRCRAFT TYPE FOR :"+ ac.getNumber());
		    System.out.println("==========================================");
		    System.out.println("   AIRCRAFT TYPE MENU    				 ");
		    System.out.println("==========================================");
		    System.out.println(" Options:                 				 ");
		    System.out.println("        1. Passenger					 ");
		    System.out.println("        2. Cargo           			 ");
		    System.out.println("        3. Return to Main Menu          ");
		    System.out.println("==========================================");
		    System.out.println("Enter your choice:");
		    choice = readInt();
		    if(choice==-1)
		    	System.out.println("Invalid Choice");
		}while(choice==-1);
		switch (choice) {
	    case 1:
	      System.out.println("Passenger aircraft selected");
	      ac.setAcType(AircraftType.PASSENGER);
	      printSizeMenu(ac );
	      break;
	    case 2:
	      System.out.println("Cargo aircraft selected");
	      ac.setAcType(AircraftType.CARGO);
	      printSizeMenu(ac);
	      break;
	    case 3:
		      System.out.println("Return to the AirTraffic Subsystem Main Menu");
		      break;
	    default:
	      System.out.println("Invalid Choice");
	      break; // This break is not really necessary
	    }
	}
	
	private void printSizeMenu(Aircraft ac){
		// Display menu graphics
		// Display menu graphics
				int choice =-1;
				do{
					System.out.println();
					System.out.println("PLEASE SELECT THE SIZE FOR AIRCRAFT :"+ ac.getNumber());
					System.out.println("==========================================");
				    System.out.println("   AIRCRAFT SIZE MENU    				 ");
				    System.out.println("==========================================");
				    System.out.println(" Options:                 				 ");
				    System.out.println("        1. Large						 ");
				    System.out.println("        2. Small	           			 ");
				    System.out.println("        3. Return to Main Menu          ");
				    System.out.println("==========================================");
				    System.out.println("Enter your choice:");
				    choice = readInt();
				    if(choice==-1)
				    	System.out.println("Invalid Choice");
				}while(choice==-1);
				switch (choice) {
			    case 1:
			      ac.setAcSize(AircraftSize.LARGE);
			      this.acQManager.addToQueue(ac);
			      System.out.println(ac.getNumber() + " :A "+" Large " + ac.getAcType().name() + " aircraft has been added to the Queue");
				     
			      break;
			    case 2:
			    	ac.setAcSize(AircraftSize.SMALL);
			    	this.acQManager.addToQueue(ac);
				    System.out.println(ac.getNumber() + " :A "+" Small " + ac.getAcType().name() + "  aircraft has been added to the Queue");
			      break;
			    case 3:
				      System.out.println("Returning to the main menu");
				      break;
			    default:
			      System.out.println("Invalid Choice");
			      break; // This break is not really necessary
			    }
	}
	
	private void printSubSystemMenu(){
		// Display menu graphics
		int choice =-1;
		do{
			System.out.println();
		    System.out.println("==========================================");
		    System.out.println("   AIR TRAFFIC SUBSYSTEM MAIN MENU		 ");
		    System.out.println("==========================================");
		    System.out.println(" Options:                 				 ");
		    System.out.println("      1. Enqueue Aircraft 				 ");
		    System.out.println("      2. Dequeue an Aircraft            ");
		    System.out.println("      3. View the aircraft queue        ");
		    System.out.println("      4. Exit 				             ");
		    System.out.println("==========================================");
		    System.out.println("Enter your choice:");
		    choice = readInt();
			switch (choice) {
		    case 1:
		      System.out.println("Enter the Aircraft/Flight number:");
		      String number = readText();
		      choice=-1;
		      if(StringUtils.isNotEmpty(number)){
		    	  Aircraft ac = new Aircraft();
		    	  ac.setNumber(number.toUpperCase());
		    	  boolean doesNotExist = this.acQManager.checkIfNotExists(ac);
		    	  
		    	  if(doesNotExist)
		    		  printTypeMenu(ac);
		    	  else{
		    		  System.out.println("Invalid Aircraft/Flight number. Flight number is already enqueued.");
		    	  }
		      }
		      else{
		    	  System.out.println("Invalid Aircraft/Flight number. Flight number can contain alphabets/numbers with a maximum of 30 characters");
		      }
		      break;
		    case 2:
		    	  this.acQManager.removeFromQ();
			      choice=-1;
			      break;
		    case 3:
		    	  this.acQManager.viewQueue();
		    	  choice=-1;
			      break;
		    case 4:
			      System.out.println("Closing AirTraffic Subsystem");
			      System.exit(0);
			      break;
		    default:
		      System.out.println("Invalid Choice");
		      break; // This break is not really necessary
		    }
		}while(choice==-1);
	}
	
	private String readText(){
		try{
				
				Scanner scan = new Scanner(System.in);
				String name = scan.nextLine();
				boolean valid = name.matches("\\w+");
				return valid && name.length()<31?name:"";
		}
		catch(Exception ex){
			return "";
		}
	}
	
	private int readInt(){
		try{
				Scanner scan = new Scanner(System.in);
				int choice = scan.nextInt();
				return choice;
		}
		catch(Exception ex){
			return -1;
		}
	}

}
