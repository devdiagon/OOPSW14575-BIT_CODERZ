package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Employee;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public interface IEmployee {
    public void create(Employee employee);
    public ArrayList<Employee> read();
    public void update(Employee employee);
    public void delete(Employee employee);
}
