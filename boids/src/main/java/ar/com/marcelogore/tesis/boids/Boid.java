package ar.com.marcelogore.tesis.boids;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class Boid {

	private static final Log log = LogFactory.getLog(Boid.class);
	private static double radius = 1000.0d;
	private static final double MAX_VELOCITY = 10.0d;
	
	private String name;
	
	private Vector position;
	private Vector oldPosition;
	private Vector velocity;
	
	private List<Boid> otherBoids;
	
	public Boid() {
		this.name = "Boid";
	}
	
	public Boid(Vector position, Vector velocity) {
		this.name = "Boid";
		this.position = position;
		this.velocity = velocity;
		
		log.debug("Created boid " + this);
	}
	
	private String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public Vector getOldPosition() {
		return oldPosition;
	}

	private Vector getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

	public void setOtherBoids(List<Boid> otherBoids) {
		this.otherBoids = otherBoids;
	}
	
	public List<Boid> getNearbyBoids() {
		
		List<Boid> nearbyBoids = new ArrayList<Boid>();
		
		for (Boid boid : this.otherBoids) {
			
			if (!this.equals(boid) && this.distance(boid) < radius) {
				
				nearbyBoids.add(boid);
			}
		}
		
		return nearbyBoids;
	}
	
	public static void setRadius(double otherBoidsRadius) {
		radius = otherBoidsRadius;
	}
	
	public double distance(Boid other) {
		
		Vector distance = new Vector();
		distance.copy(this.getPosition());
		
		return Vector.subtract(distance, other.getPosition()).length();
	}
	
	private void updatePosition() {
		
		this.oldPosition = this.position;
		this.position = Vector.add(this.position, this.velocity);
	}
	
	public void update() {
		
		Vector velocityShiftDueToRule1 = this.rule1();
		Vector velocityShiftDueToRule2 = this.rule2();
		Vector velocityShiftDueToRule3 = this.rule3();

		Vector finalVelocity = this.limitVelocity(Vector.add(velocityShiftDueToRule1, velocityShiftDueToRule2, velocityShiftDueToRule3));
		
		if (log.isDebugEnabled()) {
			
			log.debug(this.getName() + "'s velocity shift due to rule 1 is " + velocityShiftDueToRule1);
			log.debug(this.getName() + "'s velocity shift due to rule 2 is " + velocityShiftDueToRule2);
			log.debug(this.getName() + "'s velocity shift due to rule 3 is " + velocityShiftDueToRule3);
			log.debug(this.getName() + "'s final velocity is " + finalVelocity);
		}
		
		this.setVelocity(finalVelocity);
		this.updatePosition();
	}
	
	private Vector limitVelocity(Vector rawVelocity) {

		Vector finalVelocity = new Vector(rawVelocity);
		
		double speed = finalVelocity.length();
		
		if (speed > MAX_VELOCITY) {
			finalVelocity = finalVelocity.divide(speed).multiply(MAX_VELOCITY);
		}
		
		return finalVelocity;
	}

	public static Boid createRandomBoid(int x, int y) {
		
		Boid boid = new Boid();
		
		boid.position = Vector.createRandomVector(x, y, false);
		boid.velocity = new Vector();
		
		log.debug("Created new random boid " + boid);
		return boid;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder(this.name);

		sb.append("[Position:");
		sb.append(this.position);
		sb.append(";Velocity:");
		sb.append(this.velocity);
		sb.append("]");
		
		return sb.toString();
	}
	
	private Vector rule1() {
		
		Vector centerOfMass = new Vector(0, 0);
		List<Boid> nearbyBoids = this.getNearbyBoids();
		
		for (Boid nearbyBoid : nearbyBoids) {
			
			centerOfMass = Vector.add(centerOfMass, nearbyBoid.getPosition());
		}
		
		centerOfMass.divide(nearbyBoids.size());
		
		Vector velocityShift = Vector.subtract(centerOfMass, this.getPosition());
		
		return velocityShift;
	}
	
	private Vector rule2() {
		
		Vector collisionAvoidance = new Vector(0,0);
		List<Boid> nearbyBoids = this.getNearbyBoids();
		
		for (Boid nearbyBoid : nearbyBoids) {
			
			if (Vector.subtract(this.getPosition(), nearbyBoid.getPosition()).length() < 50) {
				
				collisionAvoidance = Vector.subtract(collisionAvoidance, Vector.subtract(nearbyBoid.getPosition(), this.getPosition()));
			}
		}
		
		return collisionAvoidance;
	}
	
	private Vector rule3() {
		
		Vector othersVelocity = new Vector(0, 0);
		List<Boid> nearbyBoids = this.getNearbyBoids();
		
		for (Boid nearbyBoid : nearbyBoids) {
			
			othersVelocity = Vector.add(othersVelocity, nearbyBoid.getVelocity());
		}
		
		othersVelocity.divide(nearbyBoids.size());
		
		Vector velocityShift = Vector.subtract(othersVelocity, this.getVelocity());
		
		return velocityShift;
	}
	
}

