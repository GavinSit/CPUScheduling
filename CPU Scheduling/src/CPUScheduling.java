import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduling {
	private List<Process> processes; // stores the processes that will happen
	private List<Event> event; // stores the times that the CPU processes the queued processes
	private int timeQuantum;

	public CPUScheduling() {
		processes = new ArrayList<Process>();
		event = new ArrayList<Event>();
		timeQuantum = 1;
	}

	public abstract void process();

	public void add(Process P) { // add a process to list
		processes.add(P);
	} 

	public double averageWaitingTime() {
		double sum = 0;

		for (Process P : processes) {
			sum += P.getWaitingTime();
		}
		sum = sum / processes.size();
		sum = Math.round(sum * 1000.0) / 1000.0; // round to 3 decimals
		return sum;
	}

	public double averageTurnaroundTime() {
		double sum = 0;

		for (Process P : processes) {
			sum += P.getTurnaroundTime();
		}
		sum = sum / processes.size();
		sum = Math.round(sum * 1000.0) / 1000.0; // round 3 decimals
		return sum;
	}

	public List<Process> getProcesses() { // return processes
		return processes;
	}

	public List<Event> getEvent() { // return events
		return event;
	}

	public void setTimeQuantum(int timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	public int getTimeQuantum() {
		return timeQuantum;
	}
}
