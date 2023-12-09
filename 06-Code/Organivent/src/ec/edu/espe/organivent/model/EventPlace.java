package ec.edu.espe.organivent.model;

import ec.edu.espe.organivent.utils.ManageJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class EventPlace {

    private String name;
    private String adress;
    private float rentCost;
    private int capacity;
    
     public static void menu(ArrayList<EventPlace> eventPlaceList){
         Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("---------- Event Place Manager ------------");
            System.out.println("-------------------------------------------");
            System.out.println("|    1.- See the current event places     |");
            System.out.println("|    2.- Add a new event place            |");
            System.out.println("|    3.- Return                           |");
            System.out.println("___________________________________________");
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
                    ManageJson.writeFile("event_places.json",eventPlaceList);
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
    
    
    public static void seeEventPlaces(ArrayList<EventPlace> eventPlaceList){
        
         for(EventPlace currentEventPlace : eventPlaceList) {
            System.out.print("\nEvent Place: " + currentEventPlace);
        }
    }

    @Override
    public String toString() {
        return String.format("%-30s|%-30s|%-10.2f|%-14d", name,adress,rentCost,capacity);
    }

    public EventPlace(String name, String adress, float rentCost, int capacity) {
        this.name = name;
        this.adress = adress;
        this.rentCost = rentCost;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public float getRentCost() {
        return rentCost;
    }

    public void setRentCost(float rentCost) {
        this.rentCost = rentCost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    
}
