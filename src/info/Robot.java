package info;

import java.awt.geom.Point2D;
import java.util.Hashtable;

import robocode.TeamRobot;

public class Robot {
	
	private static Hashtable enemies = new Hashtable();
	private static EnemyRobot target;
	private static Point2D.Double nextDestination;
	private static Point2D.Double lastPosition;
	private static Point2D.Double pos;
	private static double energy;
	private static double distanceToTarget;
	
	public static Hashtable getEnemies() {
		return enemies;
	}
	public static void setEnemies(Hashtable enemies) {
		Robot.enemies = enemies;
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
	public static void setEnergy(double energy) {
		Robot.energy = energy;
	}
	public static double getDistanceToTarget() {
		return distanceToTarget;
	}
	public static void setDistanceToTarget(double distanceToTarget) {
		Robot.distanceToTarget = distanceToTarget;
	}
	

}
