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
        Subject subject = new Subject();
        subject.setSubject_name("Van");
        subject.setSemester(1);

        Study study = new Study();
        study.setScore_15(10.);
        study.setScore_45(10.);
        study.setScore_mean(7.5);
        study.getStudyPK().setSubject(subject);

        int fl = StudyController.addStudy(10017, study);
        System.out.println(fl);
        if (fl == 0) {
            int fll = StudentController.updateScore(10017, study);
            System.out.println(fll);
        }
        else {
            System.out.println("Fail");
        }
    }
}
