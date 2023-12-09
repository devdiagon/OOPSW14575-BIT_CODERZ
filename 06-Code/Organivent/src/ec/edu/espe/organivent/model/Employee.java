package ec.edu.espe.organivent.model;

import ec.edu.espe.organivent.utils.ManageJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Employee {

    private static boolean header = false;
    private int id;
    private String name;
    private float hourlyWage;
    
    public static void menu(ArrayList<Employee> employeeList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----------- Employee Manager ------------");
            System.out.println("_________________________________________");
            System.out.println("|   1.- See the current employees       |");
            System.out.println("|   2.- Add a new employee              |");
            System.out.println("|   3.- Return                          |");
            System.out.println("_______________________________________");
            System.out.println("Select an option (1-3): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeEmployees(employeeList);
                     scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    employeeList.add(addEmployee());
                    ManageJson.writeFile("employees.json",employeeList);
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
    
    public static Employee addEmployee(){
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the employee id: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the employee's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the employee's hourly wage:");
        float hourlyWage = scanner.nextFloat();
        scanner.nextLine();

        return new Employee(employeeId, name, hourlyWage);
    }
    
    public static void seeEmployees(ArrayList<Employee> employeeList){
        
         for(Employee currentEmployee : employeeList) {
            System.out.print("\nEmployee: " + currentEmployee);
        }
    }
    
    

    @Override
    public String toString() {
        return String.format("%-3d | %-20s | %3.2f|", id, name, hourlyWage);
    }

    public Employee(int employeeId, String name, float hourlyWage) {
        this.id = employeeId;
        this.name = name;
        this.hourlyWage = hourlyWage;
    }

    public int getEmployeeId() {
        return id;
    }

    public void setEmployeeId(int employeeId) {
        this.id = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(float hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    
    
}
