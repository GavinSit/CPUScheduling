
public class main {

	public static void main(String[] args) {
		// TODO fill in the rest
		System.out.println("Nonpreemptive First-Come, First Serve Scheduling");
		fcfs();
		System.out.println("\nNonpreemptive Shortest Job First Scheduling");
		sjf();
		System.out.println("\nPreemptive Shortest Remaining Time First Scheduling");

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

	}

	public static void print(CPUScheduling obj) { // prints results
		System.out.println(
				"P - Process Name  AT - Arrival Time  BT - Burst Time  WT - Total Waiting Time  TT - Turnaround Time");

		System.out.println("P\tAT\tBT\tWT\tTT");
		for (Process P : obj.getProcesses()) {
			System.out.println(P.getName() + "\t" + P.getArrivalTime() + "\t" + P.getBurstTime() + "\t"
					+ P.getWaitingTime() + "\t" + P.getTurnaroundTime());
		}
		/*
		 * for(int i = 0; i < obj.processes.size(); i++) { System.out.println( ); }
		 */

		System.out.println("Average Waiting time: " + obj.averageWaitingTime());
		System.out.println("Average Turnaround Time: " + obj.averageTurnaroundTime());
	}

}
