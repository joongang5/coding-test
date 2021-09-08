package programmers.kakao.blind.n2020_09;

public class Position {
	
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getDistance(Position dest) {
		int distance = 0;
		distance += Math.abs(x - dest.getX());
		distance += Math.abs(y - dest.getY());
		
		return distance;
	}
}
