package ec.edu.espe.organivent.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.model.EventPlace;
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
public class ManageEventPlace {
    
    public void eventPlaceMenu(ArrayList<EventPlace> eventPlaceList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----- Event Place Manager -----");
            System.out.println("| 1.- See the current event places");
            System.out.println("| 2.- Add a new event place");
            System.out.println("| 3.- Return");
            System.out.println("Select an option (1-3): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeEventPlaces(eventPlaceList);
                     scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    eventPlaceList.add(addEventPlace());
                    ManageEventPlace saveInFile = new ManageEventPlace();
                    saveInFile.writeFile("event_places.json",eventPlaceList);
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
    
    public static EventPlace addEventPlace(){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the place's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the place's adress:");
        String adress = scanner.nextLine();
        System.out.println("Enter the place's rent cost:");
        float rentCost = scanner.nextFloat();
        System.out.println("Enter the place's capacity:");
        int capacity = scanner.nextInt();

        return new EventPlace(name, adress, rentCost, capacity);
    }
    
    
    public void seeEventPlaces(ArrayList<EventPlace> eventPlaceList){
        
         for(EventPlace currentEventPlace : eventPlaceList) {
            System.out.print("\nEvent Place: " + currentEventPlace);
        }
    }
    
    
    public ArrayList<EventPlace> readFile(String fileAdress){
        ArrayList<EventPlace> eventPlaces = null;
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<EventPlace>>(){}.getType();
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              
              eventPlaces=gson.fromJson(fileReader, type);
              fileReader.close();
              
          }catch(FileNotFoundException e){
              System.err.println("Error in creating the File Reader Object");
          }catch(IOException e){
              throw new RuntimeException(e);
          }
        return eventPlaces;
    }
    
    public void writeFile(String fileAdress,ArrayList<EventPlace> eventPlaces){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(eventPlaces,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
    
    
}
