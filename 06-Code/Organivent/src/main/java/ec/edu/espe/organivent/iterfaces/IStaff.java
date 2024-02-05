package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Staff;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public interface IStaff {
    public void create(Staff staff);
    public ArrayList<Staff> read();
    public void update(Staff staff);
    public void delete(Staff staff);
    
}
