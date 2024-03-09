package ec.edu.espe.organivent.model;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class PenaltyFee extends Expense{
    private String description;

    public PenaltyFee(String description, String type, float costAmount) {
        super(type, costAmount);
        this.description = description;
    }
    
    public PenaltyFee(){}

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
}
