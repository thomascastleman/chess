package chess;

import chess.Main.Color;

// handles all global utility functions
public class Util {
	
	public static String notationLetters = "abcdefgh";	// letters in algebraic chess notation
	
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
	
	public static Vector[] vectorArrayCopy(Vector[] a) {
		Vector[] copy = new Vector[a.length];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = a[i].copy();
		}
		return copy;
	}
	
	// check if two positions are on the same line of threat
	public static boolean onSameLineOfThreat(Vector p1, Vector p2) {
		return onSameAxis(p1, p2) || onSameDiag(p1, p2);	
	}
	
	public static boolean onSameAxis(Vector p1, Vector p2) {
		return p1.row == p2.row || p1.col == p2.col;
	}
	
	public static boolean onSameDiag(Vector p1, Vector p2) {
		return Math.abs(p1.row - p2.row) == Math.abs(p1.col - p2.col);
	}
	
	// get color type of piece
	public static Color getColor(char piece) {
		if (piece == Main.NULL_CHAR) {
			return null;
		} else if (piece == Main.BLACK_KING || piece == Main.BLACK_QUEEN || piece == Main.BLACK_ROOK || piece == Main.BLACK_BISHOP || piece == Main.BLACK_KNIGHT || piece == Main.BLACK_PAWN) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}
	
	public static Color oppositeColor(Color col) {
		if (col == null) {
			return null;
		} else {
			return col == Color.BLACK ? Color.WHITE : Color.BLACK;
		}
	}
	
	// for board eval, white is bound to 0, black to 1
	public static int getCorrespondingBit(Color col) {
		return col == Color.BLACK ? 1 : 0;
	}
	
	// PIECE CHECKING FUNCTIONS:
	
	public static boolean isKing(char piece) {
		return piece == Main.WHITE_KING || piece == Main.BLACK_KING;
	}
	
	public static boolean isPawn(char piece) {
		return piece == Main.WHITE_PAWN || piece == Main.BLACK_PAWN;
	}
	
	public static boolean isKnight(char piece) {
		return piece == Main.WHITE_KNIGHT || piece == Main.BLACK_KNIGHT;
	}
	
	public static boolean isQueen(char piece) {
		return piece == Main.WHITE_QUEEN || piece == Main.BLACK_QUEEN;
	}
	
	public static boolean isRook(char piece) {
		return piece == Main.WHITE_ROOK || piece == Main.BLACK_ROOK;
	}
	
	public static boolean isBishop(char piece) {
		return piece == Main.WHITE_BISHOP || piece == Main.BLACK_BISHOP;
	}
	
	public static boolean isNMove(char piece) {
		return isQueen(piece) || isRook(piece) || isBishop(piece);
	}

}
