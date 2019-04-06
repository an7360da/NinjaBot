package group09;
import robocode.*;
import java.awt.Color;


public class Main extends AdvancedRobot {

	public void run() {
		 setColors(Color.black,Color.red,Color.white); // body,gun,radar

		while(true) {
			
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(1);
		EnemyEnergy = e.getEnergy();
		
	}
}
