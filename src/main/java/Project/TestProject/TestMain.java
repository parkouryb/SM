package Project.TestProject;

import Project.Controller.ClassroomController;
import Project.Controller.StudentController;
import Project.Controller.StudyController;
import Project.Controller.SubjectController;
import Project.Object.Student;
import Project.Object.Study;
import Project.Object.Subject;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class TestMain {
    @Test
    public void createDb() {
        ClassroomController.createClassroom();

        StudentController.createStudents();

        SubjectController.createSubjects();
    }

    @Test
    public void addStudentToClass() {
        System.out.println(ClassroomController.addStudentToClass(10000, "10A3"));
        System.out.println(ClassroomController.addStudentToClass(10001, "10A3"));
    }

    @Test
    public void updateClassroom() {
        System.out.println(ClassroomController
                .updateClassroom(20, null, false, "10A3"));
        System.out.println(ClassroomController
                .updateClassroom(60, null, false, "10A2"));
        System.out.println(ClassroomController
                .updateClassroom(-1, "10A2", true, "10B2"));
    }

    @Test
    public void deleteClassroom() {
        System.out.println(ClassroomController.deleteClassroom("12A1"));
    }

    @Test
    public void listAllStudents() {
        List<Student> students = (List<Student>) ClassroomController.getStudents("10A3");
        for (Student student: students) {
            System.out.println(student);
        }
    }

    @Test
    public void listAllStudentsStudentController() {
        List<Student> students = StudentController.getStudents();
        for (Student student: students) {
            System.out.println(student);
        }
    }

    @Test
    public void studentLearn() {

        Subject subject = SubjectController.getSubjectByName("Van", 2);
        Student student = StudentController.getStudentByID(10000);

        Study study = new Study(new Study.StudyPK(student, subject));
        study.setScore_15(9.5);
        study.setScore_45(9.0);
        study.setScore_mean(10.);

        int fl = StudyController.addStudy(study);
        System.out.println(fl);
    }

    @Test
    public void deleteSubject() {
        Subject subject = SubjectController.getSubjectByName("Toan", 1);
        System.out.println(SubjectController.deleteSubject(subject));

        if (subject == null) {
            subject = new Subject();
            subject.setSubject_name("Toan");
            subject.setSemester(1);
        }
        SubjectController.addSubject(subject);
    }

    @Test
    public void getScores() {
        Student student = StudentController.getStudentByID(10000);
        System.out.println(StudentController.getScoreSemester(student, 1));
    }

    @Test
    public void bieuMau4() {
        Student student = StudentController.getStudentByID(10000);
        Subject subject = SubjectController.getSubjectByName("Van", 2);

        Study study = StudyController.getStudyByName(student, subject);
        System.out.println(study);
    }
}
