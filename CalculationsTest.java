package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import group09.Calculations;
import group09.RobotColors;
import info.EnemyRobot;
import info.Robot;

public class CalculationsTest {
	private Robot testBot;
	private MockBot mockBot;
	private Point2D.Double testPoint1;
	private Point2D.Double testPoint2;

	@Before
	public void setUp() throws Exception {
		testBot = new Robot();
		mockBot = new MockBot("NinjaBot" ,200, 0, 100, 200);
		testPoint1 = new Point2D.Double(0,0);
		testPoint2 = new Point2D.Double();
	
	}

	@After
	public void tearDown() throws Exception {
		testBot = null;
		mockBot = null;
		testPoint1 = null;
		testPoint2 = null;s
	}
 
	@Test
	public void testCalcPoint() {
		testPoint2 = Calculations.calcPoint(testPoint1, 100.00, Math.PI/2);
		assertEquals("Calculated point wrong",100,00, testPoint2.getX());
		
		testPoint2 = Calculations.calcPoint(testPoint1, 300.00, Math.PI/4);
		assertEquals("Calculated point wrong",212,13, testPoint2.getX());
		
		testPoint2 = Calculations.calcPoint(testPoint1, 100, 0);
		assertEquals("Calculated point wrong", 100,00, testPoint2.getY());
		
	}

	
	@Test
	public void testCalcAngle() {
		testPoint2 = new Point2D.Double(100, 100);
		double angle = Calculations.calcAngle(testPoint2, testPoint1);
		double expectedangle = Math.PI/4;
		assertEquals(expectedangle, angle, 0);
		
		
	}
//	public static double teamsTotalEnergy(ArrayList<EnemyRobot> team) {
//
//		double totalEnergy = 0.0;
//
//		for (int i = 0; i < team.size(); i++) {
//			totalEnergy += team.get(i).getEnergy();
//		}
//
//		return totalEnergy;
//	}
	@Test
	public void teamsTotalEnergy {
		mockBot.
		double eval = addLast*0.08/p.distanceSq(Robot.getLastPosition());
	}

}
