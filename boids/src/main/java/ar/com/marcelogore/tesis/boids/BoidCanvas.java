package ar.com.marcelogore.tesis.boids;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class BoidCanvas extends Canvas {

	private static final Log log = LogFactory.getLog(BoidCanvas.class);
	private static final long serialVersionUID = -155169107366181485L;

	private List<Boid> boids;
	
	public BoidCanvas(List<Boid> boids) {
		this.boids = boids;
		this.setSize(450, 300);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
	
		for (Boid boid : boids) {
			
			Vector oldPosition = boid.getOldPosition();
			this.setForeground(Color.WHITE);
			g.drawRect((int) oldPosition.x, (int) oldPosition.y, 1, 1);
			this.setForeground(Color.BLACK);
			
			Vector newPosition = boid.getPosition();
			g.drawRect((int) newPosition.x, (int) newPosition.y, 1, 1);
			
			if (log.isDebugEnabled()) {
				
				log.debug("Painting-> Clearing:" + oldPosition + " / Drawing:" + newPosition);
			}
		}
	}
}
