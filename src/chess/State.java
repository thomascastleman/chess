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
		this.board = Main.boardCopy(prevState.board);	// copy previous board
		
		char fromPiece = this.board[from.row][from.col];
		char toPiece = this.board[to.row][to.col];
		
		// update king positions if necessary
		if (fromPiece == Main.BLACK_KING) {
			this.blackKing = to;
		} else if (fromPiece == Main.WHITE_KING) {
			this.whiteKing = to;
		}
		
		// copy over king positions
		if (this.whiteKing == null) {
			this.whiteKing = prevState.whiteKing.copy();
		}
		if (this.blackKing == null) {
			this.blackKing = prevState.blackKing.copy();
		}
		
		// move piece
		this.board[to.row][to.col] = this.board[from.row][from.col];
		this.board[from.row][from.col] = Main.NULL_CHAR;
	}
	
	// determine whether a position is being threatened on this state
	public boolean isThreatened(Position p) {
		return true;
	}
	
	// determine whether a move puts its king in check
	public boolean moveMeetsCheckConstraint(Position from, Position to) {
		Main.Color pieceColor = Main.getColor(this.board[from.row][from.col]);
		Position relevantKing = pieceColor == Main.Color.BLACK ? this.blackKing : this.whiteKing;
		
		// if previously potentially protecting king
		if (Main.onSameLineOfThreat(from, relevantKing)) {
			State moveState = new State(this, from, to);
			Position relKingInMove = pieceColor == Main.Color.BLACK ? moveState.blackKing : moveState.whiteKing;
			
			// return whether king is threatened as result of move
			return moveState.isThreatened(relKingInMove);
		} else {
			return false;
		}
	}
	
	public void log() {
		for (char[] row : this.board) {
			for (char c : row) {
				if (c == Main.NULL_CHAR) {
					System.out.print("_ ");
				} else {
					System.out.print(c + " ");
				}
			}
			System.out.println("");
		}	
	}
	
	
}
