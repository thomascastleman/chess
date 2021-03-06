package chess;

import java.util.*;
import chess.Main.Color;

public class Human implements Player {
	
	public Main.Color color;
	public String name;
	public static Scanner input = new Scanner(System.in);
	
	public Human(String _name, Main.Color _color) {
		this.name = _name;
		this.color = _color;
	}
	
	// get name string
	public String getName() { return this.name; }
	
	// get color
	public Color getColor() { return this.color; }
	
	public Move getMove(State s) {
		String inp = null;
		
		// loop until acceptable move entered
		while (true) {
			
			if (input.hasNextLine()) {
				// read input
				inp = input.nextLine();
				inp = inp.toLowerCase();
				
				// if input is correct format
				if (inp.matches("[a-z]\\d\\s[a-z]\\d")) {
					String[] split = inp.split(" ");	// split input
					
					// extract move vector values
					Vector from = new Vector(Util.notationLetters.indexOf(split[0].charAt(0)), (split[0].charAt(1) - '0') - 1);
					Vector to = new Vector(Util.notationLetters.indexOf(split[1].charAt(0)), (split[1].charAt(1) - '0') - 1);
					
					// ensure legal board positions
					if (from.isLegalPosition() && to.isLegalPosition()) {
						
						// if actual piece exists to move
						if (s.pieceAt(from) != Main.NULL_CHAR) {
							
							ArrayList<Move> possibleMoves = s.getAllPossibleMoves(from);
							for (Move m : possibleMoves) {
								if (m.from.vectorEquals(from) && m.to.vectorEquals(to)) {
									return new Move(from, to);
								}
							}
						}					
					}
				} else if (inp.matches("list\\s[a-z]\\d")) {
					
					String[] split = inp.split(" ");
					Vector pos =  new Vector(Util.notationLetters.indexOf(split[1].charAt(0)), (split[1].charAt(1) - '0') - 1);
					
					if (s.pieceAt(pos) != Main.NULL_CHAR) {
						ArrayList<Move> possible = s.getAllPossibleMoves(pos);
						for (Move m : possible) {
							System.out.println(m.getNotationString());
						}
					}
				} else if (inp.matches("list all")) {
					for (int r = 0; r < s.board.length; r++) {
						for (int c = 0; c < s.board.length; c++) {
							char piece = s.board[r][c];
							if (piece != Main.NULL_CHAR && Util.getColor(piece) == s.currentTurnColor) {
								ArrayList<Move> possible = s.getAllPossibleMoves(new Vector(r, c));
								for (Move m : possible) {
									System.out.println(m.getNotationString());
								}
							}
						}
					}
				}
				
				System.out.print("(" + this.color + ") " + this.name + "'s move: ");
			}
		}
	
	}

}
