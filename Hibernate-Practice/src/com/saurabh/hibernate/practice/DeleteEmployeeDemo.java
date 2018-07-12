package com.saurabh.hibernate.practice;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.hibernate.practice.entity.Employee;

public class DeleteEmployeeDemo {

	public static void main(String[] args) throws ParseException {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		try {
			int empId = 6;
			
			//start a transaction
			session.beginTransaction();
			//Getting the employee object of id number 6
			Employee employee = session.get(Employee.class, empId);
			
			//delete the student
			//System.out.println("Deleting the object...."+ employee);
			//session.delete(employee);
			
			//delete employee with id =2
			System.out.println("Delete employee with id = 2");
			session.createQuery("delete from Employee where id = 2").executeUpdate();
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
		
	}

}
