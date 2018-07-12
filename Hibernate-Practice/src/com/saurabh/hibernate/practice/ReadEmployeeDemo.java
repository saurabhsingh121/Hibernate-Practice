package com.saurabh.hibernate.practice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.hibernate.practice.entity.Employee;


public class ReadEmployeeDemo {
	
	public static void main(String[] args) {
		//Create session factory 
		SessionFactory factory  = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		//Create session
		Session session = factory.getCurrentSession();
		try {
			int id = 1;
			//begin transaction
			session.beginTransaction();
			//Retrieving employee object
			System.out.println("Retrieving employee object with id ..."+id);
			Employee employee = session.get(Employee.class, id);
			System.out.println(employee);
			//commiting the transaction
			session.getTransaction().commit();
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//Querying from Employee table
			List<Employee> empList = session.createQuery("from Employee").getResultList();
			
			//display the employee
			displayEmployees(empList);
			
			//querying employee with company ADP
			empList = session.createQuery("from Employee e where e.company = 'ADP'").getResultList();
			//displaying the result
			displayEmployees(empList);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayEmployees(List<Employee> empList) {
		for (Employee employee2 : empList) {
			System.out.println(employee2);
		}
	}
}
