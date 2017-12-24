package chess;

public class State {
	// char array board representation
	public char[][] board;
	
	// king positions for win checking
	public Position blackKing;
	public Position whiteKing;
	
	// construct state from board
	public State (char[][] _board) {
		this.board = Main.boardCopy(_board);	// make copy of board
		
		// find king positions
		for (int r = 0; r < this.board.length; r++) {
			for (int c = 0; c < this.board[r].length; c++) {
				char piece = this.board[r][c];
				if (piece == Main.BLACK_KING) {
					this.blackKing = new Position(r, c);
				} else if (piece == Main.WHITE_KING) {
					this.whiteKing = new Position(r, c);
				}
			}
		}
	}
	
	public State(State prevState, Position from, Position to) {
		
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
