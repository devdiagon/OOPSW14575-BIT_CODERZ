package ec.edu.espe.organivent.model;


/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Equipment {
    
    private String type;
    private float cost;
    private int quantity;
    

    @Override
    public String toString() {   
        return String.format("%-20s |$ %-9.2f| %-8d |", type, cost, quantity);
    }

    public Equipment(String type, float cost, int quantity) {
        this.type = type;
        this.cost = cost;
        this.quantity = quantity;
    }
    
    public Equipment(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
