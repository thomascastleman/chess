package chess;

import java.util.*;
import chess.Main.Color;

public class Engine implements Player {
	
	public Main.Color color;
	public String name;
	public int searchDepth;
	
	public Engine(String _name, Main.Color _color, int _searchDepth) {
		this.name = _name;
		this.color = _color;
		this.searchDepth = _searchDepth;
	}
	
	// get name string
	public String getName() { return this.name; }
		
	// get color
	public Color getColor() { return this.color; }
	
	public Move getMove(State s) {
		State bestState = this.minimax(s, true, 1);
		System.out.println(bestState.moveFromPrevious.getNotationString());
		System.out.println("Best @ depth=" + bestState.depth + ", value=" + bestState.value);
		return bestState.moveFromPrevious;
	}
	
	// return the "best" child state using minimax search
	public State minimax(State currentState, boolean isMaximizing, int currentDepth) {
		// if leaf node
		if (currentState.isWin()) {
			if (currentState.currentTurnColor == this.color) {
				currentState.value = Float.NEGATIVE_INFINITY;	// loss, so infinitely bad
			} else {
				currentState.value = Float.POSITIVE_INFINITY;	// win, so infinitely good
			}
			currentState.depth = currentDepth;
			return currentState;
		
		// if maximum search depth reached
		} else if (currentDepth == this.searchDepth) {
			// evaluate heuristically
			currentState.value = this.boardEval(currentState, this.color);
			currentState.depth = currentDepth;
			return currentState;
		
		// otherwise, evaluate recursively
		} else {
			
			// get child states
			ArrayList<State> children = currentState.getSuccessors();
			State bestState = null;
			
			// iterate child states
			for (State child : children) {
				//  inherit depth and value from evaluation of child's child states
				State state = this.minimax(child, !isMaximizing, currentDepth + 1);
				child.depth = state.depth;
				child.value = state.value;
				
				// default to first child as best
				if (bestState == null) {
					bestState = child;
				}
				
				if (isMaximizing) {
					// choose higher value
					if (child.value > bestState.value) {
						bestState = child;
					
					} else if (child.value == bestState.value) {
						// if both wins, favor shallow depth
						if (child.value == Float.POSITIVE_INFINITY) {
							if (child.depth < bestState.depth) {
								bestState = child;
							}
						// if both losses, favor deep depth
						} else if (child.value == Float.NEGATIVE_INFINITY) {
							if (child.depth > bestState.depth) {
								bestState = child;
							}
						}
					}
				// if minimizing
				} else {
					// choose lower value
					if (child.value < bestState.value) {
						bestState = child;
					
					} else if (child.value == bestState.value) {
						// if both wins, favor deep depth
						if (child.value == Float.POSITIVE_INFINITY) {
							if (child.depth > bestState.depth) {
								bestState = child;
							}
						// if both losses, favor shallow depth
						} else if (child.value == Float.NEGATIVE_INFINITY) {
							if (child.depth < bestState.depth) {
								bestState = child;
							}
						}
					}
				}
			}
			return bestState;
		}
	}
	
	// evaluate a board position
	public float boardEval(State s, Color selfColor) {
		
		// get indices for accessing counter arrays
		int self = Util.getCorrespondingBit(selfColor);
		int opponent = self == 0 ? 1 : 0;
		
		// piece counts
		int[] queens = new int[2];
		int[] rooks = new int[2];
		int[] bishops = new int[2];
		int[] knights = new int[2];
		int[] pawns = new int[2];
		
		// for pawn evaluation
		int[][] filePawnCounts = new int[2][Main.BOARD_DIMENSIONS];
		int[] doubled = new int[2];
		int[] isolated = new int[2];
		int[] blocked = new int[2];
		
		// move counts
		int[] moves = new int[2];
		
		// iterate board
		for (int r = 0; r < s.board.length; r++) {
			for (int c = 0; c < s.board.length; c++) {
				char piece = s.board[r][c];	// get piece value
				int bit = Util.getCorrespondingBit(Util.getColor(piece));
				
				if (piece != Main.NULL_CHAR) {				
					// update relevant piece count
					if (Util.isQueen(piece)) { queens[bit]++; } else
					if (Util.isRook(piece)) { rooks[bit]++; } else
					if (Util.isBishop(piece)) { bishops[bit]++; } else
					if (Util.isKnight(piece)) { knights[bit]++; } else
					if (Util.isPawn(piece)) { 
						pawns[bit]++;
						filePawnCounts[bit][r]++;
						
						// if blocked
						if (c < Main.BOARD_DIMENSIONS - 1 && s.board[r][c + 1] != Main.NULL_CHAR) {
							blocked[bit]++;
						}												
					}
					
					// update move count
					moves[bit] += s.getAllPossibleMoves(new Vector(r, c)).size();
				}
			}
		}
		
		// extract pawn information
		for (int bit = 0; bit < filePawnCounts.length; bit++) {
			for (int file = 0; file < filePawnCounts[bit].length; file++) {
				int current = filePawnCounts[bit][file];
				
				// check for doubled pawns
				if (current > 1) {
					doubled[bit]++;
				}
				
				int prev = 0;
				int next = 0;
				if (file - 1 >= 0) {
					prev = filePawnCounts[bit][file - 1];
				}
				if (file + 1 < filePawnCounts[bit].length) {
					next = filePawnCounts[bit][file + 1];
				}
				
				// check for isolated pawns
				if ((prev == 0 && next == 0) && current > 0) {
					isolated[bit]++;
				}
			}
		}
		
//		// debug
//		System.out.println("ENGINE COUNTS: ");
//		System.out.println("Queens: " + queens[self]);
//		System.out.println("Rooks: " + rooks[self]);
//		System.out.println("Bishops: " + bishops[self]);
//		System.out.println("Knights: " + knights[self]);
//		System.out.println("Pawns: " + pawns[self]);
//		System.out.println("Moves --> " + moves[self]);
//		
//		System.out.println("OPPONENT COUNTS: ");
//		System.out.println("Queens: " + queens[opponent]);
//		System.out.println("Rooks: " + rooks[opponent]);
//		System.out.println("Bishops: " + bishops[opponent]);
//		System.out.println("Knights: " + knights[opponent]);
//		System.out.println("Pawns: " + pawns[opponent]);
//		System.out.println("Moves --> " + moves[opponent]);
		
		return (float) ((9 * (queens[self] - queens[opponent]))
				+ (5 * (rooks[self] - rooks[opponent]))
				+ (3 * (bishops[self] - bishops[opponent] + knights[self] - knights[opponent]))
				+ (pawns[self] - pawns[opponent])
				- (0.5 * (doubled[self] - doubled[opponent] + blocked[self] - blocked[opponent] + isolated[self] - isolated[opponent]))
				+ (0.1 * (moves[self] - moves[opponent])));
	}

}