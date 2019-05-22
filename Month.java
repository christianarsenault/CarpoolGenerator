package CarPool;
import java.util.ArrayList;

public class Month {
	private Day[] days;
	
	public Month(String firstDay, int numFirstDay, int lastDay) {
		int correction;
		//initialize day 0 of the month to null to make the array slots correspond to 
		//actual day numbers
		this.days = new Day[lastDay+1];
		for(int i=0;i<=lastDay;i++) {
			this.days[i] = new Day();
			//if 0th position, we fill with some placeholder
			if(i==0) {
				this.days[i].setName("filler");
				this.days[i].setDate(0);
				this.days[i].setWorkers(new ArrayList<Person>());
			}
		}
		//here we determine a correction factor based on what the number
		//the first day of the month is
		switch(firstDay) {
			case "M": correction=-1;
			          break;
			case "T": correction=0;
					  break;
			case "W": correction=1;
					  break;
			case "thur": correction=2;
					  break;
			case "F": correction=3;
					  break;
			case "S": correction=4;
					  break;
			case "sun": correction=5;
					  break;
			default: {
				System.out.println("first day not of right format");
				correction=0;
				break;
			}
		}
		for(int j=1; j<=lastDay; j++) {
			this.days[j].setDate(j);
			switch((j+correction)%7) {
				case 0: this.days[j].setName("M");
						this.days[j].setSpelledOutName("Monday");
						break;
				case 1: this.days[j].setName("T");
						this.days[j].setSpelledOutName("Tuesday");
						break;
				case 2: this.days[j].setName("W");
						this.days[j].setSpelledOutName("Wednesday");
						break;
				case 3: this.days[j].setName("thur");
						this.days[j].setSpelledOutName("Thursday");
						break;
				case 4: this.days[j].setName("F");
						this.days[j].setSpelledOutName("Friday");
						break;
				case 5: this.days[j].setName("S");
						this.days[j].setSpelledOutName("Saturday");
						break;
				case 6: this.days[j].setName("sun");
						this.days[j].setSpelledOutName("Sunday");
						break;
			}
		
				
		}
	}

	public Month(Day[] days) {
		this.days = days;
	}
	
	public Day[] getDays() {
		return this.days;
	}
	
	
}
