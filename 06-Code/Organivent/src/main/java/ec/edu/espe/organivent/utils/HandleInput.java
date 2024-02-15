package ec.edu.espe.organivent.utils;


import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Indexes.descending;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bson.Document;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class HandleInput {
    
    public static int increaseMaxId(MongoCollection coll){
        int asignedId=0;
             
        Document doc = (Document) coll.find().sort(descending("id")).limit(1).first();
        if (doc==null){
            asignedId=1;
        }else{
            asignedId = doc.getInteger("id");
            asignedId++;
        }
        
        return asignedId;
    }
       
    public static int insertInteger(){
        Scanner scanner = new Scanner(System.in);
        int analyzeInt=0;
        boolean passed=true;
        
        do{
            try{
                analyzeInt = Integer.parseInt(scanner.nextLine());
                passed=true;
            }catch (Exception e){
                System.out.println("Enter a valid whole number please: ");
                passed=false;
            }
        }while(passed==false);
        
        return analyzeInt;    
    }
    
    public static float insertFloat(String anaLizeFloat){
        boolean passed = false;
        float compareFloat=0;
        try{
            compareFloat = Float.parseFloat(anaLizeFloat);
            System.out.println(compareFloat);
            passed=true;
        }catch (Exception e){
            System.out.println("Maybe try using '.' instead of ','");
            System.out.println(compareFloat);
            passed=false;
        }        
        return compareFloat;    
    }
    
    public static float insertFloat(){
        
        Scanner scanner = new Scanner(System.in);
        float analyzeFloat=0;
        boolean passed=true;
        
        do{
            try{
                analyzeFloat = Float.parseFloat(scanner.nextLine());
                passed=true;
            }catch (Exception e){
                System.out.println("Enter a valid decimal number please");
                System.out.println("Maybe try using '.' instead of ','");
                passed=false;
            }
        }while(passed==false);
        
        return analyzeFloat;    
    }
    
    public static boolean validatePriceString(String inputString){
        String currencyRegex = "([0-9]+([,][0-9]*)?|[,][0-9]+)";
        Pattern p = Pattern.compile(currencyRegex);
        
        if (inputString == null){
            return false;
        }
        Matcher m = p.matcher(inputString);
        return m.matches();
    }
    
    public static float returnFloat(String insertedString){
        String updatedString = insertedString.replace(',', '.');
        return Float.parseFloat(updatedString);
    }
    
    public static float insertPrice(){
        float price;
        boolean passed = true;
        
        do {
            price = insertFloat();
            if (price <= 0) {
                System.out.println("enter a decimal number greater than zero");
                passed = false;
            } else {
                passed = true;
            }
        } while (!passed);

        return price;
    }
    
    public static String insertNonBlankString() {
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        String inputString;
        boolean passed = true;
        
        do {
            inputString = scanner.nextLine();
            if (inputString.trim().isEmpty()) {
                System.out.println("Please enter a non-blank string");
                passed = false;
            } else {
                passed = true;
            }
        } while (!passed);

        return inputString.trim();
    }
    
    public static boolean validateRealName(String inputString){
        boolean passed = true;
        
        if(passed != inputString.isEmpty()){
            for(char currentchar:inputString.toCharArray()){
                if(Character.isDigit(currentchar)){ 
                    passed=false;
                    break;
                }else if(!Character.isLetter(currentchar) && !Character.isWhitespace(currentchar)){
                        passed=false;
                        break;
                }else{
                    passed = true;
                }
            }
        }else{
            passed = false;
        }
        
        
        return passed;
    }
    
    public static boolean validateInteger(String inputString){
        String integerRegex = "[0-9]+";
        Pattern p = Pattern.compile(integerRegex);
        
        if (inputString == null){
            return false;
        }
        Matcher m = p.matcher(inputString);
        return m.matches();
    }
    
    public static boolean validateFloat(String inputString){
        String currencyRegex = "([0-9]+([,][0-9]*)?|[,][0-9]+)";
        Pattern p = Pattern.compile(currencyRegex);
        
        if (inputString == null){
            return false;
        }
        Matcher m = p.matcher(inputString);
        return m.matches();
    }
    
    public static boolean validateEquipmentName(String inputString){
        boolean passed = true;
        
        if(passed != inputString.isEmpty()){
            for(char currentchar:inputString.toCharArray()){
                if(!Character.isLetter(currentchar) && !Character.isWhitespace(currentchar) && !Character.isDigit(currentchar)){
                    if((currentchar != '.') && (currentchar != '-') && 
                        (currentchar != '_') && (currentchar != ',') && 
                        (currentchar != ';') && (currentchar != ':')){
                        passed = false;
                        break;
                    }
                }else{
                    passed = true;
                }
            }
        }else{
            passed = false;
        }
         
        return passed;
    }
    
    public static boolean validateStreetAdress(String inputString){
        boolean passed = true;
        if(passed != inputString.isEmpty()){
            for(char currentchar:inputString.toCharArray()){
                if(!Character.isLetter(currentchar) && !Character.isWhitespace(currentchar) && !Character.isDigit(currentchar)){
                    if((currentchar != '.') && (currentchar != '-') && 
                        (currentchar != '_') && (currentchar != ',') && 
                        (currentchar != ';') && (currentchar != ':')){
                        passed = false;
                        break;
                    }
                }else{
                    passed = true;
                }
            }
        }else{
            passed = false;
        }
        return passed;
    }
    
    public static boolean validatePassword(String passwordToCheck){        
        String pwsdRegex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=*])"
                       + "(?=\\S+$).{10,20}$";
        
        Pattern p = Pattern.compile(pwsdRegex);
        
        if (passwordToCheck == null) {
            return false;
        }
        Matcher m = p.matcher(passwordToCheck);
        return m.matches();
    }
    
    public static boolean validateEmail(String emailToCheck){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailToCheck);
        return matcher.matches();
    }
    
    public static String insertRealName() {
        String inputName;
        boolean passed = false;
        
        do {
            inputName = insertNonBlankString();
            passed = true;
            for(char currentchar:inputName.toCharArray()){
                    if(Character.isDigit(currentchar)){
                        System.out.println("A name can't have numbers!");
                        System.out.println("Please try again");
                        passed=false;
                        break;
                    }else{
                        if(!Character.isLetter(currentchar)&& !Character.isWhitespace(currentchar)){
                            System.out.println("A name can't have special characters!");
                            System.out.println("Please try again");
                            passed=false;
                        break;
                        }else{
                            passed = true;
                        }
                    }
                }
        } while (passed==false);

        return inputName;
    }
}
