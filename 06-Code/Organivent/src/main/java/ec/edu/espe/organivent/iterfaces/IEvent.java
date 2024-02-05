package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Event;

/**
 *
 * @author Frederick
 */
public interface IEvent {
    public void create(Event event);
    public Event read(int searchId);
    public void update(Event event);
    public void delete(Event event);
    
}
