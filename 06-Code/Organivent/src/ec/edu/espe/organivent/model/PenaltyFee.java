package ec.edu.espe.organivent.model;

import ec.edu.espe.organivent.utils.HandleInput;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class PenaltyFee {
    private String type;
    private String description;
    private float cost;
    
    public static void createPenaltyFees(ArrayList<PenaltyFee> penaltyFees){
        int option =1;
        
        do{
            penaltyFees.add(createPenaltyFee());
            System.out.println("Want to add another penalty fee?  1)Yes - 2)No");
            option=HandleInput.insertInteger();
        }while(option == 1);
         
    }
    
    private static PenaltyFee createPenaltyFee(){
        Scanner scanner = new Scanner(System.in, "ISO-8859-1");
        float cost=0;
        
        System.out.println("Enter the type of the penalty fee:");
        String type = HandleInput.insertNonBlankString();
        
        System.out.println("Enter a brief description about this penalty fee:");
        String description = HandleInput.insertNonBlankString();
        
        do{
            System.out.println("Enter the cost of this penalty fee:");
            cost = HandleInput.insertPrice();
        }while(cost<1f);
        
        return new PenaltyFee(type, description, cost);
    }
    
    public static float calculateTotalPenaltyFeesCost(ArrayList<PenaltyFee> penaltyFeeList){
        float individualPenaltyFeeCost=0;
        float totalPenaltyFeeCost=0;
        
        System.out.println("Penalty Fees:");
        if(!penaltyFeeList.isEmpty()){
            for(PenaltyFee currentpenaltyFee:penaltyFeeList){
                individualPenaltyFeeCost = currentpenaltyFee.getCost();
                System.out.println("   Type: " + currentpenaltyFee.getType() + " has a cost of: $ " + individualPenaltyFeeCost);
                totalPenaltyFeeCost += individualPenaltyFeeCost;
            }
        }else{
            System.out.println("This event doesn't have any penalty fee");
            totalPenaltyFeeCost=0;
        }
        System.out.println(" Total Penalty Fees cost = $" + totalPenaltyFeeCost);
        
        
        return totalPenaltyFeeCost;
    }

    public PenaltyFee(String type, String description, float cost) {
        this.type = type;
        this.description = description;
        this.cost = cost;
    }
    

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
    }
    
    
}
