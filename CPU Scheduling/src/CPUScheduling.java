import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduling {
	private List<Process> processes; //stores the processes that will happen
	private List<Event> event; //stores the times that the CPU processes the queued processes
	
	public CPUScheduling() {
		processes = new ArrayList<Process>();
		event = new ArrayList<Event>();
	}
	
	public abstract void process();
	
	public void add(Process P) { //add a process to list
		processes.add(P);
	}
	
	public double averageWaitingTime() {
		double sum = 0;
		
		for(Process P: processes) {
			sum += P.getWaitingTime();
		}
		
		return Math.round(sum * 100.0)/ 100.0; //returns sum rounded to 2 decimals
	}
	
	public double averageTurnaroundTime() { 
		double sum = 0;
		
		for(Process P: processes) {
			sum += P.getTurnaroundTime();
		}
		
		return Math.round(sum * 100.0)/ 100.0; //returns sum rounded to 2 decimals
	}
	
	public List<Process> getProcesses() { //return processes
		return processes;
	}
	
	public List<Event> getEvent() { //return events
		return event;
	}
	
}
