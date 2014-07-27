package ar.com.marcelogore.tesis.voids.scenes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.Void;
import ar.com.marcelogore.tesis.voids.util.ScenaryCreator;
import ar.com.marcelogore.tesis.voids.util.Vector;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ObliqueAcuteIntersectionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(ObliqueAcuteIntersectionScenario.class);
	
	public ObliqueAcuteIntersectionScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {

		List<Void> voids = new ArrayList<Void>();
		
		final Vector goal1 = new Vector(800,575);
		final Vector goal2 = new Vector(800,225);
		
		// Southeastbound voids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Void boid = new Void(new Vector(10, 225), new Vector());
				boid.setName("SB-Void" + i + j);
				boid.setGoal(goal1, goal1);
				boid.setCheckpoint(goal1, goal1);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedVoids.add(new CircularVoid(boid, Color.RED));
				log.debug("New void " + boid);
			}
		}

		// Northeastbound voids
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 10; j++) {
				
				Void boid = new Void(new Vector(10, 575), new Vector());
				boid.setName("WB-Void" + i + j);
				boid.setGoal(goal2, goal2);
				boid.setCheckpoint(goal2, goal2);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);

				representedVoids.add(new CircularVoid(boid, Color.BLUE));
				log.debug("New void " + boid);
			}
		}

		List<CircularVoid> obstacles = new LinkedList<CircularVoid>();
		ScenaryCreator.drawLine(obstacles, new Vector(0,200), new Vector(400,375));
		ScenaryCreator.drawLine(obstacles, new Vector(400,375), new Vector(800,200));

		ScenaryCreator.drawLine(obstacles, new Vector(0,250), new Vector(343,400));
		ScenaryCreator.drawLine(obstacles, new Vector(343,400), new Vector(0,550));
		
		ScenaryCreator.drawLine(obstacles, new Vector(800,250), new Vector(457,400));
		ScenaryCreator.drawLine(obstacles, new Vector(457,400), new Vector(800,550));

		ScenaryCreator.drawLine(obstacles, new Vector(0,600), new Vector(400,425));
		ScenaryCreator.drawLine(obstacles, new Vector(400,425), new Vector(800,600));

		representedVoids.addAll(obstacles);
		for (CircularVoid cVoid : obstacles) {
			voids.add(cVoid.getVoid());
		}
		
		final Group group = new Group(representedVoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);

	}

}
