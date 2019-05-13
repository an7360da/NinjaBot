package tests;

import group09.NinjaBot;

public class MockBot extends NinjaBot {
	private String name;
	private double fakeEnergy;
	private double fakeHeading;
	private double fakePosX;
	private double fakePosY;
	
	private double fakeAhead;
	private double fakeTurnRight;
	private double fakeGunHeading;
	private double fakeGunTurnRight;
	
	/**
	 * Construct a mock robot to be used as a substitute for BasicMeleeBot in unit testing.
	 * It overrides calls to robocode's advanced robot class to prevent robocode's exceptions from being thrown.
	 * Also it allows for an easy way to setup a robot for testing and to verify the output of classes that modify the robot's state.
	 * </br></br>
	 * The robot is setup using the input arguments. The gun starts with the same heading as the robot.
	 * The current implementation only overrides the methods used by ETSA02's BasicMeleeBot.
	 * @param name
	 * @param fakeEnergy
	 * @param fakeHeading
	 * @param fakePosX
	 * @param fakePosY
	 */
	public MockBot(String name, double fakeEnergy, double fakeHeading,
				   double fakePosX, double fakePosY) {
		this.name = name;
		this.fakeEnergy = fakeEnergy;
		this.fakeHeading = fakeHeading;
		this.fakePosX = fakePosX;
		this.fakePosY = fakePosY;
		
		fakeGunHeading = fakeHeading;
	}
	
	@Override
	public double getEnergy() {
		return fakeEnergy;
	}
	
	@Override
	public double getHeading() {
		return fakeHeading;
	}
	
	@Override
	public double getHeadingRadians() {
		return Math.toRadians(fakeHeading);
	}
	
	@Override
	public double getX() {
		return fakePosX;
	}
	
	@Override 
	public double getY() {
		return fakePosY;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setAhead(double distance) {
		fakeAhead = distance;
	}
	
	@Override
	public void setTurnRight(double degrees) {
		fakeTurnRight = degrees;
	}
	
	@Override
	public void setTurnRightRadians(double radians) {
		fakeTurnRight = Math.toDegrees(radians);
	}
	
	@Override
	public void setTurnGunRight(double degrees) {
		fakeGunTurnRight = degrees;
	}
	
	@Override
	public void setTurnGunRightRadians(double radians) {
		fakeGunTurnRight = Math.toDegrees(radians);
	}
	
	@Override
	public double getGunHeading() {
		return fakeGunHeading;
	}

	@Override
	public double getGunTurnRemaining() {
		return fakeGunTurnRight;
	}
	
	
	@Override
	public double getDistanceRemaining() {
		return fakeAhead;
	}
	
	@Override
	public double getTurnRemaining() {
		return fakeTurnRight;
	}

}
