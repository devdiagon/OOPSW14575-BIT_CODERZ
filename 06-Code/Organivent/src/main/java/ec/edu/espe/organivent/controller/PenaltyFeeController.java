package ec.edu.espe.organivent.controller;

import ec.edu.espe.organivent.model.PenaltyFee;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public class PenaltyFeeController {
    public float calculateTotalPenaltyFeeListCost(ArrayList<PenaltyFee> penaltyFeeList){
        float individualPenaltyFeeCost=0;
        float totalPenaltyFeeCost=0;
        
        if(!penaltyFeeList.isEmpty()){
            for(PenaltyFee currentpenaltyFee:penaltyFeeList){
                individualPenaltyFeeCost = currentpenaltyFee.getCostAmount();
                totalPenaltyFeeCost += individualPenaltyFeeCost;
            }
        }else{
            totalPenaltyFeeCost=0;
        }
        
        return totalPenaltyFeeCost;
    }
}
