package programmers.kakao.blind.n2020_09;

public class TruckWork {

	private TruckWorkType type;
	
	public TruckWork(TruckWorkType type) {
		this.type = type;
	}
	
	public int getRequiredTime() {
		switch (type) {
		case Move:
			return 6;
		case PickUp:
			return 6;
		}
		return -1;
	}
}
