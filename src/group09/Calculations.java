package group09;

import java.awt.geom.Point2D;
import java.util.Enumeration;

import info.EnemyRobot;
import info.Ninja;
import robocode.RobotDeathEvent;

public class Calculations {
	
	public static Point2D.Double calcPoint(Point2D.Double p, double dist, double ang) {
	return new Point2D.Double(p.x + dist*Math.sin(ang), p.y + dist*Math.cos(ang));
	}
	
	
	
	
	public static double calcAngle(Point2D.Double p2,Point2D.Double p1){
		return Math.atan2(p2.x - p1.x, p2.y - p1.y);

					
	}
	
	
	
	
	public void onRobotDeath(RobotDeathEvent e) {
		((EnemyRobot)NinjaBot.enemies.get(e.getName())).setAlive(false);
	}
	
	
	
	
	public static double evaluate(Point2D.Double p, double addLast, Ninja ninja) {
		// this is basically here that the bot uses more space on the battlefield. In melee it is dangerous to stay somewhere too long.
		double eval = addLast*0.08/p.distanceSq(ninja.getLastPosition());
 
		Enumeration _enum = NinjaBot.enemies.elements();
		while (_enum.hasMoreElements()) {
			EnemyRobot en = (EnemyRobot)_enum.nextElement();
			// this is the heart of HawkOnFire. So I try to explain what I wanted to do:
			// -	Math.min(en.energy/myEnergy,2) is multiplied because en.energy/myEnergy is an indicator how dangerous an enemy is
			// -	Math.abs(Math.cos(calcAngle(myPos, p) - calcAngle(en.pos, p))) is bigger if the moving direction isn't good in relation
			//		to a certain bot. it would be more natural to use Math.abs(Math.cos(calcAngle(p, myPos) - calcAngle(en.pos, myPos)))
			//		but this wasn't going to give me good results
			// -	1 / p.distanceSq(en.pos) is just the normal anti gravity thing
			if(en.getAlive()) {
				eval += Math.min(en.getEnergy()/ninja.getEnergy(),2) * 
						(1 + Math.abs(Math.cos(Calculations.calcAngle(ninja.getPos(), p) - Calculations.calcAngle(en.getPosition(), p)))) / p.distanceSq(en.getPosition());
			}
		}
		return eval;
	}

}
