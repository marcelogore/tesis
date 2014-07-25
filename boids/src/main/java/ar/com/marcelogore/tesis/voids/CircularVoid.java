package ar.com.marcelogore.tesis.voids;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircularVoid extends Circle {

	private static final double SIZE = 5;
 	
	private Void boid;

	public CircularVoid(Void boid) {
		this(boid, randomColor());
	}
	
	public CircularVoid(Void boid, Color color) {
		super(boid.getPosition().x, boid.getPosition().y, SIZE, color);
		this.boid = boid;
	}
	
	public Void getVoid() {
		return boid;
	}

	public void calculateNewVelocity() {
		this.boid.calculateNewVelocity();
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
	
	@Override
	public String toString() {
		return boid.toString();
	}
}
