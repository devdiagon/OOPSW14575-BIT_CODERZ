package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.organivent.iterfaces.IStaff;
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
}
