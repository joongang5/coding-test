package programmers.kakao.blind.n2020_09;

import java.util.HashMap;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) {
		APIHelper.startAPI();

		Scenario scenario = new Scenario();
		
		while (true) {
			HashMap<Integer, Integer> locationMap = APIHelper.LocationsAPI();
			
			HashMap<Integer, Truck> truckMap = APIHelper.TrucksAPI();

			HashMap<Integer, Queue<TruckWorkType>> commands = scenario.update(locationMap, truckMap);
			
			SimulateResponse res = APIHelper.SimulateAPI(commands);
			if (res.getStatus() == "finished") {
				APIHelper.ScoreAPI();
				break;	
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}