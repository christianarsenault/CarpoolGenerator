package CarPool;

public class Person implements Comparable<Person> {
	private String name; //Person's name
	private boolean canDrive; //if Person can't drive, they will pay a driver
	private int capacity; //how many people Person's car can hold
	private int priority; //Person's priority in terms of turn to drive
	private String workDays;
	
	public Person(String n, boolean d, int c, int p, String workDays) {
		this.name = n;
		this.canDrive = d;
		this.capacity = c;
		this.priority = p;
		this.workDays = workDays;
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
		this.priority++;
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
	
}
