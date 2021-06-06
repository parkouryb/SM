package Project.TestProject;

import Project.Controller.ClassroomController;
import Project.Controller.StudentController;
import Project.Hibernate.HibernateUtil;
import Project.Object.Classroom;
import Project.Object.Student;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class TestMain {
    @Test
    public void test1() {
        Classroom classroom = new Classroom(80001);

        Student student1 = new Student();
        student1.setStudent_name("Alice");
        student1.setBirthday("02/05/2005");
        student1.setGender("Nam");
        student1.setEmail("adc@hus");
        student1.setAndress("asdasdasdaso");
        student1.setClassroom(classroom);

        long id = StudentController.addStudent(student1);
        System.out.println(id);

//        Student student2 = StudentController.getStudentByID(10000);
//        System.out.println(student2);

    }

    @Test
    public void test2() {
        Collection<Student> students = ClassroomController.getStudents(80001);
        for (Student student: students) {
            System.out.println(student);
        }

        long id = ClassroomController.getIDByName("11A1");
        System.out.println(id);
    }

    @Test
    public void test3() {
    }
}
