package ec.edu.espe.organivent.controller;

import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.organivent.iterfaces.IEmployee;
import ec.edu.espe.organivent.model.Employee;
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
public class EmployeeController extends ManageMongoDB implements IEmployee {
    private String collectionName = "Employee";
    private Class classType = Employee.class;

    @Override
    public void create(Employee employee) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Document doc = Document.parse(ManageJson.passObjectToJson(employee));
        this.coll.insertOne(doc);
    }

    @Override
    public ArrayList<Employee> read() {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        ArrayList<Employee> employeesInDB = ManageJson.passCollectionToList(this.coll, classType);
        
        return employeesInDB;
    }

    @Override
    public void update(Employee employee) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",employee.getId());
        Document doc = Document.parse(ManageJson.passObjectToJson(employee));
        
        this.coll.findOneAndUpdate(filter, doc);
    }

    @Override
    public void delete(Employee employee) {
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        Bson filter = eq("id",employee.getId());
        
        this.coll.deleteOne(filter);
    }
    
    public int asignNewId(){        
        this.connectToDatabase();
        this.getFromCollection(collectionName);

        return HandleInput.increaseMaxId(this.coll);
    }
    
    public Employee findOne(int idToSearch){
        this.connectToDatabase();
        this.getFromCollection(collectionName);
        
        Bson filter = eq("id",idToSearch);
        Document doc = this.coll.find(filter).first();
        
        Employee employee = ManageJson.passJsonToObject(doc, classType);
        return employee;
    }
    
}
