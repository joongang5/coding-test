package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TruckManager {

	private HashMap<Integer, Truck> truckMap;
	
	public boolean wasInit() {
		return truckMap.isEmpty() == false;
	}
	
	public void init(HashMap<Integer, Truck> truckMap, HashMap<Integer, Location> locationMap) {
		this.truckMap = truckMap;
		
		for (Map.Entry<Integer, Truck> entry : truckMap.entrySet()) {
			Truck truck = entry.getValue();
			Location location = locationMap.get(truck.getLocationId()); 
			truck.setPosition(location.getPosition());
		}
	}
	
	public void update(HashMap<Integer, Truck> truckServerMap, HashMap<Integer, Location> locationMap) {
		for (Map.Entry<Integer, Truck> entry : truckMap.entrySet()) {
			Truck truck = entry.getValue();
			truck.setLoadedBikeCount(truckServerMap.get(entry.getKey()).getLoadedBikeCount());
			
			Location location = locationMap.get(truck.getLocationId()); 
			truck.setPosition(location.getPosition());
		}
	}
	
	public HashMap<Integer, ArrayList<Integer>> generateCommands(ArrayList<Location[]> workLocationList) {
		for (Location[] locations : workLocationList) {
			Truck truck = findNearestTruck(locations[1]);
			
			int startX = truck.getPosition().getX();
			int startY = truck.getPosition().getY();
			int goalX = locations[1].getPosition().getX();
			int goalY = locations[1].getPosition().getY();
			
			int moveX = startX - goalX;
			int moveY = startY - goalY;
			
			while (moveX != 0 || moveY != 0) {
				if (moveX > 0) {
					truck.pushWork(TruckWorkType.MoveRight);
					moveX--;
				}
				else if (moveX < 0) {
					truck.pushWork(TruckWorkType.MoveLeft);
					moveX++;
				}
				else if (moveY > 0) {
					truck.pushWork(TruckWorkType.MoveUp);
					moveY--;
				}
				else if (moveY < 0) {
					truck.pushWork(TruckWorkType.MoveDown);
					moveY++;
				}
			}
			
			truck.pushWork(TruckWorkType.PutBike);
		}
		return null;
	}
	
	private Truck findNearestTruck(Location location) {
		
		
		return null;
	}
}
