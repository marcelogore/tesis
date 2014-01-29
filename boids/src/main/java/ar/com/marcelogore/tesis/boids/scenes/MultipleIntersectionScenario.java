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

public class MultipleIntersectionScenario extends Scenario {

	private static final Log log = LogFactory.getLog(MultipleIntersectionScenario.class);
	
	@Override
	public Scene createScene() {

		List<Boid> boids = new ArrayList<Boid>();
		
		final Vector goal1 = new Vector(265,820);
		final Vector goal2 = new Vector(555,0);
		final Vector goal3 = new Vector(845,820);
		final Vector goal4 = new Vector(1125,0);
		final Vector goal5 = new Vector(1400,265);
		final Vector goal6 = new Vector(0,555);
		
		// West southbound boids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Boid boid = new Boid(new Vector(240 + 14 + 14 * j, 0 + 14 + 14 * i), new Vector());
				boid.setName("WSB-Boid" + i + j);
				boid.setGoal(goal1);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedBoids.add(new CircularBoid(boid, Color.RED));
				log.debug("New boid " + boid);
			}
		}

		// Middle-west northbound boids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Boid boid = new Boid(new Vector(530 + 14 + 14 * j, 820 - 14 - 14 * i), new Vector());
				boid.setName("MWSB-Boid" + i + j);
				boid.setGoal(goal2);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedBoids.add(new CircularBoid(boid, Color.ORANGE));
				log.debug("New boid " + boid);
			}
		}

		// Middle-east southbound boids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Boid boid = new Boid(new Vector(820 + 14 + 14 * j, 0 + 14 + 14 * i), new Vector());
				boid.setName("MESB-Boid" + i + j);
				boid.setGoal(goal3);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedBoids.add(new CircularBoid(boid, Color.BROWN));
				log.debug("New boid " + boid);
			}
		}

		// East northbound boids
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				Boid boid = new Boid(new Vector(1110 + 14 + 14 * j, 820 - 14 - 14 * i), new Vector());
				boid.setName("ESB-Boid" + i + j);
				boid.setGoal(goal4);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);
				
				representedBoids.add(new CircularBoid(boid, Color.CORAL));
				log.debug("New boid " + boid);
			}
		}

		// North eastbound boids
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 10; j++) {
				
				Boid boid = new Boid(new Vector(0 + 14 + 14 * j, 240 + 14 + 14 * i), new Vector());
				boid.setName("NEB-Boid" + i + j);
				boid.setGoal(goal5);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);

				representedBoids.add(new CircularBoid(boid, Color.BLUE));
				log.debug("New boid " + boid);
			}
		}

		// South westbound boids
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 10; j++) {
				
				Boid boid = new Boid(new Vector(1400 - 14 - 14 * j, 530 + 14 + 14 * i), new Vector());
				boid.setName("SEB-Boid" + i + j);
				boid.setGoal(goal6);
				
				boids.add(boid);
				boid.setOtherBoids(boids);
				
				boid.setMaxX((int) getSceneSize().x);
				boid.setMaxY((int) getSceneSize().y);

				representedBoids.add(new CircularBoid(boid, Color.LIGHTBLUE));
				log.debug("New boid " + boid);
			}
		}

		List<CircularBoid> obstacles = new LinkedList<CircularBoid>();

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

		representedBoids.addAll(obstacles);
		for (CircularBoid cBoid : obstacles) {
			boids.add(cBoid.getBoid());
		}
		
		final Group group = new Group(representedBoids.toArray(new Circle[0]));

		return new Scene(group, getSceneSize().x, getSceneSize().y, Color.WHITE);

	}
	
	@Override
	public Vector getSceneSize() {
		return new Vector(1400,820);
	}

}
