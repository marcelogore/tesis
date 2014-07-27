package ar.com.marcelogore.tesis.voids.scenes;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.Scene;
import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.util.Vector;

public abstract class Scenario {

	private int numberOfVoids = 10;
	
	protected List<CircularVoid> representedVoids = new LinkedList<CircularVoid>();;
	
	public abstract Scene createScene();
	
	public List<CircularVoid> getRepresentedVoids() {
		return representedVoids;
	}
	
	public Vector getSceneSize() {
		return new Vector(800,800);
	}
	
	public void setNumberOfVoids(int numberOfVoids) {
		this.numberOfVoids = numberOfVoids;
	}
	
	public int getNumberOfVoids() {
		return numberOfVoids;
	}
}