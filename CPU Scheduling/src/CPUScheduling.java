import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduling {
	private List<Process> processes;
	public CPUScheduling() {
		processes = new ArrayList();
	}
	
	public abstract void process();
	
	public void add(Process P) { //add a process to list
		processes.add(P);
	}
}
