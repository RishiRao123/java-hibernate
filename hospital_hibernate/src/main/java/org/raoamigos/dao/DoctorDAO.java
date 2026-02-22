package org.raoamigos.dao;

import org.raoamigos.entity.Doctor;

import java.util.List;

public interface DoctorDAO {

    void save(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor findById(Long id);

    void update(Doctor doctor);

    void deleteById(Long id);

}
