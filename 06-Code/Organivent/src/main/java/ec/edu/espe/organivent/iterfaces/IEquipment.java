package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Equipment;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public interface IEquipment {
    public void create(Equipment equipment);
    public ArrayList<Equipment> read();
    public void update(Equipment equipment);
    public void delete(Equipment equipment);
}
