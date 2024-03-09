package ec.edu.espe.organivent.model;


/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO
 * ESPE
 */
public class Employee extends Person{

    @Override
    public String toString() {
        return String.format("|Id: %-5d | %-20s |$ %-10.2f per hour|%n", super.getId(), super.getName(), super.getWage());
    }
    
    public Employee(int id, String name, float wage) {
        super(id, name, wage);
    }

    public Employee() {
    }
}
