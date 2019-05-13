package tests;

import static org.junit.Assert.*;



import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import group09.Calculations;
import sampleteam.RobotColors;
import info.EnemyRobot;
import info.Environment;
import info.Robot;

public class CalculationsTest {
	private EnemyRobot testBot1;
	private EnemyRobot testBot2;
	private EnemyRobot testBot3;
	private Point2D.Double testPoint1;
	private Point2D.Double testPoint2;
	private ArrayList<EnemyRobot> team;
	private EnemyRobot e1;
	private EnemyRobot e2;
	private EnemyRobot e3;
	
	@Before
	public void setUp() throws Exception {
		testBot1 = new EnemyRobot();
		testBot2 = new EnemyRobot();
		testBot3 = new EnemyRobot();
		testPoint1 = new Point2D.Double(0, 0);
		testPoint2 = new Point2D.Double();
		team = new ArrayList<EnemyRobot>();
		e1 = new EnemyRobot();
		e2 = new EnemyRobot();
		e3 = new EnemyRobot();

	}

	@After
	public void tearDown() throws Exception {
		testBot3 = null;
		testPoint1 = null;
		testPoint2 = null;
	}

	@Test
	public void testCalcPoint() {
		testPoint2 = Calculations.calcPoint(testPoint1, 100.00, Math.PI / 2);
		assertEquals("Calculated point wrong", 100, 00, testPoint2.getX());

		testPoint2 = Calculations.calcPoint(testPoint1, 300.00, Math.PI / 4);
		assertEquals("calcPoint failed", 212, 13, testPoint2.getX());

		testPoint2 = Calculations.calcPoint(testPoint1, 100, 0);
		assertEquals("calcPoint calculated wrong", 100, 00, testPoint2.getY());

	}

	@Test
	public void testCalcAngle() {
		testPoint2 = new Point2D.Double(100, 100);
		double angle = Calculations.calcAngle(testPoint2, testPoint1);
		double expectedangle = Math.PI / 4;
		assertEquals(expectedangle, angle, 0);

	}

	@Test
	public void testteamsTotalEnergy() {
		assertEquals(0, Calculations.teamsTotalEnergy(team), 0);
	
		e1.setEnergy(200);
		e2.setEnergy(300);
		e3.setEnergy(50);
		team.add(e1);
		team.add(e2);
		team.add(e3);

		assertEquals(550, Calculations.teamsTotalEnergy(team), 0);
	}

	@Test
	public void testcalcTeamMode() {
		
		e1.setEnergy(200);
		e2.setEnergy(300);
		e3.setEnergy(70);
		Environment.enemies.add(e1);
		Environment.enemies.add(e2);
		Environment.enemies.add(e3);
		testBot1.setEnergy(200);
		testBot2.setEnergy(300);
		testBot3.setEnergy(10);
		Environment.friends.add(testBot1);
		Environment.friends.add(testBot2);
		Environment.friends.add(testBot3);
		
		assertEquals("offense", Calculations.calcTeamMode());
		
		e3.setEnergy(70);
		assertEquals("offense", Calculations.calcTeamMode());
		
		e2.setEnergy(1);
		Environment.enemies.add(e2);
		assertEquals("offense", Calculations.calcTeamMode());
		
		
	}

	

}