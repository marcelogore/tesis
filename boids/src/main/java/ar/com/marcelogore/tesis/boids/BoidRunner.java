package ar.com.marcelogore.tesis.boids;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class BoidRunner {

	public static void main(String[] args) {
		
		Vector position = new Vector(10,10);
		Vector velocity = new Vector(0,0);
		Boid boid = new Boid(position, velocity);
		
		List<Boid> boids = new ArrayList<Boid>();
		boids.add(boid);
		
		BoidWindow window = new BoidWindow(boids);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
