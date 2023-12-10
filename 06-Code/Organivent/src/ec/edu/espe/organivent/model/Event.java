package ec.edu.espe.organivent.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.utils.HandleInput;
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
    
    public static ArrayList<Event> getFromFile(){
        Type type = new TypeToken<ArrayList<Event>>(){}.getType();
        ArrayList<Event> eventList = ManageJson.readFile("events.json",type);
        return eventList;
    }
    
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
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    seeEvent(eventList);
                    scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    eventList.add(addEvent(eventList.size()));
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
    
    public static Event addEvent(int listSize){
        float estimatedCost = 0;
        
        System.out.println("Enter the event start time::");
        Schedule starTime = Schedule.createEntrySchedule();
        System.out.println("Enter the event end time:");
        Schedule endTime = Schedule.createDepartureSchedule(starTime);
        
        Artist artist = Artist.searchForArtist();
        estimatedCost += artist.getHiringCost();
        
        EventPlace eventPlace = EventPlace.searchForPlace();
        estimatedCost += eventPlace.getRentCost();
        
        ArrayList<Staff> staffInEvent = Staff.enterStaff();
        estimatedCost += Staff.calculateStaffGroupCost(staffInEvent);
        
        ArrayList<Equipment> equipmentInEvent = Equipment.enterEquipment();
        estimatedCost += Equipment.calculateTotalEquipmentCost(equipmentInEvent);
        
        return new Event(listSize+1,starTime, endTime, artist.getName(), estimatedCost, staffInEvent, equipmentInEvent, eventPlace.getName());
        
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
