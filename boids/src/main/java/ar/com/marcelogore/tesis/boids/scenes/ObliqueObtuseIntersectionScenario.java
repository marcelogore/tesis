package ar.com.marcelogore.tesis.boids.scenes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.Boid;
import ar.com.marcelogore.tesis.boids.CircularBoid;
import ar.com.marcelogore.tesis.boids.util.ScenaryCreator;
import ar.com.marcelogore.tesis.boids.util.Vector;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ObliqueObtuseIntersectionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(ObliqueObtuseIntersectionScenario.class);
	
	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		final Vector goal1 = new Vector(800,575);
		final Vector goal2 = new Vector(0,575);
		
		// Southeastbound boids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Boid boid = new Boid(new Vector(10, 225), new Vector());
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

		// Southwestbound boids
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 10; j++) {
				
				Boid boid = new Boid(new Vector(790, 225), new Vector());
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
		ScenaryCreator.drawLine(obstacles, new Vector(0,200), new Vector(400,375));
		ScenaryCreator.drawLine(obstacles, new Vector(400,375), new Vector(800,200));

		ScenaryCreator.drawLine(obstacles, new Vector(0,250), new Vector(343,400));
		ScenaryCreator.drawLine(obstacles, new Vector(343,400), new Vector(0,550));
		
		ScenaryCreator.drawLine(obstacles, new Vector(800,250), new Vector(457,400));
		ScenaryCreator.drawLine(obstacles, new Vector(457,400), new Vector(800,550));

		ScenaryCreator.drawLine(obstacles, new Vector(0,600), new Vector(400,425));
		ScenaryCreator.drawLine(obstacles, new Vector(400,425), new Vector(800,600));

		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);

	}

}
