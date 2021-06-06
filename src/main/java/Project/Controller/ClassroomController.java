package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.*;
import org.hibernate.*;

import java.util.Collection;
import java.util.List;

public class ClassroomController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void addClassroom(Classroom classroom) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.save(classroom);

            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static long getIDByName(String name) {
        Session session = factory.openSession();
        Transaction transaction = null;
        long id = -999;
        try {
            transaction = session.beginTransaction();

            List<Classroom> lists = session.createCriteria(Classroom.class).list();
            for (Classroom classroom: lists) {
                if (classroom.getClass_name().equals(name)) {
                    id = classroom.getClassroom_ID();
                    break;
                }
            }

            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return 80001;
    }

    public static Collection<Student> getStudents(long classroom_ID) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Collection<Student> students = null;
        try {
            transaction = session.beginTransaction();

            Classroom classroom = (Classroom) session.get(Classroom.class, classroom_ID);
            students = classroom.getStudents();

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

    public static void createClassroom() {
        String[] names = new String[]{"10A1", "10A2", "10A3", "10A4", "11A1", "11A2", "11A3", "12A1", "12A2"};
        for (String name: names) {
            Classroom classroom = new Classroom();
            classroom.setClass_name(name);
            classroom.setNumber(40);
            addClassroom(classroom);
        }
    }
}
