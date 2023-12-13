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
public class Staff {
    
    private int id;
    private String type;
    private Workday workday;
    private ArrayList<Employee> employees;
    private float totalStaffCost;
    
    public static ArrayList<Staff> getFromFile(){
        Type type = new TypeToken<ArrayList<Staff>>(){}.getType();
        ArrayList<Staff> staffList = ManageJson.readFile("staff.json",type);
        return staffList;
    }
    
    public static void menu(ArrayList<Staff> staffList){
         Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        int option;
        do {
            System.out.println("------------- Staff Manager -------------");
            System.out.println("-----------------------------------------");
            System.out.println("|    1.- See the current Staff List     |");
            System.out.println("|    2.- Add a new staff group          |");
            System.out.println("|    3.- Return                         |");
            System.out.println("_________________________________________");
            System.out.println("Select an option (1-3): ");
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    seeStaff(staffList);
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    staffList.add(addStaff(staffList.size()));
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
    
    private static Staff addStaff(int listSize){

        Scanner scanner = new Scanner(System.in, "ISO-8859-1");

        System.out.println("Enter the staff type:");
        String staffType = scanner.nextLine();
        
        Workday workday = Workday.searchForWorkday();
        ArrayList<Employee> employees = Employee.enterEmployees();
        
        float totalStaffCost = calculateTotalCost(employees,workday.gethoursWorked());

        return new Staff(listSize+1,staffType, workday, employees, totalStaffCost);
    }
    
    
    private static float calculateTotalCost(ArrayList<Employee> employees, float hoursWorked){
        float costPerHour = 0;
        float totalStaffCost = 0;
        
        for(Employee currentEmployee : employees) {
            costPerHour = ((currentEmployee.getHourlyWage()) * hoursWorked);
            totalStaffCost += costPerHour;
        }
        
        return totalStaffCost;
    }
   
    private static void seeStaff(ArrayList<Staff> staffList){
        
         for(Staff currentStaff : staffList) {
            System.out.print("\nStaff: " + currentStaff);
        }
    }
    
    public static ArrayList<Staff> enterStaff(){
        ArrayList<Staff> staffInEvent = new ArrayList<>();
        
        int searchId;
        int addMore=1;
        boolean passed=false;
        int sizeCount=0;
        
        do{
            sizeCount=0;
            System.out.println("Insert the Staff group Id to add");
            searchId = HandleInput.insertInteger();
            
            for(Staff currentStaff : staffInEvent) {
                if(currentStaff.getId() == searchId){
                    System.out.println("The Id: " + searchId + " is already in this event");
                    passed=false;
                    break;
                }
                sizeCount++;
            }
            if(sizeCount==staffInEvent.size()){
                addMore = addStaffInEvent(staffInEvent,searchId);
            }
        }while(passed==false && addMore == 1);
        
        return staffInEvent;
    }
    
    public static int addStaffInEvent(ArrayList<Staff> staffInEvent, int searchId){
        
        ArrayList<Staff> staffList = Staff.getFromFile();
        
        int addMore=1;
        boolean passed=false;
        int sizeCount=0;
        
        do{
            sizeCount=0;
            for(Staff currentStaff : staffList) {
                if(currentStaff.getId() == searchId){
                    staffInEvent.add(currentStaff);
                    passed=true;
                    System.out.println("Want to add another Staff group? 1) Yes - 2) No");
                    addMore = HandleInput.insertInteger();
                    break;
                }
                sizeCount++;
            }
            if(sizeCount==staffList.size()){
                System.out.println("The Id: " + searchId + " was not found");
                passed=true;
            }
        }while(passed==false && addMore == 1);
        
        return addMore;
    }
    
    public static float calculateStaffGroupCost(ArrayList<Staff> staffInEvent){
        float totalCost=0;
        
        for(Staff currentStaff : staffInEvent) {
            totalCost += currentStaff.getTotalStaffCost();
        }
        
        return totalCost;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-15s | %-14s|\n%-15s \nStaff Cost: %-8.2f", id,type,workday,employees,totalStaffCost);
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
}
