
//C6. Preemptive Round Robin SCheduling (RR)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PreemptiveRoundRobin extends CPUScheduling {

	@Override
	public void process() {
		List<Event> eventList = this.getEvent();
		List<Process> processes = Copy.deepCopy(this.getProcesses());
		List<Process> arrived = new ArrayList<Process>(); // store elements that have arrived and can be run
		List<String> arrivedNames = new ArrayList<String>();
		int time;
		int timeQuantum = this.getTimeQuantum();
		int index = 0;

		Collections.sort(processes, Comparator.comparingInt(Process::getArrivalTime)); // sort by arrival time

		time = processes.get(0).getArrivalTime(); // arrival time of first event

		while (!processes.isEmpty()) {
			for (Process p : processes) { // stores all the arrived elements to compare by burst time
				if (p.getArrivalTime() <= time && !arrivedNames.contains(p.getName())) { // if the times <= the first
																							// Process' arrival time,
																							// then add to
					// arrived to be compared

					arrivedNames.add(p.getName());
					arrived.add(p);
				}
			}

			if (!arrived.isEmpty()) {// if there is something arrived
				System.out.println(index);
				if (arrived.get(index).getBurstTime() <= timeQuantum) { // if burst time is shorter than or equal to
																		// timequantum
					eventList.add(
							new Event(arrived.get(index).getName(), time, time + arrived.get(index).getBurstTime()));
					time += arrived.get(index).getBurstTime();

					int j = index(processes, arrived.get(index).getName());

					for (Process p : this.getProcesses()) {// set turnaround time and waiting time
						if (p.getName().equals(arrived.get(index).getName())) {
							// end time - execution burst time - arrival time
							p.setWaitingTime(eventList.get(eventList.size() - 1).getEndTime() - p.getBurstTime()
									- p.getArrivalTime());
							// end time - arrival time
							p.setTurnaroundTime(eventList.get(eventList.size() - 1).getEndTime() - p.getArrivalTime());
							break;
						}
					}
					arrived.remove(index);
					processes.remove(j);
					if (index > arrived.size() - 1) {// if at end of arrived
						index = 0;
					}

				} else {// if burst time > that time quantum
					eventList.add(new Event(arrived.get(index).getName(), time, time + timeQuantum));
					arrived.get(index).setBurstTime(arrived.get(index).getBurstTime() - timeQuantum);
					time += timeQuantum;
					if (index >= arrived.size() - 1) { // reset if reached the end of arrived processes
						index = 0;
					} else if (index < arrived.size() - 2) {
						index++;
					}
				}
			} else { // arrived is empty reset index
				index = 0;
			}
		}

	}

	private int index(List<Process> P, String name) {// assumes that process name is valid and unique
		for (Process p : P) {
			if (p.getName().equals(name)) {
				return P.indexOf(p); // returns index of where name is found
			}
		}
		return -1; // should not run if name is valid
	}
}