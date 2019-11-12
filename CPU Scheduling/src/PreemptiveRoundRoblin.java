//C6. Preemptive Round Robin SCheduling (RR)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.utli.time;

public class PreemptiveRoundRoblin extends CPUScheduling{

	List<Process> rows = Utility.deepCopy(this.getRows());
	int time =0;
	int timeQuantum = this.getTimeQuantum();
	
	while(!rows.isEmpty())
	{
		Process row = rows.get(0);
		
		if(time<row.getArrivalTime())
		{
			time=row.getArrivalTime();
		}
		
		int bt = row.getBurstTime()<timeQuantum ? row.getBurstTime() : timeQuantum);
		this.getTimeline().add(new Event(row.getProcessName(), time, time + bt));
		time += bt;
		rows.remove(0);
		
		if(row.geBurstTime()>timeQuantum)
		{
			row.setBurstTime(row.getBurstTime()-timeQuantum);
			for(int i=0;i<rows.size();i++)
			{
				if(rows.get(i).getArrivalTime()>time)
				{
					rows.add(i,row);
					break;
				}
				else if (i==rows.size()-1)
				{
					rows.add(row);
					break;
				}
		}
	}
}
