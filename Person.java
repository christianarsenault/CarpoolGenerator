package CarPool;
import java.util.ArrayList;

public class Person implements Comparable<Person> {
	private String name; //Person's name
	private boolean canDrive; //if Person can't drive, they will pay a driver
	private int capacity; //how many people Person's car can hold
	private int priority; //Person's priority in terms of turn to drive
	private String workDays;
	private int numWorkDays;
	private ArrayList<Integer> daysOff;
	private int daysDriven;


	

	public void setDaysPassenger(int daysPassenger) {
		this.daysPassenger = daysPassenger;
	}
	private int daysPassenger;
	
	public Person(String n, boolean d, int c, int p, String workDays, ArrayList<Integer> daysOff) {
		this.name = n;
		this.canDrive = d;
		this.capacity = c;
		this.priority = p;
		this.workDays = workDays;
		this.daysOff = daysOff;
		this.daysDriven = 0;
		this.daysPassenger = 0;
		
		int num = 0;
		//every four days taken off, your priority will increase by 1 more 
		int daysOffCorrection = (daysOff.size()/4);
		
		if(workDays.contains("M")) {
			num++;
		}
		if(workDays.contains("T")) {
			num++;
		}
		if(workDays.contains("W")) {
			num++;
		}
		if(workDays.contains("thur")) {
			num++;
		}
		if(workDays.contains("F")) {
			num++;
		}
		if(workDays.contains("S")) {
			num++;
		}
		if(workDays.contains("sun")) {
			num++;
		}
		//this allows the program to work for part time people, and more fair for those 
		//taking days off
		
		if(6-num-daysOffCorrection < 1) {
			this.numWorkDays = 1;
		}else {
			this.numWorkDays = 6-num+daysOffCorrection;
		}
	}
	
	public Person() {
	}
	
	
	
	
	
	
	
	
	//getters & setters
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean getCanDrive() {
		return canDrive;
	}


	public void setCanDrive(boolean canDrive) {
		this.canDrive = canDrive;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getWorkDays() {
		return workDays;
	}


	public void setWorkDays(String workDays) {
		this.workDays = workDays;
	}
	
	public void incrementPriority() {
		this.priority+= this.numWorkDays;
	}
	
	public void incrementDaysDriven() {
		this.daysDriven++;
	}
	public void incrementDaysPassenger() {
		this.daysPassenger++;
	}
	

	@Override
	public int compareTo(Person p) {
		if(this.priority < p.priority) {
			return -1;
		}else if(this.priority == p.priority) {
			return 0;
		}else {
			return 1;
		}
	}
	public ArrayList<Integer> getDaysOff(){
		return this.daysOff;
	}
	
	public void setDaysDriven(int daysDriven) {
		this.daysDriven = daysDriven;
	}

	public int getDaysPassenger() {
		return daysPassenger;
	}
	public int getDaysDriven() {
		return daysDriven;
	}
	
}
