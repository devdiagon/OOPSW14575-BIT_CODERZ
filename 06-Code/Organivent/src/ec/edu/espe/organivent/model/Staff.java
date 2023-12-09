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
public class Staff {
    
    private static boolean header = false;
    private int id;
    private String type;
    private Workday workday;
    private ArrayList<Employee> employees;
    private float totalStaffCost;
    
    public static void menu(ArrayList<Staff> staffList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("------------- Staff Manager -------------");
            System.out.println("-----------------------------------------");
            System.out.println("|    1.- See the current Staff List     |");
            System.out.println("|    2.- Add a new staff group          |");
            System.out.println("|    3.- Return                         |");
            System.out.println("_________________________________________");
            System.out.println("Select an option (1-3): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeStaff(staffList);
                    scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    staffList.add(addStaff());
                    ManageJson.writeFile("staff.json",staffList);
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
    
    public static Staff addStaff(){
        
        Type type = new TypeToken<ArrayList<Employee>>(){}.getType();
        ArrayList<Employee> employeeList = ManageJson.readFile("employees.json",type);
        
        type = new TypeToken<ArrayList<Workday>>(){}.getType();
        ArrayList<Workday> wordayList = ManageJson.readFile("workdays.json",type);
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Employee> employeesinEvent = new ArrayList<>();
        
        
        int addMore = 1;
        int searchId = 0;
        float costPerHour = 0;
        float totalStaffCost = 0;
        Workday workday = null;
        
        System.out.println("Enter the staff id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the staff type:");
        String staffType = scanner.nextLine();
        System.out.println("Enter the workday ID for the staff:");
        searchId = scanner.nextInt();
        scanner.nextLine();
        
        for(Workday currentWorkday : wordayList) {
            if(currentWorkday.getWorkdayId()== searchId){
                workday = currentWorkday ;
            }
        }
        
        
        do{
            System.out.println("Insert the Employee Id to add");
            searchId = scanner.nextInt();
            scanner.nextLine();
            
            for(Employee currentEmployee : employeeList) {
                if(currentEmployee.getEmployeeId() == searchId){
                    employeesinEvent.add(currentEmployee);
                    
                    costPerHour = ((currentEmployee.getHourlyWage()) * (workday.gethoursWorked()));
                    totalStaffCost += costPerHour;
                }
            }

            System.out.println("Want to add another Employee? 1) Yes - 2) No");
            addMore = scanner.nextInt();
            scanner.nextLine();
        }while(addMore == 1);
        

        return new Staff(id,staffType, workday, employeesinEvent, totalStaffCost);
    }
    
    
    public static void seeStaff(ArrayList<Staff> staffList){
        
         for(Staff currentStaff : staffList) {
            System.out.print("\nStaff: " + currentStaff);
        }
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-15s | %-14s| %-15s |%8.2f|", id,type,workday,employees,totalStaffCost);
    }

    public Staff(int id, String type, Workday workday, ArrayList<Employee> employees, float totalStaffCost) {
        this.id = id;
        this.type = type;
        this.workday = workday;
        this.employees = employees;
        this.totalStaffCost = totalStaffCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Workday getWorkday() {
        return workday;
    }

    public void setWorkday(Workday workday) {
        this.workday = workday;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public float getTotalStaffCost() {
        return totalStaffCost;
    }

    public void setTotalStaffCost(float totalStaffCost) {
        this.totalStaffCost = totalStaffCost;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public void asingWorkday(Workday workday){
    }

    public void addEmployee(Employee employee){
    }

    public float calculateCost(Workday workday){
        
        return (0);
    }
}
