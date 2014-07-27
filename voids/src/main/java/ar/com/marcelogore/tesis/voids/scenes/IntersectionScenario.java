package ar.com.marcelogore.tesis.voids.scenes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.Void;
import ar.com.marcelogore.tesis.voids.util.ScenaryCreator;
import ar.com.marcelogore.tesis.voids.util.Vector;

public class IntersectionScenario extends Scenario {

	public IntersectionScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {

		List<Void> voids = new ArrayList<Void>();
		
		final Vector goal1a = new Vector(400,180);
		final Vector goal1b = new Vector(400,220);
		final Vector goal2a = new Vector(180,400);
		final Vector goal2b = new Vector(220,400);
		
		final Vector checkpoint1a = new Vector(225,180);
		final Vector checkpoint1b = new Vector(225,220);
		final Vector checkpoint2a = new Vector(180,225);
		final Vector checkpoint2b = new Vector(220,225);

		// Southbound voids
		for (int i = 0; i < this.getNumberOfVoids(); i++) {
			
			Void boid = Void.createRandomVoid(180, 0, 40, 400);
			boid.setName("SB-Void" + i);
			boid.setGoal(goal2a, goal2b);
			boid.setCheckpoint(checkpoint2a, checkpoint2b);

			voids.add(boid);
			boid.setOtherVoids(voids);

			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedVoids.add(new CircularVoid(boid, Color.RED));
		}

		// Westbound voids
		for (int i = 0; i < this.getNumberOfVoids(); i++) {

			Void boid = Void.createRandomVoid(0, 180, 400, 40);
			boid.setName("WB-Void" + i);
			boid.setGoal(goal1a, goal1b);
			boid.setCheckpoint(checkpoint1a, checkpoint1b);

			voids.add(boid);
			boid.setOtherVoids(voids);

			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedVoids.add(new CircularVoid(boid, Color.BLUE));
		}

		List<CircularVoid> obstacles = new LinkedList<CircularVoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(0,175), new Vector(175,175));
		ScenaryCreator.drawLine(obstacles, new Vector(175,175), new Vector(175,0));

		ScenaryCreator.drawLine(obstacles, new Vector(225,0), new Vector(225,175));
		ScenaryCreator.drawLine(obstacles, new Vector(225,175), new Vector(400,175));
		
		ScenaryCreator.drawLine(obstacles, new Vector(0,225), new Vector(175,225));
		ScenaryCreator.drawLine(obstacles, new Vector(175,225), new Vector(175,400));

		ScenaryCreator.drawLine(obstacles, new Vector(225,400), new Vector(225,225));
		ScenaryCreator.drawLine(obstacles, new Vector(225,225), new Vector(400,225));

		representedVoids.addAll(obstacles);
		for (CircularVoid cVoid : obstacles) {
			voids.add(cVoid.getVoid());
		}
		
		final Group group = new Group(representedVoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(400,400);
	}

}
