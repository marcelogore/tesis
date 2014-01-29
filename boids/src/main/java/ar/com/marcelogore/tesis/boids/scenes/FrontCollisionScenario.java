package ar.com.marcelogore.tesis.boids.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.Boid;
import ar.com.marcelogore.tesis.boids.CircularBoid;
import ar.com.marcelogore.tesis.boids.util.Vector;

public class FrontCollisionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(FrontCollisionScenario.class);
	
	@Override
	public Scene createScene() {
		
		List<Boid> boids = new ArrayList<Boid>();
		
		final Vector goal1 = new Vector(400,201);
		final Vector goal2 = new Vector(0,200);
		
		Boid boid1 = new Boid(new Vector(0,201), new Vector());
		boid1.setName("Boid1");
		boid1.setGoal(goal1);
		boid1.setMaxX((int) this.getSceneSize().x);
		boid1.setMaxY((int) this.getSceneSize().y);
		

		Boid boid2 = new Boid(new Vector(400,200), new Vector());
		boid2.setName("Boid2");
		boid2.setGoal(goal2);
		boid2.setMaxX((int) this.getSceneSize().x);
		boid2.setMaxY((int) this.getSceneSize().y);

		boids.add(boid1);
		boids.add(boid2);
		
		boid1.setOtherBoids(boids);
		boid2.setOtherBoids(boids);

		representedBoids.add(new CircularBoid(boid1));
		representedBoids.add(new CircularBoid(boid2));

		final Group group = new Group(representedBoids.toArray(new Circle[0]));
		
		return new Scene(group, 400, 400, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(400,400);
	}

}
