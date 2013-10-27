package ar.com.marcelogore.tesis.boids;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class BoidCanvas extends Canvas {

	private static final long serialVersionUID = -155169107366181485L;

	private List<Boid> boids;
	
	public BoidCanvas(List<Boid> boids) {
		this.boids = boids;
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		
		for (Boid boid : boids) {
			
			Vector position = boid.getUpdatedPosition();
			g.drawRect(position.x, position.y, 1, 1);
		}
	}
}
