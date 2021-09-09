package programmers.kakao.blind.n2020_09;

import java.util.HashMap;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) {
		APIHelper.startAPI();

		Scenario scenario = new Scenario();
		
		int tryCount = 0;
		while (true) {
			tryCount++;
			if (tryCount == 16) {
				System.out.println(tryCount);
			}
			
			HashMap<Integer, Integer> locationMap = APIHelper.LocationsAPI();
			
			HashMap<Integer, Truck> truckMap = APIHelper.TrucksAPI();

			scenario.update(locationMap, truckMap);
			
			HashMap<Integer, Queue<TruckWorkType>> commands = scenario.generateCommands();
			
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