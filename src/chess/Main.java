package chess;

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
	
	// possible axis moves (horizontal / vertical)
	public static Position[] axisMoves = new Position[] {
			new Position(0, 1),
			new Position(1, 0),
			new Position(0, -1),
			new Position(-1, 0),
	};
	
	// possible diagonal moves
	public static Position[] diagMoves = new Position[] {
			new Position(1, 1),
			new Position(1, -1),
			new Position(-1, -1),
			new Position(-1, 1),
	};
	
	// possible knight moves
	public static Position[] knightMoves = new Position[] {
			new Position(1, 2),
			new Position(2, 1),
			new Position(2, -1),
			new Position(1, -2),
			new Position(-1, -2),
			new Position(-2, -1),
			new Position(-2, 1),
			new Position(-1, 2),
	};
	
	// pawn moves, from left side 
	public static Position[] pawnMoves = new Position[] {
			new Position(1, 0),
			new Position(2, 0),
			new Position(1, 1),
			new Position(1, -1)
	};
	
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

}









