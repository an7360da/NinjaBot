
package tests;

import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import info.EnemyRobot;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.testing.RobotTestBed;

public class ST_F2_Organisational extends RobotTestBed {

//	REQ-F2-1: If the majority of the opponent team is made up of droids, the Ninjabot will target the leader robot.
//	REQ-F2-2: NinjaBot is able to communicate a weak target, with below x energy, to friendly robots within a certain distance y of the target. 
//	REQ-F2-3: If the own team has in total 50 hp or more advantage over the enemy teams total hp, NinjaBot will communicate “offense” to the friendly team. If the enemy team has the same advantage, NinjaBot will instead communicate “defense”.
//	REQ-F2-4: Ninjabot does not shoot in the direction of friendly robots.  MANUAL!

	/**
	 * Test class for Feature 2 - Organisational Abilities in NinjaBot.
	 *
	 * @author Louise Pham
	 *
	 */
	// constants used to configure this system test case
	private String ROBOT_UNDER_TEST = "group09.NinjaBot*";
	private String ENEMY_ROBOTS = "sample.SittingDuck";
	private int NBR_ROUNDS = 1;
	private EnemyRobot testBot1;
	private EnemyRobot testBot2;
	private EnemyRobot testBot3;
	private MockBot NinjaBot;
	
	

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
		return "(400,400,0), (300,400,0), (700,700,0), (50,50,0) ";
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
		testBot1= new EnemyRobot();
		testBot1.setEnergy(30);
		testBot2= new EnemyRobot();
		testBot2.setEnergy(60);
		testBot3= new EnemyRobot();
		testBot3.setEnergy(10);
	}

	/**
	 * Invoked after the test battle ends. Default behavior is to do nothing.
	 * Override this method in your test case to add behavior after the battle ends.
	 */
	@Override
	protected void runTeardown() {
		testBot1=null;
		testBot2=null;
		testBot3=null;
	}

	/**
	 * Called after the battle. Provided here to show that you could use this method
	 * as part of your testing.
	 * 
	 * @param event Holds information about the battle has been completed.
	 */
	@Override
	public void onBattleCompleted(BattleCompletedEvent event) {
		


		assertTrue("ST_F1_RadarSystem not implemented yet", false);
	}

	/**
	 * Called before each round. Provided here to show that you could use this
	 * method as part of your testing.
	 * 
	 * @param event The RoundStartedEvent.
	 */
	@Override
	public void onRoundStarted(RoundStartedEvent event) {
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
	}

}
