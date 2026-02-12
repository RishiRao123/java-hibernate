package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();

            logger.info("Student saved successfully with id: {}", student.getId());

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
                logger.error("Transaction rolled back due to error");
            }

            logger.error("Error saving student", e);
        }
    }

    // To get student by id
    public Student getStudentById(Long id) {
        Transaction transaction = null;
        Student student = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            student = session.get(Student.class, id);
            transaction.commit();

            if (student != null) {
                logger.info("Student found: " + student);
            } else {
                logger.info("No student found with id: " + id);
            }

        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
                logger.error("Transaction rolled back due to error");
            }

            logger.error("Error getting student", e);

        }

        return student;
    }

    // return all students
    public List<Student> getAllStudents() {
        Transaction transaction = null;
        List<Student> students = new ArrayList<>();


    }


}
