package com.example.jdbcHibernate;

import com.example.jdbcHibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session;

        try {

            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Daffy", "Duck", "daffy@mail.com");

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            session.getTransaction().commit();

            // ----

            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get complete: " + myStudent);

            session.getTransaction().commit();

            // ----

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
