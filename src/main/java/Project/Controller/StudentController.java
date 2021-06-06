package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.List;

@Getter
@Setter
public class StudentController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    private static int limitLowAge = 15;
    private static int limitHighAge = 20;

    public static Student getStudentByID(long id) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Student student = null;
        try {
            transaction = session.beginTransaction();

            student = (Student) session.get(Student.class, id);

            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return student;
    }

    public static long addStudent(Student student) {
        Session session = factory.openSession();
        Transaction transaction = null;
        long id = -999;
        try {
            transaction = session.beginTransaction();
            System.out.println(student);
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if (student.getBirthday() == null) {
                return -404;
            }
            int age = year - Integer.parseInt(student.getBirthday().split("/")[2]);
            if (age > limitHighAge || age < limitLowAge) {
                return -1;
            }
            session.save(student);
            id = student.getStudent_ID();
            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return id;
    }

    public static List<Student> getStudents() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Student> students = null;
        try {
            transaction = session.beginTransaction();

            students = session.createCriteria(Student.class).list();

            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return students;
    }


}
