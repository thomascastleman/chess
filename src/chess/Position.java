package chess;

public class Position {
	
	public int row;
	public int col;
	
	public Position(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	// maybe add a constructor for building off of chess notation?
	
	// copy this position
	public Position copy() {
		return new Position(this.row, this.col);
	}
	
	// add another position vector
	public Position add(Position delta) {
		return new Position(this.row + delta.row, this.col + delta.col);
	}
	
	// multiply by scalar
	public Position scalarMult(int scalar) {
		return new Position(this.row * scalar, this.col * scalar);
	}
	
	
	
	// debug
	public void log() {
		System.out.println("(" + this.row + ", " + this.col + ")");
	}
}
