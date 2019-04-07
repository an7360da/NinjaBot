package info;


public class EnemyRobot {
	
	private int energy;
	private int heading;
	private int velocity;
	private int distance;
	private String name;
	
	public int getEnergy () {
		return this.energy;
	}
	
	public void setEnergy (int energy) {
		this.energy = energy;
	}
	
	
	public int getHeading () {
		return this.heading;
	}
	
	public void setHeading (int heading) {
		this.heading = heading;
	}
	
	public int getVelocity () {
		return this.velocity;
	}
	
	public void setVelocity (int velocity) {
		this.velocity = velocity;
	}
	
	public int getDistance () {
		return this.distance;
	}
	
	public void setDistance (int distance) {
		this.distance = distance;
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


}
