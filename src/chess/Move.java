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

}
