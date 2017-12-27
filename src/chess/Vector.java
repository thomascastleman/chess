package chess;

public class Vector {
	
	public int row;
	public int col;
	
	public Vector(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	public Vector() {
		
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
	
	// determine if vector is legal as a board position
	public boolean isLegalPosition() {
		return (this.row > 0 && this.col > 0) && (this.row < Main.BOARD_DIMENSIONS && this.col < Main.BOARD_DIMENSIONS);
	}
	
	
	// debug
	public void log() {
		System.out.println("(" + this.row + ", " + this.col + ")");
	}
}
