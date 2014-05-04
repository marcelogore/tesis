package ar.com.marcelogore.tesis.boids.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ar.com.marcelogore.tesis.boids.Boid;
import ar.com.marcelogore.tesis.boids.CircularBoid;
import ar.com.marcelogore.tesis.boids.util.Vector;

public class PlainScenario extends Scenario {

	public PlainScenario(Integer numberOfBoids) {
		this.setNumberOfBoids(numberOfBoids);
	}
	
	@Override
	public Scene createScene() {
		
		List<Boid> boids = new ArrayList<Boid>();
		
		final Vector goalA = new Vector(301,0);
		final Vector goalB = new Vector(301,200);
		
		for (int i = 0; i < this.getNumberOfBoids(); i++) {
			
			Boid boid = Boid.createRandomBoid(Math.round(getSceneSize().x), Math.round(getSceneSize().y));
			boid.setName("Boid" + i);
			boid.setGoal(goalA, goalB);
			
			boids.add(boid);
			boid.setOtherBoids(boids);

			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedBoids.add(new CircularBoid(boid));
		}

		final Group group = new Group(representedBoids.toArray(new Circle[0]));
		
		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}
	
	@Override
	public Vector getSceneSize() {
		return new Vector(300,200);
	}

}
