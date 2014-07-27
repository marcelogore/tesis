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

public class OneLaneWithObstaclesScenario extends Scenario {

	public OneLaneWithObstaclesScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {

		List<Void> voids = new ArrayList<Void>();
		
		// Vuelen a la banda contraria
		final Vector goalA = new Vector(this.getSceneSize().x, 80);
		final Vector goalB = new Vector(this.getSceneSize().x, 120);
		final Vector checkpointA = new Vector(this.getSceneSize().x / 2, 80);
		final Vector checkpointB = new Vector(this.getSceneSize().x / 2, 120);

		for (int j = 0; j < this.getNumberOfVoids(); j++) {
			
			Void boid = Void.createRandomVoid(0, 80, 800, 40);
			boid.setName("Void" + j);
			boid.setGoal(goalA, goalB);
			boid.setCheckpoint(checkpointA, checkpointB);
			
			voids.add(boid);
			boid.setOtherVoids(voids);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedVoids.add(new CircularVoid(boid));
		}

		List<CircularVoid> obstacles = new LinkedList<CircularVoid>();
		
		// Ancho inicial
		ScenaryCreator.drawLine(obstacles, new Vector(0,75), new Vector(800,75));
		ScenaryCreator.drawLine(obstacles, new Vector(0,125), new Vector(800,125));

		obstacles.add(new CircularVoid(new Void(new Vector(400,110), true), Color.BLACK));
		obstacles.add(new CircularVoid(new Void(new Vector(400,115), true), Color.BLACK));
		obstacles.add(new CircularVoid(new Void(new Vector(400,120), true), Color.BLACK));
		
		obstacles.add(new CircularVoid(new Void(new Vector(800,80), true), Color.BLACK));
		obstacles.add(new CircularVoid(new Void(new Vector(800,85), true), Color.BLACK));
		obstacles.add(new CircularVoid(new Void(new Vector(800,90), true), Color.BLACK));
		
		representedVoids.addAll(obstacles);
		for (CircularVoid cVoid : obstacles) {
			voids.add(cVoid.getVoid());
		}
		
		final Group group = new Group(representedVoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(800,200);
	}

}
