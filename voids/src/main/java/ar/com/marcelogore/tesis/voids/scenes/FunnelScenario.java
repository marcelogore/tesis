package ar.com.marcelogore.tesis.voids.scenes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.Void;
import ar.com.marcelogore.tesis.voids.util.ScenaryCreator;
import ar.com.marcelogore.tesis.voids.util.Vector;

public class FunnelScenario extends Scenario {

	private static final Log log = LogFactory.getLog(FunnelScenario.class);

	public FunnelScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {

		List<Void> voids = new ArrayList<Void>();
		
		// Vuelen a la esquina contraria
		final Vector goalA = new Vector(1180,820);
		final Vector goalB = new Vector(1220,780);
		
		for (int i = 0; i < this.getNumberOfVoids(); i++) {
			
			Void boid = Void.createRandomVoid(300, 300);
			boid.setName("Void" + i);
			boid.setGoal(goalA, goalB);
			boid.setCheckpoint(goalA, goalB);
			
			voids.add(boid);
			boid.setOtherVoids(voids);
			
			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedVoids.add(new CircularVoid(boid));
			log.debug("New void " + boid);
		}

		List<CircularVoid> obstacles = new LinkedList<CircularVoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(580,350), new Vector(1200,780));
		ScenaryCreator.drawLine(obstacles, new Vector(520,400), new Vector(1180,800));
		ScenaryCreator.drawLine(obstacles, new Vector(0,400), new Vector(520,400));
		ScenaryCreator.drawLine(obstacles, new Vector(400,0), new Vector(580,350));
		
		representedVoids.addAll(obstacles);
		for (CircularVoid cVoid : obstacles) {
			voids.add(cVoid.getVoid());
		}
		
		final Group group = new Group(representedVoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}

	@Override
	public Vector getSceneSize() {
		return new Vector(1200,800);
	}
}
