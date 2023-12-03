package ec.edu.espe.organivent.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.model.Employee;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class ManageEmployee {
    
    public void employeeMenu(ArrayList<Employee> employeeList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----- Employee Manager -----");
            System.out.println("| 1.- See the current employees");
            System.out.println("| 2.- Add a new employee");
            System.out.println("| 3.- Return");
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
                    ManageEmployee saveInFile = new ManageEmployee();
                    saveInFile.writeFile("employees.json",employeeList);
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
    
    public void seeEmployees(ArrayList<Employee> employeeList){
        
         for(Employee currentEmployee : employeeList) {
            System.out.print("\nEmployee: " + currentEmployee);
        }
    }
    
    
    public ArrayList<Employee> readFile(String fileAdress){
        ArrayList<Employee> employees = null;
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<Employee>>(){}.getType();
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              
              employees=gson.fromJson(fileReader, type);
              fileReader.close();
              
          }catch(FileNotFoundException e){
              System.err.println("Error in creating the File Reader Object");
          }catch(IOException e){
              throw new RuntimeException(e);
          }
        return employees;
    }
    
    public void writeFile(String fileAdress,ArrayList<Employee> employees){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(employees,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
    
    
}
