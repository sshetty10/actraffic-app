package com.sshetty.actraffic.tests;
import static org.junit.Assert.*;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sshetty.actraffic.manager.AcTrafficQueueManagerImpl;
import com.sshetty.actraffic.manager.db.DbConnection;
import com.sshetty.apps.actraffic.beans.Aircraft;
import com.sshetty.apps.actraffic.beans.AircraftSize;
import com.sshetty.apps.actraffic.beans.AircraftType;

/**
 * 
 */

/**
 * @author Sameeksha
 * Unit tests for the core Airtraffic subsytem interface
 */
public class AcTrafficQueueManagerTest{

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Mock
	DbConnection dbconnMock;
	Aircraft ac;
	AcTrafficQueueManagerImpl acqManager;
	
	@Before
	public void setUp() throws Exception {
		 MockitoAnnotations.initMocks(this);
		 ac= new Aircraft();
		 ac.setNumber(RandomStringUtils.randomAlphanumeric(7));
		 ac.setAcType(AircraftType.PASSENGER);
		 ac.setAcSize(AircraftSize.SMALL);
		 ac.setTimeStamp(System.currentTimeMillis());
	}
	
	@Test
	public void testInitialLoad(){
		AcTrafficQueueManagerImpl acqManager = new AcTrafficQueueManagerImpl(dbconnMock);
		boolean result = acqManager.initialLoadFromDb();
		assertTrue(result);
		Mockito.verify(dbconnMock).initialLoad();
	}
	
	@Test
	public void testcheckIfNotExists(){
		AcTrafficQueueManagerImpl acqManager = new AcTrafficQueueManagerImpl(dbconnMock);
		boolean result = acqManager.checkIfNotExists(ac);
		assertTrue(result);
	}
	
	
	
	
	@Test
	public void testaddQueue(){
		AcTrafficQueueManagerImpl acqManager = new AcTrafficQueueManagerImpl(dbconnMock);
		boolean result = acqManager.addToQueue(ac);
		assertTrue(result);
		Mockito.verify(dbconnMock).executeUpdate("insert into aircrafts values ('"+ac.getNumber()+"','"+ac.getAcType().name()+ "','"+ ac.getAcSize().name()+ "',"+ ac.getTimeStamp()+",'n')");
	}
	
	
	@Test
	public void testviewQueue(){
		AcTrafficQueueManagerImpl acqManager = new AcTrafficQueueManagerImpl(dbconnMock);
		boolean result = acqManager.viewQueue();
		assertTrue(result);
	}
	
	//after adding to the queue test if exists
	
	@Test
	public void testcheckIfNotExistsNegative(){
		AcTrafficQueueManagerImpl acqManager = new AcTrafficQueueManagerImpl(dbconnMock);
		boolean result = acqManager.checkIfNotExists(ac);
		assertTrue(result);
	}
	
	@Test
	public void testremoveQueue(){
		AcTrafficQueueManagerImpl acqManager = new AcTrafficQueueManagerImpl(dbconnMock);
		Aircraft result = acqManager.removeFromQ();
		assertNotNull(result);
		Mockito.verify(dbconnMock).executeUpdate("update aircrafts set deleted ='y' where ac_number='"+result.getNumber()+"'");
	}

}
