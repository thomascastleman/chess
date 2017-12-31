package chess;

public class Move {
	
	public Vector from;
	public Vector to;
	
	public Move(Vector f, Vector t) {
		this.from = f;
		this.to = t;
	}
	
	// make copy of move
	public Move copy() {
		return new Move(from.copy(), to.copy());
	}
	
	// get full algebraic notation of move
	public String getNotationString() {
		return this.from.getNotationString() + " " + this.to.getNotationString();
	}
	
	// log coordinates to console
	public void log() {
		System.out.print("(" + this.from.row + ", " + this.from.col + ") to (" + this.to.row + ", " + this.to.col + ")");
	}

}
