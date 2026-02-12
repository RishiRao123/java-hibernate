package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentDAO {

//    void save(Student student);
//    Student getById(Long id);
//    List<Student> getAll();
//    void update(Student student);
//    void delete(Long id);
//    List<Student> getStudentsByCourse(String course);

    private static final Logger logger = LoggerFactory.getLogger(StudentDAO.class);

    // To save a student object
    public void saveStudent(Student student) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.persist(student);
            tx.commit();

            logger.info("Student saved successfully with id: {}", student.getId());

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
                logger.error("Transaction rolled back due to error");
            }

            logger.error("Error saving student", e);
        }
    }


}
