package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import group09.RobotColors;
import info.Robot;
import group09.NinjaBot;

public class RobotColorsTest {
	private Robot testBot;
	private RobotColors testCol;
	private MockBot mockBot;
	

	@Before
	public void setUp() throws Exception {
		testBot = new Robot();
		testCol = new RobotColors();
		mockBot = new MockBot("NinjaBot" ,200, 0, 300, 200);
	}

	@After
	public void tearDown() throws Exception {
		testBot = null;
	}

	@Test
	public void test() {
		testCol.bodyColor = Color.black;
		testCol.bulletColor = Color.PINK;
		testCol.gunColor = Color.cyan;
		testCol.radarColor = Color.cyan;
		testCol.scanColor = Color.black;
		
		mockBot.setBodyColor(testCol.bodyColor);
		mockBot.setGunColor(testCol.gunColor);
		mockBot.setRadarColor(testCol.radarColor);
		mockBot.setScanColor(testCol.scanColor);
		mockBot.setBulletColor(testCol.bulletColor);
		
		assertEquals("Were not the same colours",mockBot,testCol.bodyColor);
		
		
	}

}
