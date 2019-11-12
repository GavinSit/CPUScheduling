import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduling {
	private List<Process> processes; // stores the processes that will happen
	private List<Event> event; // stores the times that the CPU processes the queued processes
	private final List<Row> rows;
	private final List<Event> timeline;
	private int timeQuantum;
	
	public CPUScheduling() {
		processes = new ArrayList<Process>();
		event = new ArrayList<Event>();
		rows = new ArrayList();
		timeQuantum = 1;
	}

	public abstract void process();

	public void add(Process P) { // add a process to list
		processes.add(P);
	}
	
	public boolean add(Row row)
   	{
    	    return rows.add(row);
    	}
	

	public double averageWaitingTime() {
		double sum = 0;

		for (Process P : processes) {
			sum += P.getWaitingTime();
		}
		sum = sum /processes.size();
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

	public Row getRow(String process)
    	{
        	for (Row row : rows)
        {
            if (row.getProcessName().equals(process))
            {
                return row;
            }
        }
        
        	return null;
    	}
    
    	public List<Row> getRows()
    	{
        	return rows;
    	}
    
    	public List<Event> getTimeline()
    	{
        	return timeline;
    	}
	
	public void setTimeQuantum(int timeQuantum)
    	{
        	this.timeQuantum = timeQuantum;
    	}
    
    	public int getTimeQuantum()
    	{
        	return timeQuantum;
    	}
}
