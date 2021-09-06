package programmers.kakao.blind.n2020_09;

public class Bike {
	
	private int serialNumber;
	
	private static int bikeCount = 0;
	
	public Bike() {
		serialNumber = bikeCount++;
	}
}
