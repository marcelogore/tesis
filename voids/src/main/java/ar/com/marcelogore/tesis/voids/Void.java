package ar.com.marcelogore.tesis.voids;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.voids.util.Vector;

public class Void {

	private static final Log log = LogFactory.getLog(Void.class);
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
	
	private List<Void> otherVoids;
	private List<Void> nearbyVoids = new LinkedList<Void>();
	private List<Void> nearbyObstacles = new LinkedList<Void>();
	
	private double viewAngle = VIEW_ANGLE;
	private double viewCosine = Math.cos(viewAngle);
	
	// Leave them in 0 to avoid a closed loop
	private int maxX;
	private int maxY;
	private boolean debug;
	
	private boolean crossedGoal;
	private boolean crossedCheckpoint;
	
	public Void() {
		this.name = "Void";
		this.crossedCheckpoint = true;
	}

	/**
	 * Create a Void indicating its initial position and whether it'll be
	 * an obstacle. No other parameters are set.
	 * 
	 * @param position
	 * @param obstacle
	 */
	public Void(Vector position, boolean obstacle) {
		this(position, new Vector(0,0));
		this.obstacle = obstacle;
		this.crossedCheckpoint = true;
	}
	
	/**
	 * Create a Void, named "Void", indicating its initial position and velocity. 
	 * No other parameters are set.
	 * 
	 * @param position
	 * @param velocity
	 */
	public Void(Vector position, Vector velocity) {
		this.name = "Void";
		this.currentPosition = position;
		this.currentVelocity = velocity;
		this.crossedCheckpoint = true;
	}
	
	/**
	 * A name to identify the Void
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
	 * If the Void is an obstacle, it'll be unaccounted for when calculating 
	 * group speed (rule 3)
	 * @return
	 */
	public boolean isObstacle() {
		return obstacle;
	}
	
	/**
	 * The Void's current position
	 * 
	 * @return
	 */
	public Vector getPosition() {
		return currentPosition;
	}

	/**
	 * The Void's current velocity
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
	 * The Void's goal. A Void without a goal will not move unless other moving
	 * Voids are sufficiently near for it to join them
	 * 
	 * @return
	 */
	public Vector[] getGoal() {
		return goal;
	}
	
	/**
	 * The goal can be null, a point (a pair of coordinates on an plane) or a 
	 * segment (a pair of pair-of-coordinates indicating the segment's begin and end). <br>
	 * A Void without a goal will not move unless other moving Voids are 
	 * sufficiently near for it to join them. <br>
	 * 
	 * @param goal
	 */
	public void setGoal(Vector... goal) {

		this.goal = this.setSegment(this.goal, goal);
	}
	
	public Vector[] getCheckpoint() {
		return checkpoint;
	}
	
	public void setCheckpoint(Vector... checkpoint) {

		this.checkpoint = this.setSegment(this.checkpoint, checkpoint);
	}

	private Vector[] setSegment(Vector[] where, Vector... vectors) {
		
		if ((vectors.length > 2) || (vectors.length == 2 && (vectors[0] == null || vectors[1] == null))) {
			
			throw new IllegalArgumentException("The goal can either be null, a point (one non-null Vector) or a segment (a pair of non-null Vectors)");
		}
		
		if ((vectors == null) || (vectors.length == 1 && vectors[0] == null) || (vectors.length == 2 && vectors[1] == null)) {
			
			where = null;
		
		} else {
			
			if (where == null) {
				
				where = new Vector[2];
			}

			if (vectors[0] != null) {
				
				where[0] = vectors[0];
			}
			
			if (vectors.length == 2) {
				
				where[1] = vectors[1];
				
				if (vectors[0] == null) {
					
					where[0] = vectors[1];
				}
			}
		}
		
		return where;
	}
	
	/*
	 * The Void's "viewing" angle, in radians, counted simultaneously clockwise 
	 * and counter clockwise from the direction of the Void's velocity vector
	 * Note that, then, the viewing angle is actually double the value set.
	 * 
	 */
	public void setViewAngle(double viewAngle) {
		this.viewAngle = viewAngle;
		this.viewCosine = Math.cos(viewAngle);
	}
	
	/**
	 * The set of all the other Voids
	 * 
	 * @param otherVoids
	 */
	public void setOtherVoids(List<Void> otherVoids) {
		this.otherVoids = otherVoids;
	}
	
	/* 
	 * Set MaxX and MaxY properties to make the Voids move on a toroidal space.
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
	 * Get's the Voids "at sight". That is, those Voids which distance to this 
	 * Void is smaller than RADIUS and are within this Void's viewing angle
	 * 
	 * @return
	 */
	protected void searchNearbyVoids() {
		
		for (Void boid : this.otherVoids) {
			
			double distance = this.distance(boid);
			
			if (!this.equals(boid) && distance < radius) {

				if (this.isStationary()) {

					if (boid.isObstacle()) {

						this.nearbyObstacles.add(boid);
						
					} else {
						
						this.nearbyVoids.add(boid);
					}
					
				} else {
					
					Vector velocityVector = this.getVelocity();
					Vector distanceVector = Vector.subtractInToroid(boid.getPosition(), this.getPosition(), this.getMaxX(), this.getMaxY());
					
					double cosine = ((distanceVector.x * velocityVector.x) + (distanceVector.y * velocityVector.y)) / 
							(distanceVector.length() * velocityVector.length());
					
					if (cosine >= this.viewCosine && cosine <= 1) {
						
						if (boid.isObstacle()) {

							this.nearbyObstacles.add(boid);
							
						} else {
							
							this.nearbyVoids.add(boid);
						}
					}
				}
			}
		}
	}
	
	/*
	 * For testing purposes only
	 */
	public List<Void> getNearbyVoids() {
		this.searchNearbyVoids();
		return this.nearbyVoids;
	}
	
	/**
	 * Allows change to the Voids viewing distance. Note that this method will 
	 * change the setting for ALL Voids
	 * 
	 * @param otherVoidsRadius
	 */
	public static void setRadius(double otherVoidsRadius) {
		radius = otherVoidsRadius;
	}
	
	private boolean isStationary() {
		return (this.currentVelocity.x == 0.0) && (this.currentVelocity.y == 0.0);
	}
	
	private double distance(Void other) {
		
		Vector distance = new Vector();
		distance.copy(this.getPosition());
		
		return Vector.subtractInToroid(distance, other.getPosition(), this.getMaxX(), this.getMaxY()).length();
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

	public int voidCollisionCount() {
		
		int collisionCount = 0;
		
		for (Void nearbyVoid : this.nearbyVoids) {
			
			if (this.distance(nearbyVoid) < 10) {
				
				collisionCount++;
				log.debug(this.name + this.currentPosition + " collided with obstacle in " + nearbyVoid.currentPosition);
			}
		}
		
		if (this.isDebugEnabled()) {
			log.debug("This Void collided with " + collisionCount + " other Voids in this time-step");
		}
		
		return collisionCount;
	}

	public int obstacleCollisionCount() {
		
		int collisionCount = 0;

		for (Void obstacle : this.nearbyObstacles) {
			
			if (this.distance(obstacle) < 10) {
				
				collisionCount++;
				log.debug(this.name + this.currentPosition + " collided with obstacle in " + obstacle.currentPosition);
			}
		}
		
		if (this.isDebugEnabled()) {
			log.debug("This Void collided with " + collisionCount + " obstacles in this time-step");
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
		
		this.nearbyVoids.clear();
		this.nearbyObstacles.clear();
		
		if (!obstacle) {
			
			this.searchNearbyVoids();
			
			Vector velocityShiftDueToRule1 = this.moveTowardsPercievedMassCenter();
			Vector velocityShiftDueToRule2 = this.keepDistanceFromSurroundingObjects().multiply(2);
			Vector velocityShiftDueToRule3 = this.matchOtherVoidsVelocity();
			Vector velocityShiftDueToRule4 = this.moveTowardsGoal();

			// Use this line for goal-driven voids
			// velocityShiftDueToRule4 = velocityShiftDueToRule4.multiply(MAX_VELOCITY);

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
	 * Determines the actual point where this Void is heading
	 * If the goal null, it'll return null
	 * If the goal is a single point, it'll return that
	 * Otherwise, it'll project the Void's velocity onto the line containing 
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

	public static Void createRandomVoid(long x, long y) {
		
		return createRandomVoid(0, 0, x, y);
	}
	
	public static Void createRandomVoid(long minX, long minY, long maxX, long maxY) {
		
		Void boid = new Void();
		
		boid.currentPosition = Vector.createRandomVector(minX, minY, maxX, maxY);
		boid.currentVelocity = new Vector();
		
		log.debug("Created new random Void " + boid);
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
		
		if (this.nearbyVoids.size() > 0) {
			
			for (Void nearbyVoid : this.nearbyVoids) {
				
				centerOfMass = Vector.add(centerOfMass, nearbyVoid.getPosition());
			}
			
			centerOfMass.divide(this.nearbyVoids.size());
			velocityShift = Vector.subtractInToroid(centerOfMass, this.getPosition(), this.getMaxX(), this.getMaxY());
		}

		return velocityShift.normalize();
	}
	
	private Vector keepDistanceFromSurroundingObjects() {
		
		final double power = 2;
		
		Vector collisionAvoidance = new Vector(0,0);
		List<Void> nearbyVoidsAndObstacles = new LinkedList<Void>();
		nearbyVoidsAndObstacles.addAll(this.nearbyVoids);
		nearbyVoidsAndObstacles.addAll(this.nearbyObstacles);
		
		for (Void nearbyVoid : nearbyVoidsAndObstacles) {
			
			Vector distanceVector = Vector.subtractInToroid(nearbyVoid.getPosition(), this.getPosition(), this.getMaxX(), this.getMaxY());
			double distance = distanceVector.length();
			
			double distanceCorrection = 0;
					
			if (distance <= 25) {
				
				distanceCorrection = Math.pow((distance - 25) / 12.0, power);
			}
			
			collisionAvoidance = Vector.subtractInToroid(collisionAvoidance, distanceVector.normalize().multiply(distanceCorrection), this.getMaxX(), this.getMaxY());
		}
		
		return collisionAvoidance;
	}
	
	private Vector matchOtherVoidsVelocity() {
		
		Vector othersVelocity = new Vector(0, 0);
		Vector velocityShift = new Vector(0,0);
		
		if (this.nearbyVoids.size() > 0) {
			
			for (Void nearbyVoid : this.nearbyVoids) {
				
				othersVelocity = Vector.add(othersVelocity, nearbyVoid.getVelocity());
			}
			
			othersVelocity.divide(this.nearbyVoids.size());
			velocityShift = Vector.subtractInToroid(othersVelocity, this.getVelocity(), this.getMaxX(), this.getMaxY());
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

