package ec.edu.espe.organivent.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import ec.edu.espe.organivent.iterfaces.IAdministrator;
import ec.edu.espe.organivent.model.Administrator;
import ec.edu.espe.organivent.utils.Encriptation;
import ec.edu.espe.organivent.utils.HandleInput;
import ec.edu.espe.organivent.utils.ManageJson;
import ec.edu.espe.organivent.utils.ManageMongoDB;
import java.util.ArrayList;
import java.util.List;
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
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(administrator);
        Document doc = Document.parse(jsonString);
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<Administrator> read() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Administrator> administratorsInDb = new ArrayList<>();
        Administrator inserterAdministrator;
        
        List<Document> docList;
        docList = this.coll.find().projection(fields(excludeId())).into(new ArrayList<>());
        
        for (Document currentDoc : docList) {
            inserterAdministrator = ManageJson.passJsonToObject(currentDoc, classType);
            administratorsInDb.add(inserterAdministrator);
        }
        
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
