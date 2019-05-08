package tests;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.EnemyRobot;

public class EnemyRobotTest {
	private EnemyRobot testBot;

	@Before
	public void setUp() throws Exception {
		testBot = new EnemyRobot();
		testBot.setEnergy(200.0);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEnergy() {
		assertEquals("getEnergy failed", testBot.getEnergy(), 200.0, 0);
	}

	@Test
	public void testSetEnergy() {
		testBot.setEnergy(120.3);
		assertEquals("setEnergy failed", testBot.getEnergy(), 120,3);
	}

	@Test
	public void testGetHeading() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHeading() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVelocity() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetVelocity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDistance() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDistance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAlive() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAlive() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPosition() {
		fail("Not yet implemented");
	}

}
