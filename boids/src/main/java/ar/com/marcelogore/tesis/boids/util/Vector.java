package ar.com.marcelogore.tesis.boids.util;

public class Vector {
	
	public int x;
	public int y;
	
	public Vector() {}
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public static Vector add(Vector... vectors) {
		
		Vector result = new Vector();
		
		for (Vector vector : vectors) {
			
			result.x += vector.x;
			result.y += vector.y;
		}
		
		return result;
	}

	public void subtract(Vector... vectors) {
		
		for (Vector vector : vectors) {
			
			this.x -= vector.x;
			this.y -= vector.y;
		}
	}
	
	public void multiply(double number) {
		
		this.x *= number;
		this.y *= number;
	}

	public void divide(double number) {
		
		this.x /= number;
		this.y /= number;
	}
	
	public double length() {
		
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public void copy(Vector from) {
		this.x = from.x;
		this.y = from.y;
	}
	
	public static Vector createRandomVector(int x, int y, boolean negativeComponents) {
		
		Vector vector = new Vector();
		
		double randomX = Math.random();
		double randomY = Math.random();
		
		vector.x = (int) (negativeComponents ? ((randomX - 0.5d) * x) : (randomX * x));
		vector.y = (int) (negativeComponents ? ((randomY - 0.5d) * y) : (randomY * y));

		return vector;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder("(");
		
		sb.append(this.x);
		sb.append(",");
		sb.append(this.y);
		sb.append(")");
		
		return sb.toString();
	}
}
