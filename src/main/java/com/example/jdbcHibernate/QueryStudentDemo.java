package com.example.jdbcHibernate;

import com.example.jdbcHibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Student> theStudents;

            // ----

            theStudents = session.createQuery("from Student").list();

            System.out.println("All students");
            displayStudents(theStudents);

            // ----

            theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();

            System.out.println("\n\nStudents who have name of Doe");
            displayStudents(theStudents);

            // ----

            theStudents = session.createQuery("from Student s where " +
                    "s.lastName='Doe' OR s.firstName='Daffy'").list();

            System.out.println("\nStudents who have last name of Doe or first name of Daffy");
            displayStudents(theStudents);

            // ----

            theStudents = session.createQuery("from Student s where s.email LIKE '%mail.com'").list();

            System.out.println("\nStudent who email ends with (mail.com)");
            displayStudents(theStudents);

            // ----

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student x : theStudents) {
            System.out.println(x);
        }
    }
}
