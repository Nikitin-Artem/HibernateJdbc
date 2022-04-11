package com.example.jdbcHibernate;

import com.example.jdbcHibernate.model.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Paul", "Wall", "paul@mail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tempStudent);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
