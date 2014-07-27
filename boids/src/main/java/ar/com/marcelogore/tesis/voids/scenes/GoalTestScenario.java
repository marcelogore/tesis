package ar.com.marcelogore.tesis.voids.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ar.com.marcelogore.tesis.voids.CircularVoid;
import ar.com.marcelogore.tesis.voids.Void;
import ar.com.marcelogore.tesis.voids.util.Vector;

public class GoalTestScenario extends Scenario {

//	private static final Log log = LogFactory.getLog(GoalTestScenario.class);
	
	public GoalTestScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {
		
		List<Void> voids = new ArrayList<Void>();
		
		// Vuelen a la esquina contraria
		final Vector goalA = new Vector(1000,800);
		final Vector goalB = new Vector(1200,500);
		
		for (int i = 0; i < 3; i++) {
			
			Void boid = new Void(new Vector(i * 100, 0), new Vector());
			boid.setName("VoidX" + i);
			boid.setGoal(goalA, goalB);
			boid.setCheckpoint(goalA, goalB);
			
			voids.add(boid);
			boid.setOtherVoids(voids);

			representedVoids.add(new CircularVoid(boid));
		}

		for (int i = 1; i <= 4; i++) {
			
			Void boid = new Void(new Vector(0, i * 100), new Vector());
			boid.setName("VoidY" + i);
			boid.setGoal(goalA, goalB);
			boid.setCheckpoint(goalA, goalB);
			
			voids.add(boid);
			boid.setOtherVoids(voids);

			representedVoids.add(new CircularVoid(boid));
		}

		final Group group = new Group(representedVoids.toArray(new Circle[0]));
		
		return new Scene(group, 1200, 800, Color.WHITE);
	}
}
