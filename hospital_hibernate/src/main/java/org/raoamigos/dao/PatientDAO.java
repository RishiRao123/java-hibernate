package org.raoamigos.dao;

import org.raoamigos.entity.Patient;

public interface PatientDAO {

    void save(Patient patient);

    Patient findById(Long id);

    void delete(Long id);

    void updateNotes(Long patientId, String notes);

}
