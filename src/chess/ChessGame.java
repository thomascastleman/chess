package chess;

import chess.Main;

public class ChessGame {
	
	// player objects
	public Player player_black;
	public Player player_white;
	
	// current board state
	public State currentState;
	
	// initial board configuration
	public char[][] initialBoard = {
			{Main.WHITE_ROOK, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_ROOK},
			{Main.WHITE_KNIGHT, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_KNIGHT},
			{Main.WHITE_BISHOP, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_BISHOP},
			{Main.WHITE_QUEEN, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_QUEEN},
			{Main.WHITE_KING, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_KING},
			{Main.WHITE_BISHOP, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_BISHOP},
			{Main.WHITE_KNIGHT, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_KNIGHT},
			{Main.WHITE_ROOK, Main.WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', Main.BLACK_PAWN, Main.BLACK_ROOK},
	};
	
	public ChessGame() {
		
	}
	
	public void initGame() {
		
		
	}
	
}
