package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.Student;
import Project.Object.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
}
