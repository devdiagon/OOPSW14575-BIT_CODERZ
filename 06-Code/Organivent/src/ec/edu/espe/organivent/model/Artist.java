package ec.edu.espe.organivent.model;

import ec.edu.espe.organivent.utils.ManageJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Artist {

    private static boolean header=false;
    private String name;
    private float hiringCost;
    private Schedule entryTime;
    private Schedule departureTime;
    
    public static void menu(ArrayList<Artist> artistList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("---------- Artist Manager -----------");
            System.out.println("-------------------------------------");
            System.out.println("|    1.- See the current artists    |");
            System.out.println("|    2.- Add a new artist           |");
            System.out.println("|    3.- Return                     |");
            System.out.println("_____________________________________");
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
    
    public static void seeArtists(ArrayList<Artist> artistList){
        
         for(Artist currentArtist : artistList) {
            System.out.print("\nArtist: " + currentArtist);
        }
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

    
    
    public void setEntryTime(Schedule entryTime){
    }

    public void setDepartureTime(Schedule departureTime){
    }
    
    
}
