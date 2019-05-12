package tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.EnemyRobot;
import info.Scan;

public class ScanTest {
	private Scan scan;
	private EnemyRobot friend;
	private EnemyRobot enemy;
	
	@Before
	public void setUp() throws Exception {
		scan = new Scan();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnScannedFriendlyRobot() {
		
	}

	@Test
	public void testOnScannedEnemyRobot() {
		fail("Not yet implemented");
	}

}
