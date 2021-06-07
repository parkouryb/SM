package Project.TestProject;

import Project.Controller.ClassroomController;
import Project.Controller.StudentController;
import Project.Controller.SubjectController;
import Project.Object.Student;
import org.junit.Test;

import java.util.List;

public class TestMain {
    @Test
    public void createDb() {
        ClassroomController.createClassroom();
    }

    @Test
    public void addStudent() {
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

    @Test
    public void addStudentToClass() {
        System.out.println(ClassroomController.addStudentToClass(10003, "10A3"));
        System.out.println(ClassroomController.addStudentToClass(10004, "10A3"));
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
    public void createDb2() {
        SubjectController.createSubjects();
    }
}
