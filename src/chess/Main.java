package chess;

import java.util.*;

public class Main {
	
	public static final int BOARD_DIMENSIONS = 8;
	
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
	
	public static enum Color {BLACK, WHITE};
	
	// possible axis moves (horizontal / vertical)
	public static Vector[] axisMoves = new Vector[] {
			new Vector(0, 1),
			new Vector(1, 0),
			new Vector(0, -1),
			new Vector(-1, 0),
	};
	
	// possible diagonal moves
	public static Vector[] diagMoves = new Vector[] {
			new Vector(1, 1),
			new Vector(1, -1),
			new Vector(-1, -1),
			new Vector(-1, 1),
	};
	
	// possible knight moves
	public static Vector[] knightMoves = new Vector[] {
			new Vector(1, 2),
			new Vector(2, 1),
			new Vector(2, -1),
			new Vector(1, -2),
			new Vector(-1, -2),
			new Vector(-2, -1),
			new Vector(-2, 1),
			new Vector(-1, 2),
	};
	
	// pawn moves, from left side 
	public static Vector[] pawnMoves = new Vector[] {
			new Vector(0, 1),
			new Vector(0, 2),
			new Vector(1, 1),
			new Vector(-1, 1)
	};
	
	public static Vector[] pawnAttacks = new Vector[] {
			new Vector(1, 1),
			new Vector(-1, 1)
	};
	
	public static void main(String[] args) {
		
		Player white = new Human(Color.WHITE);
		Player black = new Engine(Color.BLACK);
		ChessGame g = new ChessGame(white, black);
		g.currentState.log();

		
		Vector position = new Vector(4, 1);
		ArrayList<Move> mv = g.currentState.getAllPossibleMoves(position);
		
		System.out.print(mv.size() + " moves from ");
		position.log();
		for (Move m : mv) {
			m.to.log();
		}
		
	}

}









