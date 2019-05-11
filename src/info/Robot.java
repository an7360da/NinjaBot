package info;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Hashtable;

import robocode.TeamRobot;

public class Robot {

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
	
	public static ArrayList<EnemyRobot> getEnemies() {
		return Environment.enemies;
	}

	public static void setEnemies(ArrayList<EnemyRobot> enemies) {
		Environment.enemies = enemies;
	}

	public static EnemyRobot getTarget() {
		return target;
	}

	public static void setTarget(EnemyRobot target) {
		Robot.target = target;
	}

	public static Point2D.Double getNextDestination() {
		return nextDestination;
	}

	public static void setNextDestination(Point2D.Double nextDestination) {
		Robot.nextDestination = nextDestination;
	}

	public static Point2D.Double getLastPosition() {
		return lastPosition;
	}

	public static void setLastPosition(Point2D.Double lastPosition) {
		Robot.lastPosition = lastPosition;
	}

	public static Point2D.Double getPos() {
		return pos;
	}

	public static void setPos(Point2D.Double pos) {
		Robot.pos = pos;
	}

	public static double getEnergy() {
		return energy;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Robot.counter = counter;
	}

	public static void setEnergy(double energy) {
		Robot.energy = energy;
	}

	public static double getDistanceToTarget() {
		return distanceToTarget;
	}

	public static void setDistanceToTarget(double distanceToTarget) {
		Robot.distanceToTarget = distanceToTarget;
	}

	public static boolean hasTarget() {
		return target != null;
	}

	public static String getTeamMode() {
		return teamMode;
	}

	public static void setTeamMode(String teamMode) {
		Robot.teamMode = teamMode;
	}
	
	public static int getMissedBullets() {
		return missRate;
	}

	public static void bulletMissed() {
		missRate += 33;
	}
	
	public static void timePassed() {
		missRate -= 1;
	}
	
	public static int getBulletQuality() {
		return missRate;
	}

	public static boolean isAccurateEnoughToFire() {
		return accurateEnoughToFire;
	}

	public static void setAccurateEnoughToFire(boolean accurateEnoughToFire) {
		Robot.accurateEnoughToFire = accurateEnoughToFire;
	}
}
