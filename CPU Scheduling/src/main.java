
public class main{

	public static void main(String[] args) {
		// TODO fill in the rest
		System.out.println("Nonpreemptive First-Come, First Serve Scheduling");
		fcfs();
		System.out.println("\nNonpreemptive Shortest Job First Scheduling");
		sjf();
		System.out.println("\nPreemptive Shortest Remaining Time First Scheduling");
		//srtf(); doesnt work yet
		System.out.println("\nNonpreemptive Priority Scheduling");

		System.out.println("\nPreemptive Priority Scheduling");

		System.out.println("\nPreemptive Round-Robin Schedling");
	}

	public static void fcfs() {
		CPUScheduling fcfs = new FirstComeFirstServe();
		fcfs.add(new Process("P1", 0, 2));
		fcfs.add(new Process("P2", 3, 1));
		fcfs.add(new Process("P3", 2, 4));
		fcfs.add(new Process("P4", 5, 8));
		fcfs.add(new Process("P5", 6, 2));
		fcfs.process();
		print(fcfs);
	}

	public static void sjf() {
		CPUScheduling sjf = new ShortestJobFirst();
		sjf.add(new Process("P1", 1, 4));
		sjf.add(new Process("P2", 3, 5));
		sjf.add(new Process("P3", 6, 3));
		sjf.add(new Process("P4", 0, 3));
		sjf.add(new Process("P5", 13, 1));
		sjf.add(new Process("P6", 6, 3));
		sjf.add(new Process("P7", 6, 2));
		sjf.process();
		print(sjf);
	}
	
	public static void srtf() {
		CPUScheduling srtf = new ShortestRemainingTimeFirst();
		srtf.add(new Process("P1", 0, 8));
		srtf.add(new Process("P2", 1, 4));
		srtf.add(new Process("P3", 2, 9));
		srtf.add(new Process("P4", 3, 5));
		srtf.process();
		print(srtf);
	}

	public static void print(CPUScheduling obj) { // prints results
		System.out.println(
				"P - Process Name  AT - Arrival Time  BT - Burst Time  WT - Total Waiting Time  TT - Turnaround Time  ST - Start Time  ET - End Time\n");

		System.out.println("P\tAT\tBT\tWT\tTT");
		for (Process P : obj.getProcesses()) {
			System.out.println(P.getName() + "\t" + P.getArrivalTime() + "\t" + P.getBurstTime() + "\t"
					+ P.getWaitingTime() + "\t" + P.getTurnaroundTime());
		}

		System.out.println("\nEvents\nP\tST\tET");
		for(Event E: obj.getEvent()) {
			System.out.println(E.getName() + "\t" + E.getStartTime() + "\t" + E.getEndTime());
		}
		
		System.out.println("\nAverage Waiting time: " + obj.averageWaitingTime());
		System.out.println("Average Turnaround Time: " + obj.averageTurnaroundTime() + "\n");
	}

}
