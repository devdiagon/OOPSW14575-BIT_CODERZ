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
public class Equipment {
    
    private String type;
    private float cost;
    private int quantity;
    
     public static ArrayList<Equipment> getFromFile(){
        Type type = new TypeToken<ArrayList<Equipment>>(){}.getType();
        ArrayList<Equipment> equipmentList = ManageJson.readFile("data/equipment.json",type);
        return equipmentList;
    }
    
    public static void menu(){
        ArrayList<Equipment> equipmentList = Equipment.getFromFile();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("-------- Equipment Manager --------");
            System.out.println("-----------------------------------");
            System.out.println("| 1.- See the current equipment   |");
            System.out.println("| 2.- Add a new equipment         |");
            System.out.println("| 3.- Return                      |");
            System.out.println("___________________________________");
            System.out.println("Select an option (1-3): ");
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    seeEquipment(equipmentList);
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    equipmentList.add(addEquipment());
                    ManageJson.writeFile("data/equipment.json",equipmentList);
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
    
    public static Equipment addEquipment(){
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the type of equipment:");
        String type = scanner.nextLine();
        System.out.println("Enter the cost of the equipment:");
        float cost = HandleInput.insertFloat();
        scanner.nextLine();
        System.out.println("Enter the quantity of equipment:");
        int quantity = HandleInput.insertInteger();
        scanner.nextLine();

        return new Equipment(type, cost, quantity);
    }
    
    
    private static void seeEquipment(ArrayList<Equipment> equipmentList){
        System.out.println("============== Equipment List ===============");
        System.out.println("         Type        | Unit Cost | Quantity |");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
         for(Equipment currentEquipment : equipmentList) {
            System.out.println(currentEquipment);
        }
    }
    
    public static ArrayList<Equipment> enterEquipment(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Equipment> equipmentInEvent = new ArrayList<>();
        
        String searchName;
        int addMore=1;
        boolean passed=false;
        int sizeCount=0;
        
        do{
            sizeCount=0;
            System.out.println("Insert the Equipment Type to add");
            searchName = scanner.nextLine();
            
            for(Equipment currentEquipment : equipmentInEvent) {
                if(currentEquipment.getType().equals(searchName)){
                    System.out.println("The type: " + searchName + " is already in this event");
                    passed=false;
                    break;
                }
                sizeCount++;
            }
            if(sizeCount==equipmentInEvent.size()){
                addMore = addEquipmentInEvent(equipmentInEvent,searchName);
            }
        }while(passed==false && addMore == 1);
        
        return equipmentInEvent;
    }
    
    public static int addEquipmentInEvent(ArrayList<Equipment> equipmentInEvent, String searchName){
        
        ArrayList<Equipment> equipmentList = Equipment.getFromFile();
        
        int addMore=1;
        boolean passed=false;
        int sizeCount=0;
        
        do{
            sizeCount=0;
            for(Equipment currentEquipment : equipmentList) {
                if(currentEquipment.getType().equals(searchName)){
                    equipmentInEvent.add(currentEquipment);
                    passed=true;
                    System.out.println("Want to add another Equipment? 1) Yes - 2) No");
                    addMore = HandleInput.insertInteger();
                    break;
                }
                sizeCount++;
            }
            if(sizeCount==equipmentList.size()){
                System.out.println("The type: " + searchName + " was not found");
                passed=true;
            }
        }while(passed==false && addMore == 1);
        
        return addMore;
    }
    
    public static float getIndividualEquipmentCost(Equipment currentEquipment){
        return currentEquipment.getCost()*currentEquipment.getQuantity();
    }
    
    public static float calculateTotalEquipmentCost(ArrayList<Equipment> equipmentInEvent){
        float totalCost=0;
        
        for(Equipment currentEquipment : equipmentInEvent) {
            totalCost += ((currentEquipment.getCost()) * (currentEquipment.getQuantity()));
        }
        
        return totalCost;
    }

    @Override
    public String toString() {   
        return String.format("%-20s |$ %-9.2f| %-8d |", type, cost, quantity);
    }

    public Equipment(String type, float cost, int quantity) {
        this.type = type;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
