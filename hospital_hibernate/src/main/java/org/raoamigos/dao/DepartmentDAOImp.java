package org.raoamigos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.raoamigos.entity.Department;
import org.raoamigos.util.HibernateUtil;

import java.util.List;

public class DepartmentDAOImp implements DepartmentDAO {

    @Override
    public void save(Department department) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(department);

        tx.commit();
        session.close();
    }

    @Override
    public Department findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Department department = session.find(Department.class, id);

        session.close();
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Department> departments = session.createQuery("from Department", Department.class).getResultList();

        session.close();
        return departments;
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Department department = session.get(Department.class, id);

        if(department != null) {
            session.remove(department);
        }

        tx.commit();
        session.close();
    }
}
