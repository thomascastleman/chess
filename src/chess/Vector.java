package chess;

public class Vector {
	
	public int row;
	public int col;
	
	public Vector(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	public boolean vectorEquals(Vector v) {
		return this.row == v.row && this.col == v.col;
	}
	
	// copy this position
	public Vector copy() {
		return new Vector(this.row, this.col);
	}
	
	// create new vector by adding
	public Vector add(Vector delta) {
		return new Vector(this.row + delta.row, this.col + delta.col);
	}
	
	// update this vector by adding another
	public void addVector(Vector delta) {
		this.row += delta.row;
		this.col += delta.col;
	}
	
	public void increment() {
		// increment delta to fan out
		Vector increment = new Vector(0, 0);
		if (this.row != 0) {
			increment.row = this.row / Math.abs(this.row);
		}
		if (this.col != 0) {
			increment.col = this.col / Math.abs(this.col);
		}
		this.addVector(increment);
	}
	
	// multiply by scalar
	public Vector scalarMult(int scalar) {
		return new Vector(this.row * scalar, this.col * scalar);
	}
	
	// determine if vector is legal as a board position
	public boolean isLegalPosition() {
		return (this.row >= 0 && this.col >= 0) && (this.row < Main.BOARD_DIMENSIONS && this.col < Main.BOARD_DIMENSIONS);
	}
	
	// debug
	public void log() {
		System.out.println("(" + this.row + ", " + this.col + ")");
	}
}
