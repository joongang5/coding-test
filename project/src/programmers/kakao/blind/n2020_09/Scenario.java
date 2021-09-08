package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;
import java.util.HashMap;

public class Scenario {

	private LocationManager locationManager;
	private TruckManager truckManager;
	
	public Scenario() {
		locationManager = new LocationManager();
	}
	
	public HashMap<Integer, ArrayList<Integer>> update(HashMap<Integer, Integer> locationMap, HashMap<Integer, Truck> truckMap) {
		if (locationManager.wasInit() == false)
			locationManager.init(locationMap);
		else
			locationManager.update(locationMap);
		
		if (truckManager.wasInit() == false)
			truckManager.init(truckMap, locationManager.getLocationMap());
		else
			truckManager.update(truckMap, locationManager.getLocationMap());
		
		return generateCommands();
	}
	
	private HashMap<Integer, ArrayList<Integer>> generateCommands() {
		ArrayList<Location[]> workLocationList = locationManager.getWorkLocationList();
		
		return truckManager.generateCommands(workLocationList);
	}
}
