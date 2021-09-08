package programmers.kakao.blind.n2020_09;

public class SimulateResponse {

	private String status;
	private int time;
	private String failedRequestsCount;
	private String distance;

	public SimulateResponse(String status, int time, String failedRequestsCount, String distance) {
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

	public String getFailedRequestsCount() {
		return failedRequestsCount;
	}

	public String getDistance() {
		return distance;
	}
}
