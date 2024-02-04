package ec.edu.espe.organivent.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ec.edu.espe.organivent.iterfaces.IEmployee;
import ec.edu.espe.organivent.model.Employee;
import ec.edu.espe.organivent.utils.ManageMongoDB;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author Frederick
 */
public class EmployeeController extends ManageMongoDB implements IEmployee {
    private String collectionName = "Employee";

    @Override
    public void create(Employee employee) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(employee);
        Document doc = Document.parse(jsonString);
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<Employee> read() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
