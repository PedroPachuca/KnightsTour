

public class Tiles{
	private int row, col, moves;
	boolean visited;
	Tiles(int r, int c) {
		row = r;
		col = c;
		visited = false;
		moves = calcMoves();
	}
	private int calcMoves() {
		return 0;
	}
	public Vector getRC() {
		return new Vector(row, col);
	}
	public int getMoves() {
		return moves;
	}
	public void setMoves(int amt) {
		moves = amt;
	}
	public void clearAll() {
		visited = false;
		moves = 0;
	}
}
