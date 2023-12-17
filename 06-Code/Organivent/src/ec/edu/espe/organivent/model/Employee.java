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
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO
 * ESPE
 */
public class Employee {

    private int id;
    private String name;
    private float hourlyWage;

    public static ArrayList<Employee> getFromFile() {
        Type type = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        ArrayList<Employee> employeeList = ManageJson.readFile("data/employees.json", type);
        return employeeList;
    }

    public static void menu() {
        ArrayList<Employee> employeeList = Employee.getFromFile();
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
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    seeEmployees(employeeList);
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    employeeList.add(addEmployee(employeeList.size()));
                    ManageJson.writeFile("data/employees.json", employeeList);
                    System.out.println("\nDone! Press any button to return");
                    scanner.nextLine();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 3);

    }

    private static Employee addEmployee(int listSize) {
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");

        System.out.println("Enter the employee's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the employee's hourly wage:");
        float hourlyWage = HandleInput.insertFloat();

        return new Employee(listSize + 1, name, hourlyWage);
    }

    public static void seeEmployees(ArrayList<Employee> employeeList) {
        System.out.println("Employees:");
        for (Employee currentEmployee : employeeList) {
            System.out.print(currentEmployee);
           } 
        System.out.print("\n");
        }

    public static ArrayList<Employee> enterEmployees() {
        ArrayList<Employee> employeesInEvent = new ArrayList<>();

        int searchId;
        int addMore = 1;
        boolean passed = false;
        int sizeCount = 0;

        do {
            sizeCount = 0;
            System.out.println("Insert the Employee Id to add");
            searchId = HandleInput.insertInteger();

            for (Employee currentEmployee : employeesInEvent) {
                if (currentEmployee.getId() == searchId) {
                    System.out.println("The Id: " + searchId + " is already in this Staff group");
                    passed = false;
                    break;
                }
                sizeCount++;
            }
            if (sizeCount == employeesInEvent.size()) {
                addMore = addEmployeeInEvent(employeesInEvent, searchId);
            }
        } while (passed == false && addMore == 1);

        return employeesInEvent;
    }

    private static int addEmployeeInEvent(ArrayList<Employee> employeesInEvent, int searchId) {
        ArrayList<Employee> employeeList = Employee.getFromFile();

        int addMore = 1;
        boolean passed = false;
        int sizeCount = 0;

        do {
            sizeCount = 0;
            for (Employee currentEmployee : employeeList) {
                if (currentEmployee.getId() == searchId) {
                    employeesInEvent.add(currentEmployee);
                    passed = true;
                    System.out.println("Want to add another Employee? 1) Yes - 2) No");
                    addMore = HandleInput.insertInteger();
                    break;
                }
                sizeCount++;
            }
            if (sizeCount == employeeList.size()) {
                System.out.println("The Id: " + searchId + " was not found");
                passed = true;
            }
        } while (passed == false && addMore == 1);

        return addMore;
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-20s | %-10.2f |%n", id, name, hourlyWage);
    }

    public Employee(int employeeId, String name, float hourlyWage) {
        this.id = employeeId;
        this.name = name;
        this.hourlyWage = hourlyWage;
    }

    public int getId() {
        return id;
    }

    public void setId(int employeeId) {
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
