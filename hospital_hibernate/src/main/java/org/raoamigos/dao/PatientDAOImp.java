package org.raoamigos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.raoamigos.entity.Patient;
import org.raoamigos.util.HibernateUtil;

public class PatientDAOImp implements PatientDAO {

    @Override
    public void save(Patient patient) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(patient);

        tx.commit();
        session.close();
    }

    @Override
    public Patient findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Patient patient = session.get(Patient.class, id);

        session.close();
        return patient;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Patient patient = session.get(Patient.class, id);
        if(patient != null) {
            session.remove(patient);
        }

        tx.commit();
        session.close();
    }

    @Override
    public void updateNotes(Long patientId, String notes) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Patient patient = session.get(Patient.class, patientId);
        if(patient != null && patient.getMedicalRecord() != null) {
            patient.getMedicalRecord().setNotes(notes);
        }

        tx.commit();
        session.close();
    }

}
