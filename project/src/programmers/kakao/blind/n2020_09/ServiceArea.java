package programmers.kakao.blind.n2020_09;

import java.util.HashMap;

public class ServiceArea {

	private HashMap<Integer, RentalShop> shopMap = new HashMap<Integer, RentalShop>();
	private int areaSize;
	
	public ServiceArea(int areaSize) {
		this.areaSize = areaSize;
		
		for (int i = 0; i < areaSize; i++) {
			for (int j = 0; j < areaSize; j++) {
				int id = (areaSize - i - 1) + (areaSize * j);
				shopMap.put(id, new RentalShop(id, i, j));
			}
		}
	}
	
	public int getAreaSize() {
		return areaSize;
	}
	
	public RentalShop getRentalShop(int id) {
		return shopMap.get(id);
	}
}
