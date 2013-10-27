package ar.com.marcelogore.tesis.boids;

import java.awt.Canvas;
import java.util.List;

import javax.swing.JFrame;

public class BoidWindow extends JFrame {

	private static final long serialVersionUID = 2963983550510112053L;

	private Canvas boidCanvas;
	
	public BoidWindow(List<Boid> boids) {
		this.setTitle("Boids");
		this.setSize(300, 300);
		this.boidCanvas = new BoidCanvas(boids);
		this.add(boidCanvas);
	}
}
