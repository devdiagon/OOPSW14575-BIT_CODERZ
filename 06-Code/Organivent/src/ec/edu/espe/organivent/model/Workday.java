package ec.edu.espe.organivent.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.utils.ManageJson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Workday {

    private int workdayId;
    private Schedule entryTime;
    private Schedule departureTime;
    private float hoursWorked;
    
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
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeWorkday(workdayList);
                    scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    workdayList.add(addWorkday());
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
    
    public static Workday addWorkday() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the workday ID:");
        int workdayId = scanner.nextInt();
        System.out.println("Enter the workday start time:");
        Schedule entryTime = createSchedule();
        System.out.println("Enter the workday departure time:");
        Schedule departureTime = createSchedule();

        Workday workday = new Workday(workdayId, entryTime, departureTime);

        return workday;
    }
    
    public static Schedule createSchedule() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("   Enter the year:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("   Enter the month:");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("   Enter the day:");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.println("   Enter the hour:");
        int hour = scanner.nextInt();
        scanner.nextLine();
        System.out.println("   Enter the minutes:");
        int minutes = scanner.nextInt();
        scanner.nextLine();

        return new Schedule(year, month, day, hour, minutes);
    }
    
    
    public static void seeWorkday(ArrayList<Workday> workdayList){
        
         for(Workday currentWorkday : workdayList) {
            System.out.print("\nWorkday: " + currentWorkday);
        }
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
