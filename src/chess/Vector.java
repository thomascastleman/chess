package chess;

public class Vector {
	
	public int row;
	public int col;
	
	public Vector(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	// copy this position
	public Vector copy() {
		return new Vector(this.row, this.col);
	}
	
	// add another position vector
	public Vector add(Vector delta) {
		return new Vector(this.row + delta.row, this.col + delta.col);
	}
	
	// multiply by scalar
	public Vector scalarMult(int scalar) {
		return new Vector(this.row * scalar, this.col * scalar);
	}
	
	
	
	// debug
	public void log() {
		System.out.println("(" + this.row + ", " + this.col + ")");
	}
}
