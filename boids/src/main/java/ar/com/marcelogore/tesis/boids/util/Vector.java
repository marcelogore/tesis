package ar.com.marcelogore.tesis.boids.util;

public class Vector {
	
	public double x;
	public double y;
	
	public Vector() {}
	
	public Vector(double x, double y) {
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

	public static Vector subtract(Vector a, Vector b) {

		Vector result = new Vector();
		
		result.x = a.x - b.x;
		result.y = a.y - b.y;
		
		return result;
	}
	
	public Vector multiply(double number) {
		
		this.x *= number;
		this.y *= number;
		
		return this;
	}

	public Vector divide(double number) {
		
		this.x /= number;
		this.y /= number;
		
		return this;
	}
	
	public double length() {
		
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public void copy(Vector from) {
		this.x = from.x;
		this.y = from.y;
	}
	
	public Vector normalize() {
		
		double length = this.length();
		
		return length > 0 ? this.divide(length) : this;
	}
	
	public static Vector createRandomVector(double x, double y, boolean negativeComponents) {
		
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
		
		sb.append(String.format("%.2f", this.x));
		sb.append(",");
		sb.append(String.format("%.2f", this.y));
		sb.append(")");
		
		return sb.toString();
	}
}