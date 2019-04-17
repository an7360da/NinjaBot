package info;
import group09.Calculations;
import robocode.ScannedRobotEvent;

public class Scan {
	
	
	public void onScannedRobot(ScannedRobotEvent e, double headingRadians){
		
		EnemyRobot en = (EnemyRobot)Environment.enemies.get(e.getName());
		 
		if(en == null){
			en = new EnemyRobot();
			Environment.enemies.put(e.getName(), en);
		}
		
		en.setName(e.getName());
		en.setEnergy((double) e.getEnergy());
		en.setAlive(true);
		en.setPosition(Calculations.calcPoint(Robot.getPos(), e.getDistance(), headingRadians + e.getBearingRadians())); 
 
		// normal target selection: the one closer to you is the most dangerous so attack him
		if(!Robot.getTarget().getAlive() || e.getDistance() < Robot.getPos().distance(Robot.getTarget().getPosition())) {
			Robot.setTarget(en);
			
		}
 
		// locks the radar if there is only one opponent left

		
	}
	

}
