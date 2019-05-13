package group09;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;
import info.EnemyRobot;
import info.Environment;
import info.Robot;
import info.Scan;
import movement.MovementEvents;

public class NinjaBot extends TeamRobot {
	
	int timeCounter = 0;

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
			System.out.println("Colors");
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
		
		while (true) {
			
			try {
				System.out.println("follow me");
				broadcastMessage("leadership;followMe");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Robot.setPos(new Point2D.Double(getX(),getY()));
			Robot.setEnergy(getEnergy());
			
			// Tar max 9 ticks tills alla �r skannade			
			
			if(Robot.hasTarget() && Robot.getTarget().getAlive() && getTime()>=9) {
				
				//System.out.println("NinjaBot targets: " + Robot.getTarget().getName());
				
				java.awt.geom.Point2D.Double targetPos = Robot.getTarget().getPosition();
				java.awt.geom.Point2D.Double robotPos = Robot.getPos();
				double distance = robotPos.distance(targetPos);
				Robot.setDistanceToTarget(distance);
				if(!isTeammate(Robot.getTarget().getName()) && Robot.isAccurateEnoughToFire() || Robot.getDistanceToTarget() < 200) {
					shoot();	
				}
			}
			move();
			Robot.timePassed();
			System.out.println(Robot.getBulletQuality());
			if(Robot.getBulletQuality() < 0) {
				Robot.resetBulletQuality();
			}
			
			Calculations.setAccurateEnoughToFire();
			 if(Environment.enemies.size()>6 && Robot.getEnemyLeader() == null) {
				Calculations.findLeader();
				 try {
//					System.out.println("NinjaBot broadcasts: targetEnemy;" + Robot.getEnemyLeader());
					broadcastMessage("targetEnemy;" + Robot.getEnemyLeader());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
			execute();
		}
	}
	
	public void shoot() {
		// HeadOnTargeting 
		if(getGunTurnRemaining() == 0 && Robot.getEnergy() > 5 && Robot.hasTarget()) {
			
			setFire( Math.min(Math.min(Robot.getEnergy()/6d, 1300d/Robot.getDistanceToTarget()), Robot.getTarget().getEnergy()/3d) );
			setTurnGunRightRadians(Utils.normalRelativeAngle(Calculations.calcAngle(Robot.getTarget().getPosition(), Robot.getPos()) - getGunHeadingRadians()));
		}
	}
	
	public void move() {
		MovementEvents moveToDestination = new MovementEvents();
		MovementEvents newDestination = new MovementEvents();
	
		if(Robot.isTooClose()) {
			double angle = moveToDestination.calculateAngle(getHeadingRadians());
			double direction = moveToDestination.calculateDirection(angle);

			setAhead(-(Robot.getPos().distance(Robot.getCloseLocation()) * direction  * 5));
		}else {
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
		
	}
	
	 
//- scan event ------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Short one line description.                           (1)
	 * <p>
	 * Longer description. If there were any, it would be    (2)
	 * here.
	 * </p>
	 * And even more explanations to follow in consecutive
	 * paragraphs separated by HTML paragraph breaks.
	 *
	 * @param  variable Description text text text.          (3)
	 * @return Description text text text.
	 */

	Scan scan = new Scan();
	public void onScannedRobot(ScannedRobotEvent e) {
		
		if(e.getDistance()<=80 && e.getEnergy()>0) {
			Robot.setTooClose(true);
			Robot.setCloseLocation(Calculations.calcPoint(Robot.getPos(), e.getDistance(),
					e.getHeadingRadians() + e.getBearingRadians()));
		}else {
			Robot.setTooClose(false);
		}
		
		String teamMode = Calculations.calcTeamMode();
		
		System.out.println("EnemyTeams Total Energy:" + Calculations.teamsTotalEnergy(Environment.enemies));
		System.out.println("MyTeams Total Energy:" + Calculations.teamsTotalEnergy(Environment.friends));
		System.out.println(teamMode);
		
		try {
			broadcastMessage("teamMode;" + teamMode);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (isTeammate(e.getName())) {
			EnemyRobot scannedRobot = scan.onScannedFriendlyRobot(e, getHeadingRadians());
			try {
				System.out.println("friendPos: " + scannedRobot.getName() + ";" + scannedRobot.getPosition().x + ";" + scannedRobot.getPosition().y);
				broadcastMessage("friendPos;" + scannedRobot.getName() + ";" + scannedRobot.getPosition().x + ";" + scannedRobot.getPosition().y);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			
			EnemyRobot scannedRobot = scan.onScannedEnemyRobot(e, getHeadingRadians());
			try {
				System.out.println("targetPos: " + scannedRobot.getPosition().x + ";" + scannedRobot.getPosition().getY());
				System.out.println("enemyDetails: " + scannedRobot.getName() + ";" + scannedRobot.getPosition().x + 
						";" + scannedRobot.getPosition().y + ";" + scannedRobot.getVelocity() + ";" + 
						scannedRobot.getEnergy()  + ";" + scannedRobot.getHeading() +";0");
				broadcastMessage("targetPos;" + scannedRobot.getPosition().x + ";" + scannedRobot.getPosition().getY());
				broadcastMessage("enemyDetails;" + scannedRobot.getName() + ";" + scannedRobot.getPosition().x + 
						";" + scannedRobot.getPosition().y + ";" + scannedRobot.getVelocity() + ";" + 
						scannedRobot.getEnergy()  + ";" + scannedRobot.getHeading() +";0");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for(int i=0; i<Environment.friends.size(); i++) {
				EnemyRobot teammate;
				double targetValue;
				teammate =Environment.friends.get(i);
				
				targetValue=Calculations.targetValue(scannedRobot, teammate);
				
				
				if(targetValue>7) {
					try {
						System.out.println("Message to: " + teammate.getName() + " targetEnemy:" + scannedRobot.getName());
						sendMessage(teammate.getName(), "targetEnemy:" + scannedRobot.getName());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
		try {
			broadcastMessage("myPos;" + Robot.getPos().x + ";" + Robot.getPos().y);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(getOthers() == 1)	setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
	}
 
	@Override
	public void onRobotDeath(RobotDeathEvent e) {
		
		for (EnemyRobot en : Robot.getEnemies()) {
			if(en.getName().equalsIgnoreCase(e.getName())) {
				en.setAlive(false);
			}
		}
	}
	
	public void onBulletMissed(BulletMissedEvent e) {
		Robot.bulletMissed();
	}
}




	
 

