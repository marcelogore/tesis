package ar.com.marcelogore.tesis.boids.scenes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.Boid;
import ar.com.marcelogore.tesis.boids.CircularBoid;
import ar.com.marcelogore.tesis.boids.util.ScenaryCreator;
import ar.com.marcelogore.tesis.boids.util.Vector;

public class OneLaneScenario extends Scenario {

	private static final Log log = LogFactory.getLog(OneLaneScenario.class);
	
	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		// Vuelen a la banda contraria
		final Vector goal = new Vector(this.getSceneSize().x, 177.5);
		
		for (int j = 0; j < 100; j++) {
			
			Boid boid = new Boid(new Vector(10 * j, 200), new Vector());
			boid.setName("Boid" + j);
			boid.setGoal(goal);
			
			boids.add(boid);
			boid.setOtherBoids(boids);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedBoids.add(new CircularBoid(boid));
			log.debug("New boid " + boid);
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		
		// Ancho inicial
		ScenaryCreator.drawLine(obstacles, new Vector(0,185), new Vector(1400,185));
		ScenaryCreator.drawLine(obstacles, new Vector(0,215), new Vector(1400,215));
		
		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(1400,400);
	}

}
