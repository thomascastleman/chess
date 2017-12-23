package chess;

public class State {
	// char array board representation
	public char[][] board;
	
	// king positions for win checking
	public Position blackKing;
	public Position whiteKing;
	
	public State() {
		
	}
	
	// determine whether a position is being threatened on this state
	public boolean isThreatened(Position p) {
		return true;
	}
	
	// determine whether a move puts its king in check
	public boolean moveMeetsCheckConstraint(Position from, Position to) {
		return true;
	}
	
	
	
	public void log() {
		for (char[] row : this.board) {
			for (char c : row) {
				if (c == '\u0000') {
					System.out.print("_ ");
				} else {
					System.out.print(c + " ");
				}
			}
			System.out.println("");
		}	
	}
	
	
}
