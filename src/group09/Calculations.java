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

	public static Point2D.Double calcPoint(Point2D.Double p, double dist, double ang) {
		return new Point2D.Double(p.x + dist * Math.sin(ang), p.y + dist * Math.cos(ang));
	}

	public static double calcAngle(Point2D.Double p2, Point2D.Double p1) {
		return Math.atan2(p2.x - p1.x, p2.y - p1.y);
	}

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
	// denna metod beräknar det lag som skickas in som parameters totala energy
	public static double teamsTotalEnergy(ArrayList<EnemyRobot> team) {

		double totalEnergy = 0.0;

		for (int i = 0; i < team.size(); i++) {
			totalEnergy += team.get(i).getEnergy();
		}

		return totalEnergy;
	}
	
	//denna metod beräknar vilket teammode om/som ska broadcastas, offense eller defense som ger laget information
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
	
	
	public static String turnEnded(ArrayList<EnemyRobot> others, EnemyRobot prevTarget) {
		
		int nbrOfDroids =0;
		EnemyRobot leader = null;
		for (int i=0; i<= others.size(); i++) {
			
			String robotName = others.get(i).getName();
			
			for(int j=0; j< Environment.enemies.size(); j++) {
				
				if(Environment.enemies.get(j).getEnergy()>150) {
					leader=Environment.enemies.get(j);
				}
					if(robotName.equalsIgnoreCase(Environment.enemies.get(j).getName())) {
					nbrOfDroids++;
					}else {
						j++;
					}
			}
		}
		
		int amountOfDroids = nbrOfDroids/Environment.enemies.size();
		
		if(amountOfDroids>0.5 && leader != null) {
			return "targetEnemy;" + leader.getName();
		} else {
			return "targetEnemy;" + prevTarget.getName();
		}
		
		
	}
	
}
