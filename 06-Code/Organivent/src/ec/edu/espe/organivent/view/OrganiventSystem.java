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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class OrganiventSystem {

    public static void main(String[] args) {

    }

    public static void menuOrganivent() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("----- Organivent Menu -----");
            System.out.println("1.- Create new event");
            System.out.println("2.- Display information of the events from the Json file");
            System.out.println("3.- Exit");
            System.out.print("Select an option (1-3): ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    displayEventsToJson();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 3);
    }

    public static void createEvent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the event ID: ");
        int id = scanner.nextInt();
        System.out.println("Enter the event start time::");
        Schedule starTime = createSchedule();
        System.out.println("Enter the event end time:");
        Schedule endTime = createSchedule();
        System.out.println("Enter the artist and their information:");
        Artist artist = createArtist();
        System.out.println("Enter the approximate event costs:");
        float estimatedCost = scanner.nextFloat();
        System.out.println("Enter the number of event staff:");
        int sizeStaff = scanner.nextInt();
        Staff[] staff = new Staff[sizeStaff];
        for (int i = 0; i < sizeStaff; i++) {
            System.out.println("Enter the staff information.");
            staff[i] = createStaff();
        }
        System.out.println("Enter the number of equipment for the event:");
        int sizeEquipment = scanner.nextInt();
        Equipment[] equipment = new Equipment[sizeEquipment];
        for (int i = 0; i < sizeEquipment; i++) {
            System.out.println("Enter the equipment information:");
            equipment[i] = createEquipment();
        }
        System.out.println("Enter the information of the event place.");
        EventPlace eventPlace = createEventPlace();

        Event event = new Event(id, starTime, endTime, artist, estimatedCost, staff, equipment, eventPlace);

        saveEvent(event);
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

    public static Schedule createSchedule() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the year:");
        int year = scanner.nextInt();
        System.out.println("Enter the month:");
        int month = scanner.nextInt();
        System.out.println("Enter the day:");
        int day = scanner.nextInt();
        System.out.println("Enter the hour:");
        int hour = scanner.nextInt();
        System.out.println("Enter the minutes:");
        int minutes = scanner.nextInt();

        Schedule schedule = new Schedule(year, month, day, hour, minutes);

        return schedule;
    }

    public static Artist createArtist() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the artist's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the artist's hiring cost:");
        float hiringCost = scanner.nextFloat();
        System.out.println("Enter the entry time: ");
        Schedule entryTime = createSchedule();
        System.out.println("Enter the departure time");
        Schedule departureTime = createSchedule();

        Artist artist = new Artist(name, hiringCost, entryTime, departureTime);

        return artist;
    }

    public static Staff createStaff() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the staff type:");
        String type = scanner.nextLine();
        System.out.println("Enter the workday for the staff:");
        Workday workday = createWorkday();
        System.out.println("Enter the number of staff employees.");
        int sizeEmployees = scanner.nextInt();
        Employee[] employees = new Employee[sizeEmployees];
        for (int i = 0; i < sizeEmployees; i++) {
            System.out.println("Enter the information of the employees.");
            employees[i] = createEmployee();
        }

        System.out.println("Enter the total cost of the staff:");
        float totalStaffCost = scanner.nextFloat();

        Staff staff = new Staff(type, workday, employees, totalStaffCost);

        return staff;

    }

    public static Workday createWorkday() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the workday ID:");
        int workdayId = scanner.nextInt();
        System.out.println("Enter the workday start time:");
        Schedule entryTime = createSchedule();
        System.out.println("Enter the workday departure time:");
        Schedule departureTime = createSchedule();

        Workday workday = new Workday(workdayId, entryTime, departureTime);

        return workday;
    }

    public static Employee createEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the employee id: ");
        int employeeId = scanner.nextInt();
        System.out.println("Enter the employee's name:");
        String name = scanner.nextLine();
        System.out.println("Enter the employee's hourly wage:");
        float hourlyWage = scanner.nextFloat();

        Employee employee = new Employee(employeeId, name, hourlyWage);

        return employee;
    }

    public static Equipment createEquipment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the type of equipment:");
        String type = scanner.nextLine();
        System.out.println("Enter the cost of the equipment:");
        float cost = scanner.nextFloat();
        System.out.println("Enter the quantity of equipment:");
        int quantity = scanner.nextInt();

        Equipment equipment = new Equipment(type, cost, quantity);

        return equipment;
    }

    public static EventPlace createEventPlace() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the event place:");
        String name = scanner.nextLine();
        System.out.println("Enter the address of the event place:");
        String adress = scanner.nextLine();
        System.out.println("Enter the rent cost of the event place:");
        float rentCost = scanner.nextFloat();
        System.out.println("Enter the capacity of the event place:");
        int capacity = scanner.nextInt();

        EventPlace eventPlace = new EventPlace(name, adress, rentCost, capacity);

        return eventPlace;
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
