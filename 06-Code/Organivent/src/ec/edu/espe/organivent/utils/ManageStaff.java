package ec.edu.espe.organivent.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.model.Employee;
import ec.edu.espe.organivent.model.Staff;
import ec.edu.espe.organivent.model.Workday;
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
public class ManageStaff {
    
    public void staffMenu(ArrayList<Staff> staffList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----- Staff Manager -----");
            System.out.println("| 1.- See the current Staff List");
            System.out.println("| 2.- Add a new staff group");
            System.out.println("| 3.- Return");
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
                    ManageStaff saveInFile = new ManageStaff();
                    saveInFile.writeFile("staff.json",staffList);
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
        
        ManageEmployee employeeFile = new ManageEmployee();
        ArrayList<Employee> employeeList = employeeFile.readFile("employees.json");
        ManageWorkday workdayFile = new ManageWorkday();
        ArrayList<Workday> wordayList = workdayFile.readFile("workdays.json");
        
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
        String type = scanner.nextLine();
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
        

        return new Staff(id,type, workday, employeesinEvent, totalStaffCost);
    }
    
    
    public void seeStaff(ArrayList<Staff> staffList){
        
         for(Staff currentStaff : staffList) {
            System.out.print("\nStaff: " + currentStaff);
        }
    }
    
    
    public ArrayList<Staff> readFile(String fileAdress){
        ArrayList<Staff> staff = null;
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<Staff>>(){}.getType();
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              
              staff=gson.fromJson(fileReader, type);
              fileReader.close();
              
          }catch(FileNotFoundException e){
              System.err.println("Error in creating the File Reader Object");
          }catch(IOException e){
              throw new RuntimeException(e);
          }
        return staff;
    }
    
    public void writeFile(String fileAdress,ArrayList<Staff> staff){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(staff,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
    
    
}
