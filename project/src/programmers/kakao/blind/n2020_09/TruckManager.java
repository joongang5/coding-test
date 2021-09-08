package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class TruckManager {

	private HashMap<Integer, Truck> truckMap;
	
	public TruckManager() {
		truckMap = new HashMap<Integer, Truck>();
	}
	
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
	
	public HashMap<Integer, Queue<TruckWorkType>> generateCommands(ArrayList<Location[]> workLocationList) {
		HashMap<Integer, Queue<TruckWorkType>> commands = new HashMap<Integer, Queue<TruckWorkType>>();
		
		for (Location[] locations : workLocationList) {
			Location overflowLocation = locations[1];
			
			Truck truck = findNearestTruck(overflowLocation);
			
			pushMoveCommands(truck, overflowLocation);
			
			int pullCount = overflowLocation.pullExtraBikeCount();
			while (pullCount > 0) {
				truck.addWork(TruckWorkType.PutBike);
				pullCount--;
			}
			
			Location lackLocation = locations[0];
			
			pushMoveCommands(truck, lackLocation);
			
  			while (truck.getLoadedBikeCount() > 0) {
 				truck.addWork(TruckWorkType.DropBike);
			}
			
			commands.put(truck.getId(), truck.getWorkQueue());
		}
		
		return commands;
	}
	
	public void pushMoveCommands(Truck truck, Location goalLocation) {
		int startX = truck.getPosition().getX();
		int startY = truck.getPosition().getY();
		int goalX = goalLocation.getPosition().getX();
		int goalY = goalLocation.getPosition().getY();
		
		int moveX = goalX - startX;
		int moveY = goalY - startY;
		
		while (moveX != 0 || moveY != 0) {
			if (moveX > 0) {
				truck.addWork(TruckWorkType.MoveRight);
				moveX--;
			}
			else if (moveX < 0) {
				truck.addWork(TruckWorkType.MoveLeft);
				moveX++;
			}
			else if (moveY > 0) {
				truck.addWork(TruckWorkType.MoveUp);
				moveY--;
			}
			else if (moveY < 0) {
				truck.addWork(TruckWorkType.MoveDown);
				moveY++;
			}
		}
	}
	
	private Truck findNearestTruck(Location location) {
		int minDistance = Integer.MAX_VALUE;
		Truck nearestTruck = null;
		
		for (Map.Entry<Integer, Truck> entry : truckMap.entrySet()) {
			Truck truck = entry.getValue();
			
			Position truckPosition = truck.getPosition();
			Position locationPosition = location.getPosition();
			
			int distance = truckPosition.getDistance(locationPosition);
			if (distance < minDistance) {
				minDistance = distance;
				nearestTruck = truck;
			}
		}
		return nearestTruck;
	}
}
