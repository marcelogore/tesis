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

	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		// Vuelen a la banda contraria
		final Vector goalA = new Vector(this.getSceneSize().x, 100);
		final Vector goalB = new Vector(this.getSceneSize().x, 150);

		for (int j = 0; j < 100; j++) {
			
			Boid boid = Boid.createRandomBoid(0, 100, 800, 50);
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
		ScenaryCreator.drawLine(obstacles, new Vector(0,100), new Vector(800,100));
		ScenaryCreator.drawLine(obstacles, new Vector(0,150), new Vector(800,150));

		obstacles.add(new CircularBoid(new Boid(new Vector(100,115), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(200,135), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(300,115), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(400,135), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(500,115), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(600,135), true), Color.BLACK));
		obstacles.add(new CircularBoid(new Boid(new Vector(700,115), true), Color.BLACK));
		
		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(800,250);
	}

}
