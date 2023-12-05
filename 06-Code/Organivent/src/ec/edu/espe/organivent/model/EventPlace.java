package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class EventPlace {

    private String name;
    private String adress;
    private float rentCost;
    private int capacity;

    @Override
    public String toString() {
        return String.format("%-30s|%-30s|%-10.2f|%-14d", name,adress,rentCost,capacity);
    }

    public EventPlace(String name, String adress, float rentCost, int capacity) {
        this.name = name;
        this.adress = adress;
        this.rentCost = rentCost;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public float getRentCost() {
        return rentCost;
    }

    public void setRentCost(float rentCost) {
        this.rentCost = rentCost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    
}
