package ec.edu.espe.organivent.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.utils.ManageJson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Event {

    private int id;
    private Schedule startTime;
    private Schedule endTime;
    private String artist;
    private float estimatedCost;
    private ArrayList<Staff> staff;
    private ArrayList<Equipment> equipment;
    private String place;
    
    public static void menu(ArrayList<Event> eventList){
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
                    ManageJson.writeFile("events.json",eventList);
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
        
        Type type = new TypeToken<ArrayList<Artist>>(){}.getType();
        ArrayList<Artist> artistList = ManageJson.readFile("artists.json",type);
        
        System.out.println("Enter the artist's name:");
        String artist = scanner.nextLine();
        for(Artist currentArtist : artistList) {
            if(currentArtist.getName().equals(artist)){
                artist = currentArtist.getName();
                estimatedCost += currentArtist.getHiringCost();
            }
        }
        
        type = new TypeToken<ArrayList<EventPlace>>(){}.getType();
        ArrayList<EventPlace> eventPlaceList = ManageJson.readFile("event_places.json",type);
        
        System.out.println("Enter the place where the event is going to be:");
        String eventPlace = scanner.nextLine();
        for(EventPlace currentEventPlace : eventPlaceList) {
            if(currentEventPlace.getName().equals(eventPlace)){
                eventPlace = currentEventPlace.getName() ;
                estimatedCost += currentEventPlace.getRentCost();
            }
        }
        
        type = new TypeToken<ArrayList<Staff>>(){}.getType();
        ArrayList<Staff> staffList = ManageJson.readFile("staff.json",type);
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
        
        type = new TypeToken<ArrayList<Equipment>>(){}.getType();
        ArrayList<Equipment> equipmentList = ManageJson.readFile("equipment.json",type);
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
   
    
    
    public static void seeEvent(ArrayList<Event> eventList){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter the Event Id:");
         int id = scanner.nextInt();
        
         for(Event currentEvent : eventList) {
             if(id == currentEvent.getId()){
                System.out.print("\nEvent: " + currentEvent);
             }
        }
    }

    @Override
    public String toString() {
        return String.format("%-6d |%-14s| %-14s| %-14s| %-10.2f| %-14s|%-14s|%-14s|", id,startTime, endTime, artist, estimatedCost, staff, equipment, place);
    }

    public Event(int id, Schedule startTime, Schedule endTime, String artist, float estimatedCost, ArrayList<Staff> staff, ArrayList<Equipment> equipment, String place) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.estimatedCost = estimatedCost;
        this.staff = staff;
        this.equipment = equipment;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public float getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(float estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    

    public void addStaff(Staff staff){
    }

    public void setEventPlace(EventPlace eventPlace){
    }

    public void setArtistForEvent(Artist artist){
    }

    public void addEquipment(Equipment equipment){
    }

    public void setStartTime(Schedule startTime){
    }

    public void setEndTime(Schedule endTime){
    }
}
