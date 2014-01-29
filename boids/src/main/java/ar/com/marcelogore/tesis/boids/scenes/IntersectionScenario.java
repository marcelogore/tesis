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

public class IntersectionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(IntersectionScenario.class);
	
	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		final Vector goal1 = new Vector(400,800);
		final Vector goal2 = new Vector(800,400);
		
		// Southbound boids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Boid boid = new Boid(new Vector(375 + 14 + 14 * j, 0 + 14 + 14 * i), new Vector());
				boid.setName("SB-Boid" + i + j);
				boid.setGoal(goal1);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedBoids.add(new CircularBoid(boid, Color.RED));
				log.debug("New boid " + boid);
			}
		}

		// Westbound boids
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 10; j++) {
				
				Boid boid = new Boid(new Vector(0 + 14 + 14 * j, 375 + 14 + 14 * i), new Vector());
				boid.setName("WB-Boid" + i + j);
				boid.setGoal(goal2);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);

				representedBoids.add(new CircularBoid(boid, Color.BLUE));
				log.debug("New boid " + boid);
			}
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(0,375), new Vector(375,375));
		ScenaryCreator.drawLine(obstacles, new Vector(375,375), new Vector(375,0));

		ScenaryCreator.drawLine(obstacles, new Vector(425,0), new Vector(425,375));
		ScenaryCreator.drawLine(obstacles, new Vector(425,375), new Vector(800,375));
		
		ScenaryCreator.drawLine(obstacles, new Vector(0,425), new Vector(375,425));
		ScenaryCreator.drawLine(obstacles, new Vector(375,425), new Vector(375,800));

		ScenaryCreator.drawLine(obstacles, new Vector(425,800), new Vector(425,425));
		ScenaryCreator.drawLine(obstacles, new Vector(425,425), new Vector(800,425));

		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);

	}

}
