package ar.com.marcelogore.tesis.voids.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.Void;
import ar.com.marcelogore.tesis.voids.util.Vector;

public class FrontCollisionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(FrontCollisionScenario.class);
	
	public FrontCollisionScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {
		
		log.info("Front collision");
		
		List<Void> voids = new ArrayList<Void>();
		
		final Vector goal1a = new Vector(400,200);
		final Vector goal1b = new Vector(400,200);
		final Vector goal2a = new Vector(0,200);
		final Vector goal2b = new Vector(0,200);
		
		Void boid1 = new Void(new Vector(0,200), new Vector());
		boid1.setName("Void1");
		boid1.setGoal(goal1a, goal1b);
		boid1.setMaxX((int) this.getSceneSize().x);
		boid1.setMaxY((int) this.getSceneSize().y);
		boid1.setViewAngle(Math.PI /2.0);
		boid1.setCheckpoint(goal1a, goal1b);

		Void boid2 = new Void(new Vector(400,200), new Vector());
		boid2.setName("Void2");
		boid2.setGoal(goal2a, goal2b);
		boid2.setMaxX((int) this.getSceneSize().x);
		boid2.setMaxY((int) this.getSceneSize().y);
		boid2.setViewAngle(Math.PI /2.0);
		boid2.setCheckpoint(goal2a, goal2b);

		voids.add(boid1);
		voids.add(boid2);
		
		boid1.setOtherVoids(voids);
		boid2.setOtherVoids(voids);

		representedVoids.add(new CircularVoid(boid1));
		representedVoids.add(new CircularVoid(boid2));

		final Group group = new Group(representedVoids.toArray(new Circle[0]));
		
		return new Scene(group, 400, 400, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(400,400);
	}

}
