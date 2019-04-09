package group09;
import robocode.*;

import robocode.util.Utils;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.Hashtable;

import info.EnemyRobot;
import info.Ninja;
import movement.Movement;
import group09.Calculations;



public class NinjaBot extends TeamRobot {
	
	static Hashtable enemies = new Hashtable();
	static EnemyRobot target;
	
	
	public void run() {
		
		
		Ninja ninja = new Ninja();
		Movement movement = new Movement();
		setColors(Color.black,Color.red,Color.white); // body,gun,radar
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
 
		setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
		
		
		Point2D.Double point= new Point2D.Double(getX(), getY());
		ninja.setNextDestination(point);
		ninja.setLastPosition(point);
		ninja.setPos(point);
		
		target = new EnemyRobot();
 
		while (true) {
 
			ninja.setPos(new Point2D.Double(getX(),getY()));
			ninja.setEnergy(getEnergy());
			// Tar max 9 ticks tills alla är skannade
			if(target.getAlive() && getTime()>9) {
				ninja.setDistanceToTarget(ninja.getPos().distance(target.getPosition()));
				shoot(ninja);
				move(ninja, movement);
			}
 
			execute();
 
		}
	}
	
	public void shoot(Ninja ninja) {
		// HeadOnTargeting 
		if(getGunTurnRemaining() == 0 && ninja.getEnergy() > 5) {
			setFire( Math.min(Math.min(ninja.getEnergy()/6d, 1300d/ninja.getDistanceToTarget()), target.getEnergy()/3d) );
		}
 
		setTurnGunRightRadians(Utils.normalRelativeAngle(Calculations.calcAngle(target.getPosition(),ninja.getPos()) - getGunHeadingRadians()));
 

	}
	
	public void move(Ninja ninja, Movement movement) {
		//Anti-grav
		double distanceToNextDestination = ninja.getPos().distance(ninja.getNextDestination());
		 
		//search a new destination if I reached this one
		if (distanceToNextDestination < 15) {
			// there should be better formulas then this one but it is basically here to increase OneOnOne performance. with more bots
			// addLast will mostly be 1
			
			movement.move(ninja, distanceToNextDestination);
			
 
		} else {
 
			double angle = Calculations.calcAngle(ninja.getNextDestination(), ninja.getPos()) - getHeadingRadians();
			double direction = 1;
 
			if(Math.cos(angle) < 0) {
				angle += Math.PI;
				direction = -1;
			}
 
			setAhead(distanceToNextDestination * direction);
			setTurnRightRadians(angle = Utils.normalRelativeAngle(angle));
			// hitting walls isn't a good idea, but NinjaBot still does it pretty often
			setMaxVelocity(Math.abs(angle) > 1 ? 0 : 8d);
 
		}

	}
	
 
//- scan event ------------------------------------------------------------------------------------------------------------------------------
	public void onScannedRobot(ScannedRobotEvent e, Ninja ninja)
	{
		EnemyRobot en = (EnemyRobot)enemies.get(e.getName());
 
		if(en == null){
			en = new EnemyRobot();
			enemies.put(e.getName(), en);
		}
 
		en.setEnergy((double) e.getEnergy());
		en.setAlive(true);
		en.setPosition(Calculations.calcPoint(ninja.getPos(), e.getDistance(), getHeadingRadians() + e.getBearingRadians())); 
 
		// normal target selection: the one closer to you is the most dangerous so attack him
		if(!target.getAlive() || e.getDistance() <ninja.getPos().distance(target.getPosition())) {
			target = en;
		}
 
		// locks the radar if there is only one opponent left
		if(getOthers()==1)	setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
	}
 
//- minor events ----------------------------------------------------------------------------------------------------------------------------
	public void onRobotDeath(RobotDeathEvent e) {
		((EnemyRobot)enemies.get(e.getName())).setAlive(false);
	}
 
//- math ------------------------------------------------------------------------------------------------------------------------------------
	
}




	
 

