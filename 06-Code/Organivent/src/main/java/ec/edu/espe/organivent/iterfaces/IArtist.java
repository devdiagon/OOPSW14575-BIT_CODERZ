package ec.edu.espe.organivent.iterfaces;

import ec.edu.espe.organivent.model.Artist;
import java.util.ArrayList;

/**
 *
 * @author Frederick
 */
public interface IArtist {
    public void create(Artist artist);
    public ArrayList<Artist> read();
    public void update(Artist artist);
    public void delete(Artist artist);
}
