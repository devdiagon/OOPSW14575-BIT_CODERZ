package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Administrator;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public interface IAdministrator {
    public void create(Administrator administrator);
    public ArrayList<Administrator> read();
}
