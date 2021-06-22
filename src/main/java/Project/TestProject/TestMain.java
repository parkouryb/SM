package Project.TestProject;

import Project.Controller.*;
import Project.Object.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestMain {

    @Test
    public void testDeleteStudent() {
        Student student = StudentController.getStudentByID(10007);
        System.out.println(StudentController.deleteStudent(student));;
    }

    @Test
    public void updateStudent() {
        Student student = StudentController.getStudentByID(10001);
        student.setAndress("hihi");
        System.out.println(StudentController.updateStudent(student));
    }

    @Test
    public void updateScore() {
        Student student = StudentController.getStudentByID(10001);
        Subject subject = SubjectController.getSubjectByName("Toan", 1);
        Study study = StudyController.getStudyByName(student, subject);
        if (study == null) System.out.println("null");
        else {
            study.setScore_15(10.);
            study.setScore_45(8.);
            System.out.println(StudyController.updateScore(study));
        }
    }

    @Test
    public void deleteScore() {
        Student student = StudentController.getStudentByID(10001);
        Subject subject = SubjectController.getSubjectByName("Toan", 1);
        Study study = StudyController.getStudyByName(student, subject);
        if (study == null) System.out.println("null");
        else {
            System.out.println(StudyController.deleteScore(study));
        }
    }

    @Test
    public void createAccount() {
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("admin");
        account.setRole(Role.STUDENT);
        AccountController.createAccount(account);

        // dang nhap
        Account x = AccountController.login(account);
        if (x != null) {
            System.out.println(x.getRole()); // lấy role như này
        }
        // ông sau khi đăng nhập xong sẽ trả về 1 đối tượng account
        // ông sẽ lưu nó trên fe cái role của nó để quyết nhé

        // giả sử tạo tài khoản xong sẽ đến 1 bảng là nhập thông tin học sinh xong, ông sẽ có 2 đối tượng
        // là Account và Student(phải được lấy từ db ra)
        // ví dụ x là account tôi đăng nhập được rồi. h tôi thêm thông tin học sinh vào tài khoản đấy
        Student student = StudentController.getStudentByID(10000);

        if (student != null) {
            int fl = AccountController.addInfo(x, student);
            System.out.println(fl);
        }
    }

    @Test
    //Done
    public void createDb() {
        ClassroomController.createClassroom();

        StudentController.createStudents();

        SubjectController.createSubjects();

        System.out.println(ClassroomController.addStudentToClass(10000, "10A3"));
        System.out.println(ClassroomController.addStudentToClass(10001, "10A3"));
        System.out.println(ClassroomController.addStudentToClass(10002, "10A3"));
        System.out.println(ClassroomController.addStudentToClass(10003, "10A3"));
        System.out.println(ClassroomController.addStudentToClass(10004, "10A3"));

        SubjectController.addSubjectStudent();

    }

    @Test
    //Done
    public void addStudentToClass() {
        System.out.println(ClassroomController.addStudentToClass(10000, "10A3"));
        System.out.println(ClassroomController.addStudentToClass(10001, "10A3"));
    }

    @Test
    //Done
    public void updateClassroom() {
        System.out.println(ClassroomController
                .updateClassroom(20, null, false, "10A3"));
        System.out.println(ClassroomController
                .updateClassroom(60, null, false, "10A2"));
        System.out.println(ClassroomController
                .updateClassroom(-1, "10A2", true, "10B2"));
    }

    @Test
    //Done
    public void deleteClassroom() {
        System.out.println(ClassroomController.deleteClassroom("10A3"));
    }

    @Test
    //Done
    public void listAllStudents() {
        Set<Student> students = ClassroomController.getStudents("10A3");
        for (Student student: students) {
            System.out.println(student);
        }
    }

    @Test
    //Done
    public void listAllStudentsStudentController() {
        Set<Student> students = StudentController.getStudents();
//        for (Student student: students) {
//            System.out.println(student);
//        }
    }

    @Test
    //Done
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
    //Done
    public void getScores() {
        Student student = StudentController.getStudentByID(10000);
        System.out.println(StudentController.getScoreSemester(student, 1));
        System.out.println(StudentController.getScoreSemester(student, 2));
    }

    @Test
    //Done
    public void bieuMau4() {
        Student student = StudentController.getStudentByID(10000);
        Subject subject = SubjectController.getSubjectByName("Van", 2);

        Study study = StudyController.getStudyByName(student, subject);
        System.out.println(study);
    }

    @Test
    //Done
    public void bieumau52() {
        Set<Classroom> classrooms = ClassroomController.getClassrooms();
        for (Classroom classroom: classrooms) {
            Bieumau5 bieumau5 = ClassroomController.getInfo52(classroom, 1);
            System.out.println(bieumau5);
        }
    }

    @Test
    //Done
    public void bieumau51() {
        Set<Classroom> classrooms = ClassroomController.getClassrooms();
        Subject subject = SubjectController.getSubjectByName("Toan", 1);
        for (Classroom classroom: classrooms) {
            Bieumau5 bieumau5 = ClassroomController.getInfo51(classroom, subject);
            if(bieumau5.getNumber()!=0){
                System.out.println(bieumau5.getPass()/bieumau5.getNumber());
            }

        }
    }
    @Test
    public void testlist(){
        Set<Classroom> classrooms = ClassroomController.getClassrooms();
        List<String> listA=new ArrayList<>();
        for (Classroom classroom: classrooms) {

            listA.add(classroom.getClass_name());

        }
        System.out.println(listA);
    }

}
