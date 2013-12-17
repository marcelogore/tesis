package ar.com.marcelogore.tesis.boids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.scenes.IntersectionScenario;
import ar.com.marcelogore.tesis.boids.scenes.Scenario;

public class BoidsApplication extends Application {

	private static final Log log = LogFactory.getLog(BoidsApplication.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

//		final Scenario scenario =  new SimpleObstacleScenario();
//		final Scenario scenario =  new FunnelScenario();
		final Scenario scenario =  new IntersectionScenario();
		final Scene scene = scenario.createScene();

		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {

				for (CircularBoid circularBoid : scenario.getRepresentedBoids()) {
					
					circularBoid.update();
				}
			}
		};

		timer.start();
		
		stage.setScene(scene);
		stage.show();
	}

}
