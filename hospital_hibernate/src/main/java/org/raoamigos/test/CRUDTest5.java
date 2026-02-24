package org.raoamigos.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.raoamigos.entity.Doctor;
import org.raoamigos.entity.Patient;
import org.raoamigos.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class CRUDTest5 {

    public static void main(String[] args) {

        Doctor mehta = new Doctor();
        mehta.setName("Dr. Mehta");
        mehta.setSpecialization("Cardiology");
        mehta.setLicenseNo("LIC5001");

        Doctor singh = new Doctor();
        singh.setName("Dr. Singh");
        singh.setSpecialization("Neurology");
        singh.setLicenseNo("LIC5002");

        Patient aman = new Patient("Aman",
                LocalDate.of(1995, 5, 10),
                "O+",
                "9991110001");

        Patient priya = new Patient("Priya",
                LocalDate.of(1998, 3, 15),
                "A+",
                "9991110002");

        Patient raj = new Patient("Raj",
                LocalDate.of(1992, 7, 20),
                "B+",
                "9991110003");

        mehta.addPatient(aman);
        mehta.addPatient(priya);

        singh.addPatient(aman);
        singh.addPatient(raj);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(mehta);
        session.persist(singh);

        tx.commit();
        session.close();

        System.out.println("Doctors and patient assignments saved!");

        session = HibernateUtil.getSessionFactory().openSession();

        Doctor fetched =
                session.createQuery(
                                "SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.name = :n",
                                Doctor.class)
                        .setParameter("n", "Dr. Mehta")
                        .getSingleResult();

        System.out.println("\nPatients of Dr. Mehta:");
        fetched.getPatients().forEach(p -> System.out.println(p.getName()));

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();

        Patient fetchedPatient =
                session.createQuery(
                                "SELECT p FROM Patient p JOIN FETCH p.doctors WHERE p.name = :n",
                                Patient.class)
                        .setParameter("n", "Aman")
                        .getSingleResult();

        System.out.println("\nDoctors treating Aman:");
        fetchedPatient.getDoctors().forEach(d -> System.out.println(d.getName()));

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Doctor mehtaDB = session.get(Doctor.class, mehta.getId());
        Patient amanDB = session.get(Patient.class, aman.getId());

        mehtaDB.removePatient(amanDB);

        tx.commit();
        session.close();

        System.out.println("\nAman discharged from Dr. Mehta.");

        session = HibernateUtil.getSessionFactory().openSession();

        Doctor lazyDoc = session.get(Doctor.class, singh.getId());
        session.close();

        try {
            lazyDoc.getPatients().size();
        } catch (Exception e) {
            System.out.println("\nLazyInitializationException observed!");
        }

        session = HibernateUtil.getSessionFactory().openSession();

        Doctor fixedDoc =
                session.createQuery(
                                "SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.id = :id",
                                Doctor.class)
                        .setParameter("id", singh.getId())
                        .getSingleResult();

        session.close();

        System.out.println("\nLazy loading fixed. Patient count: "
                + fixedDoc.getPatients().size());
    }
}