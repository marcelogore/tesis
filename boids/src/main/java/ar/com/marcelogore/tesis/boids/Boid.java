package ar.com.marcelogore.tesis.boids;

import java.util.ArrayList;
import java.util.List;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class Boid {

	private static final double RADIUS = 2.0d;
	
	private Vector position;
	private Vector oldPosition;
	private Vector velocity;
	private Vector oldVelocity;
	
	private List<Boid> otherBoids;
	
	public Boid(Vector position, Vector velocity) {
		this.position = position;
		this.oldPosition = position;
		this.velocity = velocity;
		this.oldVelocity = velocity;
	}
	
	public Vector getPositionBeforeUpdate() {
		return oldPosition;
	}

	public Vector getVelocityBeforeUpdate() {
		return oldVelocity;
	}
	
	public Vector getUpdatedPosition() {
		return position;
	}
	
	public Vector getUpdatedVelocity() {
		return velocity;
	}
	
	public void setPosition(Vector position) {
		this.oldPosition.copy(this.position);
		this.position = position;
	}
	
	public void setVelocity(Vector velocity) {
		this.oldVelocity.copy(this.velocity);
		this.velocity = velocity;
	}

	public void setOtherBoids(List<Boid> otherBoids) {
		this.otherBoids = otherBoids;
	}
	
	public List<Boid> getNearbyBoids() {
		
		List<Boid> nearbyBoids = new ArrayList<Boid>();
		double radius = RADIUS;
		
		for (Boid boid : this.otherBoids) {
			
			if (!this.equals(boid) && this.distance(boid) < radius) {
				
				nearbyBoids.add(boid);
			}
		}
		
		return nearbyBoids;
	}
	
	public double distance(Boid other) {
		
		Vector distance = new Vector();
		distance.copy(this.getPositionBeforeUpdate());
		distance.subtract(other.getPositionBeforeUpdate());
		
		return distance.length();
	}
}
