package info;

import java.awt.Color;
import java.util.ArrayList;
import group09.Calculations;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class Scan {

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
	
	public EnemyRobot onScannedEnemyRobot(ScannedRobotEvent event, double headingRadians) {
	
		EnemyRobot enemey = new EnemyRobot();
		int j = 0;

		if (!Environment.enemies.isEmpty()) {
			for (int i = 0; i < Environment.enemies.size(); i++) {
				if (Environment.enemies.get(i).getName().equals(event.getName())) {
					enemey = Environment.enemies.get(i);
				} else {
					j++;
				}
			}
		}


		if (j == Environment.enemies.size()) {
			Environment.enemies.add(enemey);
		}

		enemey.setName(event.getName());
		enemey.setEnergy((double) event.getEnergy());
		enemey.setPosition(Calculations.calcPoint(Robot.getPos(), event.getDistance(),
				headingRadians + event.getBearingRadians()));
		enemey.setDistance(event.getDistance());
		enemey.setHeading(headingRadians);
		enemey.setVelocity(event.getVelocity());

		if (enemey.getEnergy() > 0) {
			enemey.setAlive(true);
		} else {
			enemey.setAlive(false);

		}

		if (Robot.hasTarget()) {
			if (!Robot.getTarget().getAlive() || 
					event.getDistance() < Robot.getPos().distance(Robot.getTarget().getPosition())) {
				Robot.setTarget(enemey);
			}
		} else {
			Robot.setTarget(enemey);
		}

		return enemey;
	}
}
