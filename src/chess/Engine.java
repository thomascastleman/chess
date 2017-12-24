package chess;

public class Engine implements Player {
	
	public Main.Color color;
	
	public Engine(Main.Color _color) {
		this.color = _color;
	}
	
	public Move getMove() {
		System.out.println("GET MOVE from Engine");
		return new Move();
	}
	
}
