import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//C4. Nonpreemptive Priority Scheduling (NPS)
public class NonpreemptivePriority extends CPUScheduling {

	@Override
	public void process() {
		// sort by arrival time
		Collections.sort(this.getProcesses(), Comparator.comparingInt(Process::getArrivalTime));
		
		List<Event> eventList = this.getEvent();
		List<Process> P = Copy.deepCopy(this.getProcesses());
		
		while (!P.isEmpty()) {
			int time = P.get(0).getArrivalTime(); // stores the time
			List<Process> arrived = new ArrayList<Process>(); // store elements that have arrived and can be run

			for (Process p : P) { // stores all the arrived elements to compare by burst time
				if (p.getArrivalTime() <= time) { // if the times <= the first Process' arrival time, then add to
													// arrived to be compared
					arrived.add(p);
				}
			}

			Collections.sort(arrived, Comparator.comparingInt(Process::getPriority));// sort arrived by priority to
																						// run
			
			if (eventList.isEmpty()) { // first event
				eventList.add(new Event(arrived.get(0).getName(), arrived.get(0).getArrivalTime(),
						arrived.get(0).getBurstTime()));
			} else { // not first event
				Event prevEvent = eventList.get(eventList.size() - 1); // need the time previous event ended
				eventList.add(new Event(arrived.get(0).getName(), prevEvent.getEndTime(),
						prevEvent.getEndTime() + arrived.get(0).getBurstTime()));
			}

			for (Process p : this.getProcesses()) { // set waiting and turn around time
				if (p.getName().equals(arrived.get(0).getName())) {
					// (CPU start execution time) - (process arrival time)
					p.setWaitingTime(
							eventList.get(eventList.size() - 1).getStartTime() - arrived.get(0).getArrivalTime());
					// (CPU finish execution time) - (arrival time);
					p.setTurnaroundTime(
							eventList.get(eventList.size() - 1).getEndTime() - arrived.get(0).getArrivalTime());
				}
			}

			for (int i = 0; i < P.size(); i++) { // remove process that is finished
				if (P.get(i).getName().equals(arrived.get(0).getName())) {
					P.remove(i);
					break;
				}
			}
		}
	}

}
