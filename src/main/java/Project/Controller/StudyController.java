package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.Set;

public class StudyController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static int addStudy(Study study) {
        Student student = study.getStudyPK().getStudent();
        Subject subject = study.getStudyPK().getSubject();

        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();

            student.getStudies().add(study);
            subject.getStudies().add(study);
            session.save(study);

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

    public static Set<Study> getScores(Student student) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Set<Study> studies = null;
        try {
            transaction = session.beginTransaction();
            studies = student.getStudies();
            transaction.commit();
        } catch(HibernateException hibernataeExeption) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return studies;
    }

    public static Set<Study> getScoresBySemester(Student student, int semester) {
        Set<Study> studies = StudyController.getScores(student);
        if (studies == null) return null;
        Set<Study> result = new HashSet<>();

        for (Study study: studies) {
            if (study.getStudyPK().getSubject().getSemester() == semester) {
                result.add(study);
            }
        }

        return result;
    }

    public static Study getStudyByName(Student student, Subject subject) {
        Study study = null;
        if (student == null || subject == null) return null;

        Set<Study> studies = getScoresBySemester(student, subject.getSemester());
        if (studies == null) return null;

        for (Study st: studies) {
            if (st.getStudyPK().getSubject().getSubject_name().equals(subject.getSubject_name())
                    && st.getStudyPK().getSubject().getSemester() == subject.getSemester()) {
                study = st;
                break;
            }
        }

        return study;
    }
}
