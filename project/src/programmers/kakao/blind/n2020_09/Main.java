package programmers.kakao.blind.n2020_09;

import java.util.HashMap;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) {
		int problem = 2;
		
		APIHelper.startAPI(problem);

		Scenario scenario = new Scenario();
		
		while (true) {
			HashMap<Integer, Integer> locationMap = APIHelper.LocationsAPI();
			
			HashMap<Integer, Truck> truckMap = APIHelper.TrucksAPI();

			scenario.update(locationMap, truckMap);
			
			HashMap<Integer, Queue<TruckWorkType>> commands = scenario.generateCommands();
			
			SimulateResponse res = APIHelper.SimulateAPI(commands);
			if (res.getStatus().equals("finished")) {
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