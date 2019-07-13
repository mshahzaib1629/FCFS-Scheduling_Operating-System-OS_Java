package application;


public class ResultSet {

	String id;
	int completionTime;
	int turnAroundTime;
	int waitingTime;
	
	
	public ResultSet(String id, int completionTime, int turnAroundTime, int waitingTime) {
		super();
		this.id = id;
		this.completionTime = completionTime;
		this.turnAroundTime = turnAroundTime;
		this.waitingTime = waitingTime;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public int getTurnAroundTime() {
		return turnAroundTime;
	}

	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	
	
}
