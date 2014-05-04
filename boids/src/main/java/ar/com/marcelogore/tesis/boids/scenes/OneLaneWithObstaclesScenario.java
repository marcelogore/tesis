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
		final Vector goalA = new Vector(this.getSceneSize().x, 10);
		final Vector goalB = new Vector(this.getSceneSize().x, 50);

		for (int j = 0; j < this.getNumberOfBoids(); j++) {
			
			Boid boid = Boid.createRandomBoid(0, 10, 800, 40);
			boid.setName("Boid" + j);
			boid.setGoal(goalA, goalB);
			
			boids.add(boid);
			boid.setOtherBoids(boids);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedBoids.add(new CircularBoid(boid));
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		
		// Ancho inicial
		ScenaryCreator.drawLine(obstacles, new Vector(0,5), new Vector(800,5));
		ScenaryCreator.drawLine(obstacles, new Vector(0,55), new Vector(800,55));

		obstacles.add(new CircularBoid(new Boid(new Vector(400,40), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(400,45), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(400,50), true), Color.BLACK));
		
		obstacles.add(new CircularBoid(new Boid(new Vector(800,10), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(800,15), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(800,20), true), Color.BLACK));
		
		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(800,60);
	}

}
