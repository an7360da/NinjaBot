package tests;

import static org.junit.Assert.*;


import java.awt.geom.Point2D;

import info.Robot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.EnemyRobot;
import info.Environment;

public class RobotTest {
	private Robot robot;
	private Point2D.Double position;
	private Point2D.Double position2;
	private EnemyRobot enemyRobot;
	private Environment environment;
	
	@Before
	public void setUp() {
		robot = new Robot();
		enemyRobot = new EnemyRobot();
		environment = new Environment();
		Robot.setAccurateEnoughToFire(false);
		position = new Point2D.Double(100 ,100);
		position2 = new Point2D.Double(200 ,200);
		Robot.setCloseLocation(position);
		Robot.setCounter(5);
		Robot.setDistanceToTarget(20);
		Robot.isTooClose();
		Robot.setEnergy(200);
		Robot.setEnemyLeader("Sven");
		Robot.setTeamMode("Ledsen");
		
	}

	@Test
	public void testGetCloseLocation() {
		assertEquals("getCloseLocation failed", position, Robot.getCloseLocation());
		
	}

	@Test
	public void testSetCloseLocation() {
		Robot.setCloseLocation(position2);
		assertEquals("setCloseLocation failed", position2, Robot.getCloseLocation());
	}

	@Test
	public void testIsTooClose() {
		assertEquals("isTooClose failed", false, Robot.isTooClose());
	}

	@Test
	public void testSetTooClose() {
		Robot.setTooClose(false);
		assertEquals("setTooClose failed", false, Robot.isTooClose());
	}

	@Test
	public void testGetEnemies() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEnemies() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTarget() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTarget() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextDestination() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNextDestination() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLastPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLastPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPos() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnergy() {
		assertEquals("getEnergy failed", 200, Robot.getEnergy(), 0);
	}

	@Test
	public void testGetCounter() {
		assertEquals("getCounter failed", 5, Robot.getCounter());
	}

	@Test
	public void testSetCounter() {
		Robot.setCounter(10);
		assertEquals("setCounter failed", 10, Robot.getCounter());
	}

	@Test
	public void testSetEnergy() {
		Robot.setEnergy(14);
		assertEquals("setEnergy failed", 14, Robot.getEnergy(), 0);
	}

	@Test
	public void testGetDistanceToTarget() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDistanceToTarget() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasTarget() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTeamMode() {
		assertEquals("getTeamMode failed", "Ledsen", Robot.getTeamMode());
	}

	@Test
	public void testSetTeamMode() {
		Robot.setTeamMode("Arg");
		assertEquals("getEnemyLeader failed", "Arg", Robot.getTeamMode());
	}

	@Test
	public void testGetMissedBullets() {
		fail("Not yet implemented");
	}

	@Test
	public void testBulletMissed() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimePassed() {
		fail("Not yet implemented");
	}

	@Test
	public void testResetBulletQuality() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBulletQuality() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAccurateEnoughToFire() {
		assertEquals("isAccurateEnoughToFire failed", false, Robot.isAccurateEnoughToFire());
	}

	@Test
	public void testSetAccurateEnoughToFire() {
		Robot.setAccurateEnoughToFire(true);
		assertEquals("isAccurateEnoughToFire failed", true, Robot.isAccurateEnoughToFire());
	}

	@Test
	public void testGetEnemyLeader() {
		assertEquals("getEnemyLeader failed", "Sven", Robot.getEnemyLeader());
	}

	@Test
	public void testSetEnemyLeader() {
		Robot.setEnemyLeader("Carl");
		assertEquals("setEnemyLeader failed", "Carl", Robot.getEnemyLeader());
	}

}
