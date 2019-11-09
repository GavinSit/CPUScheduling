
public class main {

	public static void main(String[] args) {
		// TODO fill in the rest
		System.out.println("Nonpreemptive First-Come, First Serve Scheduling");
		fcfs();
		System.out.println("Nonpreemptive Shortest Job First Scheduling");
		sjf();
		System.out.println("Preemptive Shortest Remaining Time First Scheduling");
		
		System.out.println("Nonpreemptive Priority Scheduling");
		
		System.out.println("Preemptive Priority Scheduling");
		
		System.out.println("Preemptive Round-Robin Schedling");
	}
	
	public static void print() { //prints results
		
	}
	
	public static void fcfs() { 
		CPUScheduling fcfs = new FirstComeFirstServe();
		
	}
	
	public static void sjf() {
		
	}
	
	

}
