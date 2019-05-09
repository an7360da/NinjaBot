package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.EnemyRobot;

public class MovementEventsTest {
	private MockBot testBot;
	private EnemyRobot testEnemy;
	

	@Before
	public void setUp() throws Exception {
		testBot = new MockBot("NinjaBot", 100 , 0, 200, 420);
		testEnemy = new EnemyRobot();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewDestination() {
		
	}

	@Test
	public void testCalculateAngle() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateDirection() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnHitWall() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnHitRobot() {
		fail("Not yet implemented");
	}

}
