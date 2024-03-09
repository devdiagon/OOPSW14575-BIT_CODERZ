package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import ec.edu.espe.organivent.iterfaces.IEvent;
import ec.edu.espe.organivent.model.Bill;
import ec.edu.espe.organivent.model.Event;
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
    private final float IVA = 0.12f;

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
    
    private float calculateIVA(float pureCost){
        float addedValue = pureCost * IVA;    
        return pureCost + addedValue;
    }
    
    public Bill calculateEventCost(Event currentEvent){
        float estimatedEventCost=0;
        float artistHiringCost=currentEvent.getArtist().getWage();
        float placeRentCost=currentEvent.getPlace().getRentCost();
        
        artistHiringCost = calculateIVA(artistHiringCost);
        estimatedEventCost += artistHiringCost;
        
        placeRentCost += calculateIVA(placeRentCost);
        estimatedEventCost += placeRentCost;
        
        StaffController stfctr = new StaffController();  
        float totalStaffCost=stfctr.calculateStaffListCost(currentEvent.getStaff());
        estimatedEventCost += totalStaffCost;
        
        
        EquipmentController eqmct = new EquipmentController();
        float totalEquipmentCost=eqmct.calculateEquipmentListCost(currentEvent.getEquipment());
        estimatedEventCost += totalEquipmentCost;
        
        ExpenseController expsctr = new ExpenseController();
        float totalGeneralExpenseCost=expsctr.calculateExpenseListCost(currentEvent.getGeneralExpenses());
        estimatedEventCost += totalGeneralExpenseCost;
        
        PenaltyFeeController pntfctr = new PenaltyFeeController();
        
        float totalPenaltyFeeCost=pntfctr.calculateTotalPenaltyFeeListCost(currentEvent.getPenaltyFees());
        estimatedEventCost += totalPenaltyFeeCost;
        
        return new Bill(currentEvent.getId(),artistHiringCost,placeRentCost,totalStaffCost,totalEquipmentCost,totalGeneralExpenseCost,totalPenaltyFeeCost,estimatedEventCost);
    }
}
