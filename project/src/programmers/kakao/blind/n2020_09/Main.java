package programmers.kakao.blind.n2020_09;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args) {
		APIHelper.startAPI();

		Scenario scenario = new Scenario();
		
		while (true) {
			HashMap<Integer, Integer> locationMap = APIHelper.LocationsAPI();
			HashMap<Integer, Truck> truckMap = APIHelper.TrucksAPI();

			HashMap<Integer, ArrayList<Integer>> commands = scenario.update(locationMap, truckMap);
			
			SimulateResponse res = APIHelper.SimulateAPI(commands);
			if (res.getStatus() == "finished")
				break;
		}
	}
}