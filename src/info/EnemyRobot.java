package info;

import java.awt.geom.Point2D;
/**
 * A class EnemyRobot that describes the different characteristics of NinjaBots EnemyRobots
 */
public class EnemyRobot {
	/**
	 * The information needed from the EnemyRobot: energy, heading, velocity, distance, name, alive (status),
	 *  position and targetValue
	 */
	private double energy;
	private double heading;
	private double velocity;
	private double distance;
	private String name;
	private boolean alive;
	private Point2D.Double position;
	private double targetValue;
	
	/**
	 * Returns the target value of the EnemyRobot
	 * @return target value of the EnemyRobot
	 */
	public double getTargetValue() {
		return targetValue;
	}
	/**
	 * Sets the target value of the EnemyRobot to targetValue
	 * @param targetValue the target value of the EnemyRobot
	 */
	public void setTargetValue(double targetValue) {
		this.targetValue = targetValue;
	}
	/**
	 * Returns the energy of the EnemyRobot
	 * @return energy the energy of the EnemyRobot
	 */
	public double getEnergy () {
		return this.energy;
	}
	/**
	 * Sets the energy of the EnemyRobot to d
	 * @param d the energy of the EnemyRobot
	 */
	public void setEnergy (double d) {
		this.energy = d;
	}
	/**
	 * Returns the heading of the EnemyRobot
	 * @return heading the heading of the EnemyRobot
	 */
	public double getHeading () {
		return this.heading;
	}
	/**
	 * Sets the heading of the EnemyRobot to heading  
	 * @param heading the heading of the EnemyRobot
	 */
	public void setHeading (double heading) {
		this.heading = heading;
	}
	/**
	 * Returns the velocity of the EnemyRobot
	 * @return velocity the velocity of the EnemyRobot
	 */
	public double getVelocity () {
		return this.velocity;
	}
	/**
	 * Sets the velocity of the EnemyRobot to velocity 
	 * @param velocity the velocity of the EnemyRobot
	 */
	public void setVelocity (double velocity) {
		this.velocity = velocity;
	}
	/**
	 * Returns the distance of the EnemyRobot
	 * @return distance the distance of the EnemyRobot
	 */
	public double getDistance () {
		return this.distance;
	}
	/**
	 * Sets the distance of the EnemyRobot to distance  
	 * @param distance the distance of the EnemyRobot
	 */
	public void setDistance (double distance) {
		this.distance = distance;
	}
	/**
	 * Returns the name of the EnemyRobot
	 * @return name the name of the EnemyRobot
	 */
	public String getName () {
		return this.name;
	}
	/**
	 * Sets the name of the EnemyRobot to name    
	 * @param name the name of the EnemyRobot
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the alive status of the EnemyRobot
	 * @return alive the alive status of the EnemyRobot
	 */
	public boolean getAlive() {
		return this.alive;
	}
	/**
	 * Sets the alive status of the EnemyRobot to alive     
	 * @param alive the alive status of the EnemyRobot
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	/**
	 * Returns the position in coordinates for the EnemyRobot
	 * @return position the position in coordinates for the EnemyRobot
	 */
	public Point2D.Double getPosition() {
		return this.position;
	}
	/**
	 * Sets the position coordinates of the EnemyRobot to position
	 * @param position the position in coordinates for the EnemyRobot
	 */
	public void setPosition(Point2D.Double position) {
		this.position = position;
	}
}
