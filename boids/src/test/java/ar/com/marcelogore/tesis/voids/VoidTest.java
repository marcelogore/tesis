package ar.com.marcelogore.tesis.voids;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.com.marcelogore.tesis.voids.Void;
import ar.com.marcelogore.tesis.voids.util.Vector;

public class VoidTest {

	private Void thisVoid;
	private Void[] otherVoids;
	
	@Before
	public void beforeEachTest() {

		Void.setRadius(2.0d);
	}
	
	@Test
	public void testGetNearbyVoidsForVoidsForForwardHorizontalVelocity() {
		
		thisVoid = new Void(new Vector(2,3), new Vector(1,0));
		thisVoid.setViewAngle((3 * Math.PI) / 2.0);
		
		otherVoids = new Void[11];
		
		otherVoids[0] = new Void(new Vector(2,4), null);
		otherVoids[1] = new Void(new Vector(1,5), null);
		otherVoids[2] = new Void(new Vector(1,3), null);
		otherVoids[3] = new Void(new Vector(2,2), null);
		otherVoids[4] = new Void(new Vector(1,1), null);
		otherVoids[5] = new Void(new Vector(4,1), null);
		otherVoids[6] = new Void(new Vector(4,5), null);
		otherVoids[7] = new Void(new Vector(5,3), null);
		otherVoids[8] = new Void(new Vector(3,3), null);
		otherVoids[9] = new Void(new Vector(0,4), null);
		otherVoids[10] = new Void(new Vector(0,2), null);
		
		thisVoid.setOtherVoids(Arrays.asList(otherVoids));
		
		List<Void> nearbyVoids = thisVoid.getNearbyVoids();
		
		assertTrue("Void 0 isn't nearby but it should", nearbyVoids.contains(otherVoids[0]));
		assertTrue("Void 3 isn't nearby but it should", nearbyVoids.contains(otherVoids[3]));
		assertTrue("Void 8 isn't nearby but it should", nearbyVoids.contains(otherVoids[8]));
		assertFalse("Void 1 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[1]));
		assertFalse("Void 2 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[2]));
		assertFalse("Void 4 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[4]));
		assertFalse("Void 5 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[5]));
		assertFalse("Void 6 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[6]));
		assertFalse("Void 7 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[7]));
		assertFalse("Void 9 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[9]));
		assertFalse("Void 10 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[10]));
	}

	@Test
	public void testGetNearbyVoidsForVoidsForUpwardsVelocity() {
		
		thisVoid = new Void(new Vector(2,3), new Vector(0,1));
		thisVoid.setViewAngle((3 * Math.PI) / 2.0);
		
		otherVoids = new Void[11];
		
		otherVoids[0] = new Void(new Vector(2,4), null);
		otherVoids[1] = new Void(new Vector(1,5), null);
		otherVoids[2] = new Void(new Vector(1,3), null);
		otherVoids[3] = new Void(new Vector(2,2), null);
		otherVoids[4] = new Void(new Vector(1,1), null);
		otherVoids[5] = new Void(new Vector(4,1), null);
		otherVoids[6] = new Void(new Vector(4,5), null);
		otherVoids[7] = new Void(new Vector(5,3), null);
		otherVoids[8] = new Void(new Vector(3,3), null);
		otherVoids[9] = new Void(new Vector(0,4), null);
		otherVoids[10] = new Void(new Vector(0,2), null);
		
		thisVoid.setOtherVoids(Arrays.asList(otherVoids));
		
		List<Void> nearbyVoids = thisVoid.getNearbyVoids();
		
		assertTrue("Void 0 isn't nearby but it should", nearbyVoids.contains(otherVoids[0]));
		assertTrue("Void 2 isn't nearby but it should", nearbyVoids.contains(otherVoids[2]));
		assertTrue("Void 8 isn't nearby but it should", nearbyVoids.contains(otherVoids[8]));
		assertFalse("Void 1 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[1]));
		assertFalse("Void 3 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[3]));
		assertFalse("Void 4 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[4]));
		assertFalse("Void 5 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[5]));
		assertFalse("Void 6 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[6]));
		assertFalse("Void 7 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[7]));
		assertFalse("Void 9 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[9]));
		assertFalse("Void 10 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[10]));
	}


	@Test
	public void testGetNearbyVoidsForVoidsForForwardHorizontalVelocityWith90DegreeAngle() {
		
		thisVoid = new Void(new Vector(2,3), new Vector(1,0));
		thisVoid.setViewAngle(Math.PI / 2.0);
		
		otherVoids = new Void[11];
		
		otherVoids[0] = new Void(new Vector(2,4), null);
		otherVoids[1] = new Void(new Vector(1,5), null);
		otherVoids[2] = new Void(new Vector(1,3), null);
		otherVoids[3] = new Void(new Vector(2,2), null);
		otherVoids[4] = new Void(new Vector(1,1), null);
		otherVoids[5] = new Void(new Vector(4,1), null);
		otherVoids[6] = new Void(new Vector(4,5), null);
		otherVoids[7] = new Void(new Vector(5,3), null);
		otherVoids[8] = new Void(new Vector(3,3), null);
		otherVoids[9] = new Void(new Vector(0,4), null);
		otherVoids[10] = new Void(new Vector(0,2), null);
		
		thisVoid.setOtherVoids(Arrays.asList(otherVoids));
		
		List<Void> nearbyVoids = thisVoid.getNearbyVoids();
		
		assertTrue("Void 8 isn't nearby but it should", nearbyVoids.contains(otherVoids[8]));
		assertFalse("Void 0 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[0]));
		assertFalse("Void 1 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[1]));
		assertFalse("Void 2 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[2]));
		assertFalse("Void 3 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[3]));
		assertFalse("Void 4 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[4]));
		assertFalse("Void 5 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[5]));
		assertFalse("Void 6 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[6]));
		assertFalse("Void 7 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[7]));
		assertFalse("Void 9 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[9]));
		assertFalse("Void 10 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[10]));
	}

	@Test
	public void testGetNearbyVoidsForVoidsForForwardHorizontalVelocityWith45DegreeAngle() {
		
		thisVoid = new Void(new Vector(2,3), new Vector(1,0));
		thisVoid.setViewAngle(Math.PI / 4.0);
		
		otherVoids = new Void[11];
		
		otherVoids[0] = new Void(new Vector(2,4), null);
		otherVoids[1] = new Void(new Vector(1,5), null);
		otherVoids[2] = new Void(new Vector(1,3), null);
		otherVoids[3] = new Void(new Vector(2,2), null);
		otherVoids[4] = new Void(new Vector(1,1), null);
		otherVoids[5] = new Void(new Vector(4,1), null);
		otherVoids[6] = new Void(new Vector(4,5), null);
		otherVoids[7] = new Void(new Vector(5,3), null);
		otherVoids[8] = new Void(new Vector(3,3), null);
		otherVoids[9] = new Void(new Vector(0,4), null);
		otherVoids[10] = new Void(new Vector(0,2), null);
		
		thisVoid.setOtherVoids(Arrays.asList(otherVoids));
		
		List<Void> nearbyVoids = thisVoid.getNearbyVoids();
		
		assertTrue("Void 8 isn't nearby but it should", nearbyVoids.contains(otherVoids[8]));
		assertFalse("Void 0 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[0]));
		assertFalse("Void 1 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[1]));
		assertFalse("Void 2 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[2]));
		assertFalse("Void 3 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[3]));
		assertFalse("Void 4 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[4]));
		assertFalse("Void 5 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[5]));
		assertFalse("Void 6 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[6]));
		assertFalse("Void 7 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[7]));
		assertFalse("Void 9 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[9]));
		assertFalse("Void 10 is nearby but it shouldn't", nearbyVoids.contains(otherVoids[10]));
	}
}
