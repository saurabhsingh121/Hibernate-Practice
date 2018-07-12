package com.saurabh.hibernate.practice;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.hibernate.practice.entity.Employee;

public class CreateEmployeeDemo {

	public static void main(String[] args) throws ParseException {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		try {
			//use the session object to save java object
			//create a student object
			String theDateOfBirthStr = "15/12/1995";
			 
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			System.out.println("Creating Employee objects...");
			List<Employee> empList = new ArrayList<>();
				empList.add(new Employee("Pavan", "Siva", "MindTree",theDateOfBirth));
				empList.add(new Employee("Hridayesh", "Singh", "Verilog",theDateOfBirth));
				empList.add(new Employee("Sangam", "Raju", "TCS", theDateOfBirth));
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the employee objects");
			for (Employee employee : empList) {
				session.save(employee);
			}
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
		
	}

}
