package CarPool;
import java.io.*;
import java.util.ArrayList;

public class IO {
	public static ArrayList<Person> readWorkers(String filename){
        //initialize variables to be used
        FileReader fr;
        BufferedReader br;
        String currLine = "";
        String [] currPerson = new String [4];
        ArrayList<Person> allWorkers = new ArrayList<Person>();
        boolean canDrive = false;
        //try/catch checked errors for reading files
        try{
        	fr = new FileReader(filename);
            br = new BufferedReader(fr);
            while( (currLine = br.readLine()) != null) {
            	currPerson = currLine.split(",");
            	if(currPerson[1].equals("y")) {
            		canDrive = true;
            	}else {
            		canDrive = false;
            	}
            	allWorkers.add(new Person(currPerson[0],canDrive,Integer.parseInt(currPerson[2]),0, currPerson[3]));
            	currPerson = null;
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
