package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Equipment {
    
    private static boolean header = false;
    private String type;
    private float cost;
    private int quantity;

    @Override
    public String toString() {
        if (!header){
            String headers = "             Type               | Cost    | Quantity|\n" +
                             "          -------------------------------------------";
            System.out.print(headers);
            header = true;            
        }
            
        return String.format("%-20s | %-8.2f| %-8d|", type, cost, quantity);
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
