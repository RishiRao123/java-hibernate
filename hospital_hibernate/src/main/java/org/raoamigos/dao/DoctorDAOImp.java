package org.raoamigos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.raoamigos.entity.Doctor;
import org.raoamigos.util.HibernateUtil;

import java.util.List;

public class DoctorDAOImp implements DoctorDAO {

    @Override
    public void save(Doctor doctor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(doctor);

        tx.commit();
        session.close();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Doctor> doctors = session.createQuery("from Doctor", Doctor.class).getResultList();

        session.close();
        return doctors;
    }

    @Override
    public Doctor findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Doctor doctor = session.find(Doctor.class, id);

        session.close();
        return doctor;
    }

    @Override
    public void update(Doctor doctor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(doctor);

        tx.commit();
        session.close();
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Doctor doctor = session.get(Doctor.class, id);

        if(doctor != null) {
            session.remove(doctor);
        }

        tx.commit();
        session.close();
    }
}
