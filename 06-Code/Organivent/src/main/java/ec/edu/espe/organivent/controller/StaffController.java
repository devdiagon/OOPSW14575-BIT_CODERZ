package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.organivent.iterfaces.IStaff;
import ec.edu.espe.organivent.model.Employee;
import ec.edu.espe.organivent.model.Staff;
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
public class StaffController extends ManageMongoDB implements IStaff {
    private String collectionName = "Staff";
    private Class classType = Staff.class;

    @Override
    public void create(Staff staff) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(staff));
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<Staff> read() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Staff> staffInDB = ManageJson.passCollectionToList(this.coll, classType);
        
        return staffInDB;
    }

    @Override
    public void update(Staff staff) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",staff.getId());
        Document doc = Document.parse(ManageJson.passObjectToJson(staff));
        
        this.coll.findOneAndUpdate(filter, doc);
    }

    @Override
    public void delete(Staff staff) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        Bson filter = eq("id",staff.getId());
        
        this.coll.deleteOne(filter);
    }
    
    public int asignNewId(){        
        this.connectToDatabase();
        this.getFromCollection(collectionName);

        return HandleInput.increaseMaxId(this.coll);
    }
    
    public boolean validateTypeName(String typeNameToCheck){
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("type", typeNameToCheck);
        Document doc = this.coll.find(filter).first();
        
        if(doc==null){
            return true;
        }else{
            return doc.isEmpty();
        }
    }
    
    public float calculateTotalCost(ArrayList<Employee> employees, int workingHours, int workingDays){
        float totalCostPerHour = computeTotalCostPerHour(employees,workingHours);
        float totalStaffCost = totalCostPerHour;
        
        totalStaffCost *= workingDays;
        
        return totalStaffCost;
    }
    
    public float computeTotalCostPerHour(ArrayList<Employee> employees, int workingHours){
        float costPerHours = 0;
        float totalEmployeeGroupCost = 0;
        
        for(Employee currentEmployee : employees) {
            costPerHours = ((currentEmployee.getWage()) * workingHours);
            totalEmployeeGroupCost += costPerHours;
        }
        
        return totalEmployeeGroupCost;
    }
    
    public Staff findOne(int idToSearch){
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",idToSearch);
        Document doc = this.coll.find(filter).first();
        
        Staff staff = ManageJson.passJsonToObject(doc, classType);
        return staff;
    }
    
    public float calculateStaffListCost(ArrayList<Staff> staffList){
        float totalStaffCost = 0;
        
        for(Staff currentStaff:staffList){
            totalStaffCost += currentStaff.getTotalStaffCost();
        }
        
        return totalStaffCost;
    }
}
