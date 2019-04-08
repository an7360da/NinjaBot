package movement;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import group09.NinjaBot;
import info.Environment;
import info.Ninja;
import robocode.HitByBulletEvent;
import robocode.util.Utils;

public class Movement {

	Environment environment = new Environment();
	
	
	public void move(Ninja ninja) {
		//Anti-grav
		double distanceToNextDestination = ninja.getPos().distance(ninja.getNextDestination());
		 
		//search a new destination if I reached this one
		

	}
	
}
	
	private static Point2D.Double calcPoint(Point2D.Double p, double dist, double ang) {
	return new Point2D.Double(p.x + dist*Math.sin(ang), p.y + dist*Math.cos(ang));
}
	private static double calcAngle(Point2D.Double p2,Point2D.Double p1){
		return Math.atan2(p2.x - p1.x, p2.y - p1.y);
	}
	
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
	}
	

}
