package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Equipment {

    private String type;
    private float cost;
    private int quantity;

    @Override
    public String toString() {
        return "Equipment{" + "type=" + type + ", cost=" + cost + ", quantity=" + quantity + '}';
    }

    public Equipment(String type, float cost, int quantity) {
        this.type = type;
        this.cost = cost;
        this.quantity = quantity;
    }

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

    
    
    public void purchaseEquipment(){
    }
}
