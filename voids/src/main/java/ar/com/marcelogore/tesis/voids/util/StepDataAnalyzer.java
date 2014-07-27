package ar.com.marcelogore.tesis.voids.util;


public class StepDataAnalyzer {

	private int voidCount;
	
	private double absoluteVelocitiesSum;
	private double actualVelocitiesSum;
	
	private int voidCollisionCount;
	private int obstacleCollisionCount;
	
	private int goalReachedCount;
	
	public void addVoidAbsoluteVelocity(Vector velocity) {

		this.absoluteVelocitiesSum += velocity.length();
	}

	public void addVoidActualVelocity(double velocityTowardsGoal) {

		this.actualVelocitiesSum += velocityTowardsGoal;
	}

	public double calculateAbsoluteVelocity() {
		
		return this.absoluteVelocitiesSum / this.voidCount; 
	}

	public double calculateActualVelocity() {
		
		return this.actualVelocitiesSum / this.voidCount; 
	}

	public void addVoidCollisionCount(int voidCollisionCount) {
		this.voidCollisionCount += voidCollisionCount;
	}
	
	public void addObstacleCollisionCount(int obstacleCollisionCount) {
		this.obstacleCollisionCount += obstacleCollisionCount;
	}
	
	public double calculateCollisionCount() {
		
		return obstacleCollisionCount + voidCollisionCount / 2;
	}
	
	public void increaseGoalReachedCount() {
		this.goalReachedCount++;
	}

	public double calculateGoalReachedCount() {
		return this.goalReachedCount;
	}
	
	public void nextVoid() {
		this.voidCount++;
	}
	
	public void nextTimeStep() {
		
		this.voidCount = 0;
		this.voidCollisionCount = 0;
		this.obstacleCollisionCount = 0;
		this.absoluteVelocitiesSum = 0;
		this.actualVelocitiesSum = 0;
		this.goalReachedCount = 0;
	}
}
