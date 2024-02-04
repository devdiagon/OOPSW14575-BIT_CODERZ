package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.organivent.iterfaces.IEquipment;
import ec.edu.espe.organivent.model.Equipment;
import ec.edu.espe.organivent.utils.ManageJson;
import ec.edu.espe.organivent.utils.ManageMongoDB;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Frederick
 */
public class EquipmentController extends ManageMongoDB implements IEquipment {
    private String collectionName = "Equipment";
    private Class classType = Equipment.class;

    @Override
    public void create(Equipment equipment) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(equipment));
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<Equipment> read() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Equipment> equipmentInDB = ManageJson.passCollectionToList(this.coll, classType);
        
        return equipmentInDB;
    }

    @Override
    public void update(Equipment equipment) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("type",equipment.getType());
        Document doc = Document.parse(ManageJson.passObjectToJson(equipment));
        
        this.coll.findOneAndUpdate(filter, doc);
    }

    @Override
    public void delete(Equipment equipment) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        Bson filter = eq("type",equipment.getType());
        
        this.coll.deleteOne(filter);
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
    
}
