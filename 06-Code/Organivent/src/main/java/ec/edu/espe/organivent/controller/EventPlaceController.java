package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.organivent.iterfaces.IEventPlace;
import ec.edu.espe.organivent.model.EventPlace;
import ec.edu.espe.organivent.utils.ManageJson;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Frederick
 */
public class EventPlaceController extends DataBaseController implements IEventPlace {
    private String collectionName = "EventPlace";
    private Class classType = EventPlace.class;

    @Override
    public void create(EventPlace eventPlace) {
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(eventPlace));
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<EventPlace> read() {
        this.getFromCollection(collectionName);
        
        ArrayList<EventPlace> eventPlaceInDB = ManageJson.passCollectionToList(this.coll, classType);
        
        return eventPlaceInDB;
    }

    @Override
    public void update(EventPlace eventPlace) {
        this.getFromCollection(collectionName);
        
        Bson filter = eq("name",eventPlace.getName());
        Document doc = Document.parse(ManageJson.passObjectToJson(eventPlace));
        
        this.coll.findOneAndUpdate(filter, doc);
    }

    @Override
    public void delete(EventPlace eventPlace) {
        this.getFromCollection(collectionName);
        Bson filter = eq("name",eventPlace.getName());
        
        this.coll.deleteOne(filter);
    }
    
    public boolean validateName(String nameToCheck){
        this.getFromCollection(collectionName);
        
        Bson filter = eq("name", nameToCheck);
        Document doc = this.coll.find(filter).first();
        
        if(doc==null){
            return true;
        }else{
            return doc.isEmpty();
        }
    }
    
    public EventPlace findOne(String nameToSearch){
        this.getFromCollection(collectionName);
        
        Bson filter = eq("name",nameToSearch);
        Document doc = this.coll.find(filter).first();
        
        EventPlace eventPlace = ManageJson.passJsonToObject(doc, classType);
        return eventPlace;
    }
}
