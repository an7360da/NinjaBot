package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.Robot;
import movement.MovementEvents;


public class MovementEventsTest {
	Point2D.Double pos1;
	Point2D.Double pos2;
	
	
	MovementEvents move;
	@Before
	public void setUp() throws Exception {
		pos1 = new Point2D.Double(140, 25);
		pos2 = new Point2D.Double(300, 300);
		Robot.setPos(pos1);
		Robot.setNextDestination(pos2);
		move = new MovementEvents();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewDestination() {
		
		
	}


	@Test
	public void testCalculateAngle() {
		double wantedAng = -0.9730567728105703;
		double testHeading = 1.5;
//		Point2D.Double pos1 = new Point2D.Double(140, 25);
//		Point2D.Double pos2 = new Point2D.Double(300, 300);
//		Robot.setPos(pos1);
//		Robot.setNextDestination(pos2);
		double resultAng = move.calculateAngle(testHeading);
		assertEquals("calculateAngle failed", resultAng ,wantedAng, 0.001);
		
	}

	@Test
	public void testCalculateDirection() {
		double angle1 = -2;
		double angle2 = -123;
		double angle3 = 5;
		double result1 = move.calculateDirection(angle1);
		double result2 = move.calculateDirection(angle2);
		double result3 = move.calculateDirection(angle3);
		double wantedDir1 = -1;
		double wantedDir2 = -1;
		double wantedDir3 = 1;
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		assertEquals("calculateDirection failed", result1 ,wantedDir1, 0.001);
		assertEquals("calculateDirection failed", result2 ,wantedDir2, 0.001);
		assertEquals("calculateDirection failed", result3 ,wantedDir3, 0.001);
	
	}

}
