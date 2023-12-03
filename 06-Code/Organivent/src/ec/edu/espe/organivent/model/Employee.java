package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Employee {

    private int id;
    private String name;
    private float hourlyWage;

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + id + ", name=" + name + ", hourlyWage=" + hourlyWage + '}';
    }

    public Employee(int employeeId, String name, float hourlyWage) {
        this.id = employeeId;
        this.name = name;
        this.hourlyWage = hourlyWage;
    }

    public int getEmployeeId() {
        return id;
    }

    public void setEmployeeId(int employeeId) {
        this.id = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(float hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    
    
}
