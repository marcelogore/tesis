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

public class MultipleIntersectionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(MultipleIntersectionScenario.class);
	
	@Override
	public Scene createScene() {

		List<Void> voids = new ArrayList<Void>();
		
		final Vector goal1 = new Vector(265,820);
		final Vector goal2 = new Vector(555,0);
		final Vector goal3 = new Vector(845,820);
		final Vector goal4 = new Vector(1125,0);
		final Vector goal5 = new Vector(1400,265);
		final Vector goal6 = new Vector(0,555);
		
		// West southbound voids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Void boid = new Void(new Vector(240 + 14 + 14 * j, 0 + 14 + 14 * i), new Vector());
				boid.setName("WSB-Void" + i + j);
				boid.setGoal(goal1);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedVoids.add(new CircularVoid(boid, Color.RED));
				log.debug("New void " + boid);
			}
		}

		// Middle-west northbound voids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Void boid = new Void(new Vector(530 + 14 + 14 * j, 820 - 14 - 14 * i), new Vector());
				boid.setName("MWSB-Void" + i + j);
				boid.setGoal(goal2);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedVoids.add(new CircularVoid(boid, Color.ORANGE));
				log.debug("New void " + boid);
			}
		}

		// Middle-east southbound voids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Void boid = new Void(new Vector(820 + 14 + 14 * j, 0 + 14 + 14 * i), new Vector());
				boid.setName("MESB-Void" + i + j);
				boid.setGoal(goal3);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedVoids.add(new CircularVoid(boid, Color.BROWN));
				log.debug("New void " + boid);
			}
		}

		// East northbound voids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Void boid = new Void(new Vector(1110 + 14 + 14 * j, 820 - 14 - 14 * i), new Vector());
				boid.setName("ESB-Void" + i + j);
				boid.setGoal(goal4);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedVoids.add(new CircularVoid(boid, Color.CORAL));
				log.debug("New void " + boid);
			}
		}

		// North eastbound voids
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 10; j++) {
				
				Void boid = new Void(new Vector(0 + 14 + 14 * j, 240 + 14 + 14 * i), new Vector());
				boid.setName("NEB-Void" + i + j);
				boid.setGoal(goal5);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);

				representedVoids.add(new CircularVoid(boid, Color.BLUE));
				log.debug("New void " + boid);
			}
		}

		// South westbound voids
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 10; j++) {
				
				Void boid = new Void(new Vector(1400 - 14 - 14 * j, 530 + 14 + 14 * i), new Vector());
				boid.setName("SEB-Void" + i + j);
				boid.setGoal(goal6);
				
				voids.add(boid);
				boid.setOtherVoids(voids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);

				representedVoids.add(new CircularVoid(boid, Color.LIGHTBLUE));
				log.debug("New void " + boid);
			}
		}

		List<CircularVoid> obstacles = new LinkedList<CircularVoid>();

		ScenaryCreator.drawRectangle(obstacles, new Vector(0,0), new Vector(240,240));
		ScenaryCreator.drawRectangle(obstacles, new Vector(290,0), new Vector(530,240));
		ScenaryCreator.drawRectangle(obstacles, new Vector(580,0), new Vector(820,240));
		ScenaryCreator.drawRectangle(obstacles, new Vector(870,0), new Vector(1110,240));
		ScenaryCreator.drawRectangle(obstacles, new Vector(1160,0), new Vector(1400,240));

		ScenaryCreator.drawRectangle(obstacles, new Vector(0,290), new Vector(240,530));
		ScenaryCreator.drawRectangle(obstacles, new Vector(290,290), new Vector(530,530));
		ScenaryCreator.drawRectangle(obstacles, new Vector(580,290), new Vector(820,530));
		ScenaryCreator.drawRectangle(obstacles, new Vector(870,290), new Vector(1110,530));
		ScenaryCreator.drawRectangle(obstacles, new Vector(1160,290), new Vector(1400,530));

		ScenaryCreator.drawRectangle(obstacles, new Vector(0,580), new Vector(240,820));
		ScenaryCreator.drawRectangle(obstacles, new Vector(290,580), new Vector(530,820));
		ScenaryCreator.drawRectangle(obstacles, new Vector(580,580), new Vector(820,820));
		ScenaryCreator.drawRectangle(obstacles, new Vector(870,580), new Vector(1110,820));
		ScenaryCreator.drawRectangle(obstacles, new Vector(1160,580), new Vector(1400,820));

		representedVoids.addAll(obstacles);
		for (CircularVoid cVoid : obstacles) {
			voids.add(cVoid.getVoid());
		}
		
		final Group group = new Group(representedVoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);

	}
	
	@Override
	public Vector getSceneSize() {
		return new Vector(1400,820);
	}

}
