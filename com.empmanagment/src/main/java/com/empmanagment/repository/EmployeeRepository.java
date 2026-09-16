package com.empmanagment.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.empmanagment.config.HibernateUtil;
import com.empmanagment.model.Employee;

@Repository
public class EmployeeRepository {

   
    public Employee addEmployee(Employee employee) {

        Session session =HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.persist(employee);

        session.getTransaction().commit();

        session.close();

        return employee;
    }

    
    public Employee getEmployeeById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee =
                session.get(Employee.class, id);

        session.close();

        return employee;
    }

  
    public List<Employee> getAllEmployees() {

        Session session =HibernateUtil.getSessionFactory().openSession();

        List<Employee> employees =
                session.createQuery("from Employee", Employee.class)
                       .getResultList();

        session.close();

        return employees;
    }

    
    public Employee updateEmployee(Employee employee) {

        Session session =HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.merge(employee);

        session.getTransaction().commit();

        session.close();

        return employee;
    }

    
    public void deleteEmployee(int id) {

        Session session =HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Employee employee =session.get(Employee.class, id);

        if (employee != null) {
            session.remove(employee);
        }

        session.getTransaction().commit();

        session.close();
    }
}