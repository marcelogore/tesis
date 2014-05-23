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

public class IntersectionScenario extends Scenario {

	public IntersectionScenario(Integer numberOfBoids) {
		this.setNumberOfBoids(numberOfBoids);
	}
	
	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		final Vector goal1a = new Vector(400,180);
		final Vector goal1b = new Vector(400,220);
		final Vector goal2a = new Vector(180,400);
		final Vector goal2b = new Vector(220,400);
		
		// Southbound boids
		for (int i = 0; i < this.getNumberOfBoids(); i++) {
			
			Boid boid = Boid.createRandomBoid(180, 0, 40, 400);
			boid.setName("SB-Boid" + i);
			boid.setGoal(goal2a, goal2b);

			boids.add(boid);
			boid.setOtherBoids(boids);

			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedBoids.add(new CircularBoid(boid, Color.RED));
		}

		// Westbound boids
		for (int i = 0; i < this.getNumberOfBoids(); i++) {

			Boid boid = Boid.createRandomBoid(0, 180, 400, 40);
			boid.setName("WB-Boid" + i);
			boid.setGoal(goal1a, goal1b);

			boids.add(boid);
			boid.setOtherBoids(boids);

			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedBoids.add(new CircularBoid(boid, Color.BLUE));
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(0,175), new Vector(175,175));
		ScenaryCreator.drawLine(obstacles, new Vector(175,175), new Vector(175,0));

		ScenaryCreator.drawLine(obstacles, new Vector(225,0), new Vector(225,175));
		ScenaryCreator.drawLine(obstacles, new Vector(225,175), new Vector(800,175));
		
		ScenaryCreator.drawLine(obstacles, new Vector(0,225), new Vector(175,225));
		ScenaryCreator.drawLine(obstacles, new Vector(175,225), new Vector(175,800));

		ScenaryCreator.drawLine(obstacles, new Vector(225,800), new Vector(225,225));
		ScenaryCreator.drawLine(obstacles, new Vector(225,225), new Vector(800,225));

		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(400,400);
	}

}
