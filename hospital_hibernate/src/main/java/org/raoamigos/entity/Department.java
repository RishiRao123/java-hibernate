package org.raoamigos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(name = "head_doctor_name")
    private String headDoctorName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();

    public Department() {}

    public void addDoctor(Doctor doctor) {
        if(!this.doctors.contains(doctor)) {
            this.doctors.add(doctor);
            doctor.setDepartment(this);
        }
    }

    public void removeDoctor(Doctor doctor) {
        if(this.doctors.remove(doctor)) {
            doctor.setDepartment(null);
        }
    }

    public Long getId() {
        return id;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHeadDoctorName() {
        return headDoctorName;
    }

    public void setHeadDoctorName(String headDoctorName) {
        this.headDoctorName = headDoctorName;
    }
}
