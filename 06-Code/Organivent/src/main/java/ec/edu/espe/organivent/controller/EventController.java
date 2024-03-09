package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import ec.edu.espe.organivent.iterfaces.IEvent;
import ec.edu.espe.organivent.model.Bill;
import ec.edu.espe.organivent.model.Equipment;
import ec.edu.espe.organivent.model.Event;
import ec.edu.espe.organivent.model.Expense;
import ec.edu.espe.organivent.model.PenaltyFee;
import ec.edu.espe.organivent.model.Staff;
import ec.edu.espe.organivent.utils.CalculateIVA;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.ManageJson;
import ec.edu.espe.organivent.utils.ManageMongoDB;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Frederick
 */
public class EventController extends ManageMongoDB implements IEvent {
    private String collectionName = "Event";
    private Class classType = Event.class;

    @Override
    public void create(Event event) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(event));
        this.coll.insertOne(doc);
    }

    @Override
    public Event read(int searchId) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",searchId);
        Document doc = this.coll.find(filter).projection(fields(excludeId())).first();
        
        Event eventFoundInDB = ManageJson.passJsonToObject(doc, classType);
        return eventFoundInDB;
    }

    @Override
    public void update(Event event) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",event.getId());
        Document doc = Document.parse(ManageJson.passObjectToJson(event));
        
        this.coll.findOneAndUpdate(filter, doc);
    }

    @Override
    public void delete(Event event) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        Bson filter = eq("id",event.getId());
        
        this.coll.deleteOne(filter);
    }
    
    public int asignNewId(){        
        this.connectToDatabase();
        this.getFromCollection(collectionName);

        return HandleInput.increaseMaxId(this.coll);
    }
    
    public Event findOne(int idToSearch){
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",idToSearch);
        Document doc = this.coll.find(filter).first();
        
        Event event = ManageJson.passJsonToObject(doc, classType);
        return event;
    }

    @Override
    public ArrayList<Event> readTable() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Event> eventInDB = ManageJson.passCollectionToList(this.coll, classType);
        
        return eventInDB;
    }
    
    
    public Bill calculateEventCost(Event currentEvent){
        float estimatedEventCost=0;
        
        float artistHiringCost = considerTaxCost(currentEvent.getArtist().getWage());
        estimatedEventCost += artistHiringCost;
        
        float placeRentCost = considerTaxCost(currentEvent.getPlace().getRentCost());
        estimatedEventCost += artistHiringCost;
        
        float totalStaffCost = obtainStaffCostInEvent(currentEvent.getStaff());
        estimatedEventCost += totalStaffCost;
        
        float totalEquipmentCost = obtainEquipmentCostInEvent(currentEvent.getEquipment());
        estimatedEventCost += totalEquipmentCost;
        
        float totalGeneralExpenseCost = obtainExpenseCostInEvent(currentEvent.getGeneralExpenses());
        estimatedEventCost += totalGeneralExpenseCost;
        
        float totalPenaltyFeeCost = obtainPenaltyFeeCostInEvent(currentEvent.getPenaltyFees());
        estimatedEventCost += totalPenaltyFeeCost;
        
        return new Bill(currentEvent.getId(),artistHiringCost,placeRentCost,totalStaffCost,totalEquipmentCost,totalGeneralExpenseCost,totalPenaltyFeeCost,estimatedEventCost);
    }
    
    private float considerTaxCost(float initialCost){
        CalculateIVA ivaCalc = CalculateIVA.getInstance();
        
        return ivaCalc.applyIVA(initialCost);
    }
    
    private float obtainStaffCostInEvent(ArrayList<Staff> staffInEvent){
        StaffController stfctr = new StaffController();  
        float totalStaffCost=stfctr.calculateStaffListCost(staffInEvent);
        return totalStaffCost;
    }
    
    private float obtainEquipmentCostInEvent(ArrayList<Equipment> equipmentInEvent){
        EquipmentController eqmct = new EquipmentController();
        float totalEquipmentCost=eqmct.calculateEquipmentListCost(equipmentInEvent);
        return totalEquipmentCost;
    }
    
    private float obtainExpenseCostInEvent(ArrayList<Expense> expenseInEvent){
        ExpenseController expsctr = new ExpenseController();
        float totalGeneralExpenseCost=expsctr.calculateExpenseListCost(expenseInEvent);
        return totalGeneralExpenseCost;
    }
    
    private float obtainPenaltyFeeCostInEvent(ArrayList<PenaltyFee> penaltyFeeInEvent){
        PenaltyFeeController pntfctr = new PenaltyFeeController();
        float totalPenaltyFeeCost=pntfctr.calculateTotalPenaltyFeeListCost(penaltyFeeInEvent);
        return totalPenaltyFeeCost;
    }
}
