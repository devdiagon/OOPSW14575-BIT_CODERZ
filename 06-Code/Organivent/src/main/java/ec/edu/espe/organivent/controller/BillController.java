package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import ec.edu.espe.organivent.iterfaces.IBill;
import ec.edu.espe.organivent.model.Bill;
import ec.edu.espe.organivent.utils.ManageJson;
import ec.edu.espe.organivent.utils.ManageMongoDB;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Frederick
 */
public class BillController extends ManageMongoDB implements IBill {
    private String collectionName = "Bill";
    private Class classType = Bill.class;

    @Override
    public void create(Bill bill) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(bill));
        this.coll.insertOne(doc);
    }

    @Override
    public Bill read(int searchId) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",searchId);
        Document doc = this.coll.find(filter).projection(fields(excludeId())).first();
        
        Bill billFoundInDB = ManageJson.passJsonToObject(doc, classType);
        return billFoundInDB;
    }

    @Override
    public void update(Bill bill) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",bill.getId());
        Document doc = Document.parse(ManageJson.passObjectToJson(bill));
        
        this.coll.findOneAndReplace(filter, doc);
    }

    @Override
    public void delete(Bill bill) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        Bson filter = eq("id",bill.getId());
        
        this.coll.deleteOne(filter);
    }

    @Override
    public ArrayList<Bill> readTable() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Bill> billInDB = ManageJson.passCollectionToList(this.coll, classType);
        
        return billInDB;
    }
    
    public Bill findOne(int idToSearch){
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",idToSearch);
        Document doc = this.coll.find(filter).first();
        
        Bill bill = ManageJson.passJsonToObject(doc, classType);
        return bill;
    }
    
    public boolean decectIdinDB(int idToSearch){
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",idToSearch);
        Document doc = this.coll.find(filter).first();
        
        if(doc == null){
            return false;
        }else{
            return true;
        }
    }
}
