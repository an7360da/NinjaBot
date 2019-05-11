package info;

import java.awt.geom.Point2D;

public class EnemyRobot {
	
	private double energy;
	private double heading;
	private double velocity;
	private double distance;
	private String name;
	private boolean alive;
	private Point2D.Double position;
	
	public double getEnergy () {
		return this.energy;
	}
	
	public void setEnergy (double d) {
		this.energy = d;
	}
	
	public double getHeading () {
		return this.heading;
	}
	
	public void setHeading (double heading) {
		this.heading = heading;
	}
	
	public double getVelocity () {
		return this.velocity;
	}
	
	public void setVelocity (double velocity) {
		this.velocity = velocity;
	}
	
	public double getDistance () {
		return this.distance;
	}
	
	public void setDistance (double distance) {
		this.distance = distance;
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getAlive() {
		return this.alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public Point2D.Double getPosition() {
		return this.position;
	}
	
	public void setPosition(Point2D.Double position) {
		this.position = position;
	}
}
