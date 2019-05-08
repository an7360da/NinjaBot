package info;
import java.awt.Color;
import java.util.ArrayList;

import group09.Calculations;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class Scan {
	
	
	public EnemyRobot onScannedRobot(ScannedRobotEvent event, double headingRadians){
 		
		
		EnemyRobot en= new EnemyRobot();
		int j=0;
 		
		
		if(!Environment.enemies.isEmpty()) {
		for (int i=0; i < Environment.enemies.size(); i++) {
			
			if(Environment.enemies.get(i).getName().equals(event.getName())) {
				
				en=Environment.enemies.get(i);
			 
			} else {
				j++;
			}
			
		}
		}
		
		if(j==Environment.enemies.size()) {
			Environment.enemies.add(en);
		}
		
		en.setName(event.getName());
		en.setEnergy((double) event.getEnergy());
		en.setPosition(Calculations.calcPoint(Robot.getPos(), event.getDistance(), headingRadians + event.getBearingRadians()));
		en.setDistance(event.getDistance());
		en.setHeading(headingRadians);
		en.setVelocity(event.getVelocity());
		if(en.getEnergy()>0) {
			en.setAlive(true);
		} else{
			en.setAlive(false);
			
		}
		
		
	
//		if(en == null){
//			en = new EnemyRobot();
//			Environment.enemies.put(event.getName(), en);
//		}
		
		// här låg set-grejerna
 
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
	
	
	public void onRobotDeath(RobotDeathEvent e) {
		((EnemyRobot)Robot.getEnemies().get(e.getName())).setAlive(false);
	}
}
