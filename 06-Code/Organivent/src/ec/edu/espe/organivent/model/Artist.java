package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Artist {

    private String name;
    private float hiringCost;
    private Schedule entryTime;
    private Schedule departureTime;

    @Override
    public String toString() {
        return "Artist{" + "name=" + name + ", hiringCost=" + hiringCost + ", entryTime=" + entryTime + ", departureTime=" + departureTime + '}';
    }

    public Artist(String name, float hiringCost, Schedule entryTime, Schedule departureTime) {
        this.name = name;
        this.hiringCost = hiringCost;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHiringCost() {
        return hiringCost;
    }

    public void setHiringCost(float hiringCost) {
        this.hiringCost = hiringCost;
    }

    
    
    public void setEntryTime(Schedule entryTime);

    public void setDepartureTime(Schedule departureTime);
    
    
}
