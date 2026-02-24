package org.raoamigos.test;

import org.raoamigos.dao.PatientDAO;
import org.raoamigos.dao.PatientDAOImp;
import org.raoamigos.entity.MedicalRecord;
import org.raoamigos.entity.Patient;

import java.time.LocalDate;

public class CRUDTest1 {

    public static void main(String[] args) {

        PatientDAO dao = new PatientDAOImp();

        System.out.println("---- CREATE ----");

        MedicalRecord record =
                new MedicalRecord(LocalDate.now(), "Flu", "Rest 3 days");

        Patient patient =
                new Patient("Rishi Rao",
                        LocalDate.of(2000, 5, 10),
                        "O+",
                        "9999999999");

        patient.setMedicalRecord(record);

        dao.save(patient);

        Long savedId = patient.getId();
        System.out.println("Patient saved with ID: " + savedId);

        System.out.println("\n---- READ ----");

        Patient fetched = dao.findById(savedId);

        System.out.println("Patient Name: " + fetched.getName());
        System.out.println("Diagnosis: "
                + fetched.getMedicalRecord().getDiagnosis());

        System.out.println("Note: MedicalRecord cannot navigate to Patient (unidirectional)");

        System.out.println("\n---- UPDATE ----");

        dao.updateNotes(savedId,
                "Updated: Follow-up required");

        Patient updated = dao.findById(savedId);
        System.out.println("Updated Notes: "
                + updated.getMedicalRecord().getNotes());

        System.out.println("\n---- DELETE ----");

        dao.delete(savedId);
        System.out.println("Patient deleted. MedicalRecord should also be deleted.");

    }
}