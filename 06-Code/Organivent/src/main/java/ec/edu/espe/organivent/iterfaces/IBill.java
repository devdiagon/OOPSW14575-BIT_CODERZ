package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Bill;

/**
 *
 * @author Frederick
 */
public interface IBill {
    public void create(Bill bill);
    public Bill read(int searchId);
    public void update(Bill bill);
    public void delete(Bill bill);
}
