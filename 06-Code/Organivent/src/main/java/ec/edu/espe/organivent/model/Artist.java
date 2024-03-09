package ec.edu.espe.organivent.model;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Artist extends Person{
    
    public Artist(){}

    @Override
    public String toString() {
        
        return String.format(" %-3s|%-20s |$ %-10.2f|",super.getId(), super.getName(), super.getWage());
    }

    public Artist(int id, String name, float wage) {
        super(id, name, wage);
    }
}
