package com.saurabh.hibernate.practice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.hibernate.practice.entity.Employee;


public class UpdateEmployeeDemo {
	
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
			employee.setFirstName("Satyendra");
			//Committing the transaction
			session.getTransaction().commit();
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//Update employee whose company is 'Verilog'
			session.createQuery("update Employee e set e.company='TCS' where e.company = 'Verilog'")
					.executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
