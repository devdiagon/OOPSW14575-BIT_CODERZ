package ec.edu.espe.organivent.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.ManageJson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Staff {
    
    private int id;
    private String type;
    private ArrayList<Employee> employees;
    private float totalStaffCost;
    private int daysWorked;
    private int hoursWorked;
    
    
    public static ArrayList<Staff> getFromFile(){
        Type type = new TypeToken<ArrayList<Staff>>(){}.getType();
        ArrayList<Staff> staffList = ManageJson.readFile("data/staff.json",type);
        return staffList;
    }
    
    public static void menu(){
        ArrayList<Staff> staffList = Staff.getFromFile();
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        int option;
        do {
            System.out.println("---------------- Staff Manager ----------------");
            System.out.println("-----------------------------------------------");
            System.out.println("|    1.- See the current Staff List           |");
            System.out.println("|    2.- Add a new staff group                |");
            System.out.println("|    3.- Calculate the payment of an Staff    |");
            System.out.println("|    4.- Return                               |");
            System.out.println("_______________________________________________");
            System.out.println("Select an option (1-4): ");
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    seeStaff(staffList);
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    staffList.add(addStaff(staffList.size()));
                    ManageJson.writeFile("data/staff.json",staffList);
                    System.out.println("\nDone! Press any button to return");
                    scanner.nextLine();
                    break;
                case 3:
                    searchStaff(staffList);
                    System.out.println("\nDone! Press any button to return");
                    scanner.nextLine();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 4);
    
    }
    
    private static Staff addStaff(int listSize){

        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        int daysWorked=0;
        int hoursWorked=0;

        System.out.println("Enter the staff type:");
        String staffType = HandleInput.insertNonBlankString();

        ArrayList<Employee> employees = Employee.enterEmployees();
        
        do{
            System.out.println("Enter the employee's work days");
            daysWorked = HandleInput.insertInteger();
        }while(daysWorked<1);
        
        do{
            System.out.println("Enter the employee's work hours per day");
            hoursWorked = HandleInput.insertInteger();
        }while(hoursWorked<1 || hoursWorked>24);

        float totalStaffCost = calculateTotalCost(employees,(daysWorked*hoursWorked));

        return new Staff(listSize+1, staffType, employees, totalStaffCost, daysWorked, hoursWorked);
    }
    
    
    private static float calculateTotalCost(ArrayList<Employee> employees, int workingHours){
        float costPerHour = 0;
        float totalStaffCost = 0;
        
        for(Employee currentEmployee : employees) {
            costPerHour = ((currentEmployee.getWage()) * workingHours);
            totalStaffCost += costPerHour;
        }
        
        return totalStaffCost;
    }
   
    private static void seeStaff(ArrayList<Staff> staffList){
        System.out.println("================================= Staff List =================================");
         for(Staff currentStaff : staffList) {
            System.out.println("\nId: " + currentStaff.getId() + " '" + currentStaff.getType() + "' " + ", Working Days: " + currentStaff.getDaysWorked() + ", Working Hours: " + currentStaff.getHoursWorked());
            Employee.seeEmployees(currentStaff.getEmployees());
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
    
    private static void searchStaff(ArrayList<Staff> staffList){
        System.out.println("Enter the Staff Id:");
         int id = HandleInput.insertInteger();
         int sizeCount=0;
        
         for(Staff currentStaff : staffList) {
             if(id == currentStaff.getId()){
                calculateStaffPayment(currentStaff);
                break;
            }
            sizeCount++;
        }
        if(sizeCount==staffList.size()){
            System.out.println("The Id: " + id + " was not found");
        }
    }
    
    private static void calculateStaffPayment(Staff currentStaff){
        int workingHours = (currentStaff.getDaysWorked()* currentStaff.getHoursWorked());
        float individualPayment=0;
        float totalStaffCost = 0;
        
        System.out.println("===[Staff " + currentStaff.getId() + " cost details]===");
        System.out.println("For " + workingHours + " working hours");
        System.out.println("Employees:");
        for(Employee currentEmployee : currentStaff.getEmployees()) {
            individualPayment = ((currentEmployee.getWage()) * workingHours);
            System.out.println(" Id:" + currentEmployee.getId() + " " + currentEmployee.getName() + " Payment = $" + individualPayment);
            totalStaffCost += individualPayment;
        }
        System.out.println("-------------------------------------");
        System.out.println("      Total Staff cost = $" + totalStaffCost);
    }

    public Staff(int id, String type, ArrayList<Employee> employees, float totalStaffCost, int daysWorked, int hoursWorked) {
        this.id = id;
        this.type = type;
        this.employees = employees;
        this.totalStaffCost = totalStaffCost;
        this.daysWorked = daysWorked;
        this.hoursWorked = hoursWorked;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    
}
