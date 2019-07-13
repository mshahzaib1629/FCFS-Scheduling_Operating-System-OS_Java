package application;


public class Process {

	String id;
	int arrivalTime;
	int burstTime;
	
	
	
	public Process() {
		super();
	}


	public Process(String id, int arrivalTime, int burstTime) {
		super();
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	
	
}
