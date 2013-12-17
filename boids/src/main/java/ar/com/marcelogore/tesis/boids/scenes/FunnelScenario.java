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

public class FunnelScenario extends Scenario {

	private static final Log log = LogFactory.getLog(FunnelScenario.class);

	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		// Vuelen a la esquina contraria
		final Vector goal = new Vector(1200,800);
		
		for (int i = 0; i < 50; i++) {
			
			Boid boid = Boid.createRandomBoid(300, 300);
			boid.setName("Boid" + i);
			boid.setGoal(goal);
			
			boids.add(boid);
			boid.setOtherBoids(boids);

			representedBoids.add(new CircularBoid(boid));
			log.debug("New boid " + boid);
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(580,350), new Vector(1200,780));
		ScenaryCreator.drawLine(obstacles, new Vector(520,400), new Vector(1180,800));
		ScenaryCreator.drawLine(obstacles, new Vector(0,400), new Vector(520,400));
		
		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, 1200, 800, Color.WHITE);
	}

}
