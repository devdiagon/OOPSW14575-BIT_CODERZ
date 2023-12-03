package ec.edu.espe.organivent.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.model.Artist;
import ec.edu.espe.organivent.model.Schedule;
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
public class ManageArtist {
    
    public void artistMenu(ArrayList<Artist> artistList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----- Artist Manager -----");
            System.out.println("| 1.- See the current artists");
            System.out.println("| 2.- Add a new artist");
            System.out.println("| 3.- Return");
            System.out.println("Select an option (1-3): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeArtists(artistList);
                    scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    artistList.add(addArtist());
                    ManageArtist saveInFile = new ManageArtist();
                    saveInFile.writeFile("artists.json",artistList);
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
    
    public static Artist addArtist(){
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the artist's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the artist's hiring cost:");
        float hiringCost = scanner.nextFloat();
        System.out.println("Enter the entry time: ");
        Schedule entryTime = createSchedule();
        System.out.println("Enter the departure time");
        Schedule departureTime = createSchedule();

        return new Artist(name, hiringCost, entryTime, departureTime);
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
    
    public void seeArtists(ArrayList<Artist> artistList){
        
         for(Artist currentArtist : artistList) {
            System.out.print("\nArtist: " + currentArtist);
        }
    }
    
    
    public ArrayList<Artist> readFile(String fileAdress){
        ArrayList<Artist> artists = null;
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<Artist>>(){}.getType();
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              
              artists=gson.fromJson(fileReader, type);
              fileReader.close();
              
          }catch(FileNotFoundException e){
              System.err.println("Error in creating the File Reader Object");
          }catch(IOException e){
              throw new RuntimeException(e);
          }
        return artists;
    }
    
    public void writeFile(String fileAdress,ArrayList<Artist> artists){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(artists,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
    
    
}
