package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36;

import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.db.DbFactory;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.People;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Student;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Subject;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Teacher;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file.ReadExcelFile;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file.ReadStudentExcelFile;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file.ReadSubjectExcelFile;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.execute_file.ReadTeacherExcelFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ExerciseRun18_36 {
    private static final ExerciseRun18_36 instance = new ExerciseRun18_36();

    private ExerciseRun18_36() {

    }

    public static ExerciseRun18_36 getInstance() {
        return instance;
    }

    /**
     * Exercise 25: get student-list from excel file then write to text file
     * Viết chương trình cho phép lưu thông tin các đối tượng nhập ở bài trên ra file.
     * Input: Excel file
     * Output: student.txt
     */
    public void writeStudentFile() {
        String path = "D:/topica/src/main/resources/";
        ReadExcelFile readStudent = new ReadStudentExcelFile();
        List<Student> students = (List<Student>) readStudent.readFile("student.xlsx");
        File file = new File(path + "student.txt");
        try {
            FileUtils.writeStringToFile(file, StringUtils.join(students, "\n"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exercise 25: get teacher-list from excel file then write to text file
     * Viết chương trình cho phép lưu thông tin các đối tượng nhập ở bài trên ra file.
     * Input: Excel file
     * Output: teacher.txt
     */
    public void writeTeacherFile() {
        String path = "D:/topica/src/main/resources/";
        ReadExcelFile readTeacher = new ReadTeacherExcelFile();
        List<Teacher> teachers = (List<Teacher>) readTeacher.readFile("teacher.xlsx");
        File file = new File(path + "teacher.txt");
        try {
            FileUtils.writeStringToFile(file, StringUtils.join(teachers, "\n"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exercise 25: get subject-list from excel file then write to text file
     * Viết chương trình cho phép lưu thông tin các đối tượng nhập ở bài trên ra file.
     * Input: Excel file
     * Output: subject.txt
     */
    public void writeSubjectFile() {
        String path = "D:/topica/src/main/resources/";
        ReadExcelFile readSubject = new ReadSubjectExcelFile();
        List<Subject> subjects = (List<Subject>) readSubject.readFile("subject.xlsx");
        File file = new File(path + "subject.txt");
        try {
            FileUtils.writeStringToFile(file, StringUtils.join(subjects, "\n"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exercise 27: sort students and teachers by firstName then print it out
     * Viết chương trình in ra danh sách toàn bộ Teacher và Student sắp xếp theo thuộc tính firstName
     * theo thứ tự giảm dần không phân biệt chữ hoa chữ thường trong trường hợp tên chỉ bao gồm các chữ cái không dấu.
     * Input: student-list and teacher-list get from excel file
     */
    private List<? extends People> sortPeoplesByName(List<? extends People> peoples) {
        peoples.sort(new Comparator<People>() {
            public int compare(People o1, People o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        return peoples;
    }

    public void sortPeopleByFirstName() {
        ReadExcelFile ref = new ReadStudentExcelFile();
        List<Student> students = (List<Student>) ref.getStudents("student.xlsx");
        System.out.println(StringUtils.join(sortPeoplesByName(students), "\n"));
        ref = new ReadTeacherExcelFile();
        List<Teacher> teachers = (List<Teacher>) ref.getTeachers("teacher.xlsx");
        System.out.println(StringUtils.join(sortPeoplesByName(teachers), "\n"));
    }

    /**
     * Exercise 28: sort students and teachers by firstName then print it out
     * Viết chương trình in ra danh sách toàn bộ Student sắp xếp theo thuộc tính firstName theo thứ tự giảm
     * dần không phân biệt chữ hoa chữ thường trong trường hợp tên chỉ bao gồm các chữ cái có dấu như Â, Ộ, ệ.
     * Input: student-list and teacher-list get from excel file
     */
    public void sortByFirstName() {
        ReadExcelFile ref = new ReadStudentExcelFile();
        List<Student> students = (List<Student>) ref.getStudents("student.xlsx");
        List<Student> newStudents = new ArrayList<>();
        for (Student st : students) {
            if (st.getFirstName().contains("â") || st.getFirstName().contains("ộ") || st.getFirstName().contains("ệ")) {
                newStudents.add(st);
            }
        }
        System.out.println(StringUtils.join(sortPeoplesByName(newStudents), "\n"));
        ref = new ReadTeacherExcelFile();
        List<Teacher> teachers = (List<Teacher>) ref.getTeachers("teacher.xlsx");
        List<Teacher> newTeachers = new ArrayList<>();
        for (Teacher tc : teachers) {
            if (tc.getFirstName().contains("â") || tc.getFirstName().contains("ộ") || tc.getFirstName().contains("ệ")) {
                newTeachers.add(tc);
            }
        }
        System.out.println(StringUtils.join(sortPeoplesByName(newTeachers), "\n"));
    }

    /**
     * Exercise 29: sort student-list by both last-name and first-name
     * Viết chương trình in ra danh sách toàn bộ Student sắp xếp theo tên đầy đủ bao gồm lastName và firstName
     */
    private List<Student> sortStudentByName(List<Student> students) {
        students.sort(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        students.sort(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return students;
    }

    public List<Student> sortStudentByName() {
        ReadExcelFile ref = new ReadStudentExcelFile();
        List<Student> students = (List<Student>) ref.getStudents("student.xlsx");
        System.out.println(StringUtils.join(sortPeoplesByName(students), "\n"));
        return students;
    }

    /**
     * Exercise 32: get all data from DB
     * Viết chương trình sau khi kết nối được database thì hiển thị toàn bộ danh sách các bảng mà database
     * đó có. Nếu không hiển thị được do lỗi thì cần có thông báo lỗi tại sao?
     */
    public void getAllDBdata() {
        DbFactory factory = DbFactory.getInstance();
        List<Student> students = (List<Student>) factory.listObj(Student.class.getName());
        System.out.println(StringUtils.join(students, "\n"));
    }

    /**
     * Exercise 33: Viết chương trình cho phép lưu thông tin các đối tượng nhập ở bài trên vào database
     * Input: file excel
     * Add all data from excel file to DB
     */
    public void addData() {
        DbFactory factory = DbFactory.getInstance();
        // add students
        ReadExcelFile ref = new ReadStudentExcelFile();
        List<Student> stData = (List<Student>) ref.getStudents("student.xlsx");
        factory.addListObj(stData);
        System.out.println("add students successfully");
        // add teachers
        ref = new ReadTeacherExcelFile();
        List<Teacher> tcData = (List<Teacher>) ref.getTeachers("teacher.xlsx");
        factory.addListObj(tcData);
        System.out.println("add teachers successfully");
        // add subjects
        ref = new ReadSubjectExcelFile();
        List<Subject> sjData = (List<Subject>) ref.getSubjects("subject.xlsx");
        factory.addListObj(sjData);
        System.out.println("add subjects successfully");
    }

    /**
     * Exercise 34: Input SQL then run that SQL to get or update data
     * Viết chương trình cho phép nhập câu lệnh SQL và hiển thị kết theo câu lệnh SQL đưa vào.
     * Yêu cầu chỉ với các câu lệnh liên quan đến lấy và cập nhật dữ liệu.
     *
     * SQL: change a student info
     */
    public void inputSQL() {
        String sql = "update student set first_name='Hanna' where id=2;";
        DbFactory factory = DbFactory.getInstance();
        factory.updateObjBySql(sql);
        System.out.println("update student successfully");
    }

    /**
     * Exercise 36: set sex to Student in DB
     * Viết chương trình cập nhật lại dữ liệu cho Student
     * với các thuộc tính được thêm trong People và lưu lại trong database
     * update student add new column sex
     */
    public void updateStudent() {
        DbFactory factory = DbFactory.getInstance();
        factory.updateStudent(3, People.sex.female);
        System.out.println("update student successfully");
    }
}
