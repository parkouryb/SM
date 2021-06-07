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

public class StudentController {
    // -1: loi db
    // -2: out age range
    // -3: nhap birthday sai format hoặc null
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    private static int limitLowAge = 15;
    private static int limitHighAge = 20;

    public static int getLimitLowAge() {
        return limitLowAge;
    }

    public static void setLimitLowAge(int limitLowAge) {
        StudentController.limitLowAge = limitLowAge;
    }

    public static int getLimitHighAge() {
        return limitHighAge;
    }

    public static void setLimitHighAge(int limitHighAge) {
        StudentController.limitHighAge = limitHighAge;
    }

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
        long id = -1;
        try {
            transaction = session.beginTransaction();
            System.out.println(student);
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if (student.getBirthday() == null) {
                id = -3;
            }
            else {
                int age = year - Integer.parseInt(student.getBirthday().split("/")[2]);
                if (age > limitHighAge || age < limitLowAge) {
                    id = -2;
                }
                else {
                    session.save(student);
                    id = student.getStudent_ID();
                }
            }
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
