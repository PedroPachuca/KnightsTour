public class Vector {
	public int x;
	public int y;
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y);
	}
	@Override 
	public String toString() {
		return "<" + x + "," + y + ">";
	}
}
