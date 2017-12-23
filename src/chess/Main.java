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
	
	public enum color {BLACK, WHITE};
	
	public static void main(String[] args) {
		ChessGame g = new ChessGame();
		
		for (char[] r : g.initialBoard) {
			for (char c : r) {
				System.out.print(c + " ");
			}
			System.out.println("");
		}
		
	}

}
