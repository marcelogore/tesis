package ar.com.marcelogore.tesis.boids.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

	@Test
	public void testAdd() {

		Vector a = new Vector(2,3);
		Vector b = new Vector(7,8);
		
		Vector c = Vector.add(a, b);
		
		assertEquals(9, c.x, 0.001);
		assertEquals(11, c.y, 0.001);
		
		assertEquals(7, b.x, 0.001);
		assertEquals(8, b.y, 0.001);
	}

	@Test
	public void testSubtract() {

		Vector a = new Vector(2,3);
		Vector b = new Vector(7,8);
		
		Vector c = Vector.subtract(a, b);
		
		assertEquals(-5, c.x, 0.001);
		assertEquals(-5, c.y, 0.001);
	}
	
	@Test
	public void testMultiply() {

		Vector a = new Vector(2,3);
		
		a.multiply(10);
		
		assertEquals(20, a.x, 0.001);
		assertEquals(30, a.y, 0.001);
	}

	@Test
	public void testDivide() {

		Vector a = new Vector(2,3);
		
		a.divide(2);
		
		assertEquals(1, a.x, 0.001);
		assertEquals(1.5, a.y, 0.001);
	}
	
	@Test
	public void testLength() {

		Vector a = new Vector(2,3);
		
		assertEquals(3.6055, a.length(), 0.0001);
	}

	@Test
	public void testCopy() {
		
		Vector a = new Vector(4,7);
		Vector b = new Vector(1,1);
		
		b.copy(a);
		
		assertEquals(b.x, a.x, 0.001);
		assertEquals(b.y, a.y, 0.001);
		
		assertNotSame(a, b);
	}
}
