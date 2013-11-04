package ar.com.marcelogore.tesis.boids;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BoidRunner {

	private static final Log log = LogFactory.getLog(BoidRunner.class);
	
	public static void main(String[] args) throws Exception {
		
		List<Boid> boids = new ArrayList<Boid>();
		
		for (int i = 0; i < 10; i++) {
			
			Boid boid = Boid.createRandomBoid(450, 300);
			boid.setName("Boid" + i);
			boids.add(boid);
			boid.setOtherBoids(boids);
			
			log.debug("New boid " + boid);
		}
		
		BoidWindow window = new BoidWindow(boids);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		while (true) {
			
			for (Boid boid : boids) {

				update(boid);
			}

			window.repaint();

			Thread.sleep(10);
		}
	}
	
	private static void update(Boid boid) {
		
		boid.update();
	}
}
