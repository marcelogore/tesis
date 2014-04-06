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

public class GoalTestScenario extends Scenario {

//	private static final Log log = LogFactory.getLog(GoalTestScenario.class);
	
	@Override
	public Scene createScene() {
		
		List<Boid> boids = new ArrayList<Boid>();
		
		// Vuelen a la esquina contraria
		final Vector goalA = new Vector(1000,800);
		final Vector goalB = new Vector(1200,500);
		
		for (int i = 0; i < 3; i++) {
			
			Boid boid = new Boid(new Vector(i * 100, 0), new Vector());
			boid.setName("BoidX" + i);
			boid.setGoal(goalA, goalB);
			
			boids.add(boid);
			boid.setOtherBoids(boids);

			representedBoids.add(new CircularBoid(boid));
		}

		for (int i = 1; i <= 4; i++) {
			
			Boid boid = new Boid(new Vector(0, i * 100), new Vector());
			boid.setName("BoidY" + i);
			boid.setGoal(goalA, goalB);
			
			boids.add(boid);
			boid.setOtherBoids(boids);

			representedBoids.add(new CircularBoid(boid));
		}

		final Group group = new Group(representedBoids.toArray(new Circle[0]));
		
		return new Scene(group, 1200, 800, Color.WHITE);
	}
}
