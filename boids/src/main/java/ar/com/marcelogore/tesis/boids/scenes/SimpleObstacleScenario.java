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

public class SimpleObstacleScenario extends Scenario {

	@Override
	public Scene createScene() {
		
		List<Boid> boids = new ArrayList<Boid>();
		
		final Vector goalA = new Vector(301,140);
		final Vector goalB = new Vector(301,160);
		
		for (int i = 0; i < 50; i++) {
			
			Boid boid = Boid.createRandomBoid(Math.round(getSceneSize().x), Math.round(getSceneSize().y));
			boid.setName("Boid" + i);
			
			// Debug the first boid's behavior
			if (i == 0) {
				boid.setDebugOn();
			}
			
			boid.setGoal(goalA, goalB);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			boids.add(boid);
			boid.setOtherBoids(boids);

			representedBoids.add(new CircularBoid(boid));
		}

		// Obstacles
		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(200,100), new Vector(150,150));
		ScenaryCreator.drawLine(obstacles, new Vector(150,150), new Vector(200,200));
		representedBoids.addAll(obstacles);
		
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}
	
	@Override
	public Vector getSceneSize() {
		return new Vector(300, 300);
	}
}
