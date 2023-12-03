package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Ticket {

    private int id;
    private float value;
    private int code;
    private String seat;
    private Schedule eventTime;
    private String eventPlace;

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", value=" + value + ", code=" + code + ", seat=" + seat + ", eventTime=" + eventTime + ", eventPlace=" + eventPlace + '}';
    }

    public Ticket(int id, float value, int code, String seat, Schedule eventTime, String eventPlace) {
        this.id = id;
        this.value = value;
        this.code = code;
        this.seat = seat;
        this.eventTime = eventTime;
        this.eventPlace = eventPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }
    
    

    public void setEventTime(Schedule eventTime){
    }
}
