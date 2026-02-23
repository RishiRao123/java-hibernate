package org.raoamigos.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.raoamigos.entity.Appointment;
import org.raoamigos.entity.Doctor;
import org.raoamigos.util.HibernateUtil;

import java.time.LocalDateTime;

public class CRUDTest3 {

    public static void main(String[] args) {

        Doctor d = new Doctor();
        d.setName("Rob Stark");
        d.setSpecialization("Cardio Surgeon");
        d.setLicenseNo("LIC3001");

        Appointment a1 = new Appointment();
        a1.setAppointmentDate(LocalDateTime.of(2026, 2, 24, 10, 0));
        a1.setStatus("SCHEDULED");
        a1.setReason("Heart pain");

        Appointment a2 = new Appointment();
        a2.setAppointmentDate(LocalDateTime.of(2026, 2, 23, 14, 0));
        a2.setStatus("COMPLETED");
        a2.setReason("Checkup");

        Appointment a3 = new Appointment();
        a3.setAppointmentDate(LocalDateTime.now());
        a3.setStatus("CANCELLED");
        a3.setReason("Headache");

        d.getAppointments().add(a1);
        d.getAppointments().add(a2);
        d.getAppointments().add(a3);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(d);

        tx.commit();
        session.close();

        System.out.println("Doctor and appointments saved!");

        session = HibernateUtil.getSessionFactory().openSession();

        Appointment appt = session.get(Appointment.class, 1L);

        System.out.println("Appointment Status: " + appt.getStatus());
        System.out.println("Cannot navigate from Appointment to Doctor (unidirectional)");

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Doctor doctor = session.get(Doctor.class, d.getId());

        for(Appointment ap : doctor.getAppointments()) {
            if("SCHEDULED".equals(ap.getStatus())) {
                ap.setStatus("COMPLETED");
                System.out.println("Appointment status updated!");
                break;
            }
        }

        tx.commit();
        session.close();

    }
}