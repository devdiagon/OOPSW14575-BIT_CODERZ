package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import ec.edu.espe.organivent.iterfaces.IEvent;
import ec.edu.espe.organivent.model.Event;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.ManageJson;
import ec.edu.espe.organivent.utils.ManageMongoDB;
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
    
}
