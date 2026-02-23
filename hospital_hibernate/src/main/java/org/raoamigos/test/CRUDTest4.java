package org.raoamigos.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.raoamigos.entity.Appointment;
import org.raoamigos.entity.Prescription;
import org.raoamigos.util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CRUDTest4 {

    public static void main(String[] args) {

        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentDate(LocalDateTime.of(2026, 02, 24, 10, 0));
        appointment1.setStatus("SCHEDULED");
        appointment1.setReason("Chest Pain");

        Prescription prescription = new Prescription();
        prescription.setMedicines("Dolo, Paracetamol");
        prescription.setDosage("650, 350");
        prescription.setIssuedDate(LocalDate.of(2026, 02, 24));

        appointment1.setPrescription(prescription);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(appointment1);

        System.out.println("Appointment with prescription saved!");

        transaction.commit();
        session.close();

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDate(LocalDateTime.of(2026, 02, 24, 15, 0));
        appointment2.setStatus("SCHEDULED");
        appointment2.setReason("Headache");
        appointment2.setPrescription(null);

        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        session.persist(appointment2);

        System.out.println("Appointment with null prescription saved!");

        transaction.commit();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();

        List<Appointment> appointments = session.createQuery("from Appointment", Appointment.class).getResultList();

        for (Appointment appt : appointments) {
            System.out.println("\nAppointment ID: " + appt.getId());
            System.out.println("Status: " + appt.getStatus());

            if (appt.getPrescription() != null) {
                System.out.println("Medicines: " +
                        appt.getPrescription().getMedicines());
            } else {
                System.out.println("No prescription issued.");
            }
        }

    }
}
