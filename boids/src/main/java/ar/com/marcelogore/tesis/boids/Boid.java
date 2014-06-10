package ar.com.marcelogore.tesis.boids;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class Boid {

	private static final Log log = LogFactory.getLog(Boid.class);
	private static double radius = 40.0d;
	private static final double MAX_VELOCITY = 2.0d;
	// Half the actual angle, as measured from the velocity vector direction (0 < angle < PI)
	private static final double VIEW_ANGLE = Math.PI / 3;
	
	private String name;
	private boolean obstacle;
	
	private Vector currentPosition;
	private Vector currentVelocity;
	private Vector newVelocity;
	private Vector[] goal;
	private Vector[] checkpoint;
	
	private List<Boid> otherBoids;
	private List<Boid> nearbyBoids = new LinkedList<Boid>();
	private List<Boid> nearbyObstacles = new LinkedList<Boid>();
	
	private double viewAngle = VIEW_ANGLE;
	private double viewCosine = Math.cos(viewAngle);
	
	// Leave them in 0 to avoid a closed loop
	private int maxX;
	private int maxY;
	private boolean debug;
	
	private boolean crossedGoal;
	private boolean crossedCheckpoint;
	
	public Boid() {
		this.name = "Boid";
		this.crossedCheckpoint = true;
	}

	/**
	 * Create a boid indicating its initial position and whether it'll be
	 * an obstacle. No other parameters are set.
	 * 
	 * @param position
	 * @param obstacle
	 */
	public Boid(Vector position, boolean obstacle) {
		this(position, new Vector(0,0));
		this.obstacle = obstacle;
		this.crossedCheckpoint = true;
	}
	
	/**
	 * Create a boid, named "Boid", indicating its initial position and velocity. 
	 * No other parameters are set.
	 * 
	 * @param position
	 * @param velocity
	 */
	public Boid(Vector position, Vector velocity) {
		this.name = "Boid";
		this.currentPosition = position;
		this.currentVelocity = velocity;
		this.crossedCheckpoint = true;
	}
	
	/**
	 * A name to identify the boid
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * If the boid is an obstacle, it'll be unaccounted for when calculating 
	 * group speed (rule 3)
	 * @return
	 */
	public boolean isObstacle() {
		return obstacle;
	}
	
	/**
	 * The boid's current position
	 * 
	 * @return
	 */
	public Vector getPosition() {
		return currentPosition;
	}

	/**
	 * The boid's current velocity
	 * 
	 * @return
	 */
	public Vector getVelocity() {
		return currentVelocity;
	}
	private void setNewVelocity(Vector velocity) {
		this.newVelocity = velocity;
	}


	/**
	 * The boid's goal. A boid without a goal will not move unless other moving
	 * boids are sufficiently near for it to join them
	 * 
	 * @return
	 */
	public Vector[] getGoal() {
		return goal;
	}
	
	/**
	 * The goal can be null, a point (a pair of coordinates on an plane) or a 
	 * segment (a pair of pair-of-coordinates indicating the segment's begin and end). <br>
	 * A boid without a goal will not move unless other moving boids are 
	 * sufficiently near for it to join them. <br>
	 * 
	 * @param goal
	 */
	public void setGoal(Vector... goal) {

		if ((goal.length > 2) || (goal.length == 2 && (goal[0] == null || goal[1] == null))) {
			
			throw new IllegalArgumentException("The goal can either be null, a point (one non-null Vector) or a segment (a pair of non-null Vectors)");
		}
		
		if ((goal == null) || (goal.length == 1 && goal[0] == null) || (goal.length == 2 && goal[1] == null)) {
			
			this.goal = null;
		
		} else {
			
			if (this.goal == null) {
				
				this.goal = new Vector[2];
			}

			if (goal[0] != null) {
				
				this.goal[0] = goal[0];
			}
			
			if (goal.length == 2) {
				
				this.goal[1] = goal[1];
				
				if (goal[0] == null) {
					
					this.goal[0] = goal[1];
				}
			}
		}
		
	}
	
	public Vector[] getCheckpoint() {
		return checkpoint;
	}
	
	public void setCheckpoint(Vector... checkpoint) {

		if ((checkpoint.length > 2) || (checkpoint.length == 2 && (checkpoint[0] == null || checkpoint[1] == null))) {
			
			throw new IllegalArgumentException("The goal can either be null, a point (one non-null Vector) or a segment (a pair of non-null Vectors)");
		}
		
		if ((checkpoint == null) || (checkpoint.length == 1 && checkpoint[0] == null) || (checkpoint.length == 2 && checkpoint[1] == null)) {
			
			this.checkpoint = null;
		
		} else {
			
			if (this.checkpoint == null) {
				
				this.checkpoint = new Vector[2];
			}

			if (checkpoint[0] != null) {
				
				this.checkpoint[0] = checkpoint[0];
			}
			
			if (checkpoint.length == 2) {
				
				this.checkpoint[1] = checkpoint[1];
				
				if (checkpoint[0] == null) {
					
					this.checkpoint[0] = checkpoint[1];
				}
			}
		}
	}

	/*
	 * The boid's "viewing" angle, in radians, counted simultaneously clockwise 
	 * and counter clockwise from the direction of the boid's velocity vector
	 * Note that, then, the viewing angle is actually double the value set.
	 * 
	 */
	public void setViewAngle(double viewAngle) {
		this.viewAngle = viewAngle;
		this.viewCosine = Math.cos(viewAngle);
	}
	
	/**
	 * The set of all the other boids
	 * 
	 * @param otherBoids
	 */
	public void setOtherBoids(List<Boid> otherBoids) {
		this.otherBoids = otherBoids;
	}
	
	/* 
	 * Set MaxX and MaxY properties to make the boids move on a toroidal space.
	 * Leave both values at 0 (it's default) to avoid a closed loop
	 */
	private int getMaxX() {
		return maxX;
	}
	public void setMaxX(int x) {
		this.maxX = x;
	}
	
	private int getMaxY() {
		return maxY;
	}
	public void setMaxY(int y) {
		this.maxY = y;
	}
	
	public void setDebugOn() {
		this.debug = true;
	}
	
	public boolean getCrossedGoal() {
		return crossedGoal;
	}
	
	/**
	 * Get's the boids "at sight". That is, those boids which distance to this 
	 * boid is smaller than RADIUS and are within this boid's viewing angle
	 * 
	 * @return
	 */
	protected void searchNearbyBoids() {
		
		for (Boid boid : this.otherBoids) {
			
			double distance = this.distance(boid);
			
			if (!this.equals(boid) && distance < radius) {

				if (this.isStationary()) {

					if (boid.isObstacle()) {

						this.nearbyObstacles.add(boid);
						
					} else {
						
						this.nearbyBoids.add(boid);
					}
					
				} else {
					
					Vector velocityVector = this.getVelocity();
					Vector distanceVector = Vector.subtract(boid.getPosition(), this.getPosition());
					
					double cosine = ((distanceVector.x * velocityVector.x) + (distanceVector.y * velocityVector.y)) / 
							(distanceVector.length() * velocityVector.length());
					
					if (cosine >= this.viewCosine && cosine <= 1) {
						
						if (boid.isObstacle()) {

							this.nearbyObstacles.add(boid);
							
						} else {
							
							this.nearbyBoids.add(boid);
						}
					}
				}
			}
		}
	}
	
	/*
	 * For testing purposes only
	 */
	public List<Boid> getNearbyBoids() {
		this.searchNearbyBoids();
		return this.nearbyBoids;
	}
	
	/**
	 * Allows change to the boids viewing distance. Note that this method will 
	 * change the setting for ALL boids
	 * 
	 * @param otherBoidsRadius
	 */
	public static void setRadius(double otherBoidsRadius) {
		radius = otherBoidsRadius;
	}
	
	private boolean isStationary() {
		return (this.currentVelocity.x == 0.0) && (this.currentVelocity.y == 0.0);
	}
	
	private double distance(Boid other) {
		
		Vector distance = new Vector();
		distance.copy(this.getPosition());
		
		return Vector.subtract(distance, other.getPosition()).length();
	}
	
	private void updatePosition() {
		
		if (this.isDebugEnabled()) {
			log.debug(this.getName() + "'s position is " + this.getPosition());
		}
		
		Vector newPosition = Vector.add(this.currentPosition, this.newVelocity);
		
		if (this.crossedGoal(newPosition)) {

			if (this.crossedCheckpoint) {
				
				this.crossedGoal = true;
				this.crossedCheckpoint = false;
				
				if (this.debug) {
					log.info(this.name + " crossed goal and checkpoint");
				}
			} else {
				
				this.crossedGoal = false;
				
				if (this.debug) {
					log.info(this.name + " crossed goal but not the checkpoint. It is a re-run");
				}
			}
		} else {
			
			this.crossedGoal = false;
		}
		
		if (!this.crossedCheckpoint) {
			
			this.crossedCheckpoint = this.crossedCheckpoint(newPosition);
			
			if (this.debug) {
				log.info(this.name + " crossed the checkpoint: " + this.crossedCheckpoint);
			}
		}
		
		this.currentPosition = this.modulo(newPosition);
	}
	
	private boolean crossedGoal(Vector newPosition) {

		Vector actualGoal = this.actualPoint(this.goal);
		Vector distanceToActualGoal = Vector.subtract(newPosition, actualGoal);
		Vector position = this.getPosition();
		
		return Vector.scalarProduct(distanceToActualGoal, position) > 0;
	}

	private boolean crossedCheckpoint(Vector newPosition) {

		Vector actualCheckpoint = this.actualPoint(this.checkpoint);
		
		Vector distanceToActualCheckpoint = Vector.subtract(newPosition, actualCheckpoint);
		Vector position = this.getPosition();
		
		return Vector.scalarProduct(distanceToActualCheckpoint, position) > 0 && distanceToActualCheckpoint.length() < 10;
	}

	private Vector modulo(Vector vector) {
		
		Vector moduloVector = new Vector(vector);

		if (this.getMaxX() != 0 && this.getMaxY() != 0) {
			
			if (vector.x > this.getMaxX()) {
				
				moduloVector.x = vector.x - this.getMaxX();
			}
			
			if (vector.x < 0) {
				
				moduloVector.x = this.getMaxX() + vector.x;
			}
			
			if (vector.y > this.getMaxY()) {
				
				moduloVector.y = vector.y - this.getMaxY();
			}

			if (vector.y < 0) {
				moduloVector.y = this.getMaxY() + vector.y;
			}
		}
		
		return moduloVector;
	}

	public int boidCollisionCount() {
		
		int collisionCount = 0;
		
		for (Boid nearbyBoid : this.nearbyBoids) {
			
			if (this.distance(nearbyBoid) < 10) {
				
				collisionCount++;
				log.debug(this.name + this.currentPosition + " collided with obstacle in " + nearbyBoid.currentPosition);
			}
		}
		
		if (this.isDebugEnabled()) {
			log.debug("This boid collided with " + collisionCount + " other boids in this time-step");
		}
		
		return collisionCount;
	}

	public int obstacleCollisionCount() {
		
		int collisionCount = 0;

		for (Boid obstacle : this.nearbyObstacles) {
			
			if (this.distance(obstacle) < 10) {
				
				collisionCount++;
				log.debug(this.name + this.currentPosition + " collided with obstacle in " + obstacle.currentPosition);
			}
		}
		
		if (this.isDebugEnabled()) {
			log.debug("This boid collided with " + collisionCount + " obstacles in this time-step");
		}
		
		return collisionCount;
	}
	
	
	/**
	 * Updates the position based on the previously calculated velocity
	 * 
	 */
	public void update() {
		
		this.updatePosition();
		this.currentVelocity = this.newVelocity;
	}
	
	public void calculateNewVelocity() {
		
		this.nearbyBoids.clear();
		this.nearbyObstacles.clear();
		
		if (!obstacle) {
			
			this.searchNearbyBoids();
			
			Vector velocityShiftDueToRule1 = this.moveTowardsPercievedMassCenter();
			Vector velocityShiftDueToRule2 = this.keepDistanceFromSurroundingObjects().multiply(2);
			Vector velocityShiftDueToRule3 = this.matchOtherBoidsVelocity();
			Vector velocityShiftDueToRule4 = this.moveTowardsGoal();
			
			Vector finalVelocity = this.limitVelocity(Vector.add(
					this.getVelocity(),
					velocityShiftDueToRule1, 
					velocityShiftDueToRule2, 
					velocityShiftDueToRule3, 
					velocityShiftDueToRule4));
			
			if (this.isDebugEnabled()) {
				
				log.debug(this.getName() + "'s velocity shift due to rule 1 (mass center) is " + velocityShiftDueToRule1);
				log.debug(this.getName() + "'s velocity shift due to rule 2 (distance to others) is " + velocityShiftDueToRule2);
				log.debug(this.getName() + "'s velocity shift due to rule 3 (match velocity) is " + velocityShiftDueToRule3);
				log.debug(this.getName() + "'s velocity shift due to rule 4 (goal) is " + velocityShiftDueToRule4);
				log.debug(this.getName() + "'s final velocity is " + finalVelocity);
			}
			
			this.setNewVelocity(finalVelocity);
		}
	}

	private boolean isDebugEnabled() {
		return this.debug && log.isDebugEnabled();
	}

	public double velocityTowardsGoal() {
		
		Vector distanceToGoal = this.distanceToGoal();
		
		return Vector.scalarProduct(distanceToGoal, this.getVelocity()) / distanceToGoal.length();
	}
	
	private Vector distanceToGoal() {

		return Vector.subtract(this.actualPoint(this.goal), this.getPosition());
	}

	/**
	 * Determines the actual point where this boid is heading
	 * If the goal null, it'll return null
	 * If the goal is a single point, it'll return that
	 * Otherwise, it'll project the boid's velocity onto the line containing 
	 * the segment and return the point of the segment where it projected or
	 * the extreme of the segment closest to that point.
	 * 
	 * @return
	 */
	private Vector actualPoint(Vector[] segment) {

		// Adapted from http://stackoverflow.com/questions/849211/shortest-distance-between-a-point-and-a-line-segment/1501725#1501725
		
		Vector goal = segment != null && segment.length > 0 ? segment[0] : null;

		if (segment != null) {
			
			if (segment[0] != null && segment[1] != null) {
				
				Vector goalSegment = Vector.subtract(segment[1], segment[0]);
				double goalLength = goalSegment.length();
				double goalLengthSquared = goalLength * goalLength;
				
				if (goalLengthSquared == 0.0) {
					
					goal = segment[0];
					
				} else {
					
					double projectionOntoLine = Vector.scalarProduct(Vector.subtract(this.currentPosition, segment[0]), Vector.subtract(segment[1], segment[0])) / goalLengthSquared;
					
					if (projectionOntoLine < 0.0) {
						
						goal = segment[0];
						
					} else if (projectionOntoLine > 1.0) {
						
						goal = segment[1];
						
					} else {
						
						goal = Vector.add(segment[0], goalSegment.multiply(projectionOntoLine));
					}
				}
			}
		}
		
		return goal;
	}

	private Vector limitVelocity(Vector rawVelocity) {

		Vector finalVelocity = new Vector(rawVelocity);
		
		double speed = finalVelocity.length();
		
		if (speed > MAX_VELOCITY) {
			finalVelocity = finalVelocity.divide(speed).multiply(MAX_VELOCITY);
		}
		
		return finalVelocity;
	}

	public static Boid createRandomBoid(long x, long y) {
		
		return createRandomBoid(0, 0, x, y);
	}
	
	public static Boid createRandomBoid(long minX, long minY, long maxX, long maxY) {
		
		Boid boid = new Boid();
		
		boid.currentPosition = Vector.createRandomVector(minX, minY, maxX, maxY);
		boid.currentVelocity = new Vector();
		
		log.debug("Created new random boid " + boid);
		return boid;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder(this.name);

		sb.append("[Position:");
		sb.append(this.currentPosition);
		sb.append(";Velocity:");
		sb.append(this.currentVelocity);
		sb.append("]");
		
		return sb.toString();
	}
	
	private Vector moveTowardsPercievedMassCenter() {
		
		Vector centerOfMass = new Vector(0,0);
		Vector velocityShift = new Vector(0,0);
		
		if (this.nearbyBoids.size() > 0) {
			
			for (Boid nearbyBoid : this.nearbyBoids) {
				
				centerOfMass = Vector.add(centerOfMass, nearbyBoid.getPosition());
			}
			
			centerOfMass.divide(this.nearbyBoids.size());
			velocityShift = Vector.subtract(centerOfMass, this.getPosition());
		}

		return velocityShift.normalize();
	}
	
	private Vector keepDistanceFromSurroundingObjects() {
		
		final double power = 2;
		
		Vector collisionAvoidance = new Vector(0,0);
		List<Boid> nearbyBoidsAndObstacles = new LinkedList<Boid>();
		nearbyBoidsAndObstacles.addAll(this.nearbyBoids);
		nearbyBoidsAndObstacles.addAll(this.nearbyObstacles);
		
		for (Boid nearbyBoid : nearbyBoidsAndObstacles) {
			
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
		Vector velocityShift = new Vector(0,0);
		
		if (this.nearbyBoids.size() > 0) {
			
			for (Boid nearbyBoid : this.nearbyBoids) {
				
				othersVelocity = Vector.add(othersVelocity, nearbyBoid.getVelocity());
			}
			
			othersVelocity.divide(this.nearbyBoids.size());
			velocityShift = Vector.subtract(othersVelocity, this.getVelocity());
		}

		return velocityShift.normalize();
	}
	
	private Vector moveTowardsGoal() {
		
		Vector goalDirection = new Vector(0,0);
		
		if (this.goal != null) {
			
			goalDirection = Vector.subtract(this.actualPoint(this.goal), this.getPosition());
		}
		
		return goalDirection.normalize();
	}
	
}

