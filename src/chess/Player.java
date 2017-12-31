package chess;

import chess.Main.Color;

public interface Player {
	
	public Move getMove(State s);
	public String getName();
	public Color getColor();
	
}
