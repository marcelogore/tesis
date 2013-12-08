package ar.com.marcelogore.tesis.boids;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class Boid {

	private static final Log log = LogFactory.getLog(Boid.class);
	private static double radius = 80.0d;
	private static final double MAX_VELOCITY = 10.0d;
	// Half the actual angle, as measured from the velocity vector direction (0 < angle < PI)
//	private static final double VIEW_ANGLE = Math.PI * 2.0 / 3.0;
	private static final double VIEW_ANGLE = Math.PI;
	private static final double VIEW_COSINE = Math.cos(VIEW_ANGLE);
	
	private String name;
	
	private Vector position;
	private Vector oldPosition;
	private Vector velocity;
	private Vector goal;
	
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
	
	public Vector getGoal() {
		return goal;
	}
	
	public void setGoal(Vector goal) {
		this.goal = goal;
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
			
			double distance = this.distance(boid);
			
			if (!this.equals(boid) && distance < radius) {
				
				if (this.isStationary()) {

					nearbyBoids.add(boid);
					
				} else {
					
					Vector velocityVector = this.getVelocity();
					Vector distanceVector = Vector.subtract(boid.getPosition(), this.getPosition());
					
					double cosine = ((distanceVector.x * velocityVector.x) + (distanceVector.y * velocityVector.y)) / 
							(distanceVector.length() * velocityVector.length());
					
					if (cosine >= VIEW_COSINE && cosine <= 1) {
						
						nearbyBoids.add(boid);
					}
				}
			}
		}
		
		return nearbyBoids;
	}
	
	public static void setRadius(double otherBoidsRadius) {
		radius = otherBoidsRadius;
	}
	
	private boolean isStationary() {
		return (this.velocity.x == 0.0) && (this.velocity.y == 0.0);
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
		
		Vector velocityShiftDueToRule1 = this.moveTowardsPercievedMassCenter().multiply(0.5);
		Vector velocityShiftDueToRule2 = this.keepDistanceFromSurroundingObjects();
		Vector velocityShiftDueToRule3 = this.matchOtherBoidsVelocity();
		Vector velocityShiftDueToRule4 = this.moveTowardsGoal();

		Vector finalVelocity = this.limitVelocity(Vector.add(
				velocityShiftDueToRule1, 
				velocityShiftDueToRule2, 
				velocityShiftDueToRule3, 
				velocityShiftDueToRule4));
		
		if (log.isDebugEnabled()) {
			
			log.debug(this.getName() + "'s velocity shift due to rule 1 is " + velocityShiftDueToRule1);
			log.debug(this.getName() + "'s velocity shift due to rule 2 is " + velocityShiftDueToRule2);
			log.debug(this.getName() + "'s velocity shift due to rule 3 is " + velocityShiftDueToRule3);
			log.debug(this.getName() + "'s velocity shift due to rule 4 is " + velocityShiftDueToRule4);
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
	
	private Vector moveTowardsPercievedMassCenter() {
		
		Vector centerOfMass = new Vector(0, 0);
		List<Boid> nearbyBoids = this.getNearbyBoids();
		
		if (nearbyBoids.size() > 0) {
			
			for (Boid nearbyBoid : nearbyBoids) {
				
				centerOfMass = Vector.add(centerOfMass, nearbyBoid.getPosition());
			}
			
			centerOfMass.divide(nearbyBoids.size());
			
		}

		Vector velocityShift = Vector.subtract(centerOfMass, this.getPosition());
		
		return velocityShift.normalize();
	}
	
	private Vector keepDistanceFromSurroundingObjects() {
		
		final double power = 2;
		
		Vector collisionAvoidance = new Vector(0,0);
		List<Boid> nearbyBoids = this.getNearbyBoids();
		
		for (Boid nearbyBoid : nearbyBoids) {
			
			Vector distanceVector = Vector.subtract(nearbyBoid.getPosition(), this.getPosition());
			double distance = distanceVector.length();
			
			double distanceCorrection = 0;
					
			if (distance <= 25) {
				
				distanceCorrection = Math.pow((distance - 25) / 12.0, power);
			}
			
			collisionAvoidance = Vector.subtract(collisionAvoidance, distanceVector.normalize().multiply(distanceCorrection));
		}
		
		return collisionAvoidance;
	}
	
	private Vector matchOtherBoidsVelocity() {
		
		Vector othersVelocity = new Vector(0, 0);
		List<Boid> nearbyBoids = this.getNearbyBoids();
		
		if (nearbyBoids.size() > 0) {
			
			for (Boid nearbyBoid : nearbyBoids) {
				
				othersVelocity = Vector.add(othersVelocity, nearbyBoid.getVelocity());
			}
			
			othersVelocity.divide(nearbyBoids.size());
		}
		
		Vector velocityShift = Vector.subtract(othersVelocity, this.getVelocity());
		
		return velocityShift.normalize();
	}
	
	private Vector moveTowardsGoal() {
		
		Vector goalDirection = new Vector(0,0);
		
		if (this.goal != null) {
			
			goalDirection = Vector.subtract(this.getGoal(), this.getPosition());
		}
		
		return goalDirection.normalize();
	}
	
}

