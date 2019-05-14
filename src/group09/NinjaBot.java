package group09;
import robocode.*;
import robocode.util.Utils;
import se.lth.cs.etsa02.RobotColors;

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

	/**
	 * Main robocode method, runs every turn
	 * Sets colors of NinjaBot and broadcasts them to teammates
	 * Turns radar infinately
	 * 
	 * While loop:
	 * Broadcasts followMe for teammates to interperate
	 * Updates Robot class with own values
	 * Localizes target and shoots if NinjaBot is accurate enough or is close to NinjaBot
	 * moves
	 * calls accurateEnoughToFire
	 * Checks if the enemy team consists of more than  6 robots and then targets the leader
	 */
	public void run() {
		
		RobotColors c = new RobotColors();
		
		c.bodyColor = Color.blue;
		c.gunColor = Color.blue;
		c.radarColor = Color.blue;
		c.scanColor = Color.blue;
		c.bulletColor = Color.blue;
				
		setBodyColor(c.bodyColor);
		setGunColor(c.gunColor);
		setRadarColor(c.radarColor);
		setScanColor(c.scanColor);
		setBulletColor(c.bulletColor);
		
		// Send RobotColors object to our entire team
		try {
			broadcastMessage(c);
			System.out.println("Colors: "+c);
		} catch (IOException ignored) {
		}
				
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
				
				System.out.println("NinjaBot targets: " + Robot.getTarget().getName());
				
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
			System.out.println("BulletQuality: " +Robot.getBulletQuality());
			if(Robot.getBulletQuality() < 0) {
				Robot.resetBulletQuality();
			}
			
			Calculations.setAccurateEnoughToFire();
			 if(Environment.enemies.size()>6 && Robot.getEnemyLeader() == null) {
				Calculations.findLeader();
				 try {
					System.out.println("NinjaBot broadcasts: targetEnemy;" + Robot.getEnemyLeader());
					broadcastMessage("targetEnemy;" + Robot.getEnemyLeader());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			 }
			execute();
		}
	}
	
	/**
	 * If the robot the robot has a target to shoot and the energy for it fires at the target
	 */
	public void shoot() {
		// HeadOnTargeting 
		if(getGunTurnRemaining() == 0 && Robot.getEnergy() > 5 && Robot.hasTarget()) {
			
			setFire( Math.min(Math.min(Robot.getEnergy()/6d, 1300d/Robot.getDistanceToTarget()), Robot.getTarget().getEnergy()/3d) );
			setTurnGunRightRadians(Utils.normalRelativeAngle(Calculations.calcAngle(Robot.getTarget().getPosition(), Robot.getPos()) - getGunHeadingRadians()));
		}
	}
	
	/**
	 * If the target is too close it moves from the target,
	 * otherwise it moves towards the least risky destination previous set,
	 * if the destination is reached a new destination is calculated
	 */

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
* What happens when a Robot is scanned
	 * If the enemy robot is closer than 80 units and its not rammable, NinjaBot will move away
	 * Calculates and broadcasts teamMode
	 * If a scanned robot is friendly, a friendPos message will be broadcasted
	 * If a scanned robot is an enemy, enemy details and targetPos will be sent.
	 * If the target is a better target than NinjaBot's current one, the new scanned robot will be targeted
	 * Calculates target value, i.e. if the target is close and has low energy,
	 *  it will be sent as a target to robots nearby that robot
	 * 
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
			if(scannedRobot.getName().indexOf("DoubleOSeven") !=-1? false: true) {
				try {
					System.out.println("friendPos: " + scannedRobot.getName() + ";" + scannedRobot.getPosition().x + ";" + scannedRobot.getPosition().y);
					broadcastMessage("friendPos;" + scannedRobot.getName() + ";" + scannedRobot.getPosition().x + ";" + scannedRobot.getPosition().y);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	/**
	 * Called when a robot has died,
	 * it's name is removed from enemies list
	 */
	@Override
	public void onRobotDeath(RobotDeathEvent e) {
		
		for (EnemyRobot en : Robot.getEnemies()) {
			if(en.getName().equalsIgnoreCase(e.getName())) {
				en.setAlive(false);
			}
		}
	}
	
	/**
	 * Method called when a bullet is missed, sent to robocode
	 */

	public void onBulletMissed(BulletMissedEvent e) {
		Robot.bulletMissed();
	}
}




	
 

