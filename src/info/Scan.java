package info;

import java.awt.Color;

import java.util.ArrayList;
import group09.Calculations;
import robocode.BulletMissedEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class Scan {

	/**
	 * Updates the information on a scanned friendly robot
	 * @param event, the scanned robot event , headingRadians, the headingRadians off the scanned robot. 
	 * @return the friendly robot friend. 
	 */
	
	public EnemyRobot onScannedFriendlyRobot(ScannedRobotEvent event, double headingRadians) {

		EnemyRobot friend = new EnemyRobot();
		int j = 0;
		
		if (!Environment.friends.isEmpty()) {
			for (int i = 0; i < Environment.friends.size(); i++) {
				if (Environment.friends.get(i).getName().equals(event.getName())) {
					friend = Environment.friends.get(i);
				} else {
					j++;
				}
			}
		}
		
		if (j == Environment.friends.size()) {
			Environment.friends.add(friend);
		}

		friend.setName(event.getName());
		friend.setEnergy((double) event.getEnergy());
		friend.setPosition(Calculations.calcPoint(Robot.getPos(), event.getDistance(),
				headingRadians + event.getBearingRadians()));
		friend.setDistance(event.getDistance());
		friend.setHeading(headingRadians);
		friend.setVelocity(event.getVelocity());
		

		return friend;
	}
	/**
	 * Updates the information on a scanned enemy robot and sets it as a target if NinjaBot doesnt have one. 
	 * @param event, the scanned robot event , headingRadians, the headingRadians off the scanned robot. 
	 * @return the enemy robot friend. 
	 */
	
	public EnemyRobot onScannedEnemyRobot(ScannedRobotEvent event, double headingRadians) {
	
		EnemyRobot enemy = new EnemyRobot();
		int j = 0;
		

		if (!Environment.enemies.isEmpty()) {
			for (int i = 0; i < Environment.enemies.size(); i++) {
				if (Environment.enemies.get(i).getName().equals(event.getName())) {
					enemy = Environment.enemies.get(i);
				} else {
					j++;
				}
			}
		}


		if (j == Environment.enemies.size()) {
			Environment.enemies.add(enemy);
		}

		enemy.setName(event.getName());
		enemy.setEnergy((double) event.getEnergy());
		enemy.setPosition(Calculations.calcPoint(Robot.getPos(), event.getDistance(),
				headingRadians + event.getBearingRadians()));
		enemy.setDistance(event.getDistance());
		enemy.setHeading(headingRadians);
		enemy.setVelocity(event.getVelocity());

		if (enemy.getEnergy() > 0) {
			enemy.setAlive(true);
		} else {
			enemy.setAlive(false);

		}

		if (Robot.hasTarget()) {
			if (!Robot.getTarget().getAlive() || 
					event.getDistance() < Robot.getPos().distance(Robot.getTarget().getPosition())) {
				Robot.setTarget(enemy);
			}
		} else {
			Robot.setTarget(enemy);
		}

		return enemy;
		
	
	}
}
