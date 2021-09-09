package programmers.kakao.blind.n2020_09;

public enum WorkLocationType {
	Lack(0),
	Overflow(1),
	Max(2);
	
	private final int value;
	
	WorkLocationType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
