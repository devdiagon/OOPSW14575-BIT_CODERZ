package ec.edu.espe.organivent.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.organivent.utils.ManageMongoDB;
import org.bson.Document;

/**
 *
 * @author Frederick
 */
public abstract class DataBaseController {
    protected MongoCollection<Document> coll;
    
    
    public void getFromCollection(String collectionName){
        ManageMongoDB dbHandler = ManageMongoDB.getInstance();
        MongoDatabase db = dbHandler.getDB();
        
        coll = db.getCollection(collectionName);
    }
}
