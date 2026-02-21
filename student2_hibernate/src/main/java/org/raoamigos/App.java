package org.raoamigos;

/**
 * Hello world!
 *
 */
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import entity.Student;
import entity.Address;

public class App {
    public static void main(String[] args) {

        System.out.println("Project Started");

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Student student = new Student(103, "Rohan", "punjab");

        Address address = new Address();
        address.setStreet("Jalandhar");
        address.setCity("Punjab");
        address.setDate(new Date());
        address.setX(34.8);
        address.setOpen(true);

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(student);
        session.save(address);

        tx.commit();

        session.close();
        factory.close();

        System.out.println("Data Saved Successfully!");
    }
}

