package ec.edu.espe.organivent.model;

/**
 *
 * @author Usuario
 */
public class Administrator {

    private int id;
    private String name;
    private String email;
    private int phoneNumber;

    @Override
    public String toString() {
        return "Administrator{" + "id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + '}';
    }

    public Administrator(int id, String name, String email, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    

    public void addEvent(Event event);

    public void modifyEvent(int eventId);

    public void removeEvent();

    public void reviewEvent(int eventId);
    
}
