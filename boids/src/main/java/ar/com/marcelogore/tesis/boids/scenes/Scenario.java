package ar.com.marcelogore.tesis.boids.scenes;

import java.util.LinkedList;
import java.util.List;

import ar.com.marcelogore.tesis.boids.CircularBoid;
import javafx.scene.Scene;

public abstract class Scenario {

	protected List<CircularBoid> representedBoids = new LinkedList<CircularBoid>();;
	
	public abstract Scene createScene();
	
	public List<CircularBoid> getRepresentedBoids() {
		return representedBoids;
	}
}