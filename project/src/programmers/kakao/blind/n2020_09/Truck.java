package programmers.kakao.blind.n2020_09;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Truck {

	private int id;
	private int locationId;
	private int loadedBikeCount;
	
	private Position position;
	
	private Queue<TruckWorkType> workQueue;
	
	private static final int MAX_WORK_COUNT = 10;
	private static final int MAX_BIKE_COUNT = 20;
	
	public Truck(int id, int locationId, int loadedBikeCount) {
		this.id = id;
		this.locationId = locationId;
		this.loadedBikeCount = loadedBikeCount;

		position = new Position();
		workQueue = new LinkedList<TruckWorkType>();
	}
	
	public int getId() {
		return id;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public int getLoadedBikeCount() {
		return loadedBikeCount;
	}
	
	public void setLoadedBikeCount(int loadedBikeCount) {
		this.loadedBikeCount = loadedBikeCount;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void addWork(TruckWorkType workType) {
		if (workQueue.size() >= MAX_WORK_COUNT)
			return;
		
		workQueue.add(workType);
		
		if (workType == TruckWorkType.PutBike) {
			loadedBikeCount++;
		} else if (workType == TruckWorkType.DropBike) {
			loadedBikeCount--;
		}
	}
	
	public TruckWorkType pollWork() {
		return workQueue.poll();
	}
}
