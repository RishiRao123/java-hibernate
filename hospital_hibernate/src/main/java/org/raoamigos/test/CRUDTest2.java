package org.raoamigos.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.raoamigos.dao.DepartmentDAO;
import org.raoamigos.dao.DepartmentDAOImp;
import org.raoamigos.entity.Department;
import org.raoamigos.entity.Doctor;
import org.raoamigos.util.HibernateUtil;

import java.util.List;

public class CRUDTest2 {

    public static void main(String[] args) {

        DepartmentDAO deptDao = new DepartmentDAOImp();

        System.out.println("---- CREATE DEPARTMENTS ----");

        Department cardio = new Department();
        cardio.setName("Cardiology");
        cardio.setLocation("Block A");
        cardio.setHeadDoctorName("Dr. Rishi Rao");

        Department neuro = new Department();
        neuro.setName("Neurology");
        neuro.setLocation("Block B");
        neuro.setHeadDoctorName("Dr. Karan Rao");

        Doctor d1 = new Doctor();
        d1.setName("Dr. Smith");
        d1.setSpecialization("Heart Specialist");
        d1.setLicenseNo("LIC1001");

        Doctor d2 = new Doctor();
        d2.setName("Dr. John");
        d2.setSpecialization("Cardio Surgeon");
        d2.setLicenseNo("LIC1002");


        Doctor d3 = new Doctor();
        d3.setName("Dr. Alice");
        d3.setSpecialization("Neuro Physician");
        d3.setLicenseNo("LIC2001");

        Doctor d4 = new Doctor();
        d4.setName("Dr. Bob");
        d4.setSpecialization("Brain Surgeon");
        d4.setLicenseNo("LIC2002");

        cardio.addDoctor(d1);
        cardio.addDoctor(d2);

        neuro.addDoctor(d3);
        neuro.addDoctor(d4);

        deptDao.save(cardio);
        deptDao.save(neuro);

        System.out.println("Departments and Doctors saved!");

        System.out.println("\n---- BIDIRECTIONAL NAVIGATION ----");

        Session session = HibernateUtil.getSessionFactory().openSession();

        Department fetchedDept = session.get(Department.class, cardio.getId());
        System.out.println("Doctors in Cardiology:");

        for (Doctor doc : fetchedDept.getDoctors()) {
            System.out.println(" - " + doc.getName());
        }

        Doctor fetchedDoctor = session.get(Doctor.class, d1.getId());
        System.out.println("\nDoctor's Department: "
                + fetchedDoctor.getDepartment().getName());

        session.close();

        System.out.println("\n---- TRANSFER DOCTOR ----");

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Department cardioDB = session.get(Department.class, cardio.getId());
        Department neuroDB = session.get(Department.class, neuro.getId());
        Doctor smith = session.get(Doctor.class, d1.getId());

        cardioDB.removeDoctor(smith);
        neuroDB.addDoctor(smith);

        tx.commit();
        session.close();

        System.out.println("Dr. Smith transferred to Neurology.");

        // =========================================
        System.out.println("\n---- JPQL QUERY ----");

        session = HibernateUtil.getSessionFactory().openSession();

        List<Doctor> cardioDocs =
                session.createQuery(
                                "SELECT d FROM Doctor d WHERE d.department.name = :dname",
                                Doctor.class)
                        .setParameter("dname", "Cardiology")
                        .getResultList();

        System.out.println("Doctors in Cardiology via JPQL:");
        cardioDocs.forEach(doc -> System.out.println(doc.getName()));

        session.close();

        System.out.println("\n✔ Task 2 completed.");

    }
}
