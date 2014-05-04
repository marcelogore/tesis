package ar.com.marcelogore.tesis.boids.scenes;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.Scene;
import ar.com.marcelogore.tesis.boids.CircularBoid;
import ar.com.marcelogore.tesis.boids.util.Vector;

public abstract class Scenario {

	private int numberOfBoids = 10;
	
	protected List<CircularBoid> representedBoids = new LinkedList<CircularBoid>();;
	
	public abstract Scene createScene();
	
	public List<CircularBoid> getRepresentedBoids() {
		return representedBoids;
	}
	
	public Vector getSceneSize() {
		return new Vector(800,800);
	}
	
	public void setNumberOfBoids(int numberOfBoids) {
		this.numberOfBoids = numberOfBoids;
	}
	
	public int getNumberOfBoids() {
		return numberOfBoids;
	}
}