package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Set<Student> getStudents() {
        Session session = factory.openSession();
        Transaction transaction = null;
        Set<Student> students = null;
        try {
            transaction = session.beginTransaction();
            List<Student> list = session.createCriteria(Student.class).list();
            students = new HashSet<>(list);
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

    public static double getScoreSemester(Student student, int semester) {
        Set<Study> result = StudyController.getScoresBySemester(student, 1);
        double diem = -1;
        if (result != null) {
            diem = 0.0;
            for (Study study: result) {
                diem += study.getScore_mean();
            }
            diem /= result.size();
        }
        return diem;
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

        Student student4 = new Student();
        student4.setStudent_name("Dev Nguyen");
        student4.setBirthday("10/03/2005");
        StudentController.addStudent(student4);

        Student student5 = new Student();
        student5.setStudent_name("Evan");
        student5.setBirthday("19/05/2006");
        StudentController.addStudent(student5);
    }

    public static void updateClassroom(Classroom classroom) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Set<Student> students = classroom.getStudents();
            for (Student student: students) {
                student.setClassroom(null);
                session.update(student);
            }

            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
