package chess;

public class Position {
	
	public int row;
	public int col;
	
	public Position(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	// maybe add a constructor for building off of chess notation?
	
	
	public void log() {
		System.out.println("(" + this.row + ", " + this.col + ")");
	}
	
	public Position copy() {
		return new Position(this.row, this.col);
	}
}
