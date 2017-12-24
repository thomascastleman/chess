package chess;

import java.util.Arrays;

public class Main {
	
	// chess piece character encodings
	public static final char WHITE_KING = '\u2654';
	public static final char WHITE_QUEEN = '\u2655';
	public static final char WHITE_ROOK = '\u2656';
	public static final char WHITE_BISHOP = '\u2657';
	public static final char WHITE_KNIGHT = '\u2658';
	public static final char WHITE_PAWN = '\u2659';
	
	public static final char BLACK_KING = '\u265a';
	public static final char BLACK_QUEEN = '\u265b';
	public static final char BLACK_ROOK = '\u265c';
	public static final char BLACK_BISHOP = '\u265d';
	public static final char BLACK_KNIGHT = '\u265e';
	public static final char BLACK_PAWN = '\u265f';
	
	public static final char NULL_CHAR = '\u0000';	// for empty positions
	
	// initial board configuration
	public static char[][] initialBoard = {
			{WHITE_ROOK, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_ROOK},
			{WHITE_KNIGHT, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_KNIGHT},
			{WHITE_BISHOP, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_BISHOP},
			{WHITE_QUEEN, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_QUEEN},
			{WHITE_KING, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_KING},
			{WHITE_BISHOP, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_BISHOP},
			{WHITE_KNIGHT, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_KNIGHT},
			{WHITE_ROOK, WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_ROOK},
	};
	
	public enum Color {BLACK, WHITE};
	
	public static void main(String[] args) {
		
		Player white = new Human(Color.WHITE);
		Player black = new Engine(Color.BLACK);
		
		ChessGame g = new ChessGame(white, black);
		g.currentState.log();
		g.currentState.whiteKing.log();
		g.currentState.blackKing.log();
		
		g.currentState = new State(g.currentState, new Position(4, 0), new Position(4, 3));
		
		System.out.println("BREWAK");
		g.currentState.log();
		g.currentState.whiteKing.log();
		g.currentState.blackKing.log();
	}
	
	// deep copy a board
	public static char[][] boardCopy(char[][] board) {
		char[][] copy = new char[board.length][board.length];
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				copy[r][c] = board[r][c];
			}
		}
		return copy;
	}
	
	// check if two positions are on the same line of threat
	public static boolean onSameLineOfThreat(Position p1, Position p2) {
		return (p1.row == p2.row || p1.col == p2.col) || (Math.abs(p1.row - p2.row) == Math.abs(p1.col - p2.col));		
	}
	
	// get color type of piece
	public static Color getColor(char piece) {
		if (piece == BLACK_KING || piece == BLACK_QUEEN || piece == BLACK_ROOK || piece == BLACK_BISHOP || piece == BLACK_KNIGHT || piece == BLACK_PAWN) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}
	
	
	// PIECE CHECKING FUNCTIONS:
	
	public static boolean isPawn(char piece) {
		return piece == WHITE_PAWN || piece == BLACK_PAWN;
	}
	
	public static boolean isKnight(char piece) {
		return piece == WHITE_KNIGHT || piece == BLACK_KNIGHT;
	}
	
	public static boolean isQueen(char piece) {
		return piece == WHITE_QUEEN || piece == BLACK_QUEEN;
	}
	
	public static boolean isRook(char piece) {
		return piece == WHITE_ROOK || piece == BLACK_ROOK;
	}
	
	public static boolean isBishop(char piece) {
		return piece == WHITE_BISHOP || piece == BLACK_BISHOP;
	}
	
	public static boolean isNMove(char piece) {
		return isQueen(piece) || isRook(piece) || isBishop(piece);
	}

}









