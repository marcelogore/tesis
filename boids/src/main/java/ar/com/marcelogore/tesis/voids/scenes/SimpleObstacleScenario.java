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

public class SimpleObstacleScenario extends Scenario {

	@Override
	public Scene createScene() {
		
		List<Void> voids = new ArrayList<Void>();
		
		final Vector goalA = new Vector(301,140);
		final Vector goalB = new Vector(301,160);
		
		for (int i = 0; i < 50; i++) {
			
			Void boid = Void.createRandomVoid(Math.round(getSceneSize().x), Math.round(getSceneSize().y));
			boid.setName("Void" + i);
			
			// Debug the first boid's behavior
			if (i == 0) {
				boid.setDebugOn();
			}
			
			boid.setGoal(goalA, goalB);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			voids.add(boid);
			boid.setOtherVoids(voids);

			representedVoids.add(new CircularVoid(boid));
		}

		// Obstacles
		List<CircularVoid> obstacles = new LinkedList<CircularVoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(200,100), new Vector(150,150));
		ScenaryCreator.drawLine(obstacles, new Vector(150,150), new Vector(200,200));
		representedVoids.addAll(obstacles);
		
		for (CircularVoid cVoid : obstacles) {
			voids.add(cVoid.getVoid());
		}
		
		final Group group = new Group(representedVoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}
	
	@Override
	public Vector getSceneSize() {
		return new Vector(300, 300);
	}
}
