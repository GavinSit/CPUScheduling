import java.util.ArrayList;
import java.util.List;

public class Copy {
	// makes a copy that is unassociated with previous list
	public static List<Process> deepCopy(List<Process> old) {
		List<Process> newList = new ArrayList<Process>();
		for (Process P : old) { // makes new unassociated copy of Process and stores in unnassociated List
			newList.add(new Process(P.getName(), P.getArrivalTime(), P.getBurstTime(), P.getPriority()));
		}
		return newList;
	}
}
