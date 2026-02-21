import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Rishi! Your Java 23 environment is ready.");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Student s1 = new Student("Rishi", "Rao", "rishirao@gmail.com");

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Saving the student...");
            session.persist(s1);
            transaction.commit();
            System.out.println("Student saved successfully. ID: " + s1.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}