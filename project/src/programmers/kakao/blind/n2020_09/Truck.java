package programmers.kakao.blind.n2020_09;

import java.util.LinkedList;
import java.util.Queue;

public class Truck {

	private int id;
	private int locationId;
	private int loadedBikeCount;
	
	private Position position;
	
	private Queue<TruckWorkType> workQueue;
	private int[] workLocationIds;
	
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
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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

	public boolean isWorking() {
		return workQueue.isEmpty() == false;
	}

	public boolean hasWorkingLocation(int lackId, int overflowId) {
		if (workLocationIds == null)
			return false;
		
		if (workLocationIds[WorkLocationType.Lack.getValue()] == lackId)
			return true;

		if (workLocationIds[WorkLocationType.Overflow.getValue()] == overflowId)
			return true;
		
		return false;
	}
	
	public void addWorkLocationIds(int lackId, int overflowId) {
		workLocationIds = new int[WorkLocationType.Max.getValue()];
		workLocationIds[WorkLocationType.Lack.getValue()] = lackId;
		workLocationIds[WorkLocationType.Overflow.getValue()] = overflowId;
	}
	
	public void clearWorkLocationIds() {
		workLocationIds = null;
	}
}
