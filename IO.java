package CarPool;
import java.io.*;
import java.util.ArrayList;

public class IO {
	public static ArrayList<Person> readWorkers(String filename){
        //initialize variables to be used
        FileReader fr;
        BufferedReader br;
        String currLine = "";
        ArrayList<String> currPerson = new ArrayList<String>();
        ArrayList<Person> allWorkers = new ArrayList<Person>();
        ArrayList<Integer> currPersonDaysOff = new ArrayList<Integer>();
 
        boolean canDrive = false;
        //try/catch checked errors for reading files
        try{
        	fr = new FileReader(filename);
            br = new BufferedReader(fr);
            while((currLine = br.readLine()) != null) {
            	for(String s : (currLine.split(","))){
            		currPerson.add(currPerson.size(), s);
            	}
            	if(currPerson.size()>=4){
            		for(int i=4;i<currPerson.size();i++) {
            			currPersonDaysOff.add(Integer.parseInt(currPerson.get(i)));
            		}
            	}
            	if(currPerson.contains("y")) {
            		canDrive = true;
            	}else {
            		canDrive = false;
            	}
            	allWorkers.add(new Person(currPerson.get(0),canDrive,Integer.parseInt(currPerson.get(2)),0, currPerson.get(3),currPersonDaysOff));
            	currPerson.clear();
            	currPersonDaysOff = new ArrayList<Integer>();
            }
        //catching checked errors with appropriate error messages   
        }catch(FileNotFoundException e){
            System.out.println("Error, cannot find file");
        }catch(IOException exc){
            System.out.println("Error with file");
        }
        return allWorkers;
    }
	
	public static void writeToCsv(String s) {
		FileWriter fw;
        BufferedWriter bw;
        //try/catch checked errors for reading files
        try{
        	fw = new FileWriter("Schedule.csv",true);
            bw = new BufferedWriter(fw);
            
            bw.write(s);
            bw.flush();
            bw.close();
        //catching checked errors with appropriate error messages   
        }catch(FileNotFoundException e){
            System.out.println("Error, cannot find file");
        }catch(IOException exc){
            System.out.println("Error with file");
        }
	}
	
}
