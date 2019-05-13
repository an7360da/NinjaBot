package info;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Hashtable;

import robocode.TeamRobot;
/**
 * A class Robot that describes the different the different characteristics of the NinjaBot
 */
public class Robot {
	/**
	 * The information needed about the NinjaBot: target, nextDestination, lastPosition, pos, energy, distanveToTarget, 
	 * teamMode, counter, missRate, accurateEnoughToFire, enemyLeader, tooClose, closeLocation for the NinjaBot
	 */
	private static EnemyRobot target;
	private static Point2D.Double nextDestination;
	private static Point2D.Double lastPosition;
	private static Point2D.Double pos;
	private static double energy;
	private static double distanceToTarget;
	private static String teamMode;
	private static int counter;
	private static int missRate;
	private static boolean accurateEnoughToFire;
	private static String enemyLeader;
	private static boolean tooClose;
	private static Point2D.Double closeLocation;

	/**
	 * Returns a close location to the NinjaBot
	 * @return closeLocation a close location to the NinjaBot
	 */
	public static Point2D.Double getCloseLocation() {
		return closeLocation;
	}
	/**
	 * Sets the close location of the NinjaBot to closeLocation
	 * @param closeLocation the close location to the NinjaBot
	 */
	public static void setCloseLocation(Point2D.Double closeLocation) {
		Robot.closeLocation = closeLocation;
	}
	/**
	 * Returns a boolean too close when the NinjaBot is too close to enemies
	 * @return tooClose the true or false condition of the NinjaBot being too close to enemies
	 */
	public static boolean isTooClose() {
		return tooClose;
	}
	/**
	 * Sets the boolean too close for the NinjaBot to tooClose
	 * @param tooClose the boolean tooClose of the NinjaBot
	 */
	public static void setTooClose(boolean tooClose) {
		Robot.tooClose = tooClose;
	}
	/**
	 * Returns a list of enemy robots of the NinjaBot
	 * @return enemies the enemies of the NinjaBot
	 */
	public static ArrayList<EnemyRobot> getEnemies() {
		return Environment.enemies;
	}
	/**
	 * Sets the list enemies of the NinjaBot to enemies
	 * @param enemies the enemies list of the NinjaBot
	 */
	public static void setEnemies(ArrayList<EnemyRobot> enemies) {
		Environment.enemies = enemies;
	}
	/**
	 * Returns the current target of the NinjaBot
	 * @return target the current target of the NinjaBot
	 */
	public static EnemyRobot getTarget() {
		return target;
	}
	/**
	 * Sets the target of the NinjaBot to target
	 * @param target the target of the NinjaBot
	 */
	public static void setTarget(EnemyRobot target) {
		Robot.target = target;
	}
	/**
	 * Returns the next destination of the NinjaBot
	 * @return nextDestination the next destination of the NinjaBot
	 */
	public static Point2D.Double getNextDestination() {
		return nextDestination;
	}
	/**
	 * Sets the next destination of the NinjaBot to nextDestination
	 * @param nextDestination the next destination of the NinjaBot
	 */
	public static void setNextDestination(Point2D.Double nextDestination) {
		Robot.nextDestination = nextDestination;
	}
	/**
	 * Returns the last position of the NinjaBot
	 * @return lastPosition the last position of the NinjaBot
	 */
	public static Point2D.Double getLastPosition() {
		return lastPosition;
	}
	/**
	 * Sets the last position of the NinjaBot to lastPosition
	 * @param lastPosition the last position of the NinjaBot
	 */
	public static void setLastPosition(Point2D.Double lastPosition) {
		Robot.lastPosition = lastPosition;
	}
	/**
	 * Returns the position of the NinjaBot
	 * @return pos the position of the NinjaBot
	 */
	public static Point2D.Double getPos() {
		return pos;
	}
	/**
	 * Sets the position of the NinjaBot to pos
	 * @param pos the position of the NinjaBot
	 */
	public static void setPos(Point2D.Double pos) {
		Robot.pos = pos;
	}
	/**
	 * Returns the energy of the NinjaBot
	 * @return energy the energy of the NinjaBot
	 */
	public static double getEnergy() {
		return energy;
	}
	/**
	 * Returns a counter
	 * @return counter A counter 
	 */
	public static int getCounter() {
		return counter;
	}
	/**
	 * Sets the counter to counter
	 * @param counter A counter
	 */
	public static void setCounter(int counter) {
		Robot.counter = counter;
	}
	/**
	 * Sets the energy of the NinjaBot to energy
	 * @param energy the energy of the NinjaBot
	 */
	public static void setEnergy(double energy) {
		Robot.energy = energy;
	}
	/**
	 * Returns the distance to target of the NinjaBot
	 * @return distanceToTarget the distance to target of the NinjaBot
	 */
	public static double getDistanceToTarget() {
		return distanceToTarget;
	}
	/**
	 * Sets the distance to target of the NinjaBot to distanceToTarget
	 * @param distanceToTarget the distance to target of the NinjaBot
	 */
	public static void setDistanceToTarget(double distanceToTarget) {
		Robot.distanceToTarget = distanceToTarget;
	}
	/**
	 * Returns a boolean if NinjaBot has a target of the NinjaBot
	 * @return target != null a boolean if NinjaBot has a target
	 */
	public static boolean hasTarget() {
		return target != null;
	}
	/**
	 * Returns the team mode of the NinjaBot
	 * @return teamMode the team mode of the NinjaBot
	 */
	public static String getTeamMode() {
		return teamMode;
	}
	/**
	 * Sets the team mode of the NinjaBot to teamMode
	 * @param teamMode the teamMode of the NinjaBot
	 */
	public static void setTeamMode(String teamMode) {
		Robot.teamMode = teamMode;
	}
	/**
	 * Returns the miss rate of the NinjaBot
	 * @return missRate the miss rate of the NinjaBot
	 */
	public static int getMissedBullets() {
		return missRate;
	}
	/**
	 * Sets the bullets missed of the NinjaBot to missRate+=30
	 * @param missrate +=30 the bullets missed of the NinjaBot
	 */
	public static void bulletMissed() {
		missRate += 30;
	}
	/**
	 * Sets the time passed to missRate-=1
	 * @param missRate-=1 the time passed
	 */
	public static void timePassed() {
		missRate -= 1;
	}
	/**
	 * Sets the reset bullet quality of the NinjaBot to missRate = 0
	 * @param missRate =0 the reset bullet quality of the NinjaBot
	 */
	public static void resetBulletQuality() {
		missRate = 0;
	}
	/**
	 * Returns the bullet quality of the NinjaBot
	 * @return missRate the bullet quality missRate of the NinjaBot
	 */
	public static int getBulletQuality() {
		return missRate;
	}
	/**
	 * Returns the accuracy enough to fire
	 * @return accurateEnoughToFire the accuracy enough to fire of the NinjaBot
	 */
	public static boolean isAccurateEnoughToFire() {
		return accurateEnoughToFire;
	}
	/**
	 * Sets the accuracy enough to fire of the NinjaBot to accurateEnoughToFire
	 * @param accurateEnoughToFire the accuracy enough to fire of the NinjaBot
	 */
	public static void setAccurateEnoughToFire(boolean accurateEnoughToFire) {
		Robot.accurateEnoughToFire = accurateEnoughToFire;
	}
	/**
	 * Returns the enemy leader of the opponent team
	 * @return enemyLeader the enemy leader of the opponent team
	 */
	public static String getEnemyLeader() {
		return enemyLeader;
	}
	/**
	 * Sets the enemy leader of the opponent team to enemyLeader
	 * @param enemyLeader the enemy leader of the opponent team
	 */
	public static void setEnemyLeader(String enemyLeader) {
		Robot.enemyLeader = enemyLeader;
	}
}
