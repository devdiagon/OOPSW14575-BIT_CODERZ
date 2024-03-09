package ec.edu.espe.organivent.model;

import java.util.ArrayList;

/**
 *
 * @author Frederick Tipan, Gabriel Vivanco, Jefferson Yepez - Bit Coderz - DCCO ESPE
 */
public class Staff {
    
    private int id;
    private String type;
    private ArrayList<Employee> employees;
    private float totalStaffCost;
    private int daysWorked;
    private int hoursWorked;
    

    public Staff(int id, String type, ArrayList<Employee> employees, float totalStaffCost, int daysWorked, int hoursWorked) {
        this.id = id;
        this.type = type;
        this.employees = employees;
        this.totalStaffCost = totalStaffCost;
        this.daysWorked = daysWorked;
        this.hoursWorked = hoursWorked;
    }
    
    public Staff(){
    }
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the employees
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * @return the totalStaffCost
     */
    public float getTotalStaffCost() {
        return totalStaffCost;
    }

    /**
     * @param totalStaffCost the totalStaffCost to set
     */
    public void setTotalStaffCost(float totalStaffCost) {
        this.totalStaffCost = totalStaffCost;
    }

    /**
     * @return the daysWorked
     */
    public int getDaysWorked() {
        return daysWorked;
    }

    /**
     * @param daysWorked the daysWorked to set
     */
    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    /**
     * @return the hoursWorked
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * @param hoursWorked the hoursWorked to set
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    
    
    
}
