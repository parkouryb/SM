package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.*;
import org.hibernate.*;

import java.util.Collection;
import java.util.List;

public class ClassroomController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static int addClassroom(Classroom classroom) {
        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();
            session.save(classroom);
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

    public static int getIDByName(String name) {
        Session session = factory.openSession();
        Transaction transaction = null;
        int id = 0;
        try {
            transaction = session.beginTransaction();

            List<Classroom> lists = session.createCriteria(Classroom.class).list();
            for (Classroom classroom: lists) {
                if (classroom.getClass_name().equals(name)) {
                    id = (int) classroom.getClassroom_ID();
                    break;
                }
            }

            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
            id = -1;
        } finally {
            session.close();
        }

        return id;
    }

    public static Collection<Student> getStudents(String classroom_name) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Collection<Student> students = null;
        try {
            transaction = session.beginTransaction();

            long id = getIDByName(classroom_name);
            Classroom classroom = (Classroom) session.get(Classroom.class, id);
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

    public static Classroom getClassroomByID(long classroom_ID) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Classroom classroom = null;
        try {
            transaction = session.beginTransaction();

            classroom = (Classroom) session.get(Classroom.class, classroom_ID);

            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return classroom;
    }

    public static int addStudentToClass(long student_ID, String classroom_name) {
        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();

            long id = getIDByName(classroom_name);
            if (id < 0) {
                flag = -3;
            }
            else {
                Classroom classroom = getClassroomByID(id);
                if (classroom == null) {
                    flag = -4;
                }
                else {
                    if (classroom.getStudents().size() + 1 > classroom.getNumber()) {
                        flag = -2; // out number
                    }
                    else {
                        Student student = StudentController.getStudentByID(student_ID);
                        student.setClassroom(classroom);
                        session.update(student);
                    }
                }
            }

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

    public static int updateClassroom(Integer number, String classroom_name, boolean isChangeClassName, String old_classroom_name) {
        // null modify: number: -1
        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();
            long id = getIDByName(old_classroom_name);
            if (id < 0) {
                flag = -3;
            }
            else {
                Classroom classroom = getClassroomByID(id);
                if (classroom == null) {
                    flag = -4;
                }
                else {
                    if (isChangeClassName) {
                        classroom.setClass_name(classroom_name);
                    }
                    else {
                        classroom.setNumber(number);
                    }
                    session.update(classroom);
                }
            }
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

    public static void createClassroom() {
        String[] names = new String[]{"10A1", "10A2", "10A3", "10A4", "11A1", "11A2", "11A3", "12A1", "12A2"};
        for (String name: names) {
            Classroom classroom = new Classroom();
            classroom.setClass_name(name);
            classroom.setNumber(40);
            addClassroom(classroom);
        }
    }

    public static int deleteClassroom(String name) {
        long id = getIDByName(name);
        if (id < 0) return -3;
        Classroom classroom = getClassroomByID(id);
        if (classroom == null) return -4;

        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();
            session.delete(classroom);
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
}
