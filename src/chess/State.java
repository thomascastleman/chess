package chess;

public class State {
	// char array board representation
	public char[][] board;
	
	// king positions for win checking
	public Position blackKing;
	public Position whiteKing;
	
	// determine whether a position is being threatened on this state
	public boolean isThreatened(Position p) {
		return true;
	}
	
	// determine whether a move puts its king in check
	public boolean moveMeetsCheckConstraint(Position from, Position to) {
		return true;
	}
	
	
}
