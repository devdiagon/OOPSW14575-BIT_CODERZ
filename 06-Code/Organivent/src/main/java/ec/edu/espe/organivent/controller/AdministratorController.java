package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import ec.edu.espe.organivent.iterfaces.IAdministrator;
import ec.edu.espe.organivent.model.Administrator;
import ec.edu.espe.organivent.utils.Encriptation;
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
public class AdministratorController extends ManageMongoDB implements IAdministrator {
    private String collectionName = "Administrator";
    private Class classType = Administrator.class;
    
    @Override
    public void create(Administrator administrator) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(administrator));
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<Administrator> read() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Administrator> administratorsInDb = ManageJson.passCollectionToList(this.coll, classType);
        
        return administratorsInDb;
    }
    
    public boolean validateUserName(String userNameToCheck){;
        this.connectToDatabase();
        this.getFromCollection(collectionName);

        Bson filter = eq("userName", userNameToCheck);
        Document doc = this.coll.find(filter).first();
        
        if(doc==null){
            return true;
        
        }else{
            return doc.isEmpty();
        }
    }
    
    public int asignNewId(){        
        this.connectToDatabase();
        this.getFromCollection(collectionName);

        return HandleInput.increaseMaxId(this.coll);
    }
    
    public boolean validateCredentials(String insertedUserName, String insertedPassword){
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("userName", insertedUserName);
        Document doc = this.coll.find(filter).projection(fields(excludeId())).first();
        
        if(doc == null){
            return false;
        }else{
            Administrator administratorInDB = ManageJson.passJsonToObject(doc, classType);
            byte[] realPassword = administratorInDB.getPassword();
            return Encriptation.comparePasswords(insertedPassword,realPassword);
        }
    }
    
}
