package ec.edu.espe.organivent.view;

import ec.edu.espe.organivent.model.Artist;
import ec.edu.espe.organivent.model.Employee;
import ec.edu.espe.organivent.model.Schedule;
import ec.edu.espe.organivent.model.Staff;
import ec.edu.espe.organivent.model.Workday;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class OrganiventSystem {
    public static void main(String[] args) {
        
        
    }
    
    public static void createEvent(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the event ID: ");
        int id = scanner.nextInt();
        System.out.println("Enter the event start time::");
        Schedule starTime = createSchedule();
        System.out.println("Enter the event end time:");
        Schedule endTime = createSchedule();
        System.out.println("Enter the artist and their information:");
        Artist artist = createArtist();
        System.out.println("Enter the approximate event costs:");
        float estimatedCost = scanner.nextFloat();
        
        
        
        
        
    }
    
    public static Schedule createSchedule(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the year:");
        int year = scanner.nextInt();
        System.out.println("Enter the month:");
        int month = scanner.nextInt();
        System.out.println("Enter the day:");
        int day = scanner.nextInt();
        System.out.println("Enter the hour:");
        int hour = scanner.nextInt();
        System.out.println("Enter the minutes:");
        int minutes = scanner.nextInt();
        
        Schedule schedule = new Schedule(year, month, day, hour, minutes);
        
        return schedule;
    }
    
    public static Artist createArtist(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the artist's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the artist's hiring cost:");
        float hiringCost = scanner.nextFloat();
        System.out.println("Enter the entry time: ");
        Schedule entryTime = createSchedule();
        System.out.println("Enter the departure time");
        Schedule departureTime = createSchedule();
        
        Artist artist = new Artist(name, hiringCost, entryTime, departureTime);
        
        return artist;
    }
    
    public static Staff createStaff(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the staff type:");
        String type = scanner.nextLine();
        System.out.println("Enter the workday for the staff:");
        Workday workday = createWorkday();
        System.out.println("Enter the employee and their information:");
        Employee employees = createEmployee();
        System.out.println("Enter the total cost of the staff:");
        float totalStaffCost = scanner.nextFloat();
        
        Staff staff = new Staff(type, workday, employees, totalStaffCost);        
        
    }
    
    public static Workday createWorkday(){
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
    
    public static Employee createEmployee(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the employee id: ");
        int employeeId = scanner.nextInt();
        System.out.println("Enter the employee's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the hourly wage:");
        float hourlyWage = scanner.nextFloat();
        
        Employee employee = new Employee(employeeId, name, hourlyWage);
        
        return employee;
    }
    
}
