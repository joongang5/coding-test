package programmers.kakao.blind.n2020_09;

import java.util.HashMap;
import java.util.Map;

public class ServiceArea {

	private HashMap<Integer, Location> locationMap;
	
	public boolean wasInit() {
		return locationMap.isEmpty() == false;
	}
	
	public ServiceArea() {
		locationMap = new HashMap<Integer, Location>();
	}
	
	public void init(HashMap<Integer, Integer> locationServerMap) {
		int aspectSize = (int)Math.sqrt(locationServerMap.size());
		
		for (int i = 0; i < aspectSize; i++) {
			for (int j = 0; j < aspectSize; j++) {
				int id = (aspectSize - i - 1) + (aspectSize * j);
				locationMap.put(id, new Location(id, i, j, locationServerMap.get(id)));
			}
		}
	}
	
	public void update(HashMap<Integer, Integer> locationServerMap) {
		for (Map.Entry<Integer, Location> entry : locationMap.entrySet()) {
			Location location = entry.getValue();
			location.setBikeCount(locationServerMap.get(entry.getKey()));
		}
	}
	
	public Location getRentalShop(int id) {
		return locationMap.get(id);
	}
}
