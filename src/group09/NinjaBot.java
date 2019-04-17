package group09;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import info.EnemyRobot;
import info.Robot;
import info.Scan;



public class NinjaBot extends TeamRobot {
	
//	static Hashtable enemies = new Hashtable();
//	static EnemyRobot target;
//	static Point2D.Double nextDestination;
//	static Point2D.Double lastPosition;
//	static Point2D.Double myPos;
//	static double myEnergy;
//	private double distanceToTarget;
	
	private static EnemyRobot target;
	
	public void broadcastMessage(Serializable message) {
		
	}
	
	public void run() {
		
		RobotColors c = new RobotColors();
		
		c.bodyColor = Color.black;
		c.gunColor = Color.white;
		c.radarColor = Color.orange;
		c.scanColor = Color.black;
		c.bulletColor = Color.red;
				
		setBodyColor(c.bodyColor);
		setGunColor(c.gunColor);
		setRadarColor(c.radarColor);
		setScanColor(c.scanColor);
		setBulletColor(c.bulletColor);
		
		// Send RobotColors object to our entire team
		broadcastMessage(c);

		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		
 
		setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
 
		Point2D.Double p = new Point2D.Double(getX(), getY());
		Robot.setNextDestination(p);
		Robot.setPos(p);
		Robot.setLastPosition(p);
		
		// = lastPosition = myPos = new Point2D.Double(getX(), getY());
		target = new EnemyRobot();
		target.setAlive(true);
	
		
 
		while (true) {
			
			
 
			Robot.setPos(new Point2D.Double(getX(),getY()));
			Robot.setEnergy(getEnergy());
			// Tar max 9 ticks tills alla är skannade
			
			System.out.println(target.getAlive());
			System.out.println(getTime());
			System.out.println(isTeammate(target.getName()));
			
			
			if(target.getAlive() && getTime()>=0) {
				Robot.setDistanceToTarget(Robot.getPos().distance(target.getPosition()));
				if(!isTeammate(target.getName())){
					shoot();	
				}
				move();
			}
 
			execute();
			
 
		}
	}
	
	public void shoot() {
		// HeadOnTargeting 
		if(getGunTurnRemaining() == 0 && Robot.getEnergy() > 5 && !isTeammate(target.getName())) {
			
			setFire( Math.min(Math.min(Robot.getEnergy()/6d, 1300d/Robot.getDistanceToTarget()), target.getEnergy()/3d) );
		}
 
		setTurnGunRightRadians(Utils.normalRelativeAngle(Calculations.calcAngle(target.getPosition(), Robot.getPos()) - getGunHeadingRadians()));
 

	}
	
	public void move() {
		//Anti-grav
		double distanceToNextDestination = Robot.getPos().distance(Robot.getNextDestination());
		 
		//search a new destination if I reached this one
		if (distanceToNextDestination < 15) {
			// there should be better formulas then this one but it is basically here to increase OneOnOne performance. with more bots
			// addLast will mostly be 1
			double addLast = 1 - Math.rint(Math.pow(Math.random(), getOthers()));
 
			Rectangle2D.Double battleField = new Rectangle2D.Double(30, 30, getBattleFieldWidth() - 60, getBattleFieldHeight() - 60);
			Point2D.Double testPoint;
			
			for (int i = 0 ; i < 200 ; i++) {
				//	calculate the testPoint somewhere around the current position. 100 + 200*Math.random() proved to be good if there are
				//	around 10 bots in a 1000x1000 field. but this needs to be limited this to distanceToTarget*0.8. this way the bot wont
				//	run into the target (should mostly be the closest bot) 
				testPoint = Calculations.calcPoint(Robot.getPos(), Math.min(Robot.getDistanceToTarget()*0.8, 100 + 200*Math.random()), 2*Math.PI*Math.random());
				
				if(battleField.contains(testPoint) && evaluate(testPoint, addLast) < evaluate(Robot.getNextDestination(), addLast)) {
					Robot.setNextDestination(testPoint);
				}
			}
				
			Robot.setLastPosition(Robot.getPos());
 
		} else {
 
			double angle = Calculations.calcAngle(Robot.getNextDestination(), Robot.getPos()) - getHeadingRadians();
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
	
	
 	public double evaluate(Point2D.Double p, double addLast) {
		// this is basically here that the bot uses more space on the battlefield. In melee it is dangerous to stay somewhere too long.
		double eval = addLast*0.08/p.distanceSq(Robot.getLastPosition());
 
		Enumeration _enum = Robot.getEnemies().elements();
		while (_enum.hasMoreElements()) {
			EnemyRobot en = (EnemyRobot)_enum.nextElement();
			// this is the heart of HawkOnFire. So I try to explain what I wanted to do:
			// -	Math.min(en.energy/myEnergy,2) is multiplied because en.energy/myEnergy is an indicator how dangerous an enemy is
			// -	Math.abs(Math.cos(calcAngle(myPos, p) - calcAngle(en.pos, p))) is bigger if the moving direction isn't good in relation
			//		to a certain bot. it would be more natural to use Math.abs(Math.cos(calcAngle(p, myPos) - calcAngle(en.pos, myPos)))
			//		but this wasn't going to give me good results
			// -	1 / p.distanceSq(en.pos) is just the normal anti gravity thing
			if(en.getAlive()) {
				eval += Math.min(en.getEnergy()/Robot.getEnergy(),2) * 
						(1 + Math.abs(Math.cos(Calculations.calcAngle(Robot.getPos(), p) - Calculations.calcAngle(en.getPosition(), p)))) / p.distanceSq(en.getPosition());
			}
		}
		return eval;
	}
 
//- scan event ------------------------------------------------------------------------------------------------------------------------------
	public void onScannedRobot(ScannedRobotEvent e)
	{
		Scan scan = new Scan();
		EnemyRobot scanned = scan.onScannedRobot(e, getHeadingRadians());
		broadcastMessage("targetPos;" + scanned.getPosition().x + ";" + scanned.getPosition().getY());
		broadcastMessage("myPos;" + Robot.getPos().x + ";" + Robot.getPos().getY());
		
		
		if(getOthers()==1)	setTurnRadarLeftRadians(getRadarTurnRemainingRadians());

		// FLYTTAT TILL SCAN/ENVIRONMENT
		
		
//		EnemyRobot en = (EnemyRobot)enemies.get(e.getName());
// 
//		if(en == null){
//			en = new EnemyRobot();
//			enemies.put(e.getName(), en);
//		}
//		
//		en.setName(e.getName());
//		en.setEnergy((double) e.getEnergy());
//		en.setAlive(true);
//		en.setPosition(Calculations.calcPoint(myPos, e.getDistance(), getHeadingRadians() + e.getBearingRadians())); 
// 
//		// normal target selection: the one closer to you is the most dangerous so attack him
//		if(!target.getAlive() || e.getDistance() < myPos.distance(target.getPosition())) {
//			target = en;
//		}
// 
//		// locks the radar if there is only one opponent left
	}
 
//- minor events ----------------------------------------------------------------------------------------------------------------------------
	public void onRobotDeath(RobotDeathEvent e) {
		((EnemyRobot)Robot.getEnemies().get(e.getName())).setAlive(false);
	}
 
//- math ------------------------------------------------------------------------------------------------------------------------------------

}




	
 

