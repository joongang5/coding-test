package programmers.kakao.blind.n2020_09;

public class Location {

	private int id;
	private int bikeCount;
	private int defaultBikeCount;
	private Position position;
	
	public static final int LENGTH = 100;
	
	public Location(int id, int positionX, int positionY, int bikeCount) {
		this.id = id;
		this.bikeCount = bikeCount;
		defaultBikeCount = bikeCount;
		position = new Position();
		position.setX(positionX);
		position.setY(positionY);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setBikeCount(int bikeCount) {
		this.bikeCount = bikeCount;
	}
	
	public int getBikeCount() {
		return bikeCount;
	}
	
	public int pullExtraBikeCount() {
		if (bikeCount <= 0)
			return 0;
		
		int extraCount = Math.max(1, bikeCount - defaultBikeCount);
		bikeCount -= extraCount;
		return extraCount;
	}
	
	public boolean needStuff() {
		return bikeCount <= defaultBikeCount / 2;
	}
	
	public boolean isOverflow() {
		return bikeCount > defaultBikeCount;
	}
}
