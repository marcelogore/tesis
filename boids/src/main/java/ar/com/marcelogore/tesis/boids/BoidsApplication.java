package ar.com.marcelogore.tesis.boids;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class BoidsApplication extends Application {

	private static final Log log = LogFactory.getLog(BoidsApplication.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

		List<Boid> boids = new ArrayList<Boid>();
		final List<CircularBoid> representedBoids = new LinkedList<CircularBoid>();
		
		// Vuelen a la esquina contraria
		final Vector goal = new Vector(1200,800);
		
		for (int i = 0; i < 50; i++) {
			
			Boid boid = Boid.createRandomBoid(300, 300);
			boid.setName("Boid" + i);
			boid.setGoal(goal);
			
			boids.add(boid);
			boid.setOtherBoids(boids);

			representedBoids.add(new CircularBoid(boid));
			log.debug("New boid " + boid);
		}

		final Group group = new Group(representedBoids.toArray(new Circle[0]));
		final Scene scene = new Scene(group, 1200, 800, Color.WHITE);

		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {

				for (CircularBoid circularBoid : representedBoids) {
					
					circularBoid.update();
				}
			}
		};
		
		timer.start();
		
		stage.setScene(scene);
		stage.show();
	}

}
