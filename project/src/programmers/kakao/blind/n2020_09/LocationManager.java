package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LocationManager {

	private HashMap<Integer, Location> locationMap;
	
	private int aspectSize;
	
	public LocationManager() {
		locationMap = new HashMap<Integer, Location>();
		aspectSize = 0;
	}

	public HashMap<Integer, Location> getLocationMap() {
		return locationMap;
	}
	
	public boolean wasInit() {
		return locationMap.isEmpty() == false;
	}
	
	public void init(HashMap<Integer, Integer> locationServerMap) {
		aspectSize = (int)Math.sqrt(locationServerMap.size());
		
		for (int i = 0; i < aspectSize; i++) {
			for (int j = 0; j < aspectSize; j++) {
				int id = i + j * aspectSize;
				locationMap.put(id, new Location(id, j, i, locationServerMap.get(id)));
			}
		}
	}
	
	public void update(HashMap<Integer, Integer> locationServerMap) {
		for (Map.Entry<Integer, Location> entry : locationMap.entrySet()) {
			Location location = entry.getValue();
			location.setBikeCount(locationServerMap.get(entry.getKey()));
		}
	}
	
	public ArrayList<Location[]> getWorkLocationList() {
		ArrayList<Location[]> workList = new ArrayList<Location[]>();
		
		ArrayList<Location> lackLocationList = findLackLocationList();
		if (lackLocationList.isEmpty())
			return workList;
		
		ArrayList<Location> overflowLocationList = findOverflowLocationList();
		
		for (Location lackLocation : lackLocationList) {
			int minDistance = Integer.MAX_VALUE;
			Location minDistLocation = null;
			Position lackPosition = lackLocation.getPosition();
			
			if (overflowLocationList.isEmpty())
				overflowLocationList = findAroundLocationList(lackLocation);
			
			for (Location overflowLocation : overflowLocationList) {
				boolean isWorkingLocation = false;
				for (Location[] locations : workList) {
					if (locations[WorkLocationType.Overflow.getValue()].getId() == overflowLocation.getId()) {
						isWorkingLocation = true;
						break;
					}
				}
				if (isWorkingLocation)
					continue;
				
				Position overflowPosition = overflowLocation.getPosition();
				
				int distance = lackPosition.getDistance(overflowPosition);
				if (distance < minDistance) {
					minDistance = distance;
					minDistLocation = overflowLocation;
				}
			}
			
			if (minDistLocation != null) {
				workList.add(new Location[] { lackLocation, minDistLocation });
			}
		}
		return workList;
	}
	
	private ArrayList<Location> findLackLocationList() {
		ArrayList<Location> locationList = new ArrayList<Location>(); 
		
		for (Map.Entry<Integer, Location> entry : locationMap.entrySet()) {
			Location location = entry.getValue();
			
			if (location.needStuff()) {
				locationList.add(location);
			}
		}
		return locationList;
	}
	
	private ArrayList<Location> findOverflowLocationList() {
		ArrayList<Location> locationList = new ArrayList<Location>(); 
		
		for (Map.Entry<Integer, Location> entry : locationMap.entrySet()) {
			Location location = entry.getValue();
			
			if (location.isOverflow()) {
				locationList.add(location);
			}
		}
		return locationList;
	}
	
	private ArrayList<Location> findAroundLocationList(Location centerLocation) {
		ArrayList<Location> locationList = null;
		
		for (int i = 1; i < aspectSize; i++) {
			locationList = findAroundLocationList(centerLocation, i);
			
			if (locationList.isEmpty() == false)
				break;
		}
		return locationList;
	}
	
	private ArrayList<Location> findAroundLocationList(Location centerLocation, int targetDistance) {
		ArrayList<Location> locationList = new ArrayList<Location>();

		for (Map.Entry<Integer, Location> entry : locationMap.entrySet()) {
			Location location = entry.getValue();
			int distance = centerLocation.getPosition().getDistance(location.getPosition()); 
			if (distance == targetDistance) {
				if (location.needStuff() == false) {
					locationList.add(location);
				}
			}
		}
		return locationList;
	}
}