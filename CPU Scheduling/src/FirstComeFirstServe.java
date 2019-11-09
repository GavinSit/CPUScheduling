import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Nonpreemptive First Come First Serve (FCFS)
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
				eventList.add(new Event(P.getName(), P.getArrivalTime(), P.getBurstTime()));
			} else {
				Event prevEvent = eventList.get(eventList.size() - 1); // need the time previous event ended
				eventList
						.add(new Event(P.getName(), prevEvent.getEndTime(), prevEvent.getEndTime() + P.getBurstTime()));

			}
			// (process arrival time) - (CPU start execution time)
			P.setWaitingTime(P.getArrivalTime() - eventList.get(eventList.size() - 1).getStartTime());
			// (arrival time) - (CPU finish execution time);
			P.setTurnaroundTime(P.getArrivalTime() + eventList.get(eventList.size() - 1).getEndTime());
		}
	}
}
