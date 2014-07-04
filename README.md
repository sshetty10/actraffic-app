#AirTraffic Subsytem
The following document will provide an overview of the AirTraffic subsystem implementation. 
This system can add, remove and view aircrafts in a AirTraffic Queue at any given time.
Implementation is a Maven project written in java . Executable is a jar (actraffic-app-1.0.2.jar).
It is a command line driven application with self explanatory menu choices.
An in memory H2 DB has been implemented as this is a prototype version.
Junits collaborated with Mockito used for generating basic Junits.
Log4j implementation provided for logging capabilities.


## Instructions
1. Project Architecture
	a. src/main/java
		i. com.sshetty.actraffic.manager.main
			-AcTrafficManager.java (Main class)
		ii.com.sshetty.actraffic.manager
			-AcTrafficQueueManager.java (Interface) - provides AirTraffic subsystem methods for integration into the parent application
			-AcTrafficQueueManagerImpl.java (Implementation class) - provides implementation for the above interface
		iii.com.sshetty.apps.actraffic.beans
			-AcManagerQueue.java ( Singleton class)- managing the aircraft queue
			-Aircraft.java (POJO) - aircraft object
			-AircraftType.java (Enumeration)- Enumeration for different types of an aircraft. As and when more types are added , adding the values to the enumeration only will suffice. Implemntation or beans need not be changed 
			-AircraftSize.java (Enumeration)- Enumeration for different sizes of an aircraft. As and when more sizes are added , adding the values to the enumeration only will suffice. Implemntation or beans need not be changed 
		iv.com.sshetty.actraffic.manager.db
			-DbConnection.java (Interface) - provides DB connectivity methods
			-DbConnectionImpl.java (Implementation class) - implements the DB connection methods of the above Interface 
	b. 	src/main/resources
		i.  db(folder) - Will get created when u first the run the application. Holds the context of the in memory H2 DB
		ii. dbconnection.properties-configurable for DB conections
			-jdbc.url : ds1.jdbc.url - (jdbcurl)
			-jdbc.username : dbc.username - (jdbcusername)
			-jdbc.password : jdbc.password - (jdbcpassword)
			-jdbc.driver.class: jdbc.driver.class - (org.hsqldb.jdbcDriver)
		iii. log4.properties: log4j implementation
	c.src/test/java
		i.com.sshetty.actraffic.tests
			-AcTrafficQueueManagerTest.java
	d. src/test/resources
		i.  log4j.properties-configurable paramters for application logging
		ii. dbconnection.properties-configurable for DB conections
		
2. Basic Features:
	a. Add an aircraft
	b. remove an aircraft from the system based on removal precedence(AcManagerQueue comparator implementation)
	c. Check if aircraft already exists in the queue and add only if not only then added it to the queue
	d. Start the Subsytem : Load all the active aircrafts before previous shutdown into the queue(the Queue is always persisted)
	
3. Enahnced Features
	a. Save all the aircrafts to Database when added and mark them as soft deleted when removed
	b. View all the acitve aircrafts in a queue
	c. Mockito and Junit based test cases
	d. log4j implementation - actraffic.log created
	e. Implementation of an in-memory DB. No scripts required will get created with the application.
		
3. Working
	a. AcTrafficManager - Main method triggers the menu which drives the application
		i : Menu 1
			Choices: Start the subsytem (Loads the queue state), Exit (Close the application)
		ii. Menu 2 - After starting the subsytem
			Choices
			a. Enqueue Aicraft - Add Aircraft to the system - enter the flight number (Unique for active aircrafts. Cannot add an existing aircraft(same flight number) unless its removed)
				1. Menu 3 : Choose the type of the aircraft
				2. Menu 4 : Choose the size of the aircraft
			b. Remove Aircraft - Remove aircraft from the system
			c. View Aircrafts - all active aircrafts in the queue
			d. Exit - Close the application. The queue will be persisted so that can be loaded the next time

Maven goals:
mvn clean compile
mvn test
mvn install
