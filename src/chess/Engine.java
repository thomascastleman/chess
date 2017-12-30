package chess;

public class Engine implements Player {
	
	public Main.Color color;
	public int searchDepth;
	
	public Engine(Main.Color _color, int _searchDepth) {
		this.color = _color;
		this.searchDepth = _searchDepth;
	}
	
	public Move getMove(State s) {
		State bestState = s; //this.minimax(s, true, 1, this.searchDepth);
		
		bestState.moveFromPrevious.log();	// log move result
		System.out.println(" with value " + bestState.value);
		
		return bestState.moveFromPrevious;
	}
	
//	// return the "best" child state using minimax search
//	public State minimax(State currentState, boolean isMaximizing, int currentDepth, int maxDepth) {
//		
//		
//		
//	}
	
}