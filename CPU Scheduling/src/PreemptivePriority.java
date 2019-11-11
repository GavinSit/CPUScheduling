import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//C5 Preemptive Priority Scheduling (PPS)
public class PreemptivePriority extends CPUScheduling {
    @Override
    public void process()
    {
        Collections.sort(this.getProcesses(), Comparator.comparingInt(Process::getArrivalTime));
        
        List<Event> eventList = this.getEvent();
        List<Process> P = Copy.deepCopy(this.getProcesses());
        int time = P.get(0).getArrivalTime();
        
        while (!P.isEmpty())
        {
            List<Process> arrived = new ArrayList<Process>();
            
            for (Process p : P)
            {
                if (p.getArrivalTime() <= time)
                {
                    arrived.add(p);
                }
            }
            
            Collections.sort(arrived, Comparator.comparingInt(Process::getPriority));
            
            Process p = arrived.get(0);
            eventList.add(new Event(p.getName(), time, ++time));
            p.setBurstTime(p.getBurstTime() - 1);
            
            if (p.getBurstTime() == 0)
            {
                for (int i = 0; i < P.size(); i++)
                {
                    if (P.get(i).getName().equals(p.getName()))
                    {
                        P.remove(i);
                        break;
                    }
                }
            }
        }
        
        for (int i = eventList.size() - 1; i > 0; i--)
        {
            List<Event> temp = eventList;
            
            if (temp.get(i - 1).getName().equals(temp.get(i).getName()))
            {
            	temp.get(i - 1).setEndTime(temp.get(i).getEndTime());
            	temp.remove(i);
            }
        }
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for (Process p : this.getProcesses())
        {
            map.clear();
            
            for (Event event : eventList)
            {
                if (event.getName().equals(p.getName()))
                {
                    if (map.containsKey(event.getName()))
                    {
                        int w = event.getStartTime() - (int) map.get(event.getName());
                        p.setWaitingTime(p.getWaitingTime() + w);
                    }
                    else
                    {
                        p.setWaitingTime(event.getStartTime() - p.getArrivalTime());
                    }
                    
                    map.put(event.getName(), event.getEndTime());
                }
            }
            
            p.setTurnaroundTime(p.getWaitingTime() + p.getBurstTime());
        }
    }
}
