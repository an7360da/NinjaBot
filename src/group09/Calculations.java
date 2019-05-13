package group09;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Enumeration;

import info.EnemyRobot;
import info.Environment;
import info.Robot;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;

public class Calculations {
	
	/**
	 * Calculates a new point at the distance dist and at the angle ang from the current point p.
	 * @param  p, dist,  ang, the current position p, distance to the wanted point dist, and the angle ang to the wanted point
	 * @return a new point. 
	 */

	public static Point2D.Double calcPoint(Point2D.Double p, double dist, double ang) {
		return new Point2D.Double(p.x + dist * Math.sin(ang), p.y + dist * Math.cos(ang));
	}
	/**
	 * Calculates the angle between the current point p1 and the wanted point p2.
	 * @param  p1, p2, the current point p1 and the wanted point p2.
	 * @return an angle. 
	 */
	public static double calcAngle(Point2D.Double p2, Point2D.Double p1) {
		return Math.atan2(p2.x - p1.x, p2.y - p1.y);
	}
	/**
	 * Calculates a risk-value for a possible position based on enemies energy, distance and angle
	 * @param  p, a point to evaluate, addLast, a mathematical parameter to enhance 1vs1 preformance
	 * @return a risk-value for the point. 
	 */
	public static double evaluate(Point2D.Double p, double addLast) {
		double eval = addLast * 0.08 / p.distanceSq(Robot.getLastPosition());

		for (EnemyRobot en : Robot.getEnemies()) {
			if (en.getAlive()) {
				eval += Math.min(en.getEnergy() / Robot.getEnergy(), 2) * (1 + Math.abs(Math
						.cos(Calculations.calcAngle(Robot.getPos(), p) - Calculations.calcAngle(en.getPosition(), p))))
						/ p.distanceSq(en.getPosition());
			}
		}
		return eval;
	}
	/**
	 * Calculates a teams combined amount of energy.
	 * @param  restOfTeam, the team whos energy is being calculated 
	 * @return the teams total energy.  
	 */
	public static double teamsTotalEnergy(ArrayList<EnemyRobot> restOfTeam) {
		double totalEnergy = 0.0;
		
		if(restOfTeam.equals(Environment.friends)) {
		EnemyRobot us = new EnemyRobot();
		ArrayList<EnemyRobot> team = new ArrayList<>();
		team.addAll(restOfTeam);
		us.setDistance(0);
		us.setEnergy(Robot.getEnergy());
		us.setPosition(Robot.getPos());
		team.add(us);
		
		for (int i = 0; i < team.size(); i++) {
			totalEnergy += team.get(i).getEnergy();
		}
		
		}else {

			for (int i = 0; i < restOfTeam.size(); i++) {
				totalEnergy += restOfTeam.get(i).getEnergy();
		}
		}	
		
		

		return totalEnergy;
	}
	
	/**
	 * Calculates the difference in energy between the two teams.
	 * @return the String "offense" or "defense" depending on the energy-difference between the teams .  
	 */
	public static String calcTeamMode() {
		if ((Calculations.teamsTotalEnergy(Environment.friends))
				- (Calculations.teamsTotalEnergy(Environment.enemies)) > 50) {
			Robot.setTeamMode("offense");
			return "offense";
		} 
		else if ((Calculations.teamsTotalEnergy(Environment.enemies))
				- (Calculations.teamsTotalEnergy(Environment.friends)) > 50) {
			Robot.setTeamMode("defense");
			return "defense";
		} else {
			return Robot.getTeamMode();
		}
	}
	
	/**
	 * Calculates if the NinjaBot is in a good enough state to fire bullets.  
	 */
	public static void setAccurateEnoughToFire() {
		if (Robot.getBulletQuality() > 120) {
			Robot.setAccurateEnoughToFire(false);
		} else if (Robot.getBulletQuality() < 80) {
			Robot.setAccurateEnoughToFire(true);
		}
	}
	
	/**
	 * Finds the leader in the enemyteam
	 */
	public static void findLeader(){
		for(int j=0; j< Environment.enemies.size(); j++) {
				
				if(Environment.enemies.get(j).getEnergy()>150) {
					Robot.setEnemyLeader(Environment.enemies.get(j).getName());
				}
		}
	}
	
	/**
	 * Calculates how attractive an enemy is for a specific teammate.
	 * @param  enemy, teammate, enemy a scanned EnemyRobot from the opposite team, teammate a friendly robot.   
	 * @return a targetValue of how attractive the enemy is to target for that specific teammate.  
	 */
	public static double targetValue(EnemyRobot enemy, EnemyRobot teammate) {
		double targetValue;
		double distanceValue = 5;
		double energyValue = 5;
		
		distanceValue -= enemy.getPosition().distance(teammate.getPosition()) / 100;
		energyValue -= enemy.getEnergy() / 10;
		
		targetValue= distanceValue + energyValue;
		
		return targetValue;
	}
	
}
