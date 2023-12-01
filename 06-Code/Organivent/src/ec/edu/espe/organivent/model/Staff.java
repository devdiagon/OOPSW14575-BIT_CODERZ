package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Staff {

    private String type;
    private Workday workday;
    private Employee[] employees;
    private float totalStaffCost;

    @Override
    public String toString() {
        return "Staff{" + "type=" + type + ", workday=" + workday + ", employees=" + employees + ", totalStaffCost=" + totalStaffCost + '}';
    }

    public Staff(String type, Workday workday, Employee[] employees, float totalStaffCost) {
        this.type = type;
        this.workday = workday;
        this.employees = employees;
        this.totalStaffCost = totalStaffCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Workday getWorkday() {
        return workday;
    }

    public void setWorkday(Workday workday) {
        this.workday = workday;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public float getTotalStaffCost() {
        return totalStaffCost;
    }

    public void setTotalStaffCost(float totalStaffCost) {
        this.totalStaffCost = totalStaffCost;
    }
    
    

    public void asingWorkday(Workday workday);

    public void addEmployee(Employee employee);

    public float calculateCost(Workday workday);
}
