package dao;

import entity.Student;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

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
        List<Student> students = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            students = session.createQuery("from Student", Student.class).list();
            logger.info("Fetched " + students.size() + " students");

            return students;
        } catch (Exception e) {
            logger.error("Error fetching students", e);
            return null;
        }
    }

    // update student
    public void updateStudent(Student student) {
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();

            logger.info("Student updated successfully: " + student);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error updating student", e);
        }
    }

    // Delete student by id
    public void deleteStudent(Long id) {
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
                logger.info("Student deleted with id: " + id);
            } else {
                logger.info("Student not found for deletion, id: " + id);
            }
            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting student", e);
        }
    }

    // get student by course
    public List<Student> getStudentsByCourse(String course) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Student> query = session.createQuery(
                    "from Student where course = :course",
                    Student.class
            );

            query.setParameter("course", course);
            List<Student> students = query.list();

            logger.info("Fetched " + students.size()
                    + " students for course: " + course);

            return students;
        } catch (Exception e) {
            logger.error("Error fetching students by course", e);
            return null;
        }
    }


}
