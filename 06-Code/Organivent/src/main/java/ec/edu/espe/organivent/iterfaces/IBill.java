package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Bill;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public interface IBill {
    public void create(Bill bill);
    public Bill read(int searchId);
    public ArrayList<Bill> readTable();
    public void update(Bill bill);
    public void delete(Bill bill);
}
