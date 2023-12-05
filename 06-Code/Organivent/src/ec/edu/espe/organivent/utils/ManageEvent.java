package ec.edu.espe.organivent.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.model.Artist;
import ec.edu.espe.organivent.model.Employee;
import ec.edu.espe.organivent.model.Equipment;
import ec.edu.espe.organivent.model.Event;
import ec.edu.espe.organivent.model.EventPlace;
import ec.edu.espe.organivent.model.Schedule;
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
public class ManageEvent {
    
    public void eventMenu(ArrayList<Event> eventList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----------------- Event Manager -----------------");
            System.out.println("-------------------------------------------------");
            System.out.println("|     1.- Display information from an Event     |");
            System.out.println("|     2.- Add a new Event                       |");
            System.out.println("|     3.- Return                                |");
            System.out.println("_________________________________________________");
            System.out.println("Select an option (1-3): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeEvent(eventList);
                    scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    eventList.add(addEvent());
                    ManageEvent saveInFile = new ManageEvent();
                    saveInFile.writeFile("events.json",eventList);
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
    
    public static Event addEvent(){
        Scanner scanner = new Scanner(System.in);
        int addMore=1;
        int searchId=0;
        String searchEquipment;
        float estimatedCost = 0;
        float costPerUnit = 0;
        
        System.out.println("Enter the event ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter the event start time::");
        Schedule starTime = createSchedule();
        System.out.println("Enter the event end time:");
        Schedule endTime = createSchedule();
        
        ManageArtist artistFile = new ManageArtist();
        ArrayList<Artist> artistList = artistFile.readFile("artists.json");
        
        System.out.println("Enter the artist's name:");
        String artist = scanner.nextLine();
        for(Artist currentArtist : artistList) {
            if(currentArtist.getName().equals(artist)){
                artist = currentArtist.getName();
                estimatedCost += currentArtist.getHiringCost();
            }
        }
        
        ManageEventPlace eventPlaceFile = new ManageEventPlace();
        ArrayList<EventPlace> eventPlaceList = eventPlaceFile.readFile("event_places.json");
        
        System.out.println("Enter the place where the event is going to be:");
        String eventPlace = scanner.nextLine();
        for(EventPlace currentEventPlace : eventPlaceList) {
            if(currentEventPlace.getName().equals(eventPlace)){
                eventPlace = currentEventPlace.getName() ;
                estimatedCost += currentEventPlace.getRentCost();
            }
        }
        
        ManageStaff staffFile = new ManageStaff();
        ArrayList<Staff> staffList = staffFile.readFile("staff.json");
        ArrayList<Staff> staffinEvent = new ArrayList<>();
        
        do{
            System.out.println("Insert the Staff Id to add");
            searchId = scanner.nextInt();
            scanner.nextLine();
            
            for(Staff currentStaff : staffList) {
                if(currentStaff.getId() == searchId){
                    staffinEvent.add(currentStaff);
                    estimatedCost += currentStaff.getTotalStaffCost();
                }
            }

            System.out.println("Want to add another Staff Group? 1) Yes - 2) No");
            addMore = scanner.nextInt();
            scanner.nextLine();
        }while(addMore == 1);
        
        ManageEquipment equipmentFile = new ManageEquipment();
        ArrayList<Equipment> equipmentList = equipmentFile.readFile("equipment.json");
        ArrayList<Equipment> equipmentinEvent = new ArrayList<>();
        
        do{
            System.out.println("Insert the Equipment Type to add");
            searchEquipment = scanner.nextLine();
            
            for(Equipment currentEquipment : equipmentList) {
                if(currentEquipment.getType().equals(searchEquipment)){
                    equipmentinEvent.add(currentEquipment);
                    
                    costPerUnit = ((currentEquipment.getCost()) * (currentEquipment.getQuantity()));
                    estimatedCost += costPerUnit;
                }
            }

            System.out.println("Want to add another Equipment? 1) Yes - 2) No");
            addMore = scanner.nextInt();
            scanner.nextLine();
        }while(addMore == 1);
        
        return new Event(id,starTime, endTime, artist, estimatedCost, staffinEvent, equipmentinEvent, eventPlace);
        
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
   
    
    
    public void seeEvent(ArrayList<Event> eventList){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter the Event Id:");
         int id = scanner.nextInt();
        
         for(Event currentEvent : eventList) {
             if(id == currentEvent.getId()){
                System.out.print("\nEvent: " + currentEvent);
             }
        }
    }
    
    
    public ArrayList<Event> readFile(String fileAdress){
        ArrayList<Event> event = null;
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<Event>>(){}.getType();
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              
              event=gson.fromJson(fileReader, type);
              fileReader.close();
              
          }catch(FileNotFoundException e){
              System.err.println("Error in creating the File Reader Object");
          }catch(IOException e){
              throw new RuntimeException(e);
          }
        return event;
    }
    
    public void writeFile(String fileAdress,ArrayList<Event> event){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(event,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
    
    
}
