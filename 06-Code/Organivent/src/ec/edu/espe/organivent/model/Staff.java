package ec.edu.espe.organivent.model;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Staff {
    
    private int id;
    private String type;
    private Workday workday;
    private ArrayList<Employee> employees;
    private float totalStaffCost;

    @Override
    public String toString() {
        return "Staff{ id=" + getId() + "type=" + getType() + ", workday=" + getWorkday() + ", employees=" + getEmployees() + ", totalStaffCost=" + getTotalStaffCost() + '}';
    }

    public Staff(int id, String type, Workday workday, ArrayList<Employee> employees, float totalStaffCost) {
        this.id = id;
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

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public float getTotalStaffCost() {
        return totalStaffCost;
    }

    public void setTotalStaffCost(float totalStaffCost) {
        this.totalStaffCost = totalStaffCost;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public void asingWorkday(Workday workday){
    }

    public void addEmployee(Employee employee){
    }

    public float calculateCost(Workday workday){
        
        return (0);
    }
}
