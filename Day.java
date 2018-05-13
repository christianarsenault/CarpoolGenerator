package CarPool;
import java.util.ArrayList;

public class Day {
	private String name;
	private ArrayList<Person> workers;
	private int date;
	
	//constructor if no params passed
	public Day() {
		super();
		this.name = null;
		this.workers = null;
	}
	
	
	public void setWorkers(ArrayList<Person> workers) {
		this.workers = workers;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Person> getWorkers() {
		if(this.workers == null) {
			return new ArrayList<Person>();
		}else {
			return this.workers;
		}
	}


	public int getDate() {
		return date;
	}


	public void setDate(int date) {
		this.date = date;
	}




	
	
}
