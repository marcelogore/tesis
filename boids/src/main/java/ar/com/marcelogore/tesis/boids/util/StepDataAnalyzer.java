package ar.com.marcelogore.tesis.boids.util;

import java.util.LinkedList;
import java.util.List;

public class StepDataAnalyzer {

	private List<Vector> absoluteVelocities;
	private List<Vector> actualVelocities;
	
	private double absoluteVelocitiesSum;
	private double actualVelocitiesSum;
	
	public StepDataAnalyzer() {
		
		this.absoluteVelocities = new LinkedList<Vector>();
		this.actualVelocities = new LinkedList<Vector>();
	}
	
	public void addBoidAbsoluteVelocity(Vector velocity) {

//System.out.println("Absolute: " + velocity);

		this.absoluteVelocities.add(velocity);
		this.absoluteVelocitiesSum += velocity.length();
	}

	public void addBoidActualVelocity(Vector velocityTowardsGoal) {

//System.out.println("Actual: " + velocityTowardsGoal);
		
		this.actualVelocities.add(velocityTowardsGoal);
		this.actualVelocitiesSum += velocityTowardsGoal.length();
	}

	public double calculateAbsoluteVelocity() {
		
		return this.absoluteVelocitiesSum / this.absoluteVelocities.size(); 
	}

	public double calculateActualVelocity() {
		
		return this.actualVelocitiesSum / this.actualVelocities.size(); 
	}
}
