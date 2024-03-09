package ec.edu.espe.organivent.controller;

import ec.edu.espe.organivent.model.Expense;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public class ExpenseController {
    public float calculateExpenseListCost(ArrayList<Expense> expenseList){
        float individualExpenseCost=0;
        float totalGeneralExpenseCost=0;
        
        for(Expense currentExpense:expenseList){
            individualExpenseCost = currentExpense.getCostAmount();
            totalGeneralExpenseCost += individualExpenseCost;
        }
        
        return totalGeneralExpenseCost;
    }
}
