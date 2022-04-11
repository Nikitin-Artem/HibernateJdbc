package com.example.jdbcHibernate;

import com.example.jdbcHibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestPrimaryKey {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating 3 student object...");
            Student tempStudent1 = new Student("Bonita", "Doe", "bonita@mail.com");
            Student tempStudent2 = new Student("John", "Public", "mary@mail.com");
            Student tempStudent3 = new Student("Mary", "Appelbaum", "john@mail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
