package ar.com.marcelogore.tesis.boids;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircularBoid extends Circle {

	private static final double SIZE = 5;
 	
	private Boid boid;

	public CircularBoid(Boid boid) {
		this(boid, randomColor());
	}
	
	public CircularBoid(Boid boid, Color color) {
		super(boid.getPosition().x, boid.getPosition().y, SIZE, color);
		this.boid = boid;
	}

	public void update() {
		
		this.boid.update();
		
		this.setCenterX(this.boid.getPosition().x);
		this.setCenterY(this.boid.getPosition().y);
	}
	
	public static Color randomColor() {
		
		Random random = new Random();
		
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		
		return Color.rgb(red, green, blue);
	}
}
