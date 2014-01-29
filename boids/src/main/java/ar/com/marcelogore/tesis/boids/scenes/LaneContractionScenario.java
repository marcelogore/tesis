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

public class LaneContractionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(LaneContractionScenario.class);
	
	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		// Vuelen a la banda contraria
		final Vector goal = new Vector(this.getSceneSize().x, 177.5);
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 20; j++) {
				
				Boid boid = new Boid(new Vector(10 * j, 110 + 30 * i), new Vector());
				boid.setName("Boid" + i + j);
				boid.setGoal(goal);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);

				representedBoids.add(new CircularBoid(boid));
				log.debug("New boid " + boid);
			}
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		
		// Ancho inicial
		ScenaryCreator.drawLine(obstacles, new Vector(0,80), new Vector(400,80));
		ScenaryCreator.drawLine(obstacles, new Vector(400,80), new Vector(500,125));
		
		// Un carril menos
		ScenaryCreator.drawLine(obstacles, new Vector(500,125), new Vector(900,125));
		ScenaryCreator.drawLine(obstacles, new Vector(900,125), new Vector(1000,155));

		// Un solo carril
		ScenaryCreator.drawLine(obstacles, new Vector(1000,155), new Vector(1400,155));
		
		// La linea de abajo
		ScenaryCreator.drawLine(obstacles, new Vector(0,200), new Vector(1400,200));

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
