package ar.com.marcelogore.tesis.voids.util;

import org.junit.Test;

import ar.com.marcelogore.tesis.voids.util.Vector;
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
	
	@Test
	public void testNormalizeNonZeroVector() {
		
		Vector vector = new Vector(35,18);
		vector = vector.normalize();
		
		assertEquals(1, vector.length(), 0.0001);
	}

	@Test
	public void testNormalizeNonZeroVector2() {
		
		Vector vector = new Vector(-35,18);
		vector = vector.normalize();
		
		assertEquals(1, vector.length(), 0.0001);
	}

	@Test
	public void testNormalizeZeroVector() {
		
		Vector vector = new Vector(0,0);
		vector = vector.normalize();
		
		assertEquals(0, vector.length(), 0.0001);
	}
	
	@Test
	public void subtractInToroid() {
		
		Vector a = new Vector(400,100);
		Vector b = new Vector(300,100);
		
		Vector x = Vector.subtractInToroid(a, b, 800, 200);
		
		assertEquals(100, x.x, 0.001);
		assertEquals(0, x.y, 0.001);
	}

	@Test
	public void subtractInToroidInverse() {
		
		Vector a = new Vector(400,100);
		Vector b = new Vector(300,100);
		
		Vector x = Vector.subtractInToroid(b, a, 800, 200);
		
		assertEquals(-100, x.x, 0.001);
		assertEquals(0, x.y, 0.001);
	}

	@Test
	public void subtractInToroidNearLimitX() {
		
		Vector c = new Vector(800,100);
		Vector b = new Vector(300,100);
		
		Vector x = Vector.subtractInToroid(c, b, 800, 200);
		
		assertEquals(-300, x.x, 0.001);
		assertEquals(0, x.y, 0.001);
	}

	@Test
	public void subtractInToroidNearLimitXInverse() {
		
		Vector c = new Vector(800,100);
		Vector b = new Vector(300,100);
		
		Vector x = Vector.subtractInToroid(b, c, 800, 200);
		
		assertEquals(300, x.x, 0.001);
		assertEquals(0, x.y, 0.001);
	}

	@Test
	public void subtractInToroidSamePoint() {
		
		Vector d = new Vector(550,0);
		Vector e = new Vector(550,200);
		
		Vector x = Vector.subtractInToroid(d, e, 800, 200);
		
		assertEquals(0, x.x, 0.001);
		assertEquals(0, x.y, 0.001);
	}

	@Test
	public void subtractInToroidSamePointInverse() {
		
		Vector d = new Vector(550,0);
		Vector e = new Vector(550,200);
		
		Vector x = Vector.subtractInToroid(e, d, 800, 200);
		
		assertEquals(0, x.x, 0.001);
		assertEquals(0, x.y, 0.001);
	}

	@Test
	public void subtractInToroidNearLimitY() {
		
		Vector d = new Vector(550,0);
		Vector f = new Vector(550,150);
		
		Vector x = Vector.subtractInToroid(d, f, 800, 200);
		
		assertEquals(0, x.x, 0.001);
		assertEquals(50, x.y, 0.001);
	}

	@Test
	public void subtractInToroidNearLimitYInverse() {
		
		Vector d = new Vector(550,0);
		Vector f = new Vector(550,150);
		
		Vector x = Vector.subtractInToroid(f, d, 800, 200);
		
		assertEquals(0, x.x, 0.001);
		assertEquals(-50, x.y, 0.001);
	}

	@Test
	public void subtractInToroidNearLimitXY() {
		
		Vector g = new Vector(50,100);
		Vector f = new Vector(550,150);
		
		Vector x = Vector.subtractInToroid(g, f, 800, 200);
		
		assertEquals(300, x.x, 0.001);
		assertEquals(-50, x.y, 0.001);
	}
}
