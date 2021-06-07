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

    public static int updateScore(int student_ID, Study study) {
        Student student = getStudentByID(student_ID);
        if (student == null)
            return -2; // loi student null

        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();
            if (study.getStudyPK().getSubject().getSemester() == 1) {
                double x = study.getScore_mean() + student.getScore_I();
                student.setScore_I(x / student.getNum_I());
            }
            else {
                double x = study.getScore_mean() + student.getScore_II();
                student.setScore_I(x / student.getNum_II());
            }
            session.update(student);

            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = -1;
        } finally {
            session.close();
        }
        return flag;
    }

    public static void createStudents() {
        Student student1 = new Student();
        student1.setStudent_name("Alice");
        student1.setBirthday("02/02/2006");
        StudentController.addStudent(student1);

        Student student2 = new Student();
        student2.setStudent_name("Bob");
        student2.setBirthday("28/12/2005");
        StudentController.addStudent(student2);

        Student student3 = new Student();
        student3.setStudent_name("Ceci");
        student3.setBirthday("13/08/2006");
        StudentController.addStudent(student3);
    }
}
