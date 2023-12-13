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
public class Artist {

    private static boolean header=false;
    private String name;
    private float hiringCost;
    private Schedule entryTime;
    private Schedule departureTime;
    
    public static ArrayList<Artist> getFromFile(){
        Type type = new TypeToken<ArrayList<Artist>>(){}.getType();
        ArrayList<Artist> artistList = ManageJson.readFile("artists.json",type);
        return artistList;
    }
    
    public static void menu(ArrayList<Artist> artistList){
         Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        int option;
        do {
            System.out.println("---------- Artist Manager -----------");
            System.out.println("-------------------------------------");
            System.out.println("|    1.- See the current artists    |");
            System.out.println("|    2.- Add a new artist           |");
            System.out.println("|    3.- Return                     |");
            System.out.println("_____________________________________");
            System.out.println("Select an option (1-3): ");
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    seeArtists(artistList);
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    artistList.add(addArtist());
                    ManageJson.writeFile("artists.json",artistList);
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
    
    private static Artist addArtist(){
        
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        
        System.out.println("Enter the artist's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the artist's hiring cost:");
        float hiringCost = HandleInput.insertFloat();
        System.out.println("Enter the entry time: ");
        Schedule entryTime = Schedule.createEntrySchedule();
        System.out.println("Enter the departure time");
        Schedule departureTime = Schedule.createDepartureSchedule(entryTime);

        return new Artist(name, hiringCost, entryTime, departureTime);
    }
    
    private static void seeArtists(ArrayList<Artist> artistList){
        
         for(Artist currentArtist : artistList) {
            System.out.print("\nArtist: " + currentArtist);
        }
    }
    
    public static Artist searchForArtist(){
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        ArrayList<Artist> artistList = getFromFile();
        
        Artist artist =null;
        String searchName;
        boolean passed=false;
        int sizeCount=0;
        
        do{
            sizeCount=0;
            System.out.println("Enter the artist's name:");
            searchName = scanner.nextLine();
            
            for(Artist currentArtist : artistList) {
                if(currentArtist.getName().equals(searchName)){
                    artist=currentArtist;
                    passed=true;
                    break;
                }
                sizeCount++;
            }
            
            if(sizeCount==artistList.size()){
                System.out.println("The artist: " + searchName + " was not found");
            }
        }while(passed==false);
        
        return artist;
    }

    @Override
    public String toString() {
        if (!header){
            String headers = "          Name               |Hiring Cost| Entry Time      | Departure Time|\n" +
                             "       ----------------------------------------------------------------------";
            System.out.print(headers);
            header = true;               
        }
        return String.format("%-20s | %-10.2f| %-16s| %-16s|", name, hiringCost, entryTime,departureTime);
    }

    public Artist(String name, float hiringCost, Schedule entryTime, Schedule departureTime) {
        this.name = name;
        this.hiringCost = hiringCost;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHiringCost() {
        return hiringCost;
    }

    public void setHiringCost(float hiringCost) {
        this.hiringCost = hiringCost;
    }
}
