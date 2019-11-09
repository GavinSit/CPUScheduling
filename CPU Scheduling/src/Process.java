//Object storing the process information

public class Process {
	private String name; //stores process name
	private int turnaroundTime; //time it takes to finish process from queue time to finish
	private int priority; //priority of process
	private int waitingTime; //time waiting to execute in queue	
	private int arrivalTime; //time prcess arrives to CPU
	private int burstTime; //time that is required by CPU to execute process
	
	//fcfs uses this constructor
	public Process(String name, int arrivalTime, int burstTime) {
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
	}
	
	public Process(String name, int arrivalTime, int burstTime, int priority) {
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
	}

	
	
	////////////////getter and setter////////////////
	public String getName() { 
		return name;
	}
	
	public int getTurnaroundTime() {
		return turnaroundTime;
	}
	
	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
	
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
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
////////////////end getter and setter////////////////
	
}
