package programmers.kakao.blind.n2020_09;

public class SimulateResponse {

	private String status;
	private int time;
	private int failedRequestsCount;
	private float distance;

	public SimulateResponse(String status, int time, int failedRequestsCount, float distance) {
		this.status = status;
		this.time = time;
		this.failedRequestsCount = failedRequestsCount;
		this.distance = distance;
	}

	public String getStatus() {
		return status;
	}

	public int getTime() {
		return time;
	}

	public int getFailedRequestsCount() {
		return failedRequestsCount;
	}

	public float getDistance() {
		return distance;
	}
}
