
public class Event {
	private String name; // process name
	private int startTime; // start of process
	private int endTime; // end of process

	public Event(String name, int startTime, int endTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}

}
