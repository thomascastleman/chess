package chess;

public class Main {
	
	public static enum Color {BLACK, WHITE};
	public static final int BOARD_DIMENSIONS = 8;
	
	// chess piece character encodings
	public static final char WHITE_KING =	'\u2654';
	public static final char WHITE_QUEEN =	'\u2655';
	public static final char WHITE_ROOK =	'\u2656';
	public static final char WHITE_BISHOP = '\u2657';
	public static final char WHITE_KNIGHT = '\u2658';
	public static final char WHITE_PAWN =	'\u2659';
	
	public static final char BLACK_KING =	'\u265a';
	public static final char BLACK_QUEEN =	'\u265b';
	public static final char BLACK_ROOK =	'\u265c';
	public static final char BLACK_BISHOP = '\u265d';
	public static final char BLACK_KNIGHT = '\u265e';
	public static final char BLACK_PAWN =	'\u265f';
	
	public static final char NULL_CHAR =	'\u0000';	// for empty positions
	
	// initial board configuration
	public static char[][] initialBoard = {
			{WHITE_ROOK,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_ROOK},
			{WHITE_KNIGHT,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_KNIGHT},
			{WHITE_BISHOP,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_BISHOP},
			{WHITE_QUEEN,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_QUEEN},
			{WHITE_KING,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_KING},
			{WHITE_BISHOP,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_BISHOP},
			{WHITE_KNIGHT,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_KNIGHT},
			{WHITE_ROOK,	WHITE_PAWN, '\u0000', '\u0000', '\u0000', '\u0000', BLACK_PAWN, BLACK_ROOK},
	};
	
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
		
		Player white = new Human("P1", Color.WHITE);
		Player black = new Engine("P2", Color.BLACK, 3);
		ChessGame g = new ChessGame(white, black);
		
		
//		// debug: tests with rigged board states
//		char[][] testBoard = new char[8][8];
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				testBoard[i][j] = NULL_CHAR;
//			}
//		}
//		
//		testBoard[1][0] = WHITE_KING;
//		testBoard[7][7] = BLACK_KING;
//		
//		testBoard[6][4] = WHITE_QUEEN;
//		testBoard[6][1] = WHITE_ROOK;
//		
//		g.currentState = new State(testBoard);
		
		
		g.initGame();
		
	}

}