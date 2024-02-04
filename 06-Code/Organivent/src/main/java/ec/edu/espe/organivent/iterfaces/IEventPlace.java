package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.EventPlace;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public interface IEventPlace {
    public void create(EventPlace eventPlace);
    public ArrayList<EventPlace> read();
    public void update(EventPlace eventPlace);
    public void delete(EventPlace eventPlace);
}
