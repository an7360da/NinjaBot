package group09;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import info.EnemyRobot;
import info.Robot;
import info.Scan;
import movement.MovementEvents;

public class NinjaBot extends TeamRobot {
	
//	static Hashtable enemies = new Hashtable();
//	static EnemyRobot target;
//	static Point2D.Double nextDestination;
//	static Point2D.Double lastPosition;
//	static Point2D.Double myPos;
//	static double myEnergy;
//	private double distanceToTarget;
		
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
		try {
			broadcastMessage(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		setColors(Color.black,Color.red,Color.white); // body,gun,radar
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
 
		setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
 
		
		Point2D.Double p = new Point2D.Double(getX(), getY());
		Robot.setNextDestination(p);
		Robot.setPos(p);
		Robot.setLastPosition(p);
		
		// = lastPosition = myPos = new Point2D.Double(getX(), getY());
		
		while (true) {
			
			
			Robot.setPos(new Point2D.Double(getX(),getY()));
			Robot.setEnergy(getEnergy());
			// Tar max 9 ticks tills alla �r skannade			
			
			if(Robot.hasTarget() && Robot.getTarget().getAlive() && getTime()>=0) {
				
				java.awt.geom.Point2D.Double targetPos = Robot.getTarget().getPosition();
				java.awt.geom.Point2D.Double robotPos = Robot.getPos();
				double distance = robotPos.distance(targetPos);
				Robot.setDistanceToTarget(distance);
				if(!isTeammate(Robot.getTarget().getName())){
					shoot();	
				}
			}
			move();
			execute();
		}
	}
	
	public void shoot() {
		// HeadOnTargeting 
		if(getGunTurnRemaining() == 0 && Robot.getEnergy() > 5 && Robot.hasTarget() && !isTeammate(Robot.getTarget().getName())) {
			
			setFire( Math.min(Math.min(Robot.getEnergy()/6d, 1300d/Robot.getDistanceToTarget()), Robot.getTarget().getEnergy()/3d) );
			setTurnGunRightRadians(Utils.normalRelativeAngle(Calculations.calcAngle(Robot.getTarget().getPosition(), Robot.getPos()) - getGunHeadingRadians()));
		}

	}
	
	public void move() {
		MovementEvents moveToDestination = new MovementEvents();
		MovementEvents newDestination = new MovementEvents();
	
		//Anti-grav
		double distanceToNextDestination = Robot.getPos().distance(Robot.getNextDestination());
		 
		//search a new destination if I reached this one
		if (distanceToNextDestination < 15) {
		newDestination.newDestination(getOthers(), getBattleFieldWidth(), getBattleFieldHeight());

		} else {
			
			double angle = moveToDestination.calculateAngle(getHeadingRadians());
			double direction = moveToDestination.calculateDirection(angle);
 
			setAhead(distanceToNextDestination * direction);
			setTurnRightRadians(angle = Utils.normalRelativeAngle(angle));
			// hitting walls isn't a good idea, but NinjaBot still does it pretty often
			setMaxVelocity(Math.abs(angle) > 1 ? 0 : 8d);
			
		}
	}
	 
//- scan event ------------------------------------------------------------------------------------------------------------------------------
	public void onScannedRobot(ScannedRobotEvent e) {
//		en.setPosition(Calculations.calcPoint(Robot.getPos(), event.getDistance(), headingRadians + event.getBearingRadians())); 

		Scan scan = new Scan();
		
		EnemyRobot scanned = scan.onScannedRobot(e, getHeadingRadians());
		try {
			broadcastMessage("targetPos;" + scanned.getPosition().x + ";" + scanned.getPosition().getY());
			broadcastMessage("myPos;" + Robot.getPos().x + ";" + Robot.getPos().getY());
//			broadcastMessage("enemyDetails;" + e.getName() + ";" + e.getPos().x + ";" + e.getPos().y + ";" + e.getEnergy());

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(getOthers()==1)	setTurnRadarLeftRadians(getRadarTurnRemainingRadians());

	}
 
//- minor events ----------------------------------------------------------------------------------------------------------------------------
	public void onRobotDeath(RobotDeathEvent e) {
		((EnemyRobot)Robot.getEnemies().get(e.getName())).setAlive(false);
	}
 
//- math ------------------------------------------------------------------------------------------------------------------------------------

}




	
 

