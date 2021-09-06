package programmers.kakao.blind.n2020_09;

public class RentalInfo {
	
	private int rentalShopId;
	private int returnShopId;
	private int rentalMinutes;
	
	public RentalInfo(int rentalShopId, int returnShopId, int rentalMinutes) {
		this.rentalShopId = rentalShopId;
		this.returnShopId = returnShopId;
		this.rentalMinutes = rentalMinutes;
	}

	public int getRentalShopId() {
		return rentalShopId;
	}

	public int getReturnShopId() {
		return returnShopId;
	}

	public int getRentalMinutes() {
		return rentalMinutes;
	}
}
