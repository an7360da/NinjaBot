package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.EnemyRobot;

public class EnemyRobotTest {
	
	private EnemyRobot testBot;
	
	private Point2D.Double position;
	private Point2D.Double position2;


	@Before
	public void setUp() throws Exception {
		testBot = new EnemyRobot();
		testBot.setEnergy(200.0);
		testBot.setHeading(180);
		testBot.setVelocity(90);
		testBot.setDistance(100);
		testBot.setName("name1");
		testBot.setAlive(false);
		position = new Point2D.Double(100 ,100);
		position2 = new Point2D.Double(200 ,200);
		testBot.setPosition(position);
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetEnergy() {
		
		assertEquals("Failure", 200.0, testBot.getEnergy(), 0);
		
	}

	@Test
	public void testSetEnergy() {
		
		testBot.setEnergy(100);
		assertEquals("Failure", 100 ,testBot.getEnergy(), 0);
	}

	@Test
	public void testGetHeading() {
		assertEquals("Failure", 180, testBot.getHeading(), 0);
		
	}

	@Test
	public void testSetHeading() {
		testBot.setHeading(90);
		assertEquals("Failure", 90, testBot.getHeading(), 0);
	}

	@Test
	public void testGetVelocity() {
		assertEquals("Failure", 90, testBot.getVelocity(), 0);
	}

	@Test
	public void testSetVelocity() {
		testBot.setHeading(50);
		assertEquals("Failure", 50, testBot.getHeading(), 0);
		
	}

	@Test
	public void testGetDistance() {

		assertEquals("Failure", 100, testBot.getDistance(), 0);
	
	}

	@Test
	public void testSetDistance() {
		testBot.setDistance(50);
		assertEquals("Failure", 50, testBot.getDistance(), 0);
	}

	@Test
	public void testGetName() {
		assertEquals("Failure", "name1" , testBot.getName());
	}

	@Test
	public void testSetName() {
		testBot.setName("name2");
		assertEquals("Failure", "name2", testBot.getName());
	}

	@Test
	public void testGetAlive() {
		assertEquals("Failure", false, testBot.getAlive());
	}

	@Test
	public void testSetAlive() {
		testBot.setAlive(true);
		assertEquals("Failure", true , testBot.getAlive());
	}

	@Test
	public void testGetPosition() {
		assertEquals("Failure", 100, testBot.getPosition().getX(), 0);
		assertEquals("Failure", 100, testBot.getPosition().getY(), 0);
	}

	@Test
	public void testSetPosition() {
		testBot.setPosition(position2);
		assertEquals("Failure", 200, testBot.getPosition().getX(),0);
		assertEquals("Failure", 200, testBot.getPosition().getY(),0);
	}

}
