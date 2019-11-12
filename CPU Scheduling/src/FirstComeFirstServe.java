import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Nonpreemptive First Come First Serve (FCFS)
//runs whatever event arrives first
public class FirstComeFirstServe extends CPUScheduling {
	@Override
	public void process() {
		// sort by arrival time to execute
		Collections.sort(this.getProcesses(), Comparator.comparingInt(Process::getArrivalTime)); 

		List<Event> eventList = this.getEvent();

		for (Process P : this.getProcesses()) {
			if (eventList.isEmpty()) { // if no processes have been executed yet
				// add event that starts at time 0 and runs instantly for the amount of burst
				// time because no queue as it's the first item
				eventList.add(new Event(P.getName(), P.getArrivalTime(), P.getArrivalTime() + P.getBurstTime()));
			} else {
				Event prevEvent = eventList.get(eventList.size() - 1); // need the time previous event ended
				eventList
						.add(new Event(P.getName(), prevEvent.getEndTime(), prevEvent.getEndTime() + P.getBurstTime()));

			}
			// (CPU start execution time) - (process arrival time)
			P.setWaitingTime(eventList.get(eventList.size() - 1).getStartTime() - P.getArrivalTime());
			// (CPU finish execution time) - (arrival time);
			P.setTurnaroundTime(eventList.get(eventList.size() - 1).getEndTime() - P.getArrivalTime());
		}
	}
}
