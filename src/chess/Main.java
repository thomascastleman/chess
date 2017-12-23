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
	

	public static void main(String[] args) {
		System.out.println("PAWNS:");
		System.out.println(WHITE_PAWN);
		System.out.println(BLACK_PAWN);
		
		System.out.println("BISHOPS");
		System.out.println(BLACK_BISHOP);
		System.out.println(WHITE_BISHOP);
		
		System.out.println("Rooks");
		System.out.println(WHITE_ROOK);
		System.out.println(BLACK_ROOK);
		
		System.out.println("Kings");
		System.out.println(BLACK_KING);
		System.out.println(WHITE_KING);
		
		System.out.println("Queens");
		System.out.println(BLACK_QUEEN);
		System.out.println(WHITE_QUEEN);
		
	}

}
