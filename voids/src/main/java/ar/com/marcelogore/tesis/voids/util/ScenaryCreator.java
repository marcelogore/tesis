package ar.com.marcelogore.tesis.voids.util;

import java.util.List;

import javafx.scene.paint.Color;
import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.Void;

public class ScenaryCreator {

	public static final double DISTANCE_BETWEEN_BOIDS = 5.0;
	
	public static List<CircularVoid> drawLine(List<CircularVoid> voids, Vector start, Vector end) {
		
		Vector dif = Vector.subtract(end, start);

		if (dif.length() >= DISTANCE_BETWEEN_BOIDS) {
			
			Vector middle = new Vector(start.x + dif.x / 2.0, start.y + dif.y / 2.0);
			drawLine(voids, start, middle);
			drawLine(voids, middle, end);
			
		} else {
			
			voids.add(new CircularVoid(new Void(end, true), Color.BLACK));
		}
		
		return voids;
	}

	public static List<CircularVoid> drawRectangle(List<CircularVoid> voids, Vector start, Vector end) {
		
		Vector middle = new Vector(start);
		
		middle.x = end.x;
		
		drawLine(voids, start, middle);
		drawLine(voids, middle, end);
		
		middle.x = start.x;
		middle.y = end.y;
		
		drawLine(voids, end, middle);
		drawLine(voids, middle, start);
		
		return voids;
	}
}
