package chess;

public class State {
	// char array board representation
	public char[][] board;
	
	// king positions for win checking
	public Vector blackKing;
	public Vector whiteKing;
	
	// construct state from board
	public State (char[][] _board) {
		this.board = Util.boardCopy(_board);	// make copy of board
		
		// find king positions
		for (int r = 0; r < this.board.length; r++) {
			for (int c = 0; c < this.board[r].length; c++) {
				char piece = this.board[r][c];
				if (piece == Main.BLACK_KING) {
					this.blackKing = new Vector(r, c);
				} else if (piece == Main.WHITE_KING) {
					this.whiteKing = new Vector(r, c);
				}
			}
		}
	}
	
	public State(State prevState, Vector from, Vector to) {
		this.board = Util.boardCopy(prevState.board);	// copy previous board
		
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
	public boolean isThreatened(Vector p) {
		return true;
	}
	
	// determine whether a move puts its king in check
	public boolean moveMeetsCheckConstraint(Vector from, Vector to) {
		Main.Color pieceColor = Util.getColor(this.board[from.row][from.col]);
		Vector relevantKing = pieceColor == Main.Color.BLACK ? this.blackKing : this.whiteKing;
		
		// if previously potentially protecting king
		if (Util.onSameLineOfThreat(from, relevantKing)) {
			State moveState = new State(this, from, to);
			Vector relKingInMove = pieceColor == Main.Color.BLACK ? moveState.blackKing : moveState.whiteKing;
			
			// return whether king is threatened as result of move
			return moveState.isThreatened(relKingInMove);
		} else {
			return false;
		}
	}
	
	public void log() {
		System.out.print("\n  ");
		// DEBUG
		for (int j = 0; j < Main.BOARD_DIMENSIONS; j++) {
			System.out.print(j + " ");
		}
		System.out.println("");
		
		int i = 0;
		for (char[] row : this.board) {
			System.out.print(i++);
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
