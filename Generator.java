package CarPool;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;



public class Generator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		String name = "";
		boolean canDrive = false;
		int capacity = 0;
		String workDays = "";
		String quitOrCont = "";
		
		ArrayList<Person> allPeople = new ArrayList<Person>();
		
		
		while(!quit) {
			System.out.println("Enter name of worker");
			name = sc.nextLine();
			System.out.println("Can " + name + " drive? (y/n)");
			if(sc.nextLine().equals("y")) {
				canDrive=true;			
			}else {
				canDrive=false;
			}
			System.out.println("How many people can " + name + " drive? ");
			capacity = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter " + name + "'s working days (\"MTWthurFSsun\")");
			workDays = sc.nextLine();
			
			
			System.out.println("Enter q for quit or any other key for next person");
			quitOrCont = sc.nextLine();
			if(quitOrCont.equals("q")) {
				quit = true;
			}
			
			allPeople.add(new Person(name,canDrive,capacity,0,workDays));
			
			
	
		}
		//make month
		System.out.println("Enter the name of the first day of the month (formatted as above, ie F for friday etc)");
		String firstDay = sc.nextLine();
		System.out.println("Enter the number of days in the month");
		int numDays = sc.nextInt();
		int k=0;
		sc.nextLine();
		
		Month currMonth = new Month(firstDay, numDays);
		ArrayList<Person> currDayWorkers = new ArrayList<Person>();
		for(Day d : currMonth.getDays()) {
			//work around because days starts at 1 not 0
			if(k==0) {
				k++;
				continue;
			}
			//add people that work that day to the currDayWorkers
			for(Person worker : allPeople) {
				if(worker.getWorkDays().contains(d.getName())) {
					currDayWorkers.add(worker);
				}
			}
			
			
			//for each day of the current month, set the workers to currDayWorkers
			d.setWorkers(currDayWorkers);
			
			
			System.out.println(determineDrivers(d));
			//clear list for next use
			currDayWorkers.clear();
		}
	}
	
	
	
	public static String determineDrivers(Day currDay) {
		String todaysDrivers = currDay.getName() + " the " + currDay.getDate() + ": " + System.lineSeparator();
		int carSeats = 0;
		PriorityQueue<Person> pq = new PriorityQueue<Person>(currDay.getWorkers());
		if(pq.isEmpty()) {
			return "Let's make this schedule!" + System.lineSeparator();
		}
		//initializing people ptrs to be used in loop below
		Person p = new Person();
		Person driver = new Person();
		
		while(currDay.getWorkers().size() > carSeats) {
			p = pq.remove();
			p.incrementPriority();
			
			if(p.getCanDrive()) {
				todaysDrivers += "Driver: " + p.getName() + System.lineSeparator();
				carSeats += p.getCapacity();
			}else {
				driver = new Person();
				driver.setCanDrive(false);
				while(driver.getCanDrive() == false && !pq.isEmpty()) {
					driver = pq.remove();
				}
				carSeats += driver.getCapacity();
				todaysDrivers += p.getName() + " will pay " + driver.getName() + " to drive. " + System.lineSeparator();
			}
		}
		while(!pq.isEmpty()) {
			p = pq.remove();
			todaysDrivers += p.getName() + System.lineSeparator();
		}
		return todaysDrivers;
	
	}
}
