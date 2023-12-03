package ec.edu.espe.organivent.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.model.Schedule;
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
public class ManageWorkday {
    
    public void workdayMenu(ArrayList<Workday> workdayList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----- Workday Manager -----");
            System.out.println("| 1.- See the current workays");
            System.out.println("| 2.- Add a new workday");
            System.out.println("| 3.- Return");
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
                    ManageWorkday saveInFile = new ManageWorkday();
                    saveInFile.writeFile("workdays.json",workdayList);
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
    
    
    public void seeWorkday(ArrayList<Workday> workdayList){
        
         for(Workday currentWorkday : workdayList) {
            System.out.print("\nWorkday: " + currentWorkday);
        }
    }
    
    
    public ArrayList<Workday> readFile(String fileAdress){
        ArrayList<Workday> workdays = null;
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<Workday>>(){}.getType();
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              
              workdays=gson.fromJson(fileReader, type);
              fileReader.close();
              
          }catch(FileNotFoundException e){
              System.err.println("Error in creating the File Reader Object");
          }catch(IOException e){
              throw new RuntimeException(e);
          }
        return workdays;
    }
    
    public void writeFile(String fileAdress,ArrayList<Workday> workdays){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(workdays,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
    
    
}
