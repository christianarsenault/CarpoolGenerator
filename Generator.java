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
		System.out.println("Enter 'true' if you would like table format, 'false' otherwise");
		boolean toTable = sc.nextBoolean();
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
				//if the worker is supposed to work that day, and doesn't have the day off then they work
				if(worker.getWorkDays().contains(d.getName()) && !(worker.getDaysOff().contains(d.getDate()))) {
					currDayWorkers.add(worker);
				}
			}
			
			
			//for each day of the current month, set the workers to currDayWorkers
			d.setWorkers(currDayWorkers);
			
			//**change this to output to csv file**
			
			if(d.getName().equals("S")) {
				System.out.println(determineDrivers(d,toTable));
			}else {
				System.out.print(determineDrivers(d,toTable));
			}
			
			//clear list for next use
			currDayWorkers.clear();
			
			
		}
		sc.close();
		//here we output a ratio of driver:passenger for each person to verify
		//everyone drives a fair amount
		
		for(Person q : allPeople) {
			System.out.println(q.getName() + ":   " + q.getDaysDriven() + ":" + q.getDaysPassenger());
		}
		
	}
	
	
	
	public static String determineDrivers(Day currDay, Boolean toTable) {
		String todaysDrivers;
		if(toTable) {
			todaysDrivers = currDay.getSpelledOutName() + " the " + currDay.getDate() + ":,";
		}else {
			todaysDrivers = currDay.getSpelledOutName() + " the " + currDay.getDate() + ": " + System.lineSeparator();
		}
		
		int carSeats = 0;
		PriorityQueue<Person> pq = new PriorityQueue<Person>(currDay.getWorkers());
		
		//initializing people ptrs to be used in loop below
		Person p = new Person();
		Person driver = new Person();
			
		while(currDay.getWorkers().size() > carSeats) {
			p = pq.remove();
			p.incrementPriority();
			
			if(p.getCanDrive()) {
				if(toTable) {
					todaysDrivers += p.getName() + " (Driver),";
				}else {
					todaysDrivers += p.getName() + " (Driver)" + System.lineSeparator();
				}
				carSeats += p.getCapacity();
				p.incrementDaysDriven();
				
			}else {
				driver = substituteDriver(pq);
				pq.remove(driver);
				carSeats += driver.getCapacity();
				if(toTable) {
					todaysDrivers += driver.getName() + " (Paid Driver)," + p.getName() + " (Pay " + driver.getName() + "),";
				}else {
					todaysDrivers += driver.getName() + " (Paid Driver)" + System.lineSeparator() + p.getName() + " (Pay " + driver.getName() + ")" + System.lineSeparator();
				}
				
				driver.incrementDaysPassenger();
				p.incrementDaysDriven();
				
			}
		}
		//print the remaining workers scheduled that day
		while(!pq.isEmpty()) {
			p = pq.remove();
			if(toTable) {
				todaysDrivers += p.getName() + ",";
			}else {
				todaysDrivers += p.getName() + System.lineSeparator();
			}
			if(toTable && pq.isEmpty()) {
				todaysDrivers += "-";
			}
			
			p.setDaysPassenger(p.getDaysPassenger() + 1);
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
			if(potentialDrivers.size()>0) {
				rand = rng.nextInt(potentialDrivers.size());
			}
								
		}
		return returnMe;
	}
}
