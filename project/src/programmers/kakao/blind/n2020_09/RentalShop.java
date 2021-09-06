package programmers.kakao.blind.n2020_09;

import java.util.LinkedList;
import java.util.Queue;

public class RentalShop {

	private int id;
	private int positionX;
	private int positionY;

	private Queue<Bike> bikeQueue;
	
	public static final int LENGTH = 100;
	
	public RentalShop(int id, int positionX, int positionY) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		
		bikeQueue = new LinkedList<Bike>();
		for (int i = 0; i < 4; i++) {
			bikeQueue.add(new Bike());
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Bike rental() {
		return bikeQueue.poll();
	}
}
