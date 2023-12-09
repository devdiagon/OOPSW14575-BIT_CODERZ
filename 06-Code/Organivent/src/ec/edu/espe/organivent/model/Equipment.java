package ec.edu.espe.organivent.model;

import ec.edu.espe.organivent.utils.ManageJson;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Equipment {
    
    private static boolean header = false;
    private String type;
    private float cost;
    private int quantity;
    
    public static void menu(ArrayList<Equipment> equipmentList){
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
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeEquipment(equipmentList);
                     scanner.nextLine();
                    System.out.println("\nPress any button to return");
                    scanner.nextLine();
                    break;
                case 2:
                    equipmentList.add(addEquipment());
                    ManageJson.writeFile("equipment.json",equipmentList);
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
        float cost = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Enter the quantity of equipment:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        return new Equipment(type, cost, quantity);
    }
    
    
    public static void seeEquipment(ArrayList<Equipment> equipmentList){
        
         for(Equipment currentEquipment : equipmentList) {
            System.out.print("\nEquipment: " + currentEquipment);
        }
    }

    @Override
    public String toString() {
        if (!header){
            String headers = "             Type               | Cost    | Quantity|\n" +
                             "          -------------------------------------------";
            System.out.print(headers);
            header = true;            
        }
            
        return String.format("%-20s | %-8.2f| %-8d|", type, cost, quantity);
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

    
    
    public void purchaseEquipment(){
    }
}
