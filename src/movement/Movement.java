package movement;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import group09.Calculations;
import group09.NinjaBot;
import info.Environment;
import info.Ninja;
import robocode.HitByBulletEvent;
import robocode.util.Utils;

public class Movement {

	Environment environment = new Environment();
	
	
	public void move(Ninja ninja, double distanceToNextDestination) {
		
		
		double addLast = 1 - Math.rint(Math.pow(Math.random(), getOthers()));
		 
		Rectangle2D.Double battleField = new Rectangle2D.Double(30, 30, environment.getWidth() - 60, environment.getHeight() - 60);
		Point2D.Double testPoint;
		
		for (int i = 0 ; i < 200 ; i++) {
			//	calculate the testPoint somewhere around the current position. 100 + 200*Math.random() proved to be good if there are
			//	around 10 bots in a 1000x1000 field. but this needs to be limited this to distanceToTarget*0.8. this way the bot wont
			//	run into the target (should mostly be the closest bot) 
			testPoint = Calculations.calcPoint(ninja.getPos(), Math.min(ninja.getDistanceToTarget()*0.8, 100 + 200*Math.random()), 2*Math.PI*Math.random());
			
			if(battleField.contains(testPoint) && Calculations.evaluate(testPoint, addLast, ninja) < Calculations.evaluate(ninja.getNextDestination(), addLast, ninja)) {
				ninja.setNextDestination(testPoint);
			}
		}
			
		ninja.setLastPosition(ninja.getPos());

	}
	

		
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
	}
	

}
