package ar.com.marcelogore.tesis.boids;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.com.marcelogore.tesis.boids.util.Vector;

public class BoidTest {

	private Boid thisBoid;
	private Boid[] otherBoids;
	
	@Before
	public void beforeEachTest() {

		Boid.setRadius(2.0d);
	}
	
	@Test
	public void testGetNearbyBoidsForBoidsForForwardHorizontalVelocity() {
		
		thisBoid = new Boid(new Vector(2,3), new Vector(1,0));
		thisBoid.setViewAngle((3 * Math.PI) / 2.0);
		
		otherBoids = new Boid[11];
		
		otherBoids[0] = new Boid(new Vector(2,4), null);
		otherBoids[1] = new Boid(new Vector(1,5), null);
		otherBoids[2] = new Boid(new Vector(1,3), null);
		otherBoids[3] = new Boid(new Vector(2,2), null);
		otherBoids[4] = new Boid(new Vector(1,1), null);
		otherBoids[5] = new Boid(new Vector(4,1), null);
		otherBoids[6] = new Boid(new Vector(4,5), null);
		otherBoids[7] = new Boid(new Vector(5,3), null);
		otherBoids[8] = new Boid(new Vector(3,3), null);
		otherBoids[9] = new Boid(new Vector(0,4), null);
		otherBoids[10] = new Boid(new Vector(0,2), null);
		
		thisBoid.setOtherBoids(Arrays.asList(otherBoids));
		
		List<Boid> nearbyBoids = thisBoid.getNearbyBoids();
		
		assertTrue("Boid 0 isn't nearby but it should", nearbyBoids.contains(otherBoids[0]));
		assertTrue("Boid 3 isn't nearby but it should", nearbyBoids.contains(otherBoids[3]));
		assertTrue("Boid 8 isn't nearby but it should", nearbyBoids.contains(otherBoids[8]));
		assertFalse("Boid 1 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[1]));
		assertFalse("Boid 2 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[2]));
		assertFalse("Boid 4 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[4]));
		assertFalse("Boid 5 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[5]));
		assertFalse("Boid 6 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[6]));
		assertFalse("Boid 7 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[7]));
		assertFalse("Boid 9 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[9]));
		assertFalse("Boid 10 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[10]));
	}

	@Test
	public void testGetNearbyBoidsForBoidsForUpwardsVelocity() {
		
		thisBoid = new Boid(new Vector(2,3), new Vector(0,1));
		thisBoid.setViewAngle((3 * Math.PI) / 2.0);
		
		otherBoids = new Boid[11];
		
		otherBoids[0] = new Boid(new Vector(2,4), null);
		otherBoids[1] = new Boid(new Vector(1,5), null);
		otherBoids[2] = new Boid(new Vector(1,3), null);
		otherBoids[3] = new Boid(new Vector(2,2), null);
		otherBoids[4] = new Boid(new Vector(1,1), null);
		otherBoids[5] = new Boid(new Vector(4,1), null);
		otherBoids[6] = new Boid(new Vector(4,5), null);
		otherBoids[7] = new Boid(new Vector(5,3), null);
		otherBoids[8] = new Boid(new Vector(3,3), null);
		otherBoids[9] = new Boid(new Vector(0,4), null);
		otherBoids[10] = new Boid(new Vector(0,2), null);
		
		thisBoid.setOtherBoids(Arrays.asList(otherBoids));
		
		List<Boid> nearbyBoids = thisBoid.getNearbyBoids();
		
		assertTrue("Boid 0 isn't nearby but it should", nearbyBoids.contains(otherBoids[0]));
		assertTrue("Boid 2 isn't nearby but it should", nearbyBoids.contains(otherBoids[2]));
		assertTrue("Boid 8 isn't nearby but it should", nearbyBoids.contains(otherBoids[8]));
		assertFalse("Boid 1 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[1]));
		assertFalse("Boid 3 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[3]));
		assertFalse("Boid 4 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[4]));
		assertFalse("Boid 5 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[5]));
		assertFalse("Boid 6 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[6]));
		assertFalse("Boid 7 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[7]));
		assertFalse("Boid 9 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[9]));
		assertFalse("Boid 10 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[10]));
	}


	@Test
	public void testGetNearbyBoidsForBoidsForForwardHorizontalVelocityWith90DegreeAngle() {
		
		thisBoid = new Boid(new Vector(2,3), new Vector(1,0));
		thisBoid.setViewAngle(Math.PI / 2.0);
		
		otherBoids = new Boid[11];
		
		otherBoids[0] = new Boid(new Vector(2,4), null);
		otherBoids[1] = new Boid(new Vector(1,5), null);
		otherBoids[2] = new Boid(new Vector(1,3), null);
		otherBoids[3] = new Boid(new Vector(2,2), null);
		otherBoids[4] = new Boid(new Vector(1,1), null);
		otherBoids[5] = new Boid(new Vector(4,1), null);
		otherBoids[6] = new Boid(new Vector(4,5), null);
		otherBoids[7] = new Boid(new Vector(5,3), null);
		otherBoids[8] = new Boid(new Vector(3,3), null);
		otherBoids[9] = new Boid(new Vector(0,4), null);
		otherBoids[10] = new Boid(new Vector(0,2), null);
		
		thisBoid.setOtherBoids(Arrays.asList(otherBoids));
		
		List<Boid> nearbyBoids = thisBoid.getNearbyBoids();
		
		assertTrue("Boid 8 isn't nearby but it should", nearbyBoids.contains(otherBoids[8]));
		assertFalse("Boid 0 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[0]));
		assertFalse("Boid 1 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[1]));
		assertFalse("Boid 2 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[2]));
		assertFalse("Boid 3 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[3]));
		assertFalse("Boid 4 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[4]));
		assertFalse("Boid 5 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[5]));
		assertFalse("Boid 6 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[6]));
		assertFalse("Boid 7 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[7]));
		assertFalse("Boid 9 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[9]));
		assertFalse("Boid 10 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[10]));
	}

	@Test
	public void testGetNearbyBoidsForBoidsForForwardHorizontalVelocityWith45DegreeAngle() {
		
		thisBoid = new Boid(new Vector(2,3), new Vector(1,0));
		thisBoid.setViewAngle(Math.PI / 4.0);
		
		otherBoids = new Boid[11];
		
		otherBoids[0] = new Boid(new Vector(2,4), null);
		otherBoids[1] = new Boid(new Vector(1,5), null);
		otherBoids[2] = new Boid(new Vector(1,3), null);
		otherBoids[3] = new Boid(new Vector(2,2), null);
		otherBoids[4] = new Boid(new Vector(1,1), null);
		otherBoids[5] = new Boid(new Vector(4,1), null);
		otherBoids[6] = new Boid(new Vector(4,5), null);
		otherBoids[7] = new Boid(new Vector(5,3), null);
		otherBoids[8] = new Boid(new Vector(3,3), null);
		otherBoids[9] = new Boid(new Vector(0,4), null);
		otherBoids[10] = new Boid(new Vector(0,2), null);
		
		thisBoid.setOtherBoids(Arrays.asList(otherBoids));
		
		List<Boid> nearbyBoids = thisBoid.getNearbyBoids();
		
		assertTrue("Boid 8 isn't nearby but it should", nearbyBoids.contains(otherBoids[8]));
		assertFalse("Boid 0 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[0]));
		assertFalse("Boid 1 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[1]));
		assertFalse("Boid 2 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[2]));
		assertFalse("Boid 3 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[3]));
		assertFalse("Boid 4 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[4]));
		assertFalse("Boid 5 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[5]));
		assertFalse("Boid 6 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[6]));
		assertFalse("Boid 7 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[7]));
		assertFalse("Boid 9 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[9]));
		assertFalse("Boid 10 is nearby but it shouldn't", nearbyBoids.contains(otherBoids[10]));
	}
}
