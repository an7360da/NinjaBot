package tests;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

	@RunWith(Suite.class)
	@SuiteClasses({ CalculationsTest.class, CommunicationTest.class, DodgingTest.class, EnemyRobotTest.class, EnvironmentTest.class, MovementEventsTest.class, RobotTest.class, ScanTest.class })
	public class NinjaBotUnitTest {

	}