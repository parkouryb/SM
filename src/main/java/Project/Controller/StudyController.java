package Project.Controller;

import Project.Hibernate.HibernateUtil;
import Project.Object.Student;
import Project.Object.Study;
import Project.Object.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudyController {
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static int addStudy(long student_ID, Study study) {
        Student student = StudentController.getStudentByID(student_ID);
        Subject temp = study.getStudyPK().getSubject();
        Subject subject = SubjectController.getSubjectByName(temp.getSubject_name(), temp.getSemester());

        System.out.println(student);
        System.out.println(subject);

        if (student == null || subject == null)
            return -2;

        Session session = factory.openSession();
        Transaction transaction = null;
        int flag = 0;
        try {
            transaction = session.beginTransaction();
            study.getStudyPK().setStudent(student);
            study.getStudyPK().setSubject(subject);
            session.save(study);

            if (subject.getSemester() == 1)
                student.setNum_I(student.getNum_I() + 1);
            else
                student.setNum_II(student.getNum_II() + 1);

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


}
