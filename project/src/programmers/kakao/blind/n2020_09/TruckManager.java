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
			Truck serverData = truckServerMap.get(truck.getId());
			
			truck.setLocationId(serverData.getLocationId());
			truck.setLoadedBikeCount(serverData.getLoadedBikeCount());
			
			Location location = locationMap.get(truck.getLocationId()); 
			truck.setPosition(location.getPosition());
			
			if (truck.isWorking() == false)
				truck.clearWorkLocationIds();
		}
	}
	
	public HashMap<Integer, Queue<TruckWorkType>> generateCommands(ArrayList<Location[]> workLocationList) {
		HashMap<Integer, Queue<TruckWorkType>> commands = new HashMap<Integer, Queue<TruckWorkType>>();
		
		for (Location[] locations : workLocationList) {
			Location overflowLocation = locations[WorkLocationType.Overflow.getValue()];
			Location lackLocation = locations[WorkLocationType.Lack.getValue()];

			int pullCount = overflowLocation.pullExtraBikeCount();
			if (pullCount <= 0)
				continue;
			
			if (isWorkingLocation(lackLocation.getId(), overflowLocation.getId()))
				continue;
			
			Truck truck = findNearestTruck(overflowLocation);
			if (truck == null)
				continue;

			truck.addWorkLocationIds(lackLocation.getId(), overflowLocation.getId());
			
			pushMoveCommands(truck, truck.getPosition(), overflowLocation.getPosition());
			
			while (pullCount > 0) {
				truck.addWork(TruckWorkType.PutBike);
				pullCount--;
			}
			
			pushMoveCommands(truck, overflowLocation.getPosition(), lackLocation.getPosition());
			
  			while (truck.getLoadedBikeCount() > 0) {
 				truck.addWork(TruckWorkType.DropBike);
			}
		}
		
		for (Map.Entry<Integer, Truck> entry : truckMap.entrySet()) {
			Truck truck = entry.getValue();
			if (truck.isWorking())
				commands.put(truck.getId(), truck.getWorkQueue());
		}
		
		return commands;
	}
	
	private boolean isWorkingLocation(int lackId, int overflowId) {
		for (Map.Entry<Integer, Truck> entry : truckMap.entrySet()) {
			Truck truck = entry.getValue();
			
			if (truck.hasWorkingLocation(lackId, overflowId))
				return true;
		}
		
		return false;
	}
	
	private void pushMoveCommands(Truck truck, Position startPosition, Position goalPosition) {
		int startX = startPosition.getX();
		int startY = startPosition.getY();
		int goalX = goalPosition.getX();
		int goalY = goalPosition.getY();
		
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
			if (truck.isWorking())
				continue;
			
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
