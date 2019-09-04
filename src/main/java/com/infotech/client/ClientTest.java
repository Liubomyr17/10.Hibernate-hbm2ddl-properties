package com.infotech.client;

import com.infotech.entities.Employee;
import com.infotech.service.EmployeeService;
import com.infotech.service.impl.EmployeeServiceImpl;
import com.infotech.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Date;

public class ClientTest {

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            createEmployee(session);
            getEmployeeById(session);

            HibernateUtil.closeSessionFactory ();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private static void createEmployee(Session session) {
        session.beginTransaction();
        Integer id =(Integer)session.save(getEmployee());
        System.out.println("Employee is created  with Id::"+id);
        session.getTransaction().commit();

    }

    private static void getEmployeeById(Session session) {
        Employee employee = session.get(Employee.class, 2);
        if(employee != null){
            System.out.println(employee);
        }else{
            System.out.println("Employee doesn't exist with provideded Id..");
        }
    }

    private static Employee getEmployee(){
        Employee employee= new Employee();
        employee.setEmployeeName("Baris Bingel");
        employee.setEmail("baris.cs2017@gmail.com");
        employee.setSalary(50000.00);
        employee.setDoj(new Date());
        return employee;
    }
}
