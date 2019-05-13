package movement;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import group09.Calculations;
import info.Robot;
import robocode.HitWallEvent;
import robocode.util.Utils;

public class MovementEvents {
	
	/**
	 * Updates the information on a scanned frindly robot
	 * @param event, the scanned robot event , headingRadians, the headingRadians off the scanned robo. 
	 * @return the friendly robot friend. 
	 */
	
	
	double distanceToNextDestination = Robot.getPos().distance(Robot.getNextDestination());

	public void newDestination(int others, double battleFieldWidth, double battleFieldHeight) {

		// there should be better formulas then this one but it is basically here to
		// increase OneOnOne performance. with more bots
		// � will mostly be 1
		double addLast = 1 - Math.rint(Math.pow(Math.random(), others));

		Rectangle2D.Double battleField = new Rectangle2D.Double(30, 30, battleFieldWidth - 60, battleFieldHeight - 60);
		Point2D.Double testPoint;

		for (int i = 0; i < 200; i++) {
			// calculate the testPoint somewhere around the current position. 100 +
			// 200*Math.random() proved to be good if there are
			// around 10 bots in a 1000x1000 field. but this needs to be limited this to
			// distanceToTarget*0.8. this way the bot wont
			// run into the target (should mostly be the closest bot)
			testPoint = Calculations.calcPoint(Robot.getPos(),
					Math.min(Robot.getDistanceToTarget() * 0.8, 100 + 200 * Math.random()),
					2 * Math.PI * Math.random());

			if (battleField.contains(testPoint) && Calculations.evaluate(testPoint, addLast) < Calculations
					.evaluate(Robot.getNextDestination(), addLast)) {
				Robot.setNextDestination(testPoint);
			}
		}

		Robot.setLastPosition(Robot.getPos());

	}
	/**
	 * Updates the information on a scanned frindly robot
	 * @param event, the scanned robot event , headingRadians, the headingRadians off the scanned robo. 
	 * @return the friendly robot friend. 
	 */
	
	public double calculateAngle(double headingRadians) {
		return Calculations.calcAngle(Robot.getNextDestination(), Robot.getPos()) - headingRadians;
	}

	/**
	 * Calculates the direction to move in. 
	 * @param angle 
	 * @return the direction NinjaBot should move in 
	 */
	
	public double calculateDirection(double angle) {
		double direction = 1;

		if (Math.cos(angle) < 0) {
			//angle += Math.PI;
			direction = -1;
		}
		
		return direction;
	}

}
