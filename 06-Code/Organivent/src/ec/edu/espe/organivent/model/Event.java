package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Event {

    private int id;
    private Schedule startTime;
    private Schedule endTime;
    private Artist artist;
    private float estimatedCost;
    private Staff[] staff;
    private Equipment[] equipment;
    private EventPlace place;

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", artist=" + artist + ", estimatedCost=" + estimatedCost + ", staff=" + staff + ", equipment=" + equipment + ", place=" + place + '}';
    }

    public Event(int id, Schedule startTime, Schedule endTime, Artist artist, float estimatedCost, Staff[] staff, Equipment[] equipment, EventPlace place) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artist = artist;
        this.estimatedCost = estimatedCost;
        this.staff = staff;
        this.equipment = equipment;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public float getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(float estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public Staff[] getStaff() {
        return staff;
    }

    public void setStaff(Staff[] staff) {
        this.staff = staff;
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }

    public EventPlace getPlace() {
        return place;
    }

    public void setPlace(EventPlace place) {
        this.place = place;
    }
    
    

    public void addStaff(Staff staff);

    public void setEventPlace(EventPlace eventPlace);

    public void setArtistForEvent(Artist artist);

    public void addEquipment(Equipment equipment);

    public void setStartTime(Schedule startTime);

    public void setEndTime(Schedule endTime);
}
