package CarPool;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;



public class Generator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
		ArrayList<Person> allPeople = new ArrayList<Person>();			
		allPeople = IO.readWorkers("workers.txt");
			

		//make month
		System.out.println("Enter the name of the first day of the month formatted as MTWthurFSsun (ie if June 1st is a Friday, enter F)");
		String firstDay = sc.nextLine();
		System.out.println("Enter the number of the first day of carpool (ie if June 2nd, enter 2)");
		int numFirstDay = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the number of days in the month");
		int numDays = sc.nextInt();
		int k=0;
		sc.nextLine();
		
		Month currMonth = new Month(firstDay,numFirstDay, numDays);
		ArrayList<Person> currDayWorkers = new ArrayList<Person>();
		for(Day d : currMonth.getDays()) {
			//work around because days starts at 1 not 0
			if(k<numFirstDay) {
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
			
			//**change this to output to csv file**
			System.out.println(determineDrivers(d));
			//clear list for next use
			currDayWorkers.clear();
		}
		sc.close();
	}
	
	
	
	public static String determineDrivers(Day currDay) {
		String todaysDrivers = currDay.getName() + " the " + currDay.getDate() + ": " + System.lineSeparator();
		int carSeats = 0;
		PriorityQueue<Person> pq = new PriorityQueue<Person>(currDay.getWorkers());
		
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
				driver = substituteDriver(pq);
				pq.remove(driver);
				carSeats += driver.getCapacity();
				todaysDrivers += p.getName() + " will pay " + driver.getName() + " to drive. " + System.lineSeparator();
			}
		}
		//print the remaining workers scheduled that day
		while(!pq.isEmpty()) {
			p = pq.remove();
			todaysDrivers += p.getName() + System.lineSeparator();
		}
		return todaysDrivers;	
	}



	private static Person substituteDriver(PriorityQueue<Person> pq) {
		Random rng = new Random();
		int rand = rng.nextInt(pq.size());
		ArrayList<Person> potentialDrivers = new ArrayList<Person>();
		for(Person p : pq) {
			potentialDrivers.add(p);
		}
		Person returnMe = new Person();
		returnMe.setCanDrive(false);
		while(returnMe.getCanDrive() == false) {
			returnMe = potentialDrivers.remove(rand);
			rand = rng.nextInt(potentialDrivers.size());					
		}
		return returnMe;
	}
}
