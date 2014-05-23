package ar.com.marcelogore.tesis.boids;

import java.lang.reflect.Constructor;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.scenes.Scenario;
import ar.com.marcelogore.tesis.boids.util.StepDataAnalyzer;

public class BoidsApplication extends Application {

	private static final Log log = LogFactory.getLog(BoidsApplication.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {

		List<String> params = getParameters().getUnnamed();

		String scenarioClassName = "ar.com.marcelogore.tesis.boids.scenes.PlainScenario";
		Integer numberOfBoids = 10;
		Integer numberOfSteps = 1000;

		try {
			
			scenarioClassName = params.get(0);
			numberOfBoids = Integer.valueOf(params.get(1));
			numberOfSteps = Integer.valueOf(params.get(2));
		
		} catch (Exception e) {
			 log.warn("Invalid parameters. Switching to defaults -> ar.com.marcelogore.tesis.boids.scenes.PlainScenario 10 1000");
		}
		
		final Integer finalNumberOfSteps = numberOfSteps;
		
//		final Scenario scenario = new PlainScenario();
//		final Scenario scenario = new SimpleObstacleScenario();
//		final Scenario scenario = new FunnelScenario();
//		final Scenario scenario = new ObliqueAcuteIntersectionScenario();
//		final Scenario scenario = new ObliqueObtuseIntersectionScenario();
//		final Scenario scenario = new MultipleIntersectionScenario();
//		final Scenario scenario = new LaneContractionScenario();
//		final Scenario scenario = new IntersectionCollisionScenario();
//		final Scenario scenario = new FrontCollisionScenario();
//		final Scenario scenario = new GoalTestScenario();

//		final Scenario scenario = new OneLaneScenario(10);
//		final Scenario scenario = new OneLaneWithObstaclesScenario(10);
//		final Scenario scenario = new IntersectionScenario(10);
		
		@SuppressWarnings("unchecked")
		Constructor<Scenario> constructor = (Constructor<Scenario>) Class.forName(scenarioClassName).getConstructor(Integer.class);
		final Scenario scenario = constructor.newInstance(numberOfBoids);
		
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
				
				for (CircularBoid circularBoid : scenario.getRepresentedBoids()) {
					
					circularBoid.calculateNewVelocity();

					if (!circularBoid.getBoid().isObstacle()) {
						
						stepDataAnalyzer.addBoidAbsoluteVelocity(circularBoid.getBoid().getVelocity());
						stepDataAnalyzer.addBoidActualVelocity(circularBoid.getBoid().velocityTowardsGoal());
						int obstacleCollisionCount = circularBoid.getBoid().obstacleCollisionCount();
						stepDataAnalyzer.addObstacleCollisionCount(obstacleCollisionCount);
						int boidCollisionCount = circularBoid.getBoid().boidCollisionCount();
						stepDataAnalyzer.addBoidCollisionCount(boidCollisionCount);
						
						if (log.isDebugEnabled()) {
							
							if (obstacleCollisionCount > 0) {
								log.debug(circularBoid.getBoid().getName() + " obstacle collision count is " + obstacleCollisionCount);
							}
							if (boidCollisionCount > 0) {
								log.debug(circularBoid.getBoid().getName() + " boid collision count is " + boidCollisionCount);
							}
						}

						if (circularBoid.getBoid().getCalculateFlow() && circularBoid.getBoid().getCrossedGoal()) {
							
							stepDataAnalyzer.increaseGoalReachedCount();
						}
						
						stepDataAnalyzer.nextBoid();
					}
				}

				for (CircularBoid circularBoid : scenario.getRepresentedBoids()) {
					
					if (!circularBoid.getBoid().isObstacle()) {
						circularBoid.update();
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
