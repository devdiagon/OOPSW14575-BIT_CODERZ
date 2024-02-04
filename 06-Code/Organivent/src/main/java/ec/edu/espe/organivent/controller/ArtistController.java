package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.organivent.iterfaces.IArtist;
import ec.edu.espe.organivent.model.Artist;
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
public class ArtistController extends ManageMongoDB implements IArtist {
    private String collectionName = "Artist";
    private Class classType = Artist.class;

    @Override
    public void create(Artist artist) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(artist));
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<Artist> read() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Artist> artistsInDB = ManageJson.passCollectionToList(this.coll, classType);
        
        return artistsInDB;
    }

    @Override
    public void update(Artist artist) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",artist.getId());
        Document doc = Document.parse(ManageJson.passObjectToJson(artist));
        
        this.coll.findOneAndUpdate(filter, doc);
    }

    @Override
    public void delete(Artist artist) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        Bson filter = eq("id",artist.getId());
        
        this.coll.deleteOne(filter);
    }
    
    public int asignNewId(){        
        this.connectToDatabase();
        this.getFromCollection(collectionName);

        return HandleInput.increaseMaxId(this.coll);
    }
    
}
