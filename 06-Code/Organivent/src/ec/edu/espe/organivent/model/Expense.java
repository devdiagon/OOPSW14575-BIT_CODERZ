package ec.edu.espe.organivent.model;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Expense {
    private String type;
    private float costAmount;
    
    public static Expense createExpense(){
        
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
     * @return the costAmount
     */
    public float getCostAmount() {
        return costAmount;
    }

    /**
     * @param costAmount the costAmount to set
     */
    public void setCostAmount(float costAmount) {
        this.costAmount = costAmount;
    }

    public Expense(String type, float costAmount) {
        this.type = type;
        this.costAmount = costAmount;
    }

    @Override
    public String toString() {
        return "Expense{" + "type=" + type + ", costAmount=" + costAmount + '}';
    }
}
