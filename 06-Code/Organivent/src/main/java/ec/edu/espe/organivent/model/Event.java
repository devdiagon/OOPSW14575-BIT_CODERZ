package ec.edu.espe.organivent.model;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.UseMongoDB;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Event {

    private int id;
    private Artist artist;
    private EventPlace place;
    private Schedule startTime;
    private Schedule endTime;
    private ArrayList<Staff> staff;
    private ArrayList<Equipment> equipment;
    private ArrayList<Expense> generalExpenses;
    private ArrayList<PenaltyFee> penaltyFees;
    

    public Event(int id, Artist artist, EventPlace place, Schedule startTime, Schedule endTime, ArrayList<Staff> staff, ArrayList<Equipment> equipment, ArrayList<Expense> generalExpenses, ArrayList<PenaltyFee> penaltyFees) {
        this.id = id;
        this.artist = artist;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.staff = staff;
        this.equipment = equipment;
        this.generalExpenses = generalExpenses;
        this.penaltyFees = penaltyFees;
    }
    
    public Event(){}

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
     * @return the artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * @return the place
     */
    public EventPlace getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(EventPlace place) {
        this.place = place;
    }

    /**
     * @return the startTime
     */
    public Schedule getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Schedule startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Schedule getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Schedule endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the staff
     */
    public ArrayList<Staff> getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    /**
     * @return the equipment
     */
    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    /**
     * @param equipment the equipment to set
     */
    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    /**
     * @return the generalExpenses
     */
    public ArrayList<Expense> getGeneralExpenses() {
        return generalExpenses;
    }

    /**
     * @param generalExpenses the generalExpenses to set
     */
    public void setGeneralExpenses(ArrayList<Expense> generalExpenses) {
        this.generalExpenses = generalExpenses;
    }

    /**
     * @return the penaltyFees
     */
    public ArrayList<PenaltyFee> getPenaltyFees() {
        return penaltyFees;
    }

    /**
     * @param penaltyFees the penaltyFees to set
     */
    public void setPenaltyFees(ArrayList<PenaltyFee> penaltyFees) {
        this.penaltyFees = penaltyFees;
    }
     
}
