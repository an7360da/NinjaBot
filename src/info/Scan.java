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
		
		if (friend.getEnergy() > 0) {
			friend.setAlive(true);
		} else {
			friend.setAlive(false);

		}

		return friend;
	}
	
	public EnemyRobot onScannedEnemyRobot(ScannedRobotEvent event, double headingRadians) {

		EnemyRobot en = new EnemyRobot();
		int j = 0;

		if (!Environment.enemies.isEmpty()) {
			for (int i = 0; i < Environment.enemies.size(); i++) {
				if (Environment.enemies.get(i).getName().equals(event.getName())) {
					en = Environment.enemies.get(i);
				} else {
					j++;
				}
			}
		}

		if (j == Environment.enemies.size()) {
			Environment.enemies.add(en);
		}

		en.setName(event.getName());
		en.setEnergy((double) event.getEnergy());
		en.setPosition(Calculations.calcPoint(Robot.getPos(), event.getDistance(),
				headingRadians + event.getBearingRadians()));
		en.setDistance(event.getDistance());
		en.setHeading(headingRadians);
		en.setVelocity(event.getVelocity());
		
		if (en.getEnergy() > 0) {
			en.setAlive(true);
		} else {
			en.setAlive(false);

		}

		if (Robot.hasTarget()) {
			if (!Robot.getTarget().getAlive() || 
					event.getDistance() < Robot.getPos().distance(Robot.getTarget().getPosition())) {
				Robot.setTarget(en);
			}
		} else {
			Robot.setTarget(en);
		}

		return en;
	}


}
