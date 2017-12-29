package chess;

public class ChessGame {
	
	// player objects
	public Player player_black;
	public Player player_white;
	
	// current board state
	public State currentState = new State(Main.initialBoard);
	
	public ChessGame(Player white, Player black) {
		this.player_white = white;
		this.player_black = black;
	}
	
	public void initGame() {
		Player[] players = new Player[] {player_white, player_black};
		Player winner = null;
		
		while (true) {
			for (Player p : players) {
				this.currentState.log();	// show state
				
				Move mv = p.getMove(this.currentState);	// solicit move from player
				this.currentState.makeMove(mv);	// update board
				
				if (this.currentState.isWin()) {
					winner = p;
					break;
				}
			}
			
			if (winner != null) {
				break;
			}
		}
	}
	
}
