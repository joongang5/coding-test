package programmers.kakao.blind.n2020_09;

public class Location {

	private int id;
	private int positionX;
	private int positionY;
	private int bikeCount;
	
	public static final int LENGTH = 100;
	
	public Location(int id, int positionX, int positionY, int bikeCount) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		this.bikeCount = bikeCount;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

	public void setBikeCount(int bikeCount) {
		this.bikeCount = bikeCount;
	}
	
	public int getBikeCount() {
		return bikeCount;
	}
}
