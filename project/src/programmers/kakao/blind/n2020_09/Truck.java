package programmers.kakao.blind.n2020_09;

import java.util.LinkedList;
import java.util.Queue;

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
	
	public Queue<TruckWorkType> getWorkQueue() {
		return workQueue;
	}
	
	public void addWork(TruckWorkType workType) {
		boolean addible = true;
		
		if (workType == TruckWorkType.PutBike) {
			loadedBikeCount++;
			if (loadedBikeCount > MAX_BIKE_COUNT) {
				loadedBikeCount = MAX_BIKE_COUNT;
				addible = false;
			}
		} else if (workType == TruckWorkType.DropBike) {
			loadedBikeCount--;
			if (loadedBikeCount < 0) {
				loadedBikeCount = 0;
				addible = false;
			}
		}
		
		if (addible)
			workQueue.add(workType);
	}
	
	public TruckWorkType pollWork() {
		return workQueue.poll();
	}
}
