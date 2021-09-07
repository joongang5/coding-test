package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;
import java.util.HashMap;

public class Scenario {

	private ServiceArea area;
	
	private HashMap<Integer, Truck> truckMap;
	
	public Scenario() {
		area = new ServiceArea();
	}
	
	public HashMap<Integer, ArrayList<Integer>> update(HashMap<Integer, Integer> locationMap, HashMap<Integer, Truck> truckMap) {
		if (area.wasInit() == false)
			area.init(locationMap);
		else
			area.update(locationMap);
		
		this.truckMap = truckMap;
		
		return generateCommands();
	}
	
	private HashMap<Integer, ArrayList<Integer>> generateCommands() {
		HashMap<Integer, ArrayList<Integer>> commands = new HashMap<Integer, ArrayList<Integer>>();
		
		
		
		return commands;
	}
}
