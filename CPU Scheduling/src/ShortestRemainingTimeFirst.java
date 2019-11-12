import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// C3. Preemptive Shortest Job First (SRTF)
//when new job arrives, checks for shortest job and runs shortest one first

public class ShortestRemainingTimeFirst extends CPUScheduling {

	@Override
	public void process() {
		List<Event> eventList = this.getEvent(); // stores the events of the the Processor
		List<Process> P = Copy.deepCopy(this.getProcesses());
		Collections.sort(P, Comparator.comparingInt(Process::getArrivalTime)); // sort by arrival time
		int index; // stores index of P where a name is found
		int time = P.get(0).getArrivalTime(); // stores the time

		while (!P.isEmpty()) {
			List<Process> arrived = new ArrayList<Process>(); // store elements that have arrived and can be run

			for (Process p : P) { // stores all the arrived elements to compare by burst time
				if (p.getArrivalTime() <= time) { // if the times <= the first Process' arrival time, then add to
													// arrived to be compared
					arrived.add(p);
				}
			}

			Collections.sort(arrived, Comparator.comparingInt(Process::getBurstTime));// sort arrived by burst time to
																						// run
			Process current = arrived.get(0);

			// if same process event already exist
			if (!eventList.isEmpty() && eventList.get(eventList.size() - 1).getName().equals(current.getName())) {
				eventList.get(eventList.size() - 1).setEndTime(++time);
				current.setBurstTime(current.getBurstTime() - 1); // subtract one from burst time
			} else { // if event doesnt already exist
				eventList.add(new Event(current.getName(), time, ++time));
				current.setBurstTime(current.getBurstTime() - 1); // decrement burst time
			}

			if (current.getBurstTime() == 0) { // if finished with process
				// set waiting and turnaround time after finished with that process
				for (Process p : this.getProcesses()) { // set waiting and turnaround time
					if (p.getName().equals(current.getName())) {
						// waiting time = end time - arrival time - burst time
						p.setWaitingTime(eventList.get(eventList.size() - 1).getEndTime() - p.getArrivalTime()
								- p.getBurstTime());
						System.out.println(p.getName() + ": " + p.getWaitingTime());
						// turnaround time = end time - arrival time
						p.setTurnaroundTime(eventList.get(eventList.size() - 1).getEndTime() - p.getArrivalTime());
						break;
					}
				}
				index = index(P, current.getName()); // gets index of name so we cna remove finished process
				P.remove(index);
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
