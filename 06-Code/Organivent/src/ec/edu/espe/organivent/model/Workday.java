package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Workday {

    private int workdayId;
    private Schedule entryTime;
    private Schedule departureTime;
    private float hoursWorked;

    @Override
    public String toString() {
        return getWorkdayId() + " in" + getEntryTime() + " out" + getDepartureTime();
    }

    public Workday(int workdayId, Schedule entryTime, Schedule departureTime) {
        this.workdayId = workdayId;
        this.entryTime = entryTime;
        this.departureTime = departureTime;
        
        this.hoursWorked = departureTime.getHours() - entryTime.getHours();
    }

    public int getWorkdayId() {
        return workdayId;
    }

    public void setWorkdayId(int workdayId) {
        this.workdayId = workdayId;
    }
    
    public void setEntryTime(Schedule entryTime){
    }
    
    

    public void sethoursWorked(float hoursWorked){
        this.hoursWorked = hoursWorked;
    }
    
    public float gethoursWorked(){
        return hoursWorked;
    }

    public void setDepartureTime(Schedule departureTime){
    }

    /**
     * @return the entryTime
     */
    public Schedule getEntryTime() {
        return entryTime;
    }

    /**
     * @return the departureTime
     */
    public Schedule getDepartureTime() {
        return departureTime;
    }
}
