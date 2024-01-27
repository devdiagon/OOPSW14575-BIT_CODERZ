package ec.edu.espe.organivent.model;

import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.ManageJson;
import ec.edu.espe.organivent.utils.UseMongoDB;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Bill {
    private int id;
    private float artistCost;
    private float venueCost;
    private float staffCost;
    private float equipmentCost;
    private float generalExpensesCost;
    private float penaltyFeesCost;
    private float totalAmount;
    
    private static ArrayList<Bill> getFromFile(){
        Type type = new TypeToken<ArrayList<Bill>>(){}.getType();
        ArrayList<Bill> billList = ManageJson.readFile("data/bills.json",type);
        return billList;
    }
    
    
    public static void saveBill(Bill tempBill){
        ArrayList<Bill> billList = Bill.getFromFile();
        int searchId=tempBill.getId();
        boolean exists = searchForBill(searchId);
        
        if(exists==false){
            billList.add(tempBill);
        }else{
            for(Bill currentBill : billList){
                if(currentBill.getId() == searchId){
                    billList.set(searchId-1, tempBill);
                    break;
                }
            }
        }
        
        ManageJson.writeFile("data/bills.json",billList);
    }
    
    private static boolean searchForBill(int id){
        ArrayList<Bill> billList = Bill.getFromFile();
        boolean findBill=false;
        
        for(Bill currentBill : billList){
            if(currentBill.getId() == id){
                findBill=true;
                break;
            }
        }
        return findBill;
    }
    
    public static void seeForBillId(){
        ArrayList<Bill> billList = Bill.getFromFile();
        
        System.out.println("Enter the Event Id to get it's bill:");
        int searchId = HandleInput.insertInteger();
        
        for(Bill currentBill : billList){
            if(currentBill.getId() == searchId){
                System.out.print("========[Bill from Event Id: " + searchId + " details]========");
                System.out.print(currentBill);
                break;
            }
        }
        
    }

    @Override
    public String toString() {
        String message = "\n| Artist hiring cost               | $" + artistCost + " |";
        message += "\n| Venue hiring cost                | $" + venueCost + " |";
        message += "\n| Staff total cost                 | $" + staffCost + " |";
        message += "\n| Equipment total cost             | $" + equipmentCost + " |";
        message += "\n| General expenses total cost      | $" + generalExpensesCost + " |";
        message += "\n| Penalty fees total cost          | $" + penaltyFeesCost + " |";
        message += "\n------------------------------------------------\n";
        message += "         Total Amount: $"+ totalAmount;
        message += "\n------------------------------------------------\n";
        return   message;
    }
    
    public static float calculateIVA(float pureCost){
        float addedValue = pureCost * 0.12f;    
        return pureCost + addedValue;
    }

    public Bill(int id, float artistCost, float venueCost, float staffCost, float equipmentCost, float generalExpensesCost, float penaltyFeesCost, float totalAmount) {
        this.id = id;
        this.artistCost = artistCost;
        this.venueCost = venueCost;
        this.staffCost = staffCost;
        this.equipmentCost = equipmentCost;
        this.generalExpensesCost = generalExpensesCost;
        this.penaltyFeesCost = penaltyFeesCost;
        this.totalAmount = totalAmount;
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
     * @return the artistCost
     */
    public float getArtistCost() {
        return artistCost;
    }

    /**
     * @param artistCost the artistCost to set
     */
    public void setArtistCost(float artistCost) {
        this.artistCost = artistCost;
    }

    /**
     * @return the venueCost
     */
    public float getVenueCost() {
        return venueCost;
    }

    /**
     * @param venueCost the venueCost to set
     */
    public void setVenueCost(float venueCost) {
        this.venueCost = venueCost;
    }

    /**
     * @return the staffCost
     */
    public float getStaffCost() {
        return staffCost;
    }

    /**
     * @param staffCost the staffCost to set
     */
    public void setStaffCost(float staffCost) {
        this.staffCost = staffCost;
    }

    /**
     * @return the equipmentCost
     */
    public float getEquipmentCost() {
        return equipmentCost;
    }

    /**
     * @param equipmentCost the equipmentCost to set
     */
    public void setEquipmentCost(float equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    /**
     * @return the generalExpensesCost
     */
    public float getGeneralExpensesCost() {
        return generalExpensesCost;
    }

    /**
     * @param generalExpensesCost the generalExpensesCost to set
     */
    public void setGeneralExpensesCost(float generalExpensesCost) {
        this.generalExpensesCost = generalExpensesCost;
    }

    /**
     * @return the penaltyFeesCost
     */
    public float getPenaltyFeesCost() {
        return penaltyFeesCost;
    }

    /**
     * @param penaltyFeesCost the penaltyFeesCost to set
     */
    public void setPenaltyFeesCost(float penaltyFeesCost) {
        this.penaltyFeesCost = penaltyFeesCost;
    }

    /**
     * @return the totalAmount
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
    
}
