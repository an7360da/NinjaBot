package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.testing.RobotTestBed;
import robocode.control.snapshot.IRobotSnapshot;

@RunWith(JUnit4.class)
public class ST_F1_Survival extends RobotTestBed {

//
//	REQ-F1-1: If NinjaBot misses more than 60% of the shots it fires within 10 seconds, it will hold fire.
//	WALLS DONE!!  REQ-F1-2: NinjaBot will stay at least 15 units away from walls and robots.               
//	REQ-F1-3: Ninjabot does not shoot in the direction of friendly robots. 
//	REQ-F1-4: Analyses nearby locations and identifies the least risky direction to move in. 

	/**
	 * Test class for Feature 1 - Lean Survival in NinjaBot.
	 *
	 * @author Louise Pham
	 *
	 */
	// constants used to configure this system test case
	private String ROBOT_UNDER_TEST = "group09.NinjaBot*";
	private String ENEMY_ROBOTS = "sample.RamFire,sample.RamFire";
	private int NBR_ROUNDS = 100;
	private boolean offWall;
	private int wallHits;
	private boolean hitRobot;
	private int robotHits;
	private double THRESHOLD = 0.85;	
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
		return true;
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
		System.out.println("Robot hit wall " + wallHits + " times");
		assertTrue("Robot hit the wall", offWall);
		System.out.println("Robot hit another robot " + robotHits + " times");
		assertFalse("Robot hit another Robot", hitRobot);
		// all battle results
		BattleResults[] battleResults = event.getIndexedResults();
		// BMB results
		BattleResults bmbResults = battleResults[0];
		// check that the required win rate has been reached
		double bmbWinRate = (((double) bmbResults.getFirsts()) / NBR_ROUNDS);
		
	
			System.out.println("BMB won " + bmbResults.getFirsts() + " out of " + NBR_ROUNDS + 
					" rounds (win rate = " + bmbWinRate + ")");
		
			
		
		assertTrue("Basic Melee Bot should have a win rate of at least 90% in this melee battle", bmbWinRate >= THRESHOLD);
		
	}

	/**
	 * Called before each round. Provided here to show that you could use this
	 * method as part of your testing.
	 * 
	 * @param event The RoundStartedEvent.
	 */
	@Override
	public void onRoundStarted(RoundStartedEvent event) {
		offWall = true;
		hitRobot = false;
	}

	/**
	 * Called after each round. Provided here to show that you could use this method
	 * as part of your testing.
	 * 
	 * @param event The RoundEndedEvent.
	 */
	@Override
	public void onRoundEnded(RoundEndedEvent event) {
	}

	/**
	 * Called after each turn. Provided here to show that you could use this method
	 * as part of your testing.
	 * 
	 * @param event The TurnEndedEvent.
	 */
	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		IRobotSnapshot bmb = event.getTurnSnapshot().getRobots()[0];
		double xBMB = bmb.getX();
		double yBMB = bmb.getY();
		IRobotSnapshot enemy1 = event.getTurnSnapshot().getRobots()[1];
		double xEnemy1 = enemy1.getX();
		double yEnemy1 = enemy1.getY();
		IRobotSnapshot enemy2 = event.getTurnSnapshot().getRobots()[1];
		double xEnemy2 = enemy2.getX();
		double yEnemy2 = enemy2.getY();
		
		
		//checks if robot hits wall or not after each turn
		if(xBMB < 15 || xBMB > (800-15) || yBMB < 15 || yBMB > (600-15)) {
			offWall = false;
			wallHits++;
		}
		
		//checks whether robot hits other robots or not after each turn
		if(xBMB > xEnemy1) { 
			if(xBMB - xEnemy1 < 15 || xEnemy1 - xBMB > -15 ) {
				if(yBMB > yEnemy1) {
					if(yBMB - yEnemy1 < 15 || yEnemy1 - yBMB > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				} else if(yBMB < yEnemy1) {
					if(yEnemy1 - yBMB < 15 || yBMB - yEnemy1 > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				}
			}
		}
		
		if(xBMB < xEnemy1) { 
			if(xEnemy1 - xBMB < 15 || xBMB - xEnemy1 > -15 ) {
				if(yBMB > yEnemy1) {
					if(yBMB - yEnemy1 < 15 || yEnemy1 - yBMB > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				} else if(yBMB < yEnemy1) {
					if(yEnemy1 - yBMB < 15 || yBMB - yEnemy1 > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				}
			}
		}
		
		if(xBMB > xEnemy2) { 
			if(xBMB - xEnemy2 < 15 || xEnemy2 - xBMB > -15 ) {
				if(yBMB > yEnemy2) {
					if(yBMB - yEnemy2 < 15 || yEnemy2 - yBMB > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				} else if(yBMB < yEnemy2) {
					if(yEnemy2 - yBMB < 15 || yBMB - yEnemy2 > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				}
			}
		}
		
		if(xBMB < xEnemy2) { 
			if(xEnemy2 - xBMB < 15 || xBMB - xEnemy2 > -15 ) {
				if(yBMB > yEnemy2) {
					if(yBMB - yEnemy2 < 15 || yEnemy2 - yBMB > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				} else if(yBMB < yEnemy2) {
					if(yEnemy2 - yBMB < 15 || yBMB - yEnemy2 > -15 ) {
						hitRobot = true;
						robotHits++;
					}
				}
			}
		}
		
		
		
	
	}

}
