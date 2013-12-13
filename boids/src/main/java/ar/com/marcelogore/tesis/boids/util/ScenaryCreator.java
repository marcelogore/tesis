package ar.com.marcelogore.tesis.boids.util;

import java.util.List;

import javafx.scene.paint.Color;
import ar.com.marcelogore.tesis.boids.Boid;
import ar.com.marcelogore.tesis.boids.CircularBoid;

public class ScenaryCreator {

	public static final double DISTANCE_BETWEEN_BOIDS = 10.0;
	
	public static List<CircularBoid> drawLine(List<CircularBoid> boids, Vector start, Vector end) {
		
		Vector dif = Vector.subtract(end, start);

		if (dif.length() >= DISTANCE_BETWEEN_BOIDS) {
			
			Vector middle = new Vector(start.x + dif.x / 2.0, start.y + dif.y / 2.0);
			drawLine(boids, start, middle);
			drawLine(boids, middle, end);
			
		} else {
			
			boids.add(new CircularBoid(new Boid(end, true), Color.BLACK));
		}
		
		return boids;
	}
	
}
