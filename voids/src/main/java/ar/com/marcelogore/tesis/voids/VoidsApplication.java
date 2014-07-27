package ar.com.marcelogore.tesis.voids;

import java.lang.reflect.Constructor;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.voids.scenes.Scenario;
import ar.com.marcelogore.tesis.voids.util.StepDataAnalyzer;

public class VoidsApplication extends Application {

	private static final Log log = LogFactory.getLog(VoidsApplication.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

		List<String> params = getParameters().getUnnamed();

		String scenarioClassName = "ar.com.marcelogore.tesis.voids.scenes.PlainScenario";
		Integer numberOfVoids = 10;
		Integer numberOfSteps = 1000;

		try {
			
			scenarioClassName = params.get(0);
			numberOfVoids = Integer.valueOf(params.get(1));
			numberOfSteps = Integer.valueOf(params.get(2));
		
		} catch (Exception e) {
			 log.warn("Invalid parameters. Switching to defaults -> ar.com.marcelogore.tesis.voids.scenes.PlainScenario 10 1000");
		}
		
		final Integer finalNumberOfSteps = numberOfSteps;
		
		@SuppressWarnings("unchecked")
		Constructor<Scenario> constructor = (Constructor<Scenario>) Class.forName(scenarioClassName).getConstructor(Integer.class);
		final Scenario scenario = constructor.newInstance(numberOfVoids);
		
		final Scene scene = scenario.createScene();

		AnimationTimer timer = new AnimationTimer() {
			
			private StepDataAnalyzer stepDataAnalyzer = new StepDataAnalyzer();
			private int step = 0;
			
			@Override
			public void handle(long now) {

				if (step++ == finalNumberOfSteps) {
					System.exit(0);
				}
				
				stepDataAnalyzer.nextTimeStep();
				
				for (CircularVoid circularVoid : scenario.getRepresentedVoids()) {
					
					circularVoid.calculateNewVelocity();

					if (!circularVoid.getVoid().isObstacle()) {
						
						stepDataAnalyzer.addVoidAbsoluteVelocity(circularVoid.getVoid().getVelocity());
						stepDataAnalyzer.addVoidActualVelocity(circularVoid.getVoid().velocityTowardsGoal());
						int obstacleCollisionCount = circularVoid.getVoid().obstacleCollisionCount();
						stepDataAnalyzer.addObstacleCollisionCount(obstacleCollisionCount);
						int voidCollisionCount = circularVoid.getVoid().voidCollisionCount();
						stepDataAnalyzer.addVoidCollisionCount(voidCollisionCount);
						
						if (log.isDebugEnabled()) {
							
							if (obstacleCollisionCount > 0) {
								log.debug(circularVoid.getVoid().getName() + " obstacle collision count is " + obstacleCollisionCount);
							}
							if (voidCollisionCount > 0) {
								log.debug(circularVoid.getVoid().getName() + " void collision count is " + voidCollisionCount);
							}
						}

						if (circularVoid.getVoid().getCrossedGoal()) {
							
							stepDataAnalyzer.increaseGoalReachedCount();
						}
						
						stepDataAnalyzer.nextVoid();
					}
				}

				for (CircularVoid circularVoid : scenario.getRepresentedVoids()) {
					
					if (!circularVoid.getVoid().isObstacle()) {
						circularVoid.update();
					}
				}

				log.info(step + "\t" + 
						 stepDataAnalyzer.calculateAbsoluteVelocity() + "\t" + 
						 stepDataAnalyzer.calculateActualVelocity() + "\t" + 
						 stepDataAnalyzer.calculateCollisionCount() + "\t" + 
						 stepDataAnalyzer.calculateGoalReachedCount());
			}
		};

		timer.start();

		stage.setScene(scene);
		stage.show();
	}

}
