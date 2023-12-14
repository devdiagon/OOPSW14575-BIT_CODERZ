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
     private Artist artist;
    private EventPlace place;
    private Schedule startTime;
    private Schedule endTime;
    private ArrayList<Staff> staff;
    private ArrayList<Equipment> equipment;
    
    
    private static ArrayList<Event> getFromFile(){
        Type type = new TypeToken<ArrayList<Event>>(){}.getType();
        ArrayList<Event> eventList = ManageJson.readFile("data/events.json",type);
        return eventList;
    }
    
    public static void menu(){
        ArrayList<Event> eventList = Event.getFromFile();
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        int option;
        do {
            System.out.println("--------------------- Event Manager ---------------------");
            System.out.println("---------------------------------------------------------");
            System.out.println("|     1.- Display general information from an Event     |");
            System.out.println("|     2.- Add a new Event                               |");
            System.out.println("|     3.- Calculate the cost from an Event              |");
            System.out.println("|     4.- Return                                        |");
            System.out.println("_________________________________________________________");
            System.out.println("Select an option (1-3): ");
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    searchAnEvent(eventList);
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    eventList.add(addEvent(eventList.size()));
                    ManageJson.writeFile("data/events.json",eventList);
                    System.out.println("\nDone! Press any button to return");
                    scanner.nextLine();
                    break;
                case 3:
                    searchEventIdToCalculate(eventList);
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 4);
    
    }
    
    private static Event addEvent(int listSize){
        
        System.out.println("Enter the event start time::");
        Schedule starTime = Schedule.createEntrySchedule();
        System.out.println("Enter the event end time:");
        Schedule endTime = Schedule.createDepartureSchedule(starTime);
        
        Artist artist = Artist.searchForArtist();
        EventPlace eventPlace = EventPlace.searchForPlace();
        ArrayList<Staff> staffInEvent = Staff.enterStaff();
        ArrayList<Equipment> equipmentInEvent = Equipment.enterEquipment();
        
        return new Event(listSize+1,artist,eventPlace,starTime, endTime, staffInEvent, equipmentInEvent);
        
    }
    
    private static void searchAnEvent(ArrayList<Event> eventList){
         System.out.println("Enter the Event Id:");
         int id = HandleInput.insertInteger();
         int sizeCount=0;
        
         for(Event currentEvent : eventList) {
             if(id == currentEvent.getId()){
                seeEvent(currentEvent);
                break;
            }
            sizeCount++;
        }
        if(sizeCount==eventList.size()){
            System.out.println("The Id: " + id + " was not found");
        }
         
    }
    
    private static void seeEvent(Event currentEvent){
        String message = "\n--------------------------------------\nEvent Id: " + currentEvent.getId();
        message += "\nArtist: " + currentEvent.getArtist().getName();
        message += "\nEvent Place: " + currentEvent.getPlace().getName();
        message += "\n--------------------------------------\nStart Time: " + currentEvent.getStartTime();
        message += "\nEnd Time:" +  currentEvent.getEndTime();
        message += "\n--------------------------------------\nAsgigned Staff IDs: [";
        
        for(Staff currentStaff:currentEvent.getStaff()){
            message += currentStaff.getId();
            message += ",";
        }
        message += "] \nUsed Equipment: [";
        
        for(Equipment currentEquipment:currentEvent.getEquipment()){
            message += currentEquipment.getType();
            message += ",";
        }
         message += "] \n--------------------------------------\n";
        
        System.out.print(message);
    }
    

    private static void searchEventIdToCalculate(ArrayList<Event> eventList){
         System.out.println("Enter the Event Id:");
         int id = HandleInput.insertInteger();
         int sizeCount=0;
        
         for(Event currentEvent : eventList) {
             if(id == currentEvent.getId()){
                calculateEventCost(currentEvent);
                break;
            }
            sizeCount++;
        }
        if(sizeCount==eventList.size()){
            System.out.println("The Id: " + id + " was not found");
        }
    }
    
    private static void calculateEventCost(Event currentEvent){
        float estimatedEventCost=0;
        float totalStaffCost=0;
        float totalEquipmentCost=0;
        float individualEquipmentCost=0;
        
        
        System.out.println("===[Event " + currentEvent.getId() + " cost details]===");
        System.out.println("Artist hiring cost = $" + currentEvent.getArtist().getHiringCost());
        estimatedEventCost += currentEvent.getArtist().getHiringCost();
        System.out.println("------------------------------------------------------");
        
        System.out.println("Event Place rent cost = $" + currentEvent.getPlace().getRentCost());
        estimatedEventCost += currentEvent.getPlace().getRentCost();
        System.out.println("------------------------------------------------------");
        
        System.out.println("Asigned Staff:");
        for(Staff currentStaff:currentEvent.getStaff()){
            System.out.println("   Id [" + currentStaff.getId()+ "]: " + "'" + currentStaff.getType() + "'" + " has a total cost = $ " + currentStaff.getTotalStaffCost());
            totalStaffCost += currentStaff.getTotalStaffCost();
        }
        System.out.println(" Total Staff Cost = $" + totalStaffCost);
        estimatedEventCost += totalStaffCost;
        System.out.println("------------------------------------------------------");
        
        System.out.println("Used Equipment:");
        for(Equipment currentEquipment:currentEvent.getEquipment()){
            individualEquipmentCost=Equipment.getIndividualEquipmentCost(currentEquipment);
            System.out.println("   Type: " + currentEquipment.getType() + " has a total cost = $ " + individualEquipmentCost);
            totalEquipmentCost += individualEquipmentCost;
        }
        System.out.println(" Total equipment cost = $" + totalEquipmentCost);
        estimatedEventCost += totalEquipmentCost;
        System.out.println("------------------------------------------------------\n");
        
        System.out.println("     Event final cost = $" + estimatedEventCost);
    }


    public Event(int id, Artist artist, EventPlace place, Schedule startTime, Schedule endTime, ArrayList<Staff> staff, ArrayList<Equipment> equipment) {
        this.id = id;
        this.artist = artist;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.staff = staff;
        this.equipment = equipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public EventPlace getPlace() {
        return place;
    }

    public void setPlace(EventPlace place) {
        this.place = place;
    }
    
    public Schedule getStartTime(){
        return startTime;
    }
    
     public void setStartTime(Schedule startTime){
        this.startTime = startTime ;
    }
     
    public Schedule getEndTime(){
        return endTime;
    }
    
    public void setEndTime(Schedule endTime){
        this.endTime = endTime ;
    }
    
     
}
