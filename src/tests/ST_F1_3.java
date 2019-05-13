package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import robocode.*;
import robocode.util.Utils;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.LinkedList;

import group09.Calculations;
import info.Robot;
import robocode.BattleResults;
import robocode.BulletMissedEvent;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.testing.RobotTestBed;
import robocode.control.snapshot.IRobotSnapshot;
import robocode.control.snapshot.RobotState;

@RunWith(JUnit4.class)
public class ST_F1_3 extends RobotTestBed {

//
//	REQ-F1-1: If NinjaBot misses more than 60% of the shots it fires within 10 seconds, it will hold fire.
//	WALLS DONE!!  REQ-F1-2: NinjaBot will stay at least 15 units away from walls and robots.               
//	REQ-F1-3: Analyses nearby locations and identifies the least risky direction to move in. 

	/**
	 * Test class for Feature 1 - Lean Survival in NinjaBot.
	 *
	 * @author Louise Pham
	 *
	 */
	// constants used to configure this system test case
	private String ROBOT_UNDER_TEST = "group09.NinjaBot*";
	private String ENEMY_ROBOTS = "sample.SittingDuck"; //Two enemies
	private int NBR_ROUNDS = 100;
	private boolean offWall;
	private int wallHits;
	private boolean hitRobot;
	private int robotHits;
	private double THRESHOLD = 0.51;
	private MockBot mockBot;
	private double SHOTSREQ = 0.60;
	private int counter;
	
	
	private double startDistance;
	private double avgDistance;
	private LinkedList<Double> allDistances;
	private LinkedList<Point2D> prevPos;
	private int nbrPassed;
	private double IMMOBILE_TURNS_LIMIT = 200;
	private boolean PRINT_DEBUG = false;


	/**
	 * The names of the robots that want battling is specified.
	 * 
	 * @return The names of the robots we want battling.
	 */
	@Override
	public String getRobotNames() {
		return ROBOT_UNDER_TEST + "," + ENEMY_ROBOTS;
	}

	/**
	 * Pick the amount of rounds that we want our robots to battle for.
	 *
	 * @return Amount of rounds we want to battle for.
	 */
	@Override
	public int getNumRounds() {
		return NBR_ROUNDS;
	}

	/**
	 * Returns a comma or space separated list like: x1,y1,heading1, x2,y2,heading2,
	 * which are the coordinates and heading of robot #1 and #2. So "(0,0,180),
	 * (50,80,270)" means that robot #1 has position (0,0) and heading 180, and
	 * robot #2 has position (50,80) and heading 270.
	 * 
	 * Override this method to explicitly specify the initial positions for your
	 * test cases.
	 * 
	 * Defaults to null, which means that the initial positions are determined
	 * randomly. Since battles are deterministic by default, the initial positions
	 * are randomly chosen but will always be the same each time you run the test
	 * case.
	 * 
	 * @return The list of initial positions.
	 */
	@Override
	public String getInitialPositions() {
		return null;
	}

	/**
	 * Returns true if the battle should be deterministic and thus robots will
	 * always start in the same position each time.
	 * 
	 * Override to return false to support random initialization.
	 * 
	 * @return True if the battle will be deterministic.
	 */
	@Override
	public boolean isDeterministic() {
		return false;
	}

	/**
	 * Specifies how many errors you expect this battle to generate. Defaults to 0.
	 * Override this method to change the number of expected errors.
	 * 
	 * @return The expected number of errors.
	 */
	@Override
	protected int getExpectedErrors() {
		return 0;
	}

	/**
	 * Invoked before the test battle begins. Default behavior is to do nothing.
	 * Override this method in your test case to add behavior before the battle
	 * starts.
	 */
	@Override
	protected void runSetup() {
		nbrPassed = 0;
		prevPos = new LinkedList<Point2D>();
	}

	/**
	 * Invoked after the test battle ends. Default behavior is to do nothing.
	 * Override this method in your test case to add behavior after the battle ends.
	 */
	@Override
	protected void runTeardown() {
	}

	/**
	 * Called after the battle. Provided here to show that you could use this method
	 * as part of your testing.
	 * 
	 * @param event Holds information about the battle has been completed.
	 */
	@Override
	public void onBattleCompleted(BattleCompletedEvent event) {
		assertTrue("Average distance should be larger than start distance in " + THRESHOLD + 
				" of the rounds, but it was true in only " + ((double) nbrPassed / NBR_ROUNDS) +
				" rounds.", ((double) nbrPassed / NBR_ROUNDS) > THRESHOLD);
	}

	/**
	 * Called before each round. Provided here to show that you could use this
	 * method as part of your testing.
	 * 
	 * @param event The RoundStartedEvent.
	 */
	@Override
	public void onRoundStarted(RoundStartedEvent event) {
		IRobotSnapshot ninjaBot = event.getStartSnapshot().getRobots()[0];
		double xNB = ninjaBot.getX();
		double yNB = ninjaBot.getY();
		IRobotSnapshot enemy1 = event.getStartSnapshot().getRobots()[1];
		double xEnemy1 = enemy1.getX();
		double yEnemy1 = enemy1.getY();
		
		startDistance = Math.hypot(xNB-xEnemy1, yNB-yEnemy1);
		avgDistance = 0;
		allDistances = new LinkedList<Double>();
	
	}

	/**
	 * Called after each round. Provided here to show that you could use this method
	 * as part of your testing.
	 * 
	 * @param event The RoundEndedEvent.
	 */
	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		// calculate average distance across all turns during the battle
				double totalDistances = 0;
				for (double i: allDistances) {
					totalDistances += i;
				}		
				avgDistance = totalDistances / allDistances.size();
						
				if (PRINT_DEBUG) {
					System.out.println("Start distance: " + startDistance +
								   	   " Average distance: " + avgDistance);
				}
						
				if (avgDistance > startDistance) {
					nbrPassed++;
				}
	}

	/**
	 * Called after each turn. Provided here to show that you could use this method
	 * as part of your testing.
	 * 
	 * @param event The TurnEndedEvent.
	 */
	@Override
	public void onTurnEnded(TurnEndedEvent event) {

		IRobotSnapshot ninjaBot = event.getTurnSnapshot().getRobots()[0];
		double xNB = ninjaBot.getX();
		double yNB = ninjaBot.getY();
		IRobotSnapshot enemy1 = event.getTurnSnapshot().getRobots()[1];
		double xEnemy1 = enemy1.getX();
		double yEnemy1 = enemy1.getY();

		
		double distance = Math.hypot(xNB-xEnemy1, yNB-yEnemy1);
		allDistances.add(distance);
		
		// verify unique positions from turn IMMOBILE_TURNS_LIMIT as long as SittingDuck is active
		if (event.getTurnSnapshot().getTurn() >= IMMOBILE_TURNS_LIMIT && enemy1.getState() == RobotState.ACTIVE) {
			boolean uniquePos = false;
			int counter = 0;
			for (int i = 0; !uniquePos && i < prevPos.size(); i++) {
				for (int j = i+1; !uniquePos && j < prevPos.size(); j++) {
					if (!prevPos.get(i).equals(prevPos.get(j))) {
						uniquePos = true;
					}
					counter++;
				}
			}
			assertTrue("NinjaBot did not move for " + IMMOBILE_TURNS_LIMIT + " turns (turn " + event.getTurnSnapshot().getTurn() + ")", uniquePos);
		}
		
		// store last IMMOBILE_TURNS_LIMIT positions
		if (prevPos.size() == IMMOBILE_TURNS_LIMIT) {
			prevPos.poll();
		}
		prevPos.add(new Point2D.Double(xNB, yNB));	
	}


}

