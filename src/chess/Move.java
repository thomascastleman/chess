package chess;

public class Move {
	
	public Vector from;
	public Vector to;
	
	public Move(Vector f, Vector t) {
		this.from = f;
		this.to = t;
	}
	
	public Move copy() {
		return new Move(from.copy(), to.copy());
	}
	
	public String getNotationString() {
		return this.from.getNotationString() + " " + this.to.getNotationString();
	}
	
	public void log() {
		System.out.print("(" + this.from.row + ", " + this.from.col + ") to (" + this.to.row + ", " + this.to.col + ")");
	}

}
