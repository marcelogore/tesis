package ar.com.marcelogore.tesis.boids.scenes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ar.com.marcelogore.tesis.boids.Boid;
import ar.com.marcelogore.tesis.boids.CircularBoid;
import ar.com.marcelogore.tesis.boids.util.ScenaryCreator;
import ar.com.marcelogore.tesis.boids.util.Vector;

public class OneLaneWithObstaclesScenario extends Scenario {

	public OneLaneWithObstaclesScenario(Integer numberOfBoids) {
		this.setNumberOfBoids(numberOfBoids);
	}
	
	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		// Vuelen a la banda contraria
		final Vector goalA = new Vector(this.getSceneSize().x, 80);
		final Vector goalB = new Vector(this.getSceneSize().x, 120);
		final Vector checkpointA = new Vector(this.getSceneSize().x / 2, 80);
		final Vector checkpointB = new Vector(this.getSceneSize().x / 2, 120);

		for (int j = 0; j < this.getNumberOfBoids(); j++) {
			
			Boid boid = Boid.createRandomBoid(0, 80, 800, 40);
			boid.setName("Boid" + j);
			boid.setGoal(goalA, goalB);
			boid.setCheckpoint(checkpointA, checkpointB);
			
			boids.add(boid);
			boid.setOtherBoids(boids);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedBoids.add(new CircularBoid(boid));
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		
		// Ancho inicial
		ScenaryCreator.drawLine(obstacles, new Vector(0,75), new Vector(800,75));
		ScenaryCreator.drawLine(obstacles, new Vector(0,125), new Vector(800,125));

		obstacles.add(new CircularBoid(new Boid(new Vector(400,110), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(400,115), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(400,120), true), Color.BLACK));
		
		obstacles.add(new CircularBoid(new Boid(new Vector(800,80), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(800,85), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(800,90), true), Color.BLACK));
		
		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(800,200);
	}

}
