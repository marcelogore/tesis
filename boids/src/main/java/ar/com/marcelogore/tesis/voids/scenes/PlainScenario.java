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

public class PlainScenario extends Scenario {

	public PlainScenario(Integer numberOfVoids) {
		this.setNumberOfVoids(numberOfVoids);
	}
	
	@Override
	public Scene createScene() {
		
		List<Void> voids = new ArrayList<Void>();
		
		final Vector goalA = new Vector(301,0);
		final Vector goalB = new Vector(301,200);
		
		for (int i = 0; i < this.getNumberOfVoids(); i++) {
			
			Void boid = Void.createRandomVoid(Math.round(getSceneSize().x), Math.round(getSceneSize().y));
			boid.setName("Void" + i);
			boid.setGoal(goalA, goalB);
			
			voids.add(boid);
			boid.setOtherVoids(voids);

			boid.setMaxX((int) getSceneSize().x);
			boid.setMaxY((int) getSceneSize().y);

			representedVoids.add(new CircularVoid(boid));
		}

		final Group group = new Group(representedVoids.toArray(new Circle[0]));
		
		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);
	}
	
	@Override
	public Vector getSceneSize() {
		return new Vector(300,200);
	}

}
