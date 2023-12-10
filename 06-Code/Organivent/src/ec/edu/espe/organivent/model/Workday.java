package ec.edu.espe.organivent.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.ManageJson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Workday {

    private int workdayId;
    private Schedule entryTime;
    private Schedule departureTime;
    private float hoursWorked;
    
    public static ArrayList<Workday> getFromFile(){
        Type type = new TypeToken<ArrayList<Workday>>(){}.getType();
        ArrayList<Workday> wordayList = ManageJson.readFile("workdays.json",type);
        return wordayList;
    }
    
    public static void menu(ArrayList<Workday> workdayList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----------- Workday Manager -----------");
            System.out.println("---------------------------------------");
            System.out.println("|      1.- See the current workays    |");
            System.out.println("|      2.- Add a new workday          |");
            System.out.println("|      3.- Return                     |");
            System.out.println("_______________________________________");
            System.out.println("Select an option (1-3): ");
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    seeWorkday(workdayList);
                    scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    workdayList.add(addWorkday(workdayList.size()));
                    ManageJson.writeFile("workdays.json",workdayList);
                    System.out.println("\nDone! Press any button to return");
                    scanner.nextLine();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 3);
    
    }
    
    public static Workday addWorkday(int listSize) {

        System.out.println("Enter the workday start time:");
        Schedule entryTime = Schedule.createEntrySchedule();
        System.out.println("Enter the workday departure time:");
        Schedule departureTime = Schedule.createDepartureSchedule(entryTime);

        Workday workday = new Workday(listSize+1, entryTime, departureTime);

        return workday;
    }
    
    public static void seeWorkday(ArrayList<Workday> workdayList){
        
         for(Workday currentWorkday : workdayList) {
            System.out.print("\nWorkday: " + currentWorkday);
        }
    }
    
    public static Workday searchForWorkday(){
        ArrayList<Workday> wordayList = getFromFile();
        Workday workday=null;
        int searchId=0;
        boolean passed=false;
        int sizeCount=0;
        
        do{
            System.out.println("Enter the workday ID for the staff:");
            searchId = HandleInput.insertInteger();
            
             for(Workday currentWorkday : wordayList) {
                if(currentWorkday.getWorkdayId()== searchId){
                    workday = currentWorkday;
                    passed=true;
                    break;
                }
                sizeCount++;
            }
            
            if(sizeCount==wordayList.size()){
                System.out.println("The Id: " + searchId + " was not found");
            }
        }while(passed==false);
        
        return workday;
    }

    @Override
    public String toString() {
        return getWorkdayId() + " in" + getEntryTime() + " out" + getDepartureTime();
    }

    public Workday(int workdayId, Schedule entryTime, Schedule departureTime) {
        this.workdayId = workdayId;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
        
        this.hoursWorked = departureTime.getHours() - entryTime.getHours();
    }

    public int getWorkdayId() {
        return workdayId;
    }

    public void setWorkdayId(int workdayId) {
        this.workdayId = workdayId;
    }
    
    public void setEntryTime(Schedule entryTime){
    }
    
    

    public void sethoursWorked(float hoursWorked){
        this.hoursWorked = hoursWorked;
    }
    
    public float gethoursWorked(){
        return hoursWorked;
    }

    public void setDepartureTime(Schedule departureTime){
    }

    /**
     * @return the entryTime
     */
    public Schedule getEntryTime() {
        return entryTime;
    }

    /**
     * @return the departureTime
     */
    public Schedule getDepartureTime() {
        return departureTime;
    }
}
