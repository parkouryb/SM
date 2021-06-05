package Controller;

import Hibernate.HibernateUtil;
import Object.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static long addStudent(Student student) {
        Session sessionObj = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = sessionObj.beginTransaction();
            sessionObj.save(student);
            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            sessionObj.close();
        }
        return student.getStudent_ID();
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setStudent_name("Alice");
        long id = StudentController.addStudent(student);
        System.out.println(id);
    }
}
