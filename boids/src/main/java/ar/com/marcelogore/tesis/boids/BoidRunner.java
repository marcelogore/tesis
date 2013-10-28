package ar.com.marcelogore.tesis.boids;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class BoidRunner {

	public static void main(String[] args) throws Exception {
		
		List<Boid> boids = new ArrayList<Boid>();
		
		for (int i = 0; i < 1000; i++) {
			
			Boid boid = Boid.createRandomBoid(900, 600);
			boids.add(boid);

			System.out.println(boid);
		}
		
		BoidWindow window = new BoidWindow(boids);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		while (true) {
			
			for (Boid boid : boids) {
				
				boid.setPosition(Vector.add(boid.getPositionBeforeUpdate(), boid.getVelocityBeforeUpdate()));
			}

			window.repaint();

			Thread.sleep(10);
		}
	}
}
