package ec.edu.espe.organivent.model;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.organivent.utils.Encriptation;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.ManageJson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Administrator {

    private int id;
    private String userName;
    private byte[] password;
    private String name;
    private String email;
    private int phoneNumber;
    
    public static ArrayList<Administrator> getFromFile(){
        Type type = new TypeToken<ArrayList<Administrator>>(){}.getType();
        ArrayList<Administrator> administratorList = ManageJson.readFile("administrators.json",type);
        return administratorList;
    }

    public static Administrator registerAdministrator(ArrayList<Administrator> administratorList){
        
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        int size = administratorList.size();
        
        System.out.println("Enter a User Name");
        String userName = validateUserName(administratorList);
        
        System.out.println("Enter your password (at least 10 digits)");
        String password = validatePassword();
        byte[] encriptedPassword = Encriptation.encrypt(password);
        
        System.out.println("Enter your full name");
        String name = scanner.nextLine();
        
        System.out.println("Enter your Email");
        String email = scanner.nextLine();
        
        System.out.println("Enter your Phone Number");
        int phoneNumber = validatePhoneNumber();
        
        return new Administrator(size+1,userName,encriptedPassword,name,email,phoneNumber);
    }
    
    private static String validateUserName(ArrayList<Administrator> administratorList){
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        boolean isTaken=false;
        String userToCheck;
        
 
        do{
            userToCheck = scanner.nextLine();
            for(Administrator currentAdministrator:administratorList){
                if(currentAdministrator.getUserName().equals(userToCheck)){
                    System.out.println("User Name Already taken! Try again");
                    System.out.println("Enter a User Name");
                    isTaken=true;
                    break;
                }else{
                    isTaken=false;
                }
            }    
        }while(isTaken==true);
        
        return userToCheck;
    }
    
    private static String validatePassword(){
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        boolean passed=true;
        String passwordToCheck;
        do{
            passwordToCheck = scanner.nextLine();
            if(passwordToCheck.length()<10){
                System.out.println("Password without enough characters!");
                System.out.println("Enter your password (at least 10 digits)");
                passed=false;
            }else{
                passed=true;
                for(char currentchar:passwordToCheck.toCharArray()){
                    if(currentchar == ',' || currentchar == ';' || currentchar == ' ' || currentchar == '\n'){
                        System.out.println("Invalid characters, ',' ';' ' ' ");
                        System.out.println("Enter your password (at least 10 digits)");
                        passed=false;
                        break;
                    }
                }
            }
        }while(passed==false);
        
        return passwordToCheck;
    }
    
    private static int validatePhoneNumber(){
        boolean passed=true;
        int phoneToCheck=0;
 
        do{
            phoneToCheck = HandleInput.insertInteger();
            if(phoneToCheck < 100000000 || phoneToCheck > 999999999 ){
                System.out.println("Invalid phone number");
                System.out.println("Enter your Phone Number");
                passed=false;
            }else{
                passed=true;
            }
        }while(passed==false);
        
        return phoneToCheck;
    }
    
    
    public static void logIn(ArrayList<Administrator> administratorList){
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        String userToCheck;
        String tryPassword=null;
        byte[] realPassword=null;
        int attempts=3;
        boolean matchUserName=false;
        boolean passwordAccepted=false;
        
        do{
            System.out.println("Enter your User Name: ");
            userToCheck = scanner.nextLine();
            for(Administrator currentAdministrator:administratorList){
                if(currentAdministrator.getUserName().equals(userToCheck)){
                     realPassword=currentAdministrator.getPassword();
                     matchUserName=true;
                     break;
                }
            }
        }while(matchUserName==false);
        
        
        do{
             System.out.println("Enter your Password: ");
             System.out.println("Attempts: " + attempts + "/3");
             tryPassword = scanner.nextLine();
             
            passwordAccepted= Encriptation.comparePasswords(tryPassword,realPassword);
            if(passwordAccepted==true){
                administratorMenu();
            }else{
                attempts--;
            }
        }while(attempts>0);
    }
    
    private static void administratorMenu(){

        ArrayList<Employee> employeeList = Employee.getFromFile();
        ArrayList<Artist> artistList = Artist.getFromFile();
        ArrayList<EventPlace> eventPlaceList = EventPlace.getFromFile();
        ArrayList<Equipment> equipmentList = Equipment.getFromFile();
        ArrayList<Workday> wordayList = Workday.getFromFile();
        ArrayList<Staff> staffList = Staff.getFromFile();
        ArrayList<Event> eventList = Event.getFromFile();
        
        int option;
        
        do {
            System.out.println("----- ADMINISTRATOR MENU -----");
            System.out.println("-------------------------------");
            System.out.println("__________________________________");
            System.out.println("|    1.- Manage employees        |");
            System.out.println("|    2.- Manage staff            |");
            System.out.println("|    3.- Manage artists          |");
            System.out.println("|    4.- Manage place            |");
            System.out.println("|    5.- Manage equipment        |");
            System.out.println("|    6.- Manage workdays         |");
            System.out.println("|    7.- Manage events           |");
            System.out.println("|    8.- Exit                    |");
            System.out.println("__________________________________");
            System.out.println("");
            System.out.println("Select an option (1-8): ");
            option = HandleInput.insertInteger();
            switch (option) {
                case 1:
                    Employee.menu(employeeList);
                    break;
                case 2:
                    Staff.menu(staffList);
                    break;
                case 3:
                    Artist.menu(artistList);
                    break;
                case 4:
                    EventPlace.menu(eventPlaceList);
                    break;
                case 5:
                    Equipment.menu(equipmentList);
                    break;
                case 6:
                    Workday.menu(wordayList);
                    break;
                case 7:
                    Event.menu(eventList);
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

    public Administrator(int id, String userName, byte[] password, String name, String email, int phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    

    public void addEvent(Event event){
    }

    public void modifyEvent(int eventId){
    }

    public void removeEvent(){
    }

    public void reviewEvent(int eventId){
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(byte[] password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}