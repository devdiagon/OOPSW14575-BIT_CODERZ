package ec.edu.espe.organivent.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.model.Equipment;
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
public class ManageEquipment {
    
    public void equipmentMenu(ArrayList<Equipment> equipmentList){
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
                    ManageEquipment saveInFile = new ManageEquipment();
                    saveInFile.writeFile("equipment.json",equipmentList);
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
    
    
    public void seeEquipment(ArrayList<Equipment> equipmentList){
        
         for(Equipment currentEquipment : equipmentList) {
            System.out.print("\nEquipment: " + currentEquipment);
        }
    }
    
    
    public ArrayList<Equipment> readFile(String fileAdress){
        ArrayList<Equipment> eventPlaces = null;
        try{
              FileReader fileReader = new FileReader(fileAdress);
              Type type = new TypeToken<ArrayList<Equipment>>(){}.getType();
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
    
    public void writeFile(String fileAdress,ArrayList<Equipment> equipment){         
          try{
              FileWriter fileWriter = new FileWriter(fileAdress);
              Gson gson = new GsonBuilder().setPrettyPrinting().create();
              gson.toJson(equipment,fileWriter);
              fileWriter.close();
          
          }catch(IOException e){
              throw new RuntimeException(e);
          }
    }
    
    
}
