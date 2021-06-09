package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.Classroom;
import Project.Object.Student;
import Project.Object.Study;
import Project.Object.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SubjectController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void addSubject(Subject subject) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void createSubjects() {
        String[] names = new String[]{"Toan", "Ly", "Hoa", "Sinh", "Su", "Dia", "Van", "Dao Duc", "The Duc"};
        for (String name: names) {
            for (int i = 1;i <= 2;++ i) {
                Subject subject = new Subject();
                subject.setSubject_name(name);
                subject.setSemester(i);
                addSubject(subject);
            }
        }

    }

    public static Subject getSubjectByName(String subject_name, int semester) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Subject subject = null;
        try {
            transaction = session.beginTransaction();

            List<Subject> lists = session.createCriteria(Subject.class).list();
            for (Subject sb: lists) {
                if (sb.getSubject_name().equals(subject_name) && sb.getSemester() == semester) {
                    subject = sb;
                    break;
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
        return subject;
    }

    public static int deleteSubject(Subject subject) {
        if (subject == null)
            return -2;
        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();
            session.delete(subject);
            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = -1;
        } finally {
            session.close();
        }
        return flag;
    }

    public static Set<Study> getStudents(Subject subject, Classroom classroom) {
        Set<Study> studies = null;
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            studies = subject.getStudies();

            transaction.commit();
        } catch(HibernateException hibernateExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return studies;
    }

    public static void addSubjectStudent() {
        double leftLimit = 1D;
        double rightLimit = 10D;
        String[] names = new String[]{"Toan"};
        for (int id = 10000;id <= 10004;++ id) {
            Student student = StudentController.getStudentByID(id);
            for (String name: names) {
                for (int i = 1;i <= 2;++ i) {
                    Subject subject = SubjectController.getSubjectByName(name, i);

                    Study study = new Study(new Study.StudyPK(student, subject));
                    double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
                    study.setScore_15(generatedDouble);
                    generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
                    study.setScore_45(generatedDouble);
                    generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
                    study.setScore_mean(generatedDouble);

                    StudyController.addStudy(study);
                }
            }
        }
    }

    public static Set<Subject> getSubjects() {
        Session session = factory.openSession();
        Transaction transaction = null;
        Set<Subject> subjects = null;
        try {
            transaction = session.beginTransaction();
            List<Subject> list = session.createCriteria(Subject.class).list();
            subjects = new HashSet<>(list);
            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return subjects;
    }
}
