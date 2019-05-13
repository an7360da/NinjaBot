package tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.EnemyRobot;
import info.Scan;

public class ScanTest {
	private Scan scan;
	
	private EnemyRobot friend1;
	private EnemyRobot friend2;
	private EnemyRobot friend3;
	private EnemyRobot friend4;
	
	private EnemyRobot enemy1;
	private EnemyRobot enemy2;
	private EnemyRobot enemy3;
	private EnemyRobot enemy4;
	
	@Before
	public void setUp() throws Exception {
		scan = new Scan();
		enemy1 = new EnemyRobot();
		enemy2 = new EnemyRobot();
		enemy3 = new EnemyRobot();
		enemy4 = new EnemyRobot();
		friend1 = new EnemyRobot();
		friend2 = new EnemyRobot();
		friend3 = new EnemyRobot();
		friend4 = new EnemyRobot();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnScannedFriendlyRobot() {
	}

	@Test
	public void testOnScannedEnemyRobot() {
	
	}

}
