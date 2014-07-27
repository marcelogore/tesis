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

public class ShortOneLaneScenario extends Scenario {

	public ShortOneLaneScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {

		List<Void> voids = new ArrayList<Void>();
		
		// Vuelen a la banda contraria
		final Vector goalA = new Vector(this.getSceneSize().x, 10);
		final Vector goalB = new Vector(this.getSceneSize().x, 50);

		for (int j = 0; j < this.getNumberOfVoids(); j++) {
			
			Void boid = Void.createRandomVoid(0, 10, 100, 40);
			boid.setName("Void" + j);
			boid.setGoal(goalA, goalB);
			
			voids.add(boid);
			boid.setOtherVoids(voids);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedVoids.add(new CircularVoid(boid));
		}

		List<CircularVoid> obstacles = new LinkedList<CircularVoid>();
		
		// Ancho inicial
		ScenaryCreator.drawLine(obstacles, new Vector(0,5), new Vector(200,5));
		ScenaryCreator.drawLine(obstacles, new Vector(0,55), new Vector(200,55));
		
		representedVoids.addAll(obstacles);
		for (CircularVoid cVoid : obstacles) {
			voids.add(cVoid.getVoid());
		}
		
		final Group group = new Group(representedVoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(200,60);
	}

}
