package ar.com.marcelogore.tesis.boids.util;


public class StepDataAnalyzer {

	private int boidCount;
	
	private double absoluteVelocitiesSum;
	private double actualVelocitiesSum;
	
	private int boidCollisionCount;
	private int obstacleCollisionCount;
	
	private int goalReachedCount;
	
	public void addBoidAbsoluteVelocity(Vector velocity) {

		this.absoluteVelocitiesSum += velocity.length();
	}

	public void addBoidActualVelocity(Vector velocityTowardsGoal) {

		this.actualVelocitiesSum += velocityTowardsGoal.length();
	}

	public double calculateAbsoluteVelocity() {
		
		return this.absoluteVelocitiesSum / this.boidCount; 
	}

	public double calculateActualVelocity() {
		
		return this.actualVelocitiesSum / this.boidCount; 
	}

	public void addBoidCollisionCount(int boidCollisionCount) {
		this.boidCollisionCount += boidCollisionCount;
	}
	
	public void addObstacleCollisionCount(int obstacleCollisionCount) {
		this.obstacleCollisionCount += obstacleCollisionCount;
	}
	
	public double calculateCollisionCount() {
		
		return obstacleCollisionCount + boidCollisionCount / 2;
	}
	
	public void increaseGoalReachedCount() {
		this.goalReachedCount++;
	}

	public double calculateGoalReachedCount() {
		return this.goalReachedCount;
	}
	
	public void nextBoid() {
		this.boidCount++;
	}
	
	public void nextTimeStep() {
		
		this.boidCount = 0;
		this.boidCollisionCount = 0;
		this.obstacleCollisionCount = 0;
		this.absoluteVelocitiesSum = 0;
		this.actualVelocitiesSum = 0;
		this.goalReachedCount = 0;
	}
}
