package programmers.kakao.blind.n2020_09;

public enum TruckWorkType {
	Idle(0),
	MoveUp(1),
	MoveRight(2),
	MoveDown(3),
	MoveLeft(4),
	PutBike(5),
	DropBike(6);
	
	private final int value;
	
	TruckWorkType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
