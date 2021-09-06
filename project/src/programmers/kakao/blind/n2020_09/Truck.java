package programmers.kakao.blind.n2020_09;

import java.util.Stack;

public class Truck {

	private int positionX;
	private int positionY;
	
	private Stack<TruckWork> workStack;
	private int maxCapacity;
	
	public Truck() {
		positionX = 0;
		positionY = 0;
		maxCapacity = 20;
		
		workStack = new Stack<TruckWork>();
	}
	
	public boolean tryPushWork(TruckWork work) {
		if (workStack.size() >= maxCapacity)
			return false;
		
		workStack.push(work);
		return true;
	}
	
	public void update(int elapsedTime) {
		
	}
}
