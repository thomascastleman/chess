package chess;

public class Human implements Player {
	
	public Main.Color color;
	
	public Human(Main.Color _color) {
		this.color = _color;
	}
	
	public Position getMove() {
		System.out.println("GET MOVE from Human");
		return new Position(0, 0);
	}

}
