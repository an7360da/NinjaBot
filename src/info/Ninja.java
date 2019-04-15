package info;

import java.awt.geom.Point2D;

public class Ninja {
	
	private  Point2D.Double nextDestination;
	private Point2D.Double lastPosition;
	private Point2D.Double pos;
	private double energy;
	private double distanceToTarget;
	
	public Point2D.Double getNextDestination() {
		return nextDestination;
	}
	public void setNextDestination(Point2D.Double nextDestination) {
		this.nextDestination = nextDestination;
	}
	public Point2D.Double getLastPosition() {
		return lastPosition;
	}
	public void setLastPosition(Point2D.Double lastPosition) {
		this.lastPosition = lastPosition;
	}
	public Point2D.Double getPos() {
		return pos;
	}
	public void setPos(Point2D.Double myPos) {
		this.pos = myPos;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double myEnergy) {
		this.energy = myEnergy;
	}
	public double getDistanceToTarget() {
		return distanceToTarget;
	}
	public void setDistanceToTarget(double distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}
	
	

}
