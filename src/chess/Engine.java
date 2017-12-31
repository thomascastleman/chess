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
			currentState.value = this.boardEval(currentState);
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
	public int boardEval(State s) {
		return 0;
	}
	
}