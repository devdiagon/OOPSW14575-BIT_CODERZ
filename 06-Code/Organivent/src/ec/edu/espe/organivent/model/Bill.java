package ec.edu.espe.organivent.model;

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
    private float generalExpemsesCost;
    private float penaltyFeesCost;
    private float totalAmount;

    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", artistCost=" + artistCost + ", venueCost=" + venueCost + ", staffCost=" + staffCost + ", equipmentCost=" + equipmentCost + ", generalExpemsesCost=" + generalExpemsesCost + ", penaltyFeesCost=" + penaltyFeesCost + ", totalAmount=" + totalAmount + '}';
    }

    public Bill(int id, float artistCost, float venueCost, float staffCost, float equipmentCost, float generalExpemsesCost, float penaltyFeesCost, float totalAmount) {
        this.id = id;
        this.artistCost = artistCost;
        this.venueCost = venueCost;
        this.staffCost = staffCost;
        this.equipmentCost = equipmentCost;
        this.generalExpemsesCost = generalExpemsesCost;
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
     * @return the generalExpemsesCost
     */
    public float getGeneralExpemsesCost() {
        return generalExpemsesCost;
    }

    /**
     * @param generalExpemsesCost the generalExpemsesCost to set
     */
    public void setGeneralExpemsesCost(float generalExpemsesCost) {
        this.generalExpemsesCost = generalExpemsesCost;
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
