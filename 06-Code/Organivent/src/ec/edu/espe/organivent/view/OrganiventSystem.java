package ec.edu.espe.organivent.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import ec.edu.espe.organivent.model.Artist;
import ec.edu.espe.organivent.model.Employee;
import ec.edu.espe.organivent.model.Equipment;
import ec.edu.espe.organivent.model.Event;
import ec.edu.espe.organivent.model.EventPlace;
import ec.edu.espe.organivent.model.Schedule;
import ec.edu.espe.organivent.model.Staff;
import ec.edu.espe.organivent.model.Workday;
import ec.edu.espe.organivent.utils.ManageArtist;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ec.edu.espe.organivent.utils.ManageEmployee;
import ec.edu.espe.organivent.utils.ManageEquipment;
import ec.edu.espe.organivent.utils.ManageEvent;
import ec.edu.espe.organivent.utils.ManageEventPlace;
import ec.edu.espe.organivent.utils.ManageStaff;
import ec.edu.espe.organivent.utils.ManageWorkday;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class OrganiventSystem {

    public static void main(String[] args) {

        ManageEmployee employeeFile = new ManageEmployee();
        ArrayList<Employee> employeeList = employeeFile.readFile("employees.json");
        
        ManageArtist artistFile = new ManageArtist();
        ArrayList<Artist> artistList = artistFile.readFile("artists.json");
        
        ManageEventPlace eventPlaceFile = new ManageEventPlace();
        ArrayList<EventPlace> eventPlaceList = eventPlaceFile.readFile("event_places.json");
        
        ManageEquipment equipmentFile = new ManageEquipment();
        ArrayList<Equipment> equipmentList = equipmentFile.readFile("equipment.json");
        
        ManageWorkday workdayFile = new ManageWorkday();
        ArrayList<Workday> wordayList = workdayFile.readFile("workdays.json");
        
        ManageStaff staffFile = new ManageStaff();
        ArrayList<Staff> staffList = staffFile.readFile("staff.json");
        
        ManageEvent eventFile = new ManageEvent();
        ArrayList<Event> eventList = eventFile.readFile("events.json");
        
        
        
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----- Administrator Menu -----");
            System.out.println("| 1.- Manage employees");
            System.out.println("| 2.- Manage staff");
            System.out.println("| 3.- Manage artists");
            System.out.println("| 4.- Manage place");
            System.out.println("| 5.- Manage equipment");
            System.out.println("| 6.- Manage workdays");
            System.out.println("| 7.- Manage events");
            System.out.println("| 8.- Exit");
            System.out.println("Select an option (1-8): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    employeeFile.employeeMenu(employeeList);
                    break;
                case 2:
                    staffFile.staffMenu(staffList);
                    break;
                case 3:
                    artistFile.artistMenu(artistList);
                    break;
                case 4:
                    eventPlaceFile.eventPlaceMenu(eventPlaceList);
                    break;
                case 5:
                    equipmentFile.equipmentMenu(equipmentList);
                    break;
                case 6:
                    workdayFile.workdayMenu(wordayList);
                    break;
                case 7:
                    eventFile.eventMenu(eventList);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 8);

    }

    public static void saveEvent(Event event) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to save your event in a JSON file? Yes (1) or no (2)");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                /**saveEventToJson(event);**/
                System.out.println("Event saved successfully.");
                break;
            case 2:
                System.out.println("Event is not saved");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
    
    public static void displayEventsToJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String JSON_FILE = "events.json";
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            JsonObject eventsObject = gson.fromJson(jsonContent.toString(), JsonObject.class);
            for (String key : eventsObject.keySet()) {
                try {
                    JsonObject eventObject = eventsObject.getAsJsonObject(key);
                    System.out.println("Event (ID: " + key + "):");
                    System.out.println(gson.toJson(eventObject));
                    System.out.println("------------------------------");
                } catch (JsonSyntaxException e) {
                    System.out.println("Error processing the event with ID." + key);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the JSON file.");
        }
    }
   
}
