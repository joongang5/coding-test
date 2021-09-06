package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;

public class RentalService {

	private ServiceArea area;
	
	private ArrayList<RentalInfo> rentalInfos;
	private ArrayList<Truck> trucks;
	
	public RentalService(int gridSize) {
		area = new ServiceArea(gridSize);
		
		rentalInfos = new ArrayList<RentalInfo>();
		
		trucks = new ArrayList<Truck>();
		for (int i = 0; i < 5; i++) {
			trucks.add(new Truck());
		}
	}
	
	public void update() {
		RentalInfo rentalInfo = new RentalInfo(1, 2, 10);
		
		requestRental(rentalInfo);
	}


	
	public boolean requestRental(RentalInfo rentalInfo) {
		/*
		사용자는 정시 분(minute)에 대여 요청을 보낸다.
		즉, 12시 1분에 대여 요청을 할 수 있으나 12시 1분 30초에는 대여 요청을 하지 않는다.
		사용자가 요청을 보낸 시점에 자전거 대여소에 자전거가 부족하면 사용자가 보낸 요청은 자동으로 취소된다.
		자전거를 대여한 사용자는 반드시 대여 시 약속한 시각과 장소에 자전거를 반납하는 것으로 가정한다
		 */
		
		RentalShop rentalShop = area.getRentalShop(rentalInfo.getRentalShopId());
		Bike bike = rentalShop.rental();
		if (bike == null) {
			return false;
		}
		
		rentalInfos.add(rentalInfo);
		
		return true;
	}
}
