package chess;

public class Move {
	
	public Vector from;
	public Vector to;
	
	public Move(Vector f, Vector t) {
		this.from = f;
		this.to = t;
	}
	
	// debug 
	public Move() {
		
	}
	
	public Move copy() {
		return new Move(from.copy(), to.copy());
	}
	
	public void log() {
		System.out.println("(" + this.from.row + ", " + this.from.col + ") to (" + this.to.row + ", " + this.to.col + ")");
	}

}
