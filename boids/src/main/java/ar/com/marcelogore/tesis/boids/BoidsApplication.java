package ar.com.marcelogore.tesis.boids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.scenes.*;
import ar.com.marcelogore.tesis.boids.util.StepDataAnalyzer;

public class BoidsApplication extends Application {

	private static final Log log = LogFactory.getLog(BoidsApplication.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

//		log.info("Running Boids");
		
//		final Scenario scenario = new PlainScenario();
//		final Scenario scenario = new SimpleObstacleScenario();
//		final Scenario scenario = new FunnelScenario();
//		final Scenario scenario = new IntersectionScenario();
		final Scenario scenario = new ObliqueAcuteIntersectionScenario();
//		final Scenario scenario = new ObliqueObtuseIntersectionScenario();
//		final Scenario scenario = new MultipleIntersectionScenario();
//		final Scenario scenario = new LaneContractionScenario();
//		final Scenario scenario = new IntersectionCollisionScenario();
//		final Scenario scenario = new FrontCollisionScenario();
//		final Scenario scenario = new OneLaneScenario();
//		final Scenario scenario = new OneLaneWithObstaclesScenario();
//		final Scenario scenario = new GoalTestScenario();
		
		final Scene scene = scenario.createScene();

		AnimationTimer timer = new AnimationTimer() {
			
			private StepDataAnalyzer stepDataAnalyzer;
			private int step = 1;
			
			@Override
			public void handle(long now) {

				if (step++ == 1000) {
					System.exit(0);
				}
				
				stepDataAnalyzer = new StepDataAnalyzer();
				
				for (CircularBoid circularBoid : scenario.getRepresentedBoids()) {
					
					circularBoid.calculateNewVelocity();

					if (!circularBoid.getBoid().isObstacle()) {
						
						stepDataAnalyzer.addBoidAbsoluteVelocity(circularBoid.getBoid().getVelocity());
						stepDataAnalyzer.addBoidActualVelocity(circularBoid.getBoid().velocityTowardsGoal());
					}
				}

//				System.out.println(stepDataAnalyzer.calculateActualVelocity());

				for (CircularBoid circularBoid : scenario.getRepresentedBoids()) {
					
					if (!circularBoid.getBoid().isObstacle()) {
						circularBoid.update();
					}
				}
				
//				log.info("Average boid absolute velocity is " + stepDataAnalyzer.calculateAbsoluteVelocity());
//				log.info("Average boid actual velocity is " + stepDataAnalyzer.calculateActualVelocity());
			}
		};

		timer.start();

		stage.setScene(scene);
		stage.show();
	}

}
