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
		
		while (true) {
			
			for (Player p : players) {
				
				this.currentState.log();
				
				
				
			}
		}
		
	}
	
	public void solicitMove(Player p) {
		
	}
	
}
