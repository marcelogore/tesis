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
		this.setSize(900, 600);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		
		for (Boid boid : boids) {
			
			Vector oldPosition = boid.getPositionBeforeUpdate();
			this.setForeground(Color.WHITE);
			g.drawRect(oldPosition.x, oldPosition.y, 1, 1);
			this.setForeground(Color.BLACK);
			
			Vector newPosition = boid.getUpdatedPosition();
			g.drawRect(newPosition.x, newPosition.y, 1, 1);
		}
	}
}
