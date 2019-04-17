package info;
import java.awt.Color;

import group09.Calculations;
import robocode.ScannedRobotEvent;

public class Scan {
	
	
	public EnemyRobot onScannedRobot(ScannedRobotEvent event, double headingRadians){
 		
 		
		EnemyRobot en = (EnemyRobot)Environment.enemies.get(event.getName());
		 
		if(en == null){
			en = new EnemyRobot();
			Environment.enemies.put(event.getName(), en);
		}
		
		en.setName(event.getName());
		en.setEnergy((double) event.getEnergy());
		en.setAlive(true);
		en.setPosition(Calculations.calcPoint(Robot.getPos(), event.getDistance(), headingRadians + event.getBearingRadians())); 
 
		// normal target selection: the one closer to you is the most dangerous so attack him
		if (Robot.hasTarget()) {
			if(!Robot.getTarget().getAlive() || event.getDistance() < Robot.getPos().distance(Robot.getTarget().getPosition())) {
				Robot.setTarget(en);
			}
		} else {
			Robot.setTarget(en);
		}
		
		return en;

		
	}
	

}
