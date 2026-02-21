package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Entity is declare to make this class an object for the
// database
@Entity
@Table(name="student")
public class Student {
    @Id
    private int id;
    private String firstName;
    private String city;

    public Student() {
        super();

    }
    public Student(int id, String firstName, String city) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.city = city;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", city=" + city + "]";
    }
}