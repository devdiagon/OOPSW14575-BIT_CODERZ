package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Employee {

    private static boolean header = false;
    private int id;
    private String name;
    private float hourlyWage;

    @Override
    public String toString() {
        if (!header) {
            String headers = "          ID    | Name                | Hourly Wage|\n" +
                             "          -----------------------------------------";
            System.out.print(headers);
            header = true;
        }

        return String.format("%-5d | %-20s | %.2f|", id, name, hourlyWage);
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
