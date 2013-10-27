package ar.com.marcelogore.tesis.boids;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class BoidTest {

	@Test
	public void testSetPosition() {

		Vector position = new Vector(0,0);
		Boid boid = new Boid(position, null);
		
		assertSame(position, boid.getUpdatedPosition());
		assertSame(position, boid.getPositionBeforeUpdate());
		
		Vector newPosition = new Vector(2,2);
		boid.setPosition(newPosition);
		assertSame(position, boid.getPositionBeforeUpdate());
		assertSame(newPosition, boid.getUpdatedPosition());
	}

	@Test
	public void testSetVelocity() {

		Vector velocity = new Vector(0,0);
		Boid boid = new Boid(null, velocity);
		
		assertSame(velocity, boid.getUpdatedVelocity());
		assertSame(velocity, boid.getVelocityBeforeUpdate());
		
		Vector newVelocity = new Vector(2,2);
		boid.setVelocity(newVelocity);
		assertSame(velocity, boid.getVelocityBeforeUpdate());
		assertSame(newVelocity, boid.getUpdatedVelocity());
	}

	@Test
	public void testGetNearbyBoids1() {
		
		Vector posBoid1 = new Vector(1,1);
		Vector posBoid2 = new Vector(2,2);
		Vector posBoid3 = new Vector(3,3);
		
		Boid boid1 = new Boid(posBoid1, null);
		Boid boid2 = new Boid(posBoid2, null);
		Boid boid3 = new Boid(posBoid3, null);
		
		List<Boid> otherBoids = new ArrayList<Boid>();
		otherBoids.add(boid1);
		otherBoids.add(boid2);
		otherBoids.add(boid3);
		
		boid1.setOtherBoids(otherBoids);
		
		List<Boid> nearbyBoids = boid1.getNearbyBoids();
		assertTrue(nearbyBoids.contains(boid2));
		assertFalse(nearbyBoids.contains(boid1));
		assertFalse(nearbyBoids.contains(boid3));
	}

	@Test
	public void testGetNearbyBoids2() {
		
		Vector posBoid1 = new Vector(1,1);
		Vector posBoid2 = new Vector(2,2);
		Vector posBoid3 = new Vector(3,3);
		
		Boid boid1 = new Boid(posBoid1, null);
		Boid boid2 = new Boid(posBoid2, null);
		Boid boid3 = new Boid(posBoid3, null);
		
		List<Boid> otherBoids = new ArrayList<Boid>();
		otherBoids.add(boid1);
		otherBoids.add(boid2);
		otherBoids.add(boid3);
		
		boid2.setOtherBoids(otherBoids);
		
		List<Boid> nearbyBoids = boid2.getNearbyBoids();
		assertFalse(nearbyBoids.contains(boid2));
		assertTrue(nearbyBoids.contains(boid1));
		assertTrue(nearbyBoids.contains(boid3));
	}
}
