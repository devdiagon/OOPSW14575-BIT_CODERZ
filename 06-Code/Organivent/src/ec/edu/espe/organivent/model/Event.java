package ec.edu.espe.organivent.model;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Event {

    private int id;
    private Schedule startTime;
    private Schedule endTime;
    private String artist;
    private float estimatedCost;
    private ArrayList<Staff> staff;
    private ArrayList<Equipment> equipment;
    private String place;

    @Override
    public String toString() {
        return String.format("%-6d |%-14s| %-14s| %-14s| %-10.2f| %-14s|%-14s|%-14s|", id,startTime, endTime, artist, estimatedCost, staff, equipment, place);
    }

    public Event(int id, Schedule startTime, Schedule endTime, String artist, float estimatedCost, ArrayList<Staff> staff, ArrayList<Equipment> equipment, String place) {
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public float getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(float estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    

    public void addStaff(Staff staff){
    }

    public void setEventPlace(EventPlace eventPlace){
    }

    public void setArtistForEvent(Artist artist){
    }

    public void addEquipment(Equipment equipment){
    }

    public void setStartTime(Schedule startTime){
    }

    public void setEndTime(Schedule endTime){
    }
}
