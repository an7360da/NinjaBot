//package tests;
//
//import static org.junit.Assert.*;
//
//
//import java.awt.Color;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import group09.RobotColors;
//import robocode.control.events.TurnEndedEvent;
//import robocode.control.snapshot.IRobotSnapshot;
//
//
//public class RobotColorsTest {
//	private RobotColors testCol;
//	private MockBot mockBot;
//	private IRobotSnapshot snapCol;
//	
//
//	@Before
//	public void setUp() throws Exception {
//		testCol = new RobotColors();
//		mockBot = new MockBot("NinjaBot" ,200, 0, 300, 200);
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		testCol = null;
//		mockBot = null;
//	}
//
//	@Test
//	public void test() {
//		IRobotSnapshot snapCol = getTurnSnapshot().getRobots()[0];
//		testCol.bodyColor = Color.black;
//		testCol.gunColor = Color.cyan;
//		testCol.radarColor = Color.cyan;
//		testCol.scanColor = Color.black;
//		
//		
//		mockBot.setBodyColor(testCol.bodyColor);
//		mockBot.setGunColor(testCol.gunColor);
//		mockBot.setRadarColor(testCol.radarColor);
//		mockBot.setScanColor(testCol.scanColor);
//		
//		assertEquals("Setting the Bot's body colours failed",snapCol.getBodyColor(),testCol.bodyColor);
//		assertEquals("Setting the Bot colours failed",snapCol.getGunColor(),testCol.gunColor);
//		assertEquals("Setting the Bot colours failed",snapCol.getRadarColor(),testCol.radarColor);
//		assertEquals("Setting the Bot colours failed",snapCol.getScanColor(),testCol.scanColor);
//		
//	}
//
//}
