package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Workday {

    private int workdayId;
    private Schedule entryTime;
    private Schedule departureTime;

    @Override
    public String toString() {
        return "Workday{" + "workdayId=" + workdayId + ", entryTime=" + entryTime + ", departureTime=" + departureTime + '}';
    }

    public Workday(int workdayId, Schedule entryTime, Schedule departureTime) {
        this.workdayId = workdayId;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
    }

    public int getWorkdayId() {
        return workdayId;
    }

    public void setWorkdayId(int workdayId) {
        this.workdayId = workdayId;
    }
    
    

    public void setEntryTime(Schedule entryTime);

    public void setDepartureTime(Schedule departureTime);
}
