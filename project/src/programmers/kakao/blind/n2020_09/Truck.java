package programmers.kakao.blind.n2020_09;

import java.util.Stack;

public class Truck {

	private int id;
	private int locationId;
	private int loadedBikeCount;
	
	private Stack<TruckWork> workStack;
	private static final int MAX_WORK_COUNT = 10;
	private static final int MAX_BIKE_COUNT = 20;
	
	public Truck(int id, int locationId, int loadedBikeCount) {
		this.id = id;
		this.locationId = locationId;
		this.loadedBikeCount = loadedBikeCount;
		
		workStack = new Stack<TruckWork>();
	}
	
	public void pushWork(TruckWork work) {
		if (workStack.size() >= MAX_WORK_COUNT)
			return;
		
		workStack.push(work);
	}
	
}
